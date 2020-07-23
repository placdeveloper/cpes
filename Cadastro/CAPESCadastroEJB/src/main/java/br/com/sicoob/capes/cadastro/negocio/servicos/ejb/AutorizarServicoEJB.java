package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.ParametroDelegate;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAutorizacaoDTO;
import br.com.sicoob.capes.cadastro.negocio.enums.ParametroEnum;
import br.com.sicoob.capes.cadastro.negocio.enums.ProcessoEnum;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum;
import br.com.sicoob.capes.cadastro.negocio.estrategias.EstrategiaAutorizacaoContext;
import br.com.sicoob.capes.cadastro.negocio.excecao.AutorizacaoNegocioException;
import br.com.sicoob.capes.cadastro.negocio.excecao.CAPESCadastroNegocioException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.AutorizacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.AutorizarServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.AutorizarServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.vo.EncaminharAutorizacaoVO;
import br.com.sicoob.capes.cadastro.util.IntegracaoUtil;
import br.com.sicoob.capes.cadastro.util.SerializacaoUtils;
import br.com.sicoob.capes.comum.negocio.dto.GFTIntegracaoDTO;
import br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum;
import br.com.sicoob.capes.comum.negocio.vo.ControleVO;
import br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO;
import br.com.sicoob.capes.comum.negocio.vo.OcorrenciaAtividadeVO;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.GFTIntegracaoDelegate;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.integracao.negocio.excecao.CAPESIntegracaoException;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.AutorizacaoDocumento;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.DadosConfiguracaoFluxo;
import br.com.sicoob.capes.negocio.entidades.DocumentoComprobatorio;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Comprovavel;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;

/**
 * Implementa as operações do serviço de autorização
 *
 * @author Rodrigo.Chaves
 */
@Stateless
@Local({ AutorizarServicoLocal.class })
@Remote({ AutorizarServicoRemote.class })
public class AutorizarServicoEJB extends CAPESCadastroServicoEJB implements
		AutorizarServicoLocal, AutorizarServicoRemote {

	private final String PROPRIEDADE_ID_USUARIO_ENVIO = "idUsuarioEnvio";
	
	/** O atributo fabrica. */
	private CAPESCadastroFabricaDelegates fabrica =
			CAPESCadastroFabricaDelegates.getInstance();

	/** O atributo autorizacaoDelegate. */
	private AutorizacaoDelegate autorizacaoDelegate = this.fabrica.criarAutorizacaoDelegate();

	/** O atributo autorizacaoCadastroDelegate. */
	private AutorizacaoCadastroDelegate autorizacaoCadastroDelegate = this.fabrica
			.criarAutorizacaoCadastroDelegate();
	
	/** O atributo sciDelegate. */
	private SCIIntegracaoDelegate sciDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
	
	/** O atributo parametroDelegate. */
	private ParametroDelegate parametroDelegate = CAPESCadastroFabricaDelegates
			.getInstance().criarParametroDelegate();

	/** O atributo autorizarServico. */
	@EJB(mappedName = "capes/cadastro/AutorizarServico")
	private AutorizarServicoLocal autorizarServico;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaDto<Autorizacao> pesquisarAutorizacoesPendentes(
			ConsultaAutorizacaoDTO criterios) throws BancoobException {

		Autorizacao filtro = (Autorizacao) criterios.getFiltro();

		// Apenas as autorizações que estão na vez do usuário
		GFTIntegracaoDelegate gftDelegate =
				CAPESIntegracaoFabricaDelegates.getInstance().criarGFTIntegracaoDelegate();
		Set<String> idsRegistroControlado = gftDelegate.obterIDRegistrosControlados();

		// se existem registros a serem exibidos
		if ((idsRegistroControlado != null) && !idsRegistroControlado.isEmpty()) {
			criterios.setIdsRegistroControlado(idsRegistroControlado);
			preencherIdInstituicao(filtro.getInstituicaoOrigem());

			boolean isGestor = CAPESCadastroFabricaDelegates.getInstance()
					.criarAutorizacaoCadastroDelegate().isGestor(obterUsuario());
			if (isGestor) {
				criterios.setTipoProcura("GESTOR");
			}
			this.autorizacaoDelegate.pesquisarAutorizacoesPendentes(criterios);
		}
		return obterDTORetorno(criterios);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaDto<Autorizacao> pesquisarAutorizacoesDevolvidas(
			ConsultaAutorizacaoDTO criterios) throws BancoobException {

		Autorizacao filtro = (Autorizacao) criterios.getFiltro();

		// Apenas as autorizações que estão na vez do usuário
		Set<String> idsRegistroControlado =
				CAPESIntegracaoFabricaDelegates.getInstance().criarGFTIntegracaoDelegate().obterIDRegistrosControlados();
		if ((idsRegistroControlado != null) && !idsRegistroControlado.isEmpty()) {
			criterios.setIdsRegistroControlado(idsRegistroControlado);
			Instituicao instituicaoDestino = filtro.getInstituicaoDestino();
			preencherIdInstituicao(instituicaoDestino);
			this.autorizacaoDelegate.pesquisarAutorizacoesDevolvidas(criterios);
		}
		return obterDTORetorno(criterios);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaDto<Autorizacao> pesquisarAutorizacoesEncaminhadas(
			ConsultaAutorizacaoDTO criterios) throws BancoobException {

		Autorizacao filtro = (Autorizacao) criterios.getFiltro();
		Instituicao instituicaoDestino = filtro.getInstituicaoDestino();

		preencherIdInstituicao(instituicaoDestino);

		ConsultaAutorizacaoDTO resultadoPesquisa =
				this.autorizacaoDelegate.pesquisarAutorizacoesEncaminhadas(criterios);
		return obterDTORetorno(resultadoPesquisa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaAutorizacaoDTO pesquisarAutorizacoesNaoEncaminhadas(
			ConsultaAutorizacaoDTO criterios) throws BancoobException {

		return this.autorizacaoDelegate.pesquisarAutorizacoesNaoEncaminhadas(criterios);
	}

	/**
	 * Cria um objeto {@link ConsultaDto} com o resultado de
	 * {@link #pesquisarAutorizacoesEncaminhadas(ConsultaAutorizacaoDTO)}
	 *
	 * @param resultado
	 *            O resultado da pesquisa
	 * @return o DTO
	 */
	private ConsultaDto<Autorizacao> obterDTORetorno(ConsultaAutorizacaoDTO resultado)
			throws BancoobException {

		Map<Integer, InstituicaoVO> cache = new HashMap<Integer, InstituicaoVO>();
		ConsultaDto<Autorizacao> dto = transformarDTO(resultado);
		for (Autorizacao autorizacao : dto.getResultado()) {
			preencherDadosCooperativa(cache, autorizacao.getInstituicaoOrigem());
			preencherDadosCooperativa(cache, autorizacao.getInstituicaoDestino());
		}
		return dto;
	}
	
	/**
	 * O método Preencher id instituicao.
	 *
	 * @param instituicao o valor de instituicao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void preencherIdInstituicao(Instituicao instituicao) throws BancoobException {

		if (instituicao != null) {
			InstituicaoVO instCoop = obterInstituicaoSCI(instituicao.getNumero());
			if (instCoop != null) {
				instituicao.setIdInstituicao(instCoop.getIdInstituicao());
			}
		}
	}

	/**
	 * O método Preencher dados cooperativa.
	 *
	 * @param cache o valor de cache
	 * @param instituicao o valor de instituicao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void preencherDadosCooperativa(Map<Integer, InstituicaoVO> cache,
			Instituicao instituicao) throws BancoobException {

		Integer idInstituicao = instituicao.getIdInstituicao();
		InstituicaoVO cooperativa = cache.get(idInstituicao);
		if (cooperativa == null) {
			cooperativa = sciDelegate.obterInstituicaoPorId(idInstituicao);
			cache.put(idInstituicao, cooperativa);
		}
		instituicao.setNome(cooperativa.getNome());
		instituicao.setNumero(String.valueOf(cooperativa.getNumero()));
	}

	/**
	 * Transforma o {@code resultado} (do tipo {@code ConsultaDto<Autorizacao>}
	 * no tipo {@code ConsultaDto<ItemAutorizacaoVO>}
	 *
	 * @see ConsultaDto
	 * @param resultado
	 *            {@code ConsultaDto<Autorizacao>} com os dados para criação da
	 *            nova instância
	 * @return O DTO
	 */
	private ConsultaDto<Autorizacao> transformarDTO(ConsultaAutorizacaoDTO resultado) {

		ConsultaDto<Autorizacao> dto = new ConsultaDto<Autorizacao>();
		dto.setFiltro(resultado.getFiltro());
		dto.setOrdemCrescente(resultado.isOrdemCrescente());
		dto.setOrdenacao(resultado.getOrdenacao());
		dto.setPagina(resultado.getPagina());
		dto.setProcurarPor(resultado.getProcurarPor());
		List<Autorizacao> lista = resultado.getResultado();
		dto.setResultado(lista == null ? new ArrayList<Autorizacao>() : lista);
		dto.setTamanhoPagina(resultado.getTamanhoPagina());
		dto.setTipoProcura(resultado.getTipoProcura());
		dto.setTotalRegistros(lista == null ? NumberUtils.INTEGER_ZERO : resultado
				.getTotalRegistros());
		return dto;
	}

	/**
	 * Recupera a cooperativa a partir do idInstituicao informado.
	 *
	 * @param idInstituicao
	 *            O identificador da instituição.
	 *
	 * @return A cooperativa.
	 * @throws BancoobException
	 * @throws
	 */
	private InstituicaoVO obterInstituicaoSCI(String numero) throws BancoobException {

		InstituicaoVO instituicaoCooperativa = null;
		if (numero != null && !"".equals(numero)) {
			instituicaoCooperativa = sciDelegate.obterInstituicaoPorNumeroCooperativa(Integer.valueOf(numero));
		}
		return instituicaoCooperativa;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Autorizacao> pesquisarAutorizacoesAptasEncaminhar(
			ConsultaAutorizacaoDTO criterios)throws BancoobException {
		return this.autorizacaoDelegate.pesquisarAutorizacoesAptasEncaminhar(criterios);
	}
	
	/**
	 * O método Executar controles.
	 *
	 * @param controles o valor de controles
	 * @param autorizacao o valor de autorizacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void executarControles(List<ControleVO> controles, Autorizacao autorizacao)
			throws BancoobException {

		if (controles != null) {
			final String detalheLog = "Autorizacao#" + autorizacao.getId() + "("
					+ autorizacao.getIdRegistroControlado() + ")";
			try {
				getLogger().debug("iniciando execucao de controles: ", detalheLog, " ",
						controles.toString());

				TipoAutorizacaoEntidadeEnum tipoAutorizacao = TipoAutorizacaoEntidadeEnum.valueOf(autorizacao.getTipoAutorizacao());
				for (ControleVO controle : controles) {
					EstrategiaAutorizacaoContext contexto = new EstrategiaAutorizacaoContext(tipoAutorizacao, controle);
					contexto.executar(autorizacao);
				}
				getLogger().debug("controles executados: ", detalheLog, " ", controles.toString());
			} catch (NegocioException e) {
				getLogger().erro(e, "Erro ao executar controles: " + detalheLog);
				throw new AutorizacaoNegocioException(e, e.getMessage());
			} catch (BancoobRuntimeException e) {
				getLogger().erro(e, "Erro ao executar controles: " + detalheLog);
				throw e;
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean atualizarResponsavel(ConsultaAutorizacaoDTO criterios) throws BancoobException {

		List<Autorizacao> autorizacoes = pesquisarAutorizacoesAptasEncaminhar(criterios);

		if (autorizacoes == null || autorizacoes.isEmpty()) {
			throw new CAPESCadastroNegocioException(
					"Os registros da sua instituição já foram encaminhados para aprovação.");
		}

		Aprovavel aprovavel = null;
		boolean mudouDadosResponsavel = false;

		getLogger().info("Verificando alterações de responsável nas autorizações");
		for (Autorizacao autorizacao : autorizacoes) {

			// recupera a entidade aprovavel
			aprovavel = recuperarEntidadeAprovavel(autorizacao);

			//dados destino (ATUAL) responsável
			Instituicao instituicaoDestinoResponsavel = null;

			DadosConfiguracaoFluxo fluxoConfig = this.autorizacaoCadastroDelegate.obterConfiguracoesFluxo(aprovavel);
			if (fluxoConfig.getInstituicaoDestino() == null) {
				instituicaoDestinoResponsavel = this.autorizacaoCadastroDelegate.obterInstituicaoResponsavel(aprovavel);
			} else {
				instituicaoDestinoResponsavel = fluxoConfig.getInstituicaoDestino();
			}

			// Se o tipo do relacionamento não for compartilhado, a alteração deverá ser encaminhada
			// para a instituição do usuário logado.
			if (aprovavel instanceof RelacionamentoPessoa) {
				RelacionamentoPessoa relacionamento = (RelacionamentoPessoa) aprovavel;
				if (relacionamento.getTipoRelacionamento() != null && !relacionamento.getTipoRelacionamento().getPermiteCompartilhamento()) {
					instituicaoDestinoResponsavel = autorizacao.getInstituicaoDestino();
				}
			}

			boolean alterarAutorizacao = alterarAutorizacao(autorizacao, instituicaoDestinoResponsavel);

			if(alterarAutorizacao){

				//se houve alteração em qualquer autorização esta deve ser atualizada
				// e a autorização não deve ser encaminhada
				mudouDadosResponsavel = true;
				autorizacao.setInstituicaoDestino(instituicaoDestinoResponsavel);
				this.autorizacaoDelegate.alterar(autorizacao);
			}
		}
		return mudouDadosResponsavel;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void atualizarResponsavelAutorizacao(ConsultaAutorizacaoDTO criterios) throws BancoobException {
		List<Autorizacao> autorizacoes = pesquisarAutorizacoesAptasEncaminhar(criterios);

		if (CollectionUtils.isNotEmpty(autorizacoes)) {
			Aprovavel aprovavel = null;

			getLogger().info("Verificando alterações de responsável nas autorizações");
			for (Autorizacao autorizacao : autorizacoes) {

				// recupera a entidade aprovavel
				aprovavel = recuperarEntidadeAprovavel(autorizacao);

				// dados destino (ATUAL) responsável
				Instituicao instituicaoDestinoResponsavel = null;

				DadosConfiguracaoFluxo fluxoConfig = this.autorizacaoCadastroDelegate.obterConfiguracoesFluxo(aprovavel);
				if (fluxoConfig.getInstituicaoDestino() == null) {
					instituicaoDestinoResponsavel = this.autorizacaoCadastroDelegate.obterInstituicaoResponsavel(aprovavel);
				} else {
					instituicaoDestinoResponsavel = fluxoConfig.getInstituicaoDestino();
				}

				// Se o tipo do relacionamento não for compartilhado, a alteração deverá ser encaminhada para a instituição do usuário logado.
				if (aprovavel instanceof RelacionamentoPessoa) {
					RelacionamentoPessoa relacionamento = (RelacionamentoPessoa) aprovavel;
					if (relacionamento.getTipoRelacionamento() != null && !relacionamento.getTipoRelacionamento().getPermiteCompartilhamento()) {
						instituicaoDestinoResponsavel = autorizacao.getInstituicaoDestino();
					}
				}

				boolean alterarAutorizacao = alterarAutorizacao(autorizacao, instituicaoDestinoResponsavel);
				if (alterarAutorizacao) {
					// se houve alteração em qualquer autorização esta deve ser atualizada
					autorizacao.setInstituicaoDestino(instituicaoDestinoResponsavel);
					this.autorizacaoDelegate.alterar(autorizacao);
				}
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public boolean encaminharAutorizacoes(ConsultaAutorizacaoDTO criterios) throws BancoobException {

		List<Autorizacao> autorizacoes = pesquisarAutorizacoesAptasEncaminhar(criterios);
		boolean autorizacoesPendentes = (autorizacoes != null) && !autorizacoes.isEmpty();

		if (autorizacoesPendentes) {
			getLogger().info("Encaminhando autorizações...");
			for (Autorizacao autorizacao : autorizacoes) {
				autorizacao = this.autorizacaoDelegate.obter(autorizacao.getId());
				if(autorizacao != null) {
					if (StringUtils.isBlank(criterios.getIdUsuarioEnvio())) {
						this.autorizarServico.encaminharAutorizacao(autorizacao);						
					} else {
						this.autorizarServico.encaminharAutorizacao(autorizacao, criterios.getIdUsuarioEnvio());
					}
				}
			}
		}
		return autorizacoesPendentes;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void encaminharAutorizacao(Autorizacao autorizacao) throws BancoobException {
		Aprovavel aprovavel = null;
		GFTIntegracaoDelegate delegateGFT =
				CAPESIntegracaoFabricaDelegates.getInstance().criarGFTIntegracaoDelegate();

		getLogger().debug("Encaminhando autorizacao: ",
				String.valueOf(autorizacao.getIdAutorizacao()));

		// recupera a entidade aprovavel
		aprovavel = recuperarEntidadeAprovavel(autorizacao);

		/*
		 * transfere os documentos da autorização para a entidade
		 * aprovavel para possibilitar a correta identificacao/recuperacao dos dados
		 * de configuracao do fluxo a ser instanciado
		 */
		Set<AutorizacaoDocumento> documentos = autorizacao.getDocumentos();
		if ((documentos != null) && !documentos.isEmpty()) {
			Comprovavel comprovavel = (Comprovavel) aprovavel;
			comprovavel.setDocumentosComprobatorios(new HashSet<DocumentoComprobatorio>());
			for (AutorizacaoDocumento autorizacaoDocumento : documentos) {
				comprovavel.getDocumentosComprobatorios().add(
						autorizacaoDocumento.getDocumento());
			}
		}

		// obtem as configuracoes do fluxo
		getLogger().info("Obtendo configuracoes do fluxo");
		
		DadosConfiguracaoFluxo config = null;
		
		try {
			config = this.autorizacaoCadastroDelegate.obterConfiguracoesFluxo(aprovavel);
		} catch (NullPointerException e) { //NOPMD
			getLogger().erro(e, "Erro ao encaminhar a autorização: " + autorizacao.getId());
			throw e;
		}
		
		getLogger().debug("Configuracoes do fluxo obtidas: ", config.getSiglaProcesso(),
				"#", String.valueOf(config.getIdDadosConfiguracaoFluxo()));

		autorizacao.setSiglaProcesso(config.getSiglaProcesso());
		autorizacao.setDataHoraSolicitacao(new DateTimeDB());
		
		// Se o tipo do relacionamento não for compartilhado, a alteração deverá ser encaminhada para a instituição do usuário logado.
		if (aprovavel instanceof RelacionamentoPessoa) {
			RelacionamentoPessoa relacionamento = (RelacionamentoPessoa) aprovavel;
			if (relacionamento.getTipoRelacionamento() != null && !relacionamento.getTipoRelacionamento().getPermiteCompartilhamento()) {
				autorizacao.setSiglaProcesso(ProcessoEnum.CAPES_AICD.getSigla());
			}
		}

		incluirUsuarioEnvioAutorizacao(autorizacao);
		
		getLogger().info("Instanciando fluxo: ", config.getSiglaProcesso());
		
		if(obterParametroCancelarFluxo()) { 
			if(this.autorizarServico.isFluxoAtivo(autorizacao.getSiglaProcesso() , autorizacao.getIdRegistroControlado())) {
				delegateGFT.cancelarFluxo(config.getSiglaProcesso(), autorizacao.getIdRegistroControlado());
			}
		}
		delegateGFT.instanciarFluxo(IntegracaoUtil.criarGFTIntegracaoDTO(autorizacao));

		getLogger().debug("Autorizacao encaminhada: incluindo a sigla do processo (",
				config.getSiglaProcesso(), ") e ", "atulizando a data da solicitacao (",
				autorizacao.getDataHoraSolicitacao().toString(), ")");
		this.autorizacaoDelegate.alterar(autorizacao);
		
	}
	
	/**
	 * Obtem o parametro para cancelar o fluxo
	 * @return
	 */
	private boolean obterParametroCancelarFluxo() {
		Integer idInstituicaoUsuario = Integer.valueOf(obterInstituicaoUsuarioLogado().getIdInstituicao());
		boolean parametroValor = parametroDelegate.obterParametroValorBoolean(
				ParametroEnum.CANCELAR_FLUXO_AUTORIZACAO .getCodigo(), idInstituicaoUsuario);
		return parametroValor;
	}
	
	/**
	 * Método que recupera a entidade aprovável.
	 * @param autorizacao
	 * @return Retorna a entidade aprovável
	 * @throws BancoobException
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public boolean isFluxoAtivo(String siglaProcesso, String idRegistroControlado) throws BancoobException {
		GFTIntegracaoDelegate delegateGFT = CAPESIntegracaoFabricaDelegates.getInstance().criarGFTIntegracaoDelegate();
		return delegateGFT.isFluxoAtivo(siglaProcesso , idRegistroControlado);
	}
	
	

	/**
	 * Método que recupera a entidade aprovável.
	 * @param autorizacao
	 * @return Retorna a entidade aprovável
	 * @throws BancoobException
	 */
	private Aprovavel recuperarEntidadeAprovavel(Autorizacao autorizacao) throws BancoobException {
		CAPESCadastroCrudDelegate<CAPESEntidade<?>, ?> delegate = null;
		Aprovavel retorno = null;

		if (StringUtils.isNotBlank(autorizacao.getAlteracao())) {
			getLogger().info("Obtendo entidade aprovavel: deserializando o JSON");
			retorno = SerializacaoUtils.deserializarJson(Aprovavel.class, autorizacao.getAlteracao());
		} else {
			getLogger().info("Obtendo entidade aprovavel: pesquisando ", autorizacao.getTipoAutorizacao().name(), "#", String.valueOf(autorizacao.getIdRegistroEmAutorizacao()));
			
			TipoAutorizacaoEntidadeEnum tipoAutorizacao = TipoAutorizacaoEntidadeEnum.valueOf(autorizacao.getTipoAutorizacao());
			delegate = this.fabrica.criarDelegate(tipoAutorizacao.getTipo());

			retorno = (Aprovavel) delegate.obter(autorizacao.getIdRegistroEmAutorizacao());
		}
		return retorno;
	}
	
	/**
	 * Metodo que deve incluir dados do usuario que esta enviando para a aprovacao, a entidade em questao.
	 * Verifica se deve recuperar dados do JSON de autorizacao ou deve obter dados da autorizacao.
	 * 
	 * @param autorizacao
	 * 
	 * @throws BancoobException
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public void incluirUsuarioEnvioAutorizacao(Autorizacao autorizacao) throws BancoobException {
		if (autorizacao != null && StringUtils.isNotBlank(autorizacao.getNomePessoa())) {
			incluirUsuarioEnvioAutorizacao(autorizacao, autorizacao.getNomePessoa());
		} else {
			if(autorizacao != null) {
				TipoAutorizacaoEntidadeEnum tipoAutorizacao = TipoAutorizacaoEntidadeEnum.valueOf(autorizacao.getTipoAutorizacao());
				CAPESCadastroCrudDelegate<CAPESEntidade<?>, ?> delegate = this.fabrica.criarDelegate(tipoAutorizacao.getTipo());
				CAPESEntidade retorno = null;

				if (StringUtils.isNotBlank(autorizacao.getAlteracao())) {
					getLogger().info("Obtendo entidade aprovavel: deserializando o JSON");
					retorno = (CAPESEntidade) SerializacaoUtils.deserializarJson(Aprovavel.class, autorizacao.getAlteracao());

					if (ReflexaoUtils.objetoPossuiAtributo(retorno, PROPRIEDADE_ID_USUARIO_ENVIO)) {
						Field propriedade = ReflexaoUtils.getField(retorno.getClass(), PROPRIEDADE_ID_USUARIO_ENVIO);
						ReflexaoUtils.setValorAtributo(retorno, propriedade, obterUsuario().getLogin(), Boolean.TRUE);

						// TODO - Verificar necessidade de validar a exclusão Json inserindo a dataHoraInicio.
						if (autorizacao.getTipoOperacao().equals(TipoOperacaoEnum.E)) {
							CAPESEntidade entidade = delegate.obter(autorizacao.getIdRegistroAntigo());
							Field dataHoraInicio = ReflexaoUtils.getField(retorno.getClass(), "dataHoraInicio");
							ReflexaoUtils.setValorAtributo(retorno, dataHoraInicio, ReflexaoUtils.getValorAtributo(entidade, "dataHoraInicio"), Boolean.TRUE);
						}

						autorizacao.setAlteracao(SerializacaoUtils.serilizarJson(retorno, "dataHoraInicio"));
						autorizacaoDelegate.alterar(autorizacao);
					}
				} else {
					retorno = delegate.obter(autorizacao.getIdRegistroNovo() != null ? autorizacao.getIdRegistroNovo() : autorizacao.getIdRegistroAntigo());
					if (ReflexaoUtils.objetoPossuiAtributo(retorno, PROPRIEDADE_ID_USUARIO_ENVIO)) {
						retorno = setaValorIdUsuarioEnvioAutorizacao(retorno);
						delegate.alterarSemValidacao(retorno);
					}
				}
			}
		}
	}

	/**
	 * Metodo que deve incluir dados do usuario que esta enviando para a aprovacao, a entidade em questao.
	 * Verifica se deve recuperar dados do JSON de autorizacao ou deve obter dados da autorizacao.
	 * 
	 * @param autorizacao
	 * 
	 * @throws BancoobException
	 */
	@SuppressWarnings("rawtypes")
	public void incluirUsuarioEnvioAutorizacao(Autorizacao autorizacao, String usuario) throws BancoobException {
		if(autorizacao != null) {
			TipoAutorizacaoEntidadeEnum tipoAutorizacao = TipoAutorizacaoEntidadeEnum.valueOf(autorizacao.getTipoAutorizacao());
			CAPESCadastroCrudDelegate<CAPESEntidade<?>, ?> delegate = this.fabrica.criarDelegate(tipoAutorizacao.getTipo());
			CAPESEntidade retorno = null;
			
			if (StringUtils.isNotBlank(autorizacao.getAlteracao())) {
				getLogger().info("Obtendo entidade aprovavel: deserializando o JSON");
				retorno = (CAPESEntidade) SerializacaoUtils.deserializarJson(Aprovavel.class, autorizacao.getAlteracao());
				
				if (ReflexaoUtils.objetoPossuiAtributo(retorno, PROPRIEDADE_ID_USUARIO_ENVIO)) {
					Field propriedade = ReflexaoUtils.getField(retorno.getClass(), PROPRIEDADE_ID_USUARIO_ENVIO);
					ReflexaoUtils.setValorAtributo(retorno, propriedade, usuario, Boolean.TRUE);
					
					// TODO - Verificar necessidade de validar a exclusão Json inserindo a dataHoraInicio.
					if (autorizacao.getTipoOperacao().equals(TipoOperacaoEnum.E)) {
						CAPESEntidade entidade = delegate.obter(autorizacao.getIdRegistroAntigo());
						Field dataHoraInicio = ReflexaoUtils.getField(retorno.getClass(), "dataHoraInicio");
						ReflexaoUtils.setValorAtributo(retorno, dataHoraInicio, ReflexaoUtils.getValorAtributo(entidade, "dataHoraInicio"), Boolean.TRUE);
					}
					
					autorizacao.setAlteracao(SerializacaoUtils.serilizarJson(retorno, "dataHoraInicio"));
					autorizacaoDelegate.alterar(autorizacao);
				}
			} else {
				retorno = delegate.obter(autorizacao.getIdRegistroNovo() != null ? autorizacao.getIdRegistroNovo() : autorizacao.getIdRegistroAntigo());
				if (ReflexaoUtils.objetoPossuiAtributo(retorno, PROPRIEDADE_ID_USUARIO_ENVIO)) {
					Field propriedade = ReflexaoUtils.getField(retorno.getClass(), PROPRIEDADE_ID_USUARIO_ENVIO);
					Field propriedadeVerificaAutorizacao = ReflexaoUtils.getField(retorno.getClass(), "verificarAutorizacao");
					ReflexaoUtils.setValorAtributo(retorno, propriedade, usuario, Boolean.TRUE);
					ReflexaoUtils.setValorAtributo(retorno, propriedadeVerificaAutorizacao, Boolean.FALSE, Boolean.TRUE);
					delegate.alterarSemValidacao(retorno);
				}
			}
		}
	}
	
	/**
	 * Seta o valor do usuario logado na propriedade idUsuarioEnvio, e retorna para salvar em autorizacao por JSON
	 * ou salvar na entidade que esta sendo enviado para aprovacao.
	 * 
	 * @param retorno
	 * @return CAPESEntidade
	 */
	@SuppressWarnings("rawtypes")
	private CAPESEntidade setaValorIdUsuarioEnvioAutorizacao(CAPESEntidade retorno) {
		Field propriedade = ReflexaoUtils.getField(retorno.getClass(), PROPRIEDADE_ID_USUARIO_ENVIO);
		Field propriedadeVerificaAutorizacao = ReflexaoUtils.getField(retorno.getClass(), "verificarAutorizacao");
		ReflexaoUtils.setValorAtributo(retorno, propriedade, obterUsuario().getLogin(), Boolean.TRUE);
		ReflexaoUtils.setValorAtributo(retorno, propriedadeVerificaAutorizacao, Boolean.FALSE, Boolean.TRUE);

		return retorno;
	}
	
	/**
	 * Método que informa se será necessário a alteração da autorização no momento de encaminhá-las.
	 * Isto deve ocorrer para evitar a instancia de um fluxo incorreto no GFT. A autorização
	 * deve ser atualizada antes de ser encaminhada no seguinte caso: <br>
	 *
	 * <ul>
	 * 		<li>A instituição responsável foi alterada e não está igual instituição de destino da autorização gerada.</li>
	 * </ul>
	 * @param autorizacao
	 * @param instituicaoDestinoResponsavel
	 * @return retorna <code>false</code> caso não houve alteração dos dados do responsável com a autorização gerada, caso
	 * contrário retorna <code>true</code>
	 * @throws BancoobException
	 */
	private boolean alterarAutorizacao(Autorizacao autorizacao, Instituicao instituicaoDestinoResponsavel) throws BancoobException {

		//dados destino autorizacao
		Instituicao instituicaoDestinoAutorizacao = autorizacao.getInstituicaoDestino();
		
		if(!instituicaoDestinoResponsavel.getIdInstituicao().equals(instituicaoDestinoAutorizacao.getIdInstituicao())){
			getLogger().debug("Autorização id: " + autorizacao.getId() + " |" +
					" alteração de destino de: " + instituicaoDestinoAutorizacao.getIdInstituicao() +
					" para: " + instituicaoDestinoResponsavel.getIdInstituicao());
			return true;
		}

		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void executarProcedimento(Autorizacao autorizacao, OcorrenciaAtividadeVO procedimento, String justificativa) throws BancoobException {
		autorizacao = this.autorizacaoDelegate.obter(autorizacao.getId());

		if (autorizacao != null) {
			GFTIntegracaoDelegate delegateGFT = CAPESIntegracaoFabricaDelegates.getInstance().criarGFTIntegracaoDelegate();
			List<ControleVO> controles = delegateGFT.obterControles(procedimento);
			executarControles(controles, autorizacao);
			delegateGFT.executarProcedimento(IntegracaoUtil.criarGFTIntegracaoDTO(autorizacao), procedimento, justificativa);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluirAutorizacaoEncaminhada(EncaminharAutorizacaoVO vo) throws BancoobException {
		Autorizacao autorizacao = autorizacaoDelegate.obter(vo.getIdAutorizacao());

		if (autorizacao != null) {
			ControleVO controle = new ControleVO();
			controle.setCodigo(Constantes.Negocio.GFT_CODIGO_CONTROLE_REJEITAR_ALTERACOES);
			TipoAutorizacaoEntidadeEnum tipoAutorizacao = TipoAutorizacaoEntidadeEnum.valueOf(autorizacao.getTipoAutorizacao());
			EstrategiaAutorizacaoContext contexto = new EstrategiaAutorizacaoContext(tipoAutorizacao, controle);
			contexto.executar(autorizacao);
		}
	}

	private Aprovavel iniciarEncaminharAutorizacao(Autorizacao autorizacao) throws BancoobException {
		Aprovavel aprovavel = null;
		getLogger().debug("Encaminhando autorizacao: ",
				String.valueOf(autorizacao.getIdAutorizacao()));

		// recupera a entidade aprovavel
		aprovavel = recuperarEntidadeAprovavel(autorizacao);

		/*
		 * transfere os documentos da autorização para a entidade
		 * aprovavel para possibilitar a correta identificacao/recuperacao dos dados
		 * de configuracao do fluxo a ser instanciado
		 */
		Set<AutorizacaoDocumento> documentos = autorizacao.getDocumentos();
		if ((documentos != null) && !documentos.isEmpty()) {
			Comprovavel comprovavel = (Comprovavel) aprovavel;
			comprovavel.setDocumentosComprobatorios(new HashSet<DocumentoComprobatorio>());
			for (AutorizacaoDocumento autorizacaoDocumento : documentos) {
				comprovavel.getDocumentosComprobatorios().add(
						autorizacaoDocumento.getDocumento());
			}
		}
		
		return aprovavel;
		
	}

	private void finalizarEncaminharAutorizacao(DadosConfiguracaoFluxo config, Autorizacao autorizacao, String usuario) throws BancoobException {
		GFTIntegracaoDelegate delegateGFT =
				CAPESIntegracaoFabricaDelegates.getInstance().criarGFTIntegracaoDelegate();
		
		getLogger().info("Instanciando fluxo: ", config.getSiglaProcesso());
		delegateGFT.instanciarFluxo(IntegracaoUtil.criarGFTIntegracaoDTO(autorizacao));
		autorizacao.setNomePessoa(usuario);

		getLogger().debug("Autorizacao encaminhada: incluindo a sigla do processo (",
				config.getSiglaProcesso(), ") e ", "atulizando a data da solicitacao (",
				autorizacao.getDataHoraSolicitacao().toString(), ")");
		this.autorizacaoDelegate.alterar(autorizacao);
	}
	
    /**
     * Encaminhar uma entidade aprovavel para autorizacao.
     * Usuario um usuario pro configurado, que tem autorizacao para encaminhar e aprovar
     * 
     * @param autorizacao Autorizacao necessario para uma entidade aprovavel
     * @param usuario é o SUPER usuario configurado para ser utilizado no centralizacao
     */
	@Override
	public void encaminharAutorizacao(Autorizacao autorizacao,
			String usuario) throws BancoobException {
		Aprovavel aprovavel = iniciarEncaminharAutorizacao(autorizacao);
		
		DadosConfiguracaoFluxo config = null;
		try {
			config = this.autorizacaoCadastroDelegate.obterConfiguracoesFluxo(aprovavel);
		} catch (NullPointerException e) { //NOPMD
			getLogger().erro(e, "Erro ao encaminhar a autorização: " + autorizacao.getId());
			throw e;
		}
		
		getLogger().debug("Configuracoes do fluxo obtidas: ", config.getSiglaProcesso(),
				"#", String.valueOf(config.getIdDadosConfiguracaoFluxo()));
	
		autorizacao.setSiglaProcesso(config.getSiglaProcesso());
		autorizacao.setDataHoraSolicitacao(new DateTimeDB());
		
		// Se o tipo do relacionamento não for compartilhado, a alteração deverá ser encaminhada para a instituição do usuário logado.
		if (aprovavel instanceof RelacionamentoPessoa) {
			RelacionamentoPessoa relacionamento = (RelacionamentoPessoa) aprovavel;
			if (relacionamento.getTipoRelacionamento() != null && !relacionamento.getTipoRelacionamento().getPermiteCompartilhamento()) {
				autorizacao.setSiglaProcesso(ProcessoEnum.CAPES_AICD.getSigla());
			}
		}
		
		incluirUsuarioEnvioAutorizacao(autorizacao, usuario);
		
		finalizarEncaminharAutorizacao(config, autorizacao, usuario);
	}
	
	/**
	 * Pesquisar autorizacoes somente canais de auto atendimento.
	 *
	 * @param criterios o valor de criterios
	 * @return ConsultaDto
	 * @throws BancoobException 
	 */
	public ConsultaDto<Autorizacao> pesquisarAutorizacoesPendentesAutoAtendimento(ConsultaAutorizacaoDTO criterios) throws BancoobException {
		Autorizacao filtro = (Autorizacao) criterios.getFiltro();

		// Apenas as autorizações que estão na vez do usuário
		GFTIntegracaoDelegate gftDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarGFTIntegracaoDelegate();
		Set<String> idsRegistroControlado = gftDelegate.obterIDRegistrosControlados();

		// se existem registros a serem exibidos
		if ((idsRegistroControlado != null) && !idsRegistroControlado.isEmpty()) {
			criterios.setIdsRegistroControlado(idsRegistroControlado);
			preencherIdInstituicao(filtro.getInstituicaoOrigem());

			boolean isGestor = CAPESCadastroFabricaDelegates.getInstance().criarAutorizacaoCadastroDelegate().isGestor(obterUsuario());
			if (isGestor) {
				criterios.setTipoProcura("GESTOR");
			}
			this.autorizacaoDelegate.obterAutorizacoesAutoAtendimento(criterios);
		}
		return obterDTORetorno(criterios);
	}
	
	public Autorizacao obterAutorizacaoPorIdRegistroNovo(Long IdRegistroNovo, Boolean devolvido) throws BancoobException {
		return autorizacaoDelegate.obterAutorizacaoPorIdRegistroNovo(IdRegistroNovo, devolvido);
	}
}