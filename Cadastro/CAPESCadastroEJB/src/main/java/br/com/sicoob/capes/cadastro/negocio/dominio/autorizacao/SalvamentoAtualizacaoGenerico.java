package br.com.sicoob.capes.cadastro.negocio.dominio.autorizacao;

import java.util.HashSet;
import java.util.Set;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.DadosConfiguracaoFluxoDelegate;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum;
import br.com.sicoob.capes.cadastro.negocio.excecao.CAPESCadastroNegocioException;
import br.com.sicoob.capes.comum.negocio.annotation.CamposAutorizacao;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.AutorizacaoDocumento;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.DadosConfiguracaoFluxo;
import br.com.sicoob.capes.negocio.entidades.DocumentoComprobatorio;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Comprovavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * A Classe SalvamentoAtualizacaoGenerico.
 */
public class SalvamentoAtualizacaoGenerico
		implements SalvamentoAtualizacaoStrategy {

	/** A constante LOGGER. */
	protected static final ISicoobLogger LOGGER = SicoobLoggerPadrao
			.getInstance(SalvamentoAtualizacaoStrategy.class);

	/** O atributo fabricaDelegate. */
	private CAPESCadastroFabricaDelegates fabricaDelegate;
	
	/** O atributo autorizacaoDelegate. */
	private AutorizacaoDelegate autorizacaoDelegate;
	
	/** O atributo autorizacaoCadastroDelegate. */
	private AutorizacaoCadastroDelegate autorizacaoCadastroDelegate;
	
	/** O atributo dadosFluxoDelegate. */
	private DadosConfiguracaoFluxoDelegate dadosFluxoDelegate;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Autorizacao execute(Aprovavel aprovavel, TipoOperacaoEnum tipoOperacao) throws BancoobException {
		String idRegistroControlado = aprovavel.getClass().getSimpleName() + "#" + aprovavel.getId();
		String mensagemRollback = getClass().getSimpleName() + ".execute: rollback salvamento atualizacao ";

		try {
			Instituicao instituicao = obterInstituicaoUsuarioLogado();
			LOGGER.debug(getClass().getSimpleName(), ": instituicao usuario logado - ", instituicao.toString());

			Autorizacao autorizacao = criarAutorizacao(aprovavel, tipoOperacao, instituicao);
			marcarEmAlteracao(aprovavel, autorizacao);
			salvarAtualizacao(aprovavel, autorizacao, tipoOperacao);
			return salvarAutorizacao(autorizacao);
		} catch (NegocioException e) {
			LOGGER.erro(e, mensagemRollback + idRegistroControlado);
			throw new CAPESCadastroNegocioException(e.getMessage(), e);
		}
	}

	/**
	 * Salvar autorizacao.
	 *
	 * @param autorizacao o valor de autorizacao
	 * @return Autorizacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected final Autorizacao salvarAutorizacao(Autorizacao autorizacao)
			throws BancoobException {
		return getAutorizacaoDelegate().incluir(autorizacao);
	}

	/**
	 * O método Marcar em alteracao.
	 *
	 * @param aprovavel o valor de aprovavel
	 * @param autorizacao o valor de autorizacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected void marcarEmAlteracao(Aprovavel aprovavel, Autorizacao autorizacao) throws BancoobException {
		if (aprovavel.getId() != null) {
			Instituicao instituicaoOrigem = autorizacao.getInstituicaoOrigem();
			getAutorizacaoCadastroDelegate().marcarEmAlteracao(aprovavel, instituicaoOrigem.getIdInstituicao());
		}
	}

	/**
	 * Realiza o salvamento da atualização realizada pelo usuário para torná-la
	 * válida após a autorização
	 *
	 * @param aprovavel
	 *            a atualização para salvamento
	 * @param autorizacao
	 *            a solicitação de autorização
	 * @param tipoOperacao
	 *            o tipo de operação solicitado pelo usuário
	 *
	 * @return o registro salvo
	 */
	@SuppressWarnings("unchecked")
	protected void salvarAtualizacao(Aprovavel aprovavel, Autorizacao autorizacao,
			TipoOperacaoEnum tipoOperacao) throws BancoobException {

		Aprovavel novoRegistro = null;
		CAPESCadastroCrudDelegate<CAPESEntidade<?>, ?> delegate;
		if (!TipoOperacaoEnum.E.equals(tipoOperacao)) {

			// obtem a classe aprovavel
			final Class<Aprovavel> classeAprovavel = (Class<Aprovavel>) aprovavel.getClass();

			// cria o novo registro com os dados do alterados
			novoRegistro = criarNovoRegistro(aprovavel, autorizacao.getInstituicaoOrigem(),
					classeAprovavel);

			// obtem a delegate
			delegate = getFabricaDelegate().criarDelegate(
					TipoAutorizacaoEntidadeEnum.valueOf(autorizacao.getTipoAutorizacao()).getTipo());

			// salva o novo registro
			novoRegistro = (Aprovavel) delegate.incluir((CAPESEntidade<Long>) novoRegistro);
			
			// Atualiza a referência do objeto antigo.
			if (ReflexaoUtils.getValorAtributo(aprovavel, "id") == null) {
				ReflexaoUtils.setPropriedade(aprovavel, "id", novoRegistro.getId());
			}

			// preenche o ID do registro novo na autorizacao
			autorizacao.setIdRegistroNovo(((CAPESEntidade<Long>) novoRegistro)
					.getId());
		}
	}

	/**
	 * Copia o objeto {@code aprovavel}
	 *
	 * @param aprovavel
	 *            O objeto a ser copiado
	 * @param instAtualizacao
	 *            a instituição para preenchimento do
	 *            {@link Aprovavel#setIdInstituicaoAtualizacao(Integer)}
	 * @param classeAprovavel
	 *            A classe do objeto {@code aprovavel}
	 * @return o novo objeto
	 */
	protected Aprovavel criarNovoRegistro(final Aprovavel aprovavel, final Instituicao instAtualizacao,
			final Class<Aprovavel> classeAprovavel) {

		CamposAutorizacao camposAutorizacao =
				classeAprovavel.getAnnotation(CamposAutorizacao.class);

		// instancia e preenche os dados da entidade com as alteracoes
		Aprovavel novoRegistro = ReflexaoUtils.construirObjetoPorClasse(classeAprovavel);
		ReflexaoUtils.copiarPropriedades(novoRegistro, aprovavel, getPropriedadesIgnoradas(camposAutorizacao.id()));
		novoRegistro.setIdInstituicaoAtualizacao(instAtualizacao.getIdInstituicao());
		novoRegistro.setVerificarAutorizacao(false);

		Vigente vigente = novoRegistro;
		vigente.setGravarHistorico(false);
		vigente.setDataHoraInicio(null);

		return novoRegistro;
	}

	/**
	 * Copiar id.
	 *
	 * @return {@code true}, em caso de sucesso
	 */
	protected boolean copiarID() {
		return false;
	}

	/**
	 * Recupera o valor de propriedadesIgnoradas.
	 *
	 * @param atributoID o valor de atributo id
	 * @return o valor de propriedadesIgnoradas
	 */
	protected final String[] getPropriedadesIgnoradas(String atributoID) {

		String[] propriedadesBasicas = getPropriedadesIgnoradas();
		String[] propriedades = new String[propriedadesBasicas.length + 2];
		System.arraycopy(propriedadesBasicas, 0, propriedades, 0, propriedadesBasicas.length);
		if (!copiarID()) {
			propriedades[propriedadesBasicas.length] = "id";
			propriedades[propriedadesBasicas.length + 1] = atributoID;
		}
		return propriedades;
	}

	/**
	 * Recupera o valor de propriedadesIgnoradas.
	 *
	 * @return o valor de propriedadesIgnoradas
	 */
	protected String[] getPropriedadesIgnoradas() {

		return new String[] {};
	}

	/**
	 * Criar autorizacao.
	 *
	 * @param aprovavel o valor de aprovavel
	 * @param tipoOperacao o valor de tipo operacao
	 * @param instOrigem o valor de inst origem
	 * @return Autorizacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected Autorizacao criarAutorizacao(Aprovavel aprovavel, TipoOperacaoEnum tipoOperacao,
			Instituicao instOrigem) throws BancoobException {

		final Instituicao instDestino = obterInstituicaoDestino(aprovavel);

		Autorizacao autorizacao = new Autorizacao();
		autorizacao.setDataHoraCadastro(new DateTimeDB());
		autorizacao.setIdRegistroAntigo(aprovavel.getId());
		autorizacao.setInstituicaoDestino(instDestino);
		autorizacao.setInstituicaoOrigem(instOrigem);
		autorizacao.setPessoa(aprovavel.getPessoaCompartilhamento().getPessoa());
		autorizacao.setTipoAutorizacao(aprovavel.getTipoAutorizacao());
		autorizacao.setTipoOperacao(tipoOperacao);

		adicionarDocumentos(aprovavel, autorizacao);

		return autorizacao;
	}

	/**
	 * Obtém a instituição de destino.
	 * @param aprovavel
	 * @return
	 * @throws BancoobException
	 */
	protected Instituicao obterInstituicaoDestino(Aprovavel aprovavel) throws BancoobException {
		final Instituicao instDestino;

		// obtém as configurações do fluxo
		DadosConfiguracaoFluxo config = getAutorizacaoCadastroDelegate().obterConfiguracoesFluxo(aprovavel);

		// obtem a instituicao de destino
		if (config.getInstituicaoDestino() != null) {
			instDestino = config.getInstituicaoDestino();
		} else {
			instDestino = obterInstituicaoResponsavel(aprovavel);
		}
		return instDestino;
	}
	
	/**
	 * Obter instituicao responsavel.
	 *
	 * @param aprovavel o valor de aprovavel
	 * @return Instituicao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected Instituicao obterInstituicaoResponsavel(Aprovavel aprovavel) throws BancoobException {
		return getAutorizacaoCadastroDelegate().obterInstituicaoResponsavel(
				aprovavel);
	}

	/**
	 * O método Adicionar documentos.
	 *
	 * @param aprovavel o valor de aprovavel
	 * @param autorizacao o valor de autorizacao
	 */
	protected void adicionarDocumentos(Aprovavel aprovavel, Autorizacao autorizacao) {
		if (aprovavel instanceof Comprovavel) {
			Comprovavel comprovavel = (Comprovavel) aprovavel;
			if (comprovavel.getDocumentosComprobatorios() != null) {
    			Set<AutorizacaoDocumento> documentos = new HashSet<AutorizacaoDocumento>();
				for (DocumentoComprobatorio comprovante : comprovavel.getDocumentosComprobatorios()) {
					AutorizacaoDocumento documento = new AutorizacaoDocumento(null, comprovante);
					documento.setAutorizacao(autorizacao);
					documentos.add(documento);
				}
    			autorizacao.setDocumentos(documentos);
			}
		}
	}

	/**
	 * Obter usuario.
	 *
	 * @return UsuarioBancoob
	 */
	protected UsuarioBancoob obterUsuario() {

		InformacoesUsuarioCAPES informacoes = InformacoesUsuarioCAPES.getInstance();
		if (informacoes == null) {
			throw new UnsupportedOperationException(
					"O InformacoesUsuarioCAPES não foi instanciado!");
		}

		LOGGER.debug(getClass().getSimpleName() + ": usuario logado - ", informacoes.toString());

		UsuarioBancoob usuarioBancoob = new UsuarioBancoob();
		usuarioBancoob.setCooperativa(informacoes.getCooperativa());
		usuarioBancoob.setIdInstituicao(informacoes.getIdInstituicao());
		usuarioBancoob.setIdUnidadeInstituicao(informacoes.getIdUnidadeInstituicao());
		usuarioBancoob.setLogin(informacoes.getLogin());
		usuarioBancoob.setPac(informacoes.getPac());
		return usuarioBancoob;
	}

	/**
	 * Obter instituicao usuario logado.
	 *
	 * @return Instituicao
	 */
	protected Instituicao obterInstituicaoUsuarioLogado() {

		UsuarioBancoob usuario = obterUsuario();
		Instituicao instituicao = new Instituicao();
		instituicao.setIdInstituicao(Integer.valueOf(usuario.getIdInstituicao()));
		instituicao.setIdUnidadeInst(Integer.valueOf(usuario.getIdUnidadeInstituicao()));
		return instituicao;
	}
	
	protected Instituicao obterInstituicaoBancoob() {

		Instituicao instituicao = new Instituicao();
		instituicao.setIdInstituicao(Constantes.Comum.ID_INSTITUICAO_BANCOOB);
		instituicao.setIdUnidadeInst(Constantes.Comum.ID_UNIDADEINST_ZERO);
		return instituicao;
	}


	/**
	 * Recupera o valor de fabricaDelegate.
	 *
	 * @return o valor de fabricaDelegate
	 */
	protected CAPESCadastroFabricaDelegates getFabricaDelegate() {
		if (this.fabricaDelegate == null) {
			this.fabricaDelegate = CAPESCadastroFabricaDelegates.getInstance();
		}
		return this.fabricaDelegate;
	}

	/**
	 * Recupera o valor de autorizacaoCadastroDelegate.
	 *
	 * @return o valor de autorizacaoCadastroDelegate
	 */
	protected AutorizacaoCadastroDelegate getAutorizacaoCadastroDelegate() {
		if (this.autorizacaoCadastroDelegate == null) {
			this.autorizacaoCadastroDelegate = getFabricaDelegate().criarAutorizacaoCadastroDelegate();
		}
		return this.autorizacaoCadastroDelegate;
	}

	/**
	 * Recupera o valor de dadosFluxoDelegate.
	 *
	 * @return o valor de dadosFluxoDelegate
	 */
	protected DadosConfiguracaoFluxoDelegate getDadosFluxoDelegate() {
		if (this.dadosFluxoDelegate == null) {
			this.dadosFluxoDelegate = getFabricaDelegate().criarDadosConfiguracaoFluxoDelegate();
		}
		return this.dadosFluxoDelegate;
	}

	/**
	 * Recupera o valor de autorizacaoDelegate.
	 *
	 * @return o valor de autorizacaoDelegate
	 */
	protected AutorizacaoDelegate getAutorizacaoDelegate() {
		if (this.autorizacaoDelegate == null) {
			this.autorizacaoDelegate = getFabricaDelegate().criarAutorizacaoDelegate();
		}
		return this.autorizacaoDelegate;
	}

}