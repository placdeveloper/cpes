package br.com.sicoob.capes.cadastro.negocio.servicos.interceptors;

import static br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum.BLOQUEADO;
import static br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum.EM_AUTORIZACAO;

import java.io.Serializable;
import java.lang.reflect.Method;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.CompartilhamentoCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum;
import br.com.sicoob.capes.cadastro.negocio.excecao.InstituicaoNaoResponsavelPeloCadastroException;
import br.com.sicoob.capes.cadastro.negocio.excecao.RegistroNaoEncontradoNegocioException;
import br.com.sicoob.capes.cadastro.negocio.excecao.RegistroPendenteAprovacaoException;
import br.com.sicoob.capes.cadastro.negocio.servicos.CAPESCadastroCrudServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.EntidadeCadastroServico;
import br.com.sicoob.capes.cadastro.util.SerializacaoUtils;
import br.com.sicoob.capes.comum.negocio.annotation.Autorizar;
import br.com.sicoob.capes.comum.negocio.annotation.IdEntidadeAprovavel;
import br.com.sicoob.capes.comum.negocio.annotation.IgnorarAutorizar;
import br.com.sicoob.capes.comum.negocio.annotation.IntegracaoGedGft;
import br.com.sicoob.capes.comum.negocio.annotation.NaoVerificarGestorResponsavel;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * Interceptor responsavel por verificar se o usuário logado e dono do
 * cadastro da entidade manipulada pelo Servico EJB, caso nao seja, a
 * alteracao na entidade ficara pendente de aprovacao.
 * 
 * @see Autorizar
 * @see IdEntidadeAprovavel
 * @see Aprovavel
 * @see IntegracaoGedGft
 * 
 * @author Juan.Damasceno
 * 
 */
public class AutorizacaoCadastroInterceptor {

	/** O atributo logger. */
	private ISicoobLogger logger = SicoobLoggerPadrao
			.getInstance(AutorizacaoCadastroInterceptor.class);
	
	/** O atributo delegate. */
	private AutorizacaoCadastroDelegate delegate;
	
	/**
	 * Intercepta a chamada ao Serviço EJB.
	 * 
	 * <p>
	 * A sintaxe utilizada para a chamada do método
	 * {@link #obterEntidadeAprovavel(InvocationContext, Method, Autorizar)} é
	 * em decorrência do bug <a
	 * href="http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6302954"
	 * >6302954</a> da JDK, que impedia o Maven de compilar o projeto
	 * corretamente.
	 * </p>
	 * 
	 * @param contexto
	 *            o contexto de execução do EJB.
	 * @return o retorno do Serviço EJB.
	 */
	@AroundInvoke
	public <A extends CAPESEntidade<Long> & Aprovavel> Object intercept(
			InvocationContext context) throws Exception {

		Object resultado = null;
		Method method = getBusinessMethod(context);
		Autorizar annotation =
				ReflexaoUtils.getAnnotationMetodoSobrescrito(Autorizar.class, method,
						context.getParameters());
		if ((annotation != null)
				&& !method.isAnnotationPresent(IgnorarAutorizar.class)) {
			resultado = this.<A>tratarMetodoAprovavel(context, method, annotation);
		} else {
			logger.alerta("Operacao sem necessidade de autorizacao: metodo nao marcado");
			resultado = context.proceed();
		}
		return resultado;
	}
	
	/**
	 * Tratar metodo aprovavel.
	 *
	 * @param <A> o tipo generico
	 * @param context o valor de context
	 * @param metodo o valor de metodo
	 * @param annotation o valor de annotation
	 * @return Object
	 * @throws Exception lança a exceção Exception
	 */
	private <A extends CAPESEntidade<Long> & Aprovavel> Object
			tratarMetodoAprovavel(InvocationContext context, Method metodo, Autorizar annotation)
					throws Exception {

		Object resultado = null;
		logger.debug("Metodo marcado para autorizacao: ", metodo.getName());
		A aprovavel = this.<A> obterEntidadeAprovavel(context, metodo);
		if (deveVerificarAutorizacao(aprovavel)) {
			resultado = verificarAutorizacao(context, metodo, annotation, aprovavel);
		} else {
			logger.alerta("Operacao sem necessidade de autorizacao: entidade=",
					String.valueOf(aprovavel), " | verificar autorizacao=",
					String.valueOf(aprovavel.getVerificarAutorizacao()));

			resultado = context.proceed();
		}
		return resultado;
	}

	/**
	 * Verificar autorizacao.
	 *
	 * @param <A> o tipo generico
	 * @param context o valor de context
	 * @param metodo o valor de metodo
	 * @param annotation o valor de annotation
	 * @param aprovavel o valor de aprovavel
	 * @return Object
	 * @throws Exception lança a exceção Exception
	 */
	private <A extends CAPESEntidade<Long> & Aprovavel> Object
			verificarAutorizacao(InvocationContext context, Method metodo, Autorizar annotation,
					A aprovavel) throws Exception {

		Object resultado = null;

		if (!isRegistroEmAprovacao(aprovavel)) {
			
			final Boolean naoVerificarGestorResponsavel = aprovavel.getClass()
					.isAnnotationPresent(NaoVerificarGestorResponsavel.class); 
			
			if (isIntegracaoHabilitada(context)) {
				resultado = tratarIntegracaoHabilitada(context, metodo, annotation, aprovavel);
			} else if (naoVerificarGestorResponsavel || isResponsavel(aprovavel) || isGestor() ) {
				logger.alerta("Operacao sem necessidade de autorizacao");
				resultado = context.proceed();
			} else {
				throw new InstituicaoNaoResponsavelPeloCadastroException();
			}
		} else {
			bloquearOperacaoRegistroPendenteAprovacao(aprovavel);
		}
		return resultado;
	}
	
	/**
	 * Verifica se eh responsavel.
	 *
	 * @param aprovavel o valor de aprovavel
	 * @return {@code true}, se for responsavel
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private boolean isResponsavel(Aprovavel aprovavel) throws BancoobException {	
		UsuarioBancoob usuario = obterUsuario();
		Integer idInstituicaoUsuario = Integer.valueOf(usuario.getIdInstituicao());
		return getDelegate().isInstituicaoResponsavelCadastro(aprovavel, idInstituicaoUsuario);
	}

	/**
	 * Verifica se eh gestor.
	 *
	 * @return {@code true}, se for gestor
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private boolean isGestor() throws BancoobException {
		UsuarioBancoob usuario = obterUsuario();
		return getDelegate().isGestor(usuario);
	}

	/**
	 * O método Bloquear operacao registro pendente aprovacao.
	 *
	 * @param <A> o tipo generico
	 * @param aprovavel o valor de aprovavel
	 * @throws RegistroPendenteAprovacaoException lança a exceção RegistroPendenteAprovacaoException
	 */
	private <A extends CAPESEntidade<Long> & Aprovavel> void
			bloquearOperacaoRegistroPendenteAprovacao(A aprovavel)
					throws RegistroPendenteAprovacaoException {
		logger.alerta("Registro em processo de aprovacao: ", aprovavel.getClass()
				.getCanonicalName(), "#", String.valueOf(((Aprovavel) aprovavel).getId()), "(",
				String.valueOf(aprovavel.getIdInstituicaoAtualizacao()), ")");
		throw new RegistroPendenteAprovacaoException();
	}

	/**
	 * Tratar integracao habilitada.
	 *
	 * @param <A> o tipo generico
	 * @param context o valor de context
	 * @param metodo o valor de metodo
	 * @param annotation o valor de annotation
	 * @param aprovavel o valor de aprovavel
	 * @return Object
	 * @throws Exception lança a exceção Exception
	 */
	private <A extends CAPESEntidade<Long> & Aprovavel> Object
			tratarIntegracaoHabilitada(InvocationContext context, Method metodo,
					Autorizar annotation, A aprovavel) throws Exception {

		Object resultado = null;
		if (!TipoOperacaoEnum.I.equals(annotation.atualizacao()) &&
				isRegistroCorrigivel(aprovavel)) {
			tratarCorrecao(context, annotation, aprovavel);
		} else {
			invocarMetodoValidacao(context, annotation);
			getDelegate().gravarSolicitacaoAprovacao(aprovavel, annotation.atualizacao());
		}
		resultado = obterRetorno(aprovavel, metodo);
		return resultado;
	}

	/**
	 * O método Tratar correcao.
	 *
	 * @param <A> o tipo generico
	 * @param context o valor de context
	 * @param annotation o valor de annotation
	 * @param aprovavel o valor de aprovavel
	 * @throws Exception lança a exceção Exception
	 */
	private <A extends CAPESEntidade<Long> & Aprovavel> void tratarCorrecao(
			InvocationContext context, Autorizar annotation, A aprovavel) throws Exception {

		AutorizacaoDelegate delegate =
				CAPESCadastroFabricaDelegates.getInstance().criarAutorizacaoDelegate();
		Autorizacao autorizacao = delegate.obterPorEntidade(aprovavel);

		// ainda não foi enviada
		if ((autorizacao != null) && (autorizacao.getDataHoraSolicitacao() == null)) {
			invocarMetodoValidacao(context, annotation);
			getDelegate().corrigirAutorizacao(autorizacao, aprovavel,
					annotation.atualizacao());
		} else {
			Boolean permitidoCorrigir = getDelegate().isPermitidoCorrigir(autorizacao);
			if (TipoOperacaoEnum.A.equals(annotation.atualizacao()) && (permitidoCorrigir != null)
					&& permitidoCorrigir) {
				invocarMetodoValidacao(context, annotation);
				getDelegate().corrigirAutorizacao(autorizacao, aprovavel, annotation.atualizacao());
			} else {
				bloquearOperacaoRegistroPendenteAprovacao(aprovavel);
			}
		}
	}

	/**
	 * se o objeto eh persistente (possui ID) e esta em processo de aprovacao
	 * 
	 * @param aprovavel
	 *            o registro a ser verificado
	 * @return {@code true} se esta em aprovacao
	 * @throws BancoobException 
	 */
	private <A extends Aprovavel> boolean isRegistroEmAprovacao(A aprovavel) throws BancoobException {
		SituacaoRegistroEnum situacaoAprovacao = null;
		if (aprovavel.getId() != null) {
			A persistente = obterObjetoPersistente(aprovavel);
			if (persistente != null) {
				aprovavel = persistente;
			} else if (!aprovavel.getTipoAutorizacao().isUtilizaJSON()) {
				throw new RegistroNaoEncontradoNegocioException(aprovavel.getTipoAutorizacao().getDescricao());
			}
		}
		situacaoAprovacao = aprovavel.getSituacaoAprovacao();
		return BLOQUEADO.equals(situacaoAprovacao) || EM_AUTORIZACAO.equals(situacaoAprovacao);
	}

	/**
	 * Obter objeto persistente.
	 *
	 * @param <A> o tipo generico
	 * @param aprovavel o valor de aprovavel
	 * @return A
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@SuppressWarnings("unchecked")
	private <A extends Aprovavel> A obterObjetoPersistente(A aprovavel) throws BancoobException {
		
		A persistente = null;
		CAPESCadastroFabricaDelegates fabrica = CAPESCadastroFabricaDelegates.getInstance();
		CAPESCadastroCrudDelegate<CAPESEntidade<?>, ?> delegate = fabrica.criarDelegate(aprovavel.getClass());
		if ((aprovavel.getDataHoraInicio() == null)
		        && aprovavel.getTipoAutorizacao().isUtilizaJSON()) {
			AutorizacaoDelegate autorizacaoDelegate = fabrica.criarAutorizacaoDelegate();
			Autorizacao autorizacao = autorizacaoDelegate.obterPorEntidade(aprovavel);
			if (autorizacao != null) {
				TipoAutorizacaoEntidadeEnum tipoAutorizacao = TipoAutorizacaoEntidadeEnum
				        .valueOf(autorizacao.getTipoAutorizacao());
				persistente = (A) SerializacaoUtils.deserializarJson(tipoAutorizacao.getTipo(),
						autorizacao.getAlteracao());
				persistente.setSituacaoAprovacao(null);
				persistente.setCodigoSituacaoAprovacao(autorizacao.obterSituacao().getCodigo());
			}
		} else {
			persistente  = (A) delegate.obter(aprovavel.getId());
		}
	    return persistente;
    }

	/**
	 * Verifica se o registro nao eh "corrigivel", ou seja, se ele eh um
	 * registro originado de uma atualizacao cadastral, que ainda esta em
	 * processo de autorizacao e que não possui atividades para o usuario no GFT
	 * 
	 * @param aprovavel
	 *            o registro
	 * @return {@code true} caso o registro nao seja corrigivel
	 */
	private <A extends Aprovavel> boolean isRegistroCorrigivel(A aprovavel) throws BancoobException {
		boolean isRegistroCandidato = (aprovavel.getDataHoraInicio() == null)
						&& (aprovavel.getIdInstituicaoAtualizacao() != null);
		
		return isRegistroCandidato || isDevolvido(aprovavel);
	}

