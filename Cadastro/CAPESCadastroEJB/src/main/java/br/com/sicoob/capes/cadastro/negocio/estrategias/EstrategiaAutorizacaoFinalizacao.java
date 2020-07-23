package br.com.sicoob.capes.cadastro.negocio.estrategias;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum;
import br.com.sicoob.capes.cadastro.negocio.excecao.RegistroNaoEncontradoNegocioException;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * Implementa as responsabilidades (e funcionalidades básicas) das estratégias
 * de autorização para a finalização de um fluxo de aprovação
 *
 * 15/02/2011
 *
 * @author Rodrigo.Chaves
 */
public abstract class EstrategiaAutorizacaoFinalizacao implements EstrategiaAutorizacao {

	/** A constante LOGGER. */
	protected static final SicoobLoggerPadrao LOGGER = SicoobLoggerPadrao
			.getInstance(EstrategiaAutorizacaoFinalizacao.class);

	/** O atributo fabricaDelegatesComum. */
	private transient CAPESCadastroFabricaDelegates fabricaDelegatesComum = CAPESCadastroFabricaDelegates
			.getInstance();

	/** O atributo fabricaDelegatesIntegracao. */
	private transient CAPESIntegracaoFabricaDelegates fabricaDelegatesIntegracao = CAPESIntegracaoFabricaDelegates
			.getInstance();

	/**
	 * {@inheritDoc}
	 */
	public final void executar(Autorizacao autorizacao) throws BancoobException {

		TipoOperacaoEnum tipoOperacao = autorizacao.getTipoOperacao();
		
		Aprovavel aprovavel = obterDadosAprovacao(autorizacao);
		LOGGER.debug("Estrategia autorizacao: tipoOperacao = ", tipoOperacao.getDescricao());
		if (TipoOperacaoEnum.I.equals(tipoOperacao)) {
			tratarInclusao(autorizacao, aprovavel);
		} else if (TipoOperacaoEnum.A.equals(tipoOperacao)) {
			tratarAlteracao(autorizacao, aprovavel);
		} else if (TipoOperacaoEnum.E.equals(tipoOperacao)) {
			tratarExclusao(autorizacao, aprovavel);
		}
		excluirAutorizacao(autorizacao);
	}

	/**
	 * Obter dados aprovacao.
	 *
	 * @param autorizacao o valor de autorizacao
	 * @return Aprovavel
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected Aprovavel obterDadosAprovacao(Autorizacao autorizacao) throws BancoobException {
		Long id = obterIDRegistroEmAprovacao(autorizacao);
		TipoAutorizacaoEntidadeEnum tipoAutorizacao = TipoAutorizacaoEntidadeEnum.valueOf(autorizacao
				.getTipoAutorizacao());
		Aprovavel aprovavel = obter(tipoAutorizacao, id);

		if (aprovavel == null) {
			RegistroNaoEncontradoNegocioException e =
					new RegistroNaoEncontradoNegocioException("Registro de " + autorizacao.getTipoAutorizacao().getDescricao());
			LOGGER.erro(e, "Erro ao executar a operacao " + autorizacao.getTipoOperacao().getDescricao()
					+ " na estrategia: " + this.getClass().getSimpleName()
					+ " idRegistroControlado: " + autorizacao.getIdRegistroControlado());
			throw e;
		}

		aprovavel.setVerificarAutorizacao(false);
		return aprovavel;
	}

	/**
	 * O método Tratar historico.
	 *
	 * @param aprovavel o valor de aprovavel
	 * @param gravarHistorico o valor de gravar historico
	 */
	protected void tratarHistorico(Aprovavel aprovavel, boolean gravarHistorico) {
		Vigente vigente = aprovavel;
		vigente.setGravarHistorico(gravarHistorico);
	}

	/**
	 * O método Excluir dados aprovacao.
	 *
	 * @param aprovavel o valor de aprovavel
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected void excluirDadosAprovacao(Aprovavel aprovavel) throws BancoobException {

		aprovavel.setVerificarAutorizacao(false);
		tratarHistorico(aprovavel, false);
		excluir(aprovavel);
	}

	/**
	 * O método Tratar inclusao.
	 *
	 * @param autorizacao o valor de autorizacao
	 * @param aprovavel o valor de aprovavel
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected abstract void tratarInclusao(Autorizacao autorizacao, Aprovavel aprovavel)
			throws BancoobException;

	/**
	 * O método Tratar alteracao.
	 *
	 * @param autorizacao o valor de autorizacao
	 * @param aprovavel o valor de aprovavel
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected abstract void tratarAlteracao(Autorizacao autorizacao, Aprovavel aprovavel)
			throws BancoobException;

	/**
	 * O método Tratar exclusao.
	 *
	 * @param autorizacao o valor de autorizacao
	 * @param aprovavel o valor de aprovavel
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected abstract void tratarExclusao(Autorizacao autorizacao, Aprovavel aprovavel)
			throws BancoobException;


	/**
	 * Deleta a {@link Autorizacao} recebida como parâmetro
	 *
	 * @param autorizacao
	 *            a autorização a ser excluída
	 */
	protected abstract void excluirAutorizacao(Autorizacao autorizacao) throws BancoobException;

	/**
	 * Realiza a exclusão do registro {@link Aprovavel}
	 *
	 * @see CAPESCadastroCrudDelegate#excluirEntidade(CAPESEntidade)
	 *
	 * @param aprovavel
	 *            o objeto a ser excluído
	 */
	protected void excluir(Aprovavel aprovavel) throws BancoobException {

		LOGGER.debug("Excluindo ", aprovavel.getTipoAutorizacao().name(), ": ",
				String.valueOf(aprovavel.getId()));

		CAPESCadastroCrudDelegate<CAPESEntidade<?>, ?> delegate;
		delegate = criarDelegate(aprovavel
						.getTipoAutorizacao());
		delegate.excluirEntidade((CAPESEntidade<?>) aprovavel);
	}

	/**
	 * Criar delegate.
	 *
	 * @param tipoAutorizacao o valor de tipo autorizacao
	 * @return CAPESCadastroCrudDelegate
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected CAPESCadastroCrudDelegate<CAPESEntidade<?>, ?> criarDelegate(TipoAutorizacaoEnum tipoAutorizacao) throws BancoobException {

		return this.fabricaDelegatesComum.criarDelegate(TipoAutorizacaoEntidadeEnum.valueOf(tipoAutorizacao).getTipo());
	}

	/**
	 * Realiza a alteração do registro {@link Aprovavel}
	 *
	 * @see br.com.bancoob.negocio.delegates.BancoobCrudDelegate#alterar(br.com.bancoob.negocio.entidades.BancoobEntidade)
	 *
	 * @param aprovavel
	 *            o objeto a ser alterado
	 */
	protected void alterar(Aprovavel aprovavel) throws BancoobException {

		LOGGER.debug("Alterando ", aprovavel.getTipoAutorizacao().name(), ": ",
				String.valueOf(aprovavel.getId()));

		CAPESCadastroCrudDelegate<CAPESEntidade<?>, ?> delegate;
		delegate = criarDelegate(aprovavel.getTipoAutorizacao());
		delegate.alterar((CAPESEntidade<?>) aprovavel);
	}

	/**
	 * Realiza a inclusão do registro {@link Aprovavel}
	 *
	 * @see br.com.bancoob.negocio.delegates.BancoobCrudDelegate#incluir(br.com.bancoob.negocio.entidades.BancoobEntidade)
	 *
	 * @param aprovavel
	 *            o objeto a ser incluído
	 */
	protected void incluir(Aprovavel aprovavel) throws BancoobException {

		LOGGER.debug("Incluindo ", aprovavel.getTipoAutorizacao().name(), ": ",
				String.valueOf(aprovavel.getId()));

		CAPESCadastroCrudDelegate<CAPESEntidade<?>, ?> delegate;
		delegate = criarDelegate(aprovavel.getTipoAutorizacao());
		delegate.incluir((CAPESEntidade<?>) aprovavel);
	}