	/**
	 * Verifica se eh devolvido.
	 *
	 * @param <A> o tipo generico
	 * @param aprovavel o valor de aprovavel
	 * @return {@code true}, se for devolvido
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private <A extends Aprovavel> boolean isDevolvido(A aprovavel) throws BancoobException {
		AutorizacaoDelegate delegate =
				CAPESCadastroFabricaDelegates.getInstance().criarAutorizacaoDelegate();
		Autorizacao autorizacao = delegate.obterPorEntidade(aprovavel);
		return (autorizacao != null) && (autorizacao.getDevolvido() != null)
				&& autorizacao.getDevolvido();
	}

	/**
	 * Verifica se o serviçoo (target) está marcado para se integrar com o
	 * GED/GFT
	 * 
	 * @param classeServico
	 *            a classe do serviço de destino da chamada (
	 *            {@link InvocationContext#getTarget()})
	 * @return {@code true} caso a classe esteja marcada
	 * @see IntegracaoGedGft
	 */
	private boolean isServicoIntegradoGedGft(Class<? extends Object> classeServico) {

		boolean annotationPresent = classeServico.isAnnotationPresent(IntegracaoGedGft.class);

		logger.debug("Servico \"", classeServico.getSimpleName(), "\" integrado ao GED/GFT: ",
				String.valueOf(annotationPresent));

		return annotationPresent;
	}

	/**
	 * Verifica o {@link CompartilhamentoCadastro#getUtilizaGedGft()} para
	 * determinar se no compartilhamento do usuário logado utiliza-se GED/GFT
	 * 
	 * @return {@code true} caso o {@link CompartilhamentoCadastro} utilize
	 *         GED/GFT
	 */
	private boolean isCompartilhamentoUtilizandoGedGft() throws BancoobException {

		CompartilhamentoCadastroDelegate delegate =
				CAPESCadastroFabricaDelegates.getInstance()
						.criarCompartilhamentoCadastroDelegate();

		InformacoesUsuarioCAPES usuario = InformacoesUsuarioCAPES.getInstance();
		if (usuario == null) {
			throw new UnsupportedOperationException(
					"O InformacoesUsuarioCAPES não foi instanciado!");
		}
		logger.debug(getClass().getSimpleName() + ": usuario logado - ", usuario.toString());
		
		Short codigo = usuario.getCodigoCompartilhamento();
		CompartilhamentoCadastro compartilhamento = delegate.obter(codigo);

		logger.debug("Compartilhamento \"", compartilhamento.getDescricao(),
				"\" utilizando GED/GFT: ", String.valueOf(compartilhamento.getUtilizaGedGft()));

		return compartilhamento.getUtilizaGedGft() != null && compartilhamento.getUtilizaGedGft();
	}

	/**
	 * Obter retorno.
	 *
	 * @param aprovavel o valor de aprovavel
	 * @param businessMethod o valor de business method
	 * @return Object
	 */
	private Object obterRetorno(Aprovavel aprovavel, Method businessMethod) {

		Object objetoRetorno = null;
		Class<?> tipoRetorno = businessMethod.getReturnType();
		Class<?> clazzEntidadeAprovavel = aprovavel.getClass();
		if (tipoRetorno.isAssignableFrom(clazzEntidadeAprovavel)) {
			objetoRetorno = aprovavel;
		}
		return objetoRetorno;
	}

	/**
	 * O método Invocar metodo validacao.
	 *
	 * @param context o valor de context
	 * @param autorizar o valor de autorizar
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void invocarMetodoValidacao(InvocationContext context, Autorizar autorizar)
			throws BancoobException {

		String nomeMetodo = autorizar.metodoValidacao();
		if (StringUtils.isNotBlank(nomeMetodo)) {
			Object target = context.getTarget();
			Object[] parametros = context.getParameters();
			Method metodo =
					ReflexaoUtils.obterMetodoPorParametros(nomeMetodo, parametros,
							target.getClass());
			ReflexaoUtils.invocarMetodo(target, metodo, parametros);
		}
	}

	/**
	 * Obtém o valor do identificador da entidade que será aprovada.
	 * 
	 * @param contexto
	 *            o contexto de execução do EJB.
	 * @param businessMethod
	 *            o método que será interceptado.
	 * @return o valor do identificador da entidade que será aprovada.
	 */
	private Serializable
			obterIDEntidadeAprovavel(InvocationContext contexto, Method businessMethod) {

		Integer indexParameter =
				ReflexaoUtils.obterIndexParametroMetodoSobrescrito(businessMethod,
						contexto.getParameters(), IdEntidadeAprovavel.class);
		if (indexParameter != null) {
			return (Serializable) contexto.getParameters()[indexParameter];
		}
		return null;
	}