	/**
	 * Recupera um registro "aprovavel"
	 *
	 * @param tipoAutorizacao
	 *            o tipo da autorização
	 * @param id
	 *            o ID do registro a ser recuperado
	 * @return o registro
	 */
	protected Aprovavel obter(TipoAutorizacaoEntidadeEnum tipoAutorizacao, Long id) throws BancoobException {
		Aprovavel aprovavel = null;

		LOGGER.debug("Obtendo ", tipoAutorizacao.name(), ": ", String.valueOf(id));

		if(id != null){
			aprovavel = (Aprovavel) this.fabricaDelegatesComum.criarDelegate(tipoAutorizacao.getTipo()).obter(id);
		}

		return aprovavel;
	}

	/**
	 * Obtém o ID do registro que contém os dados em aprovação, segundo a
	 * seguite regra:
	 *
	 * <pre>
	 * {@link Autorizacao#getTipoOperacao()} == {@link TipoOperacaoEnum#I}: {@link Autorizacao#getIdRegistroNovo()}
	 * {@link Autorizacao#getTipoOperacao()} == {@link TipoOperacaoEnum#A}: {@link Autorizacao#getIdRegistroNovo()}
	 * {@link Autorizacao#getTipoOperacao()} == {@link TipoOperacaoEnum#E}: {@link Autorizacao#getIdRegistroAntigo()}
	 * </pre>
	 *
	 * @param autorizacao
	 *            a autorização para a qual se deseja obter o ID
	 * @return o ID do registro
	 */
	protected final Long obterIDRegistroEmAprovacao(Autorizacao autorizacao) {

		Long id = null;
		String registro = null;
		TipoOperacaoEnum tipoOperacao = autorizacao.getTipoOperacao();
		LOGGER.debug("obtendo o ID do registro em aprovacao: autorizacao#", autorizacao.getIdAutorizacao().toString());
		if (TipoOperacaoEnum.I.equals(tipoOperacao) || TipoOperacaoEnum.A.equals(tipoOperacao)) {
			id = autorizacao.getIdRegistroNovo();
			registro = "NOVO";
		} else if (TipoOperacaoEnum.E.equals(tipoOperacao)) {
			id = autorizacao.getIdRegistroAntigo();
			registro = "ANTIGO";
		}
		LOGGER.debug("ID obtido do registro ", registro, ": ", String.valueOf(id));
		return id;
	}

	/**
	 * Obter usuario.
	 *
	 * @return UsuarioBancoob
	 */
	protected UsuarioBancoob obterUsuario() {

		InformacoesUsuarioCAPES info = InformacoesUsuarioCAPES.getInstance();

		if (info == null) {
			throw new UnsupportedOperationException(
					"O InformacoesUsuarioCAPES não foi instanciado!");
		}

		UsuarioBancoob usuarioBancoob = new UsuarioBancoob();
		usuarioBancoob.setCooperativa(info.getCooperativa());
		usuarioBancoob.setIdInstituicao(info.getIdInstituicao());
		usuarioBancoob.setIdUnidadeInstituicao(info.getIdUnidadeInstituicao());
		usuarioBancoob.setLogin(info.getLogin());
		usuarioBancoob.setPac(info.getPac());

		return usuarioBancoob;
	}

	/**
	 * Recupera o valor de fabricaDelegatesComum.
	 *
	 * @return o valor de fabricaDelegatesComum
	 */
	protected CAPESCadastroFabricaDelegates getFabricaDelegatesComum() {

		return this.fabricaDelegatesComum;
	}

	/**
	 * Recupera o valor de fabricaDelegatesIntegracao.
	 *
	 * @return o valor de fabricaDelegatesIntegracao
	 */
	protected CAPESIntegracaoFabricaDelegates getFabricaDelegatesIntegracao() {

		return this.fabricaDelegatesIntegracao;
	}

}