	/**
	 * Obtem o metodo que esta sendo executado pelo EJB.
	 * 
	 * @param context
	 *            o contexto de invocacao do EJB.
	 * @return o metodo que esta sendo executado pelo EJB.
	 */
	private Method getBusinessMethod(InvocationContext context) {

		String nomeMetodo = context.getMethod().getName();
		Object[] parametros = context.getParameters();
		Class<? extends Object> classeServico = context.getTarget().getClass();

		return ReflexaoUtils.obterMetodoPorParametros(nomeMetodo, parametros, classeServico);
	}

	/**
	 * Obtem a entidade aprovavel no metodo do EJB que sendo executado.
	 * 
	 * <p>
	 * A sintaxe utilizada para a chamada dos metodos
	 * {@link #obterEntidadePorParametroAnotado(InvocationContext, Method)} e
	 * {@link #obterEntidadePorTipoParametro(InvocationContext, Aprovavel, Autorizar)}
	 * em decorrencia do bug <a
	 * href="http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6302954"
	 * >6302954</a> da JDK, que impedia o Maven de compilar o projeto
	 * corretamente.
	 * </p>
	 * 
	 * @param contexto
	 *            o contexto de execucao do EJB.
	 * @param businessMethod
	 *            o metodo sendo executado.
	 * @return a entidade aprovável no método do EJB que sendo executado.
	 * @throws BancoobException
	 *             caso alguma exceção ocorra.
	 */
	private <A extends CAPESEntidade<Long> & Aprovavel> A
			obterEntidadeAprovavel(InvocationContext contexto, Method businessMethod) throws BancoobException {
		A aprovavel = this.<A> obterEntidadePorParametroAnotado(contexto, businessMethod);
		if (aprovavel == null) {
			aprovavel = this.<A> obterEntidadePorTipoParametro(contexto);
		}
		return aprovavel;
	}

	/**
	 * Busca a entidade aprovavel verificando o tipo dos parametro do método,
	 * a primeira instancia de Aprovavel encontrada será retornada.
	 * 
	 * @param contexto
	 *            o contexto de execução do EJB.
	 * @param businessMethod
	 *            o método sendo executado.
	 * @return a entidade aprovavel.
	 * @throws BancoobException
	 *             caso alguma exceção ocorra.
	 */
	@SuppressWarnings("unchecked")
	private <A extends CAPESEntidade<Long> & Aprovavel> A
			obterEntidadePorTipoParametro(InvocationContext contexto) throws BancoobException {

		A result = null;
		Object[] parameters = contexto.getParameters();
		for (int i = 0; (i < parameters.length) && (result == null); i++) {
			Object parametro = parameters[i];
			if (isAprovavel(parametro)) {
				result = (A) parametro;
				logger.debug("Entidade obtida atraves do tipo do parametro: ", parametro.getClass()
						.toString());
			}
		}

		if (result == null) {
			logger.alerta("Nao foi possivel obter a entidade por tipo de parametro: ",
					ArrayUtils.toString(parameters));
		}
		return result;
	}

	/**
	 * Verifica se a entidade passada como parametro é uma instância de
	 * Aprovavel.
	 * 
	 * @param parametro
	 * @return true se a a entidade passada como parametro for uma instância
	 *         de Aprovavel.
	 */
	private boolean isAprovavel(Object parametro) {
		return parametro instanceof Aprovavel;
	}

	/**
	 * Busca a entidade aprovavel na base de dados, o parametro do metodo sendo
	 * executado que possue a annotation {@link IdEntidadeAprovavel} será
	 * usado para consultar a entidade.
	 * 
	 * <p>
	 * A sintaxe utilizada para a chamada do método
	 * {@link #consultarEntidadeAprovavel(InvocationContext, Long)} é em
	 * decorrência do bug <a
	 * href="http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6302954"
	 * >6302954</a> da JDK, que impedia o Maven de compilar o projeto
	 * corretamente.
	 * </p>
	 * 
	 * @param contexto
	 *            o contexto de execução do EJB.
	 * @param businessMethod
	 *            o método sendo executado.
	 * @return a entidade aprovavel.
	 * @throws BancoobException
	 *             caso alguma exceção ocorra.
	 */
	private <A extends CAPESEntidade<Long> & Aprovavel> A
			obterEntidadePorParametroAnotado(InvocationContext contexto, Method businessMethod)
					throws BancoobException {

		A aprovavel = null;
		Long id = (Long) obterIDEntidadeAprovavel(contexto, businessMethod);
		if (id != null) {
			logger.debug("Parametro anotado localizado: ", String.valueOf(id));
			if (contexto.getTarget() instanceof CAPESCadastroCrudServico<?>) {

				aprovavel = this.<A> consultarEntidadeAprovavel(contexto, id);
				if (aprovavel != null) {
					logger.debug("Entidade obtida atraves do parametro anotado: ", aprovavel
							.getClass().getName());
				} else {
					logger.alerta("Nao foi possivel obter a entidade por parametro anotado: ",
							"Entidade nao localizada pelo ID");
				}
			}
		} else {
			logger.alerta("Nao foi possivel obter a entidade por parametro anotado: ",
					"Parametro anotado inexistente");
		}
		return aprovavel;
	}

	/**
	 * Consulta a entidade caso seja uma instancia de {@link Aprovavel}.
	 * 
	 * @param contexto
	 *            o InvocationContext que está sendo executado.
	 * @param idEntidadeAprovavel
	 *            o identificador que será usado para consultar a entidade.
	 * @return a entidade persistente.
	 * @throws BancoobException
	 *             caso a entidade seja uma instancia de {@link Aprovavel}.
	 */
	@SuppressWarnings("unchecked")
	private <A extends CAPESEntidade<Long> & Aprovavel> A
			consultarEntidadeAprovavel(InvocationContext contexto, Long idEntidadeAprovavel)
					throws BancoobException {

		A aprovavel = null;
		CAPESEntidade<Long> entidade = null;

		CAPESCadastroCrudServico<?> cucCrudServico = obterServico(contexto);
		entidade =
				(CAPESEntidade<Long>) cucCrudServico
						.obter(idEntidadeAprovavel);

		logger.debug("Foi efetuada a busca da entidade pelo identificador ",
				idEntidadeAprovavel.toString());

		if (entidade != null) {
			if (isAprovavel(entidade)) {
				aprovavel = (A) entidade;
			} else {
				throw new BancoobException(entidade.getClass() + " nao e uma instancia de "
						+ Aprovavel.class.getSimpleName());
			}
		}
		return aprovavel;
	}

	/**
	 * Deve verificar autorizacao.
	 *
	 * @param <A> o tipo generico
	 * @param aprovavel o valor de aprovavel
	 * @return {@code true}, em caso de sucesso
	 */
	private <A extends CAPESEntidade<Long> & Aprovavel> boolean
			deveVerificarAutorizacao(A aprovavel) {
		return (aprovavel != null) && (aprovavel.getVerificarAutorizacao() != null)
				&& aprovavel.getVerificarAutorizacao();
	}

	/**
	 * Verifica se eh integracao habilitada.
	 *
	 * @param context o valor de context
	 * @return {@code true}, se for integracao habilitada
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private boolean isIntegracaoHabilitada(InvocationContext context) throws BancoobException {
		return isCompartilhamentoUtilizandoGedGft()
				&& isServicoIntegradoGedGft(context.getTarget().getClass());
	}

	/**
	 * Recupera o valor de delegate.
	 *
	 * @return o valor de delegate
	 */
	private AutorizacaoCadastroDelegate getDelegate() {
		if (delegate == null) {
			delegate =
					CAPESCadastroFabricaDelegates.getInstance()
							.criarAutorizacaoCadastroDelegate();
		}
		return delegate;
	}

	/**
	 * Obter servico.
	 *
	 * @param contexto o valor de contexto
	 * @return EntidadeCadastroServico
	 */
	private EntidadeCadastroServico<?> obterServico(InvocationContext contexto) {
		return (EntidadeCadastroServico<?>) contexto.getTarget();
	}

	/**
	 * Obter usuario.
	 *
	 * @return UsuarioBancoob
	 */
	private UsuarioBancoob obterUsuario() {

		InformacoesUsuarioCAPES informacoes = InformacoesUsuarioCAPES.getInstance();

		if (informacoes == null) {
			throw new UnsupportedOperationException(
					"O InformacoesUsuarioCAPES nao foi instanciado!");
		}

		UsuarioBancoob usuarioBancoob = new UsuarioBancoob();
		usuarioBancoob.setCooperativa(informacoes.getCooperativa());
		usuarioBancoob.setIdInstituicao(informacoes.getIdInstituicao());
		usuarioBancoob.setIdUnidadeInstituicao(informacoes.getIdUnidadeInstituicao());
		usuarioBancoob.setLogin(informacoes.getLogin());
		usuarioBancoob.setPac(informacoes.getPac());

		return usuarioBancoob;
	}
}