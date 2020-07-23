package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.negocio.servicos.annotations.Cache;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.EntidadeCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.ResponsavelCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TransicaoPessoaDelegate;
import br.com.sicoob.capes.cadastro.negocio.dominio.autorizacao.CorrecaoAtualizacaoContext;
import br.com.sicoob.capes.cadastro.negocio.dominio.autorizacao.SalvamentoAtualizacaoContext;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAutorizacaoDTO;
import br.com.sicoob.capes.cadastro.negocio.enums.ParametroEnum;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum;
import br.com.sicoob.capes.cadastro.negocio.excecao.RegistroNaoEncontradoNegocioException;
import br.com.sicoob.capes.cadastro.negocio.servicos.ParametroServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.AutorizacaoCadastroServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.AutorizacaoCadastroServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.AutorizacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.DadosConfiguracaoFluxoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoCompartilhamentoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ParametroServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ResponsavelCadastroServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.vo.ParametroVO;
import br.com.sicoob.capes.cadastro.util.IntegracaoUtil;
import br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum;
import br.com.sicoob.capes.comum.negocio.vo.TipoDocumentoVO;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.CTAIntegracaoDelegate;
import br.com.sicoob.capes.integracao.negocio.delegates.GEDIntegracaoDelegate;
import br.com.sicoob.capes.integracao.negocio.delegates.GFTIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.AutorizacaoDocumento;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.DadosConfiguracaoFluxo;
import br.com.sicoob.capes.negocio.entidades.DocumentoComprobatorio;
import br.com.sicoob.capes.negocio.entidades.EntidadeCadastroBase;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Comprovavel;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.ResponsavelCadastro;

/**
 * EJB contendo servicos relacionados a AutorizacaoCadastro.
 */
@Stateless
@Local({ AutorizacaoCadastroServicoLocal.class })
@Remote({ AutorizacaoCadastroServicoRemote.class })
public class AutorizacaoCadastroServicoEJB extends CAPESCadastroServicoEJB implements
		AutorizacaoCadastroServicoRemote, AutorizacaoCadastroServicoLocal {

	/** O atributo responsavelCadastroServico. */
	@EJB(mappedName = "capes/cadastro/ResponsavelCadastroServico")
	private ResponsavelCadastroServicoLocal responsavelCadastroServico;

	/** O atributo grupoCompartilhamentoServico. */
	@EJB(mappedName = "capes/cadastro/GrupoCompartilhamentoServico")
	private GrupoCompartilhamentoServicoLocal grupoCompartilhamentoServico;

	/** O atributo configuracaoFluxoServico. */
	@EJB(mappedName = "capes/cadastro/DadosConfiguracaoFluxoServico")
	private DadosConfiguracaoFluxoServicoLocal configuracaoFluxoServico;

	/** O atributo autorizacaoServico. */
	@EJB(mappedName = "capes/cadastro/AutorizacaoServico")
	private AutorizacaoServicoLocal autorizacaoServico;
	
	/** O atributo parametroServico. */
	@EJB(mappedName = "capes/cadastro/AutorizacaoServico")
	private ParametroServicoLocal parametroServico;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DadosConfiguracaoFluxo obterConfiguracoesFluxo(Aprovavel aprovavel) throws BancoobException {
		return obterConfiguracoesFluxo(aprovavel, obterUsuario());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DadosConfiguracaoFluxo obterConfiguracoesFluxo(Aprovavel aprovavel, UsuarioBancoob usuario) throws BancoobException {

		getLogger().info("Obtendo os dados de configuracao do fluxo");

		Integer idInstituicao = Integer.valueOf(usuario.getIdInstituicao());

		// verifica se o usuario e da insitituicao responsavel pelo cadastro
		Boolean isResponsavel = isInstituicaoResponsavelCadastro(aprovavel, idInstituicao);

		// verifica se o usuario e um gestor
		Boolean isGestor = isGestor(usuario);

		// verifica se possui documentos associados
		Boolean possuiDocumento = aprovavel instanceof Comprovavel;
		if (possuiDocumento) {
			if(aprovavel instanceof Comprovavel){
				Comprovavel comprovavel = (Comprovavel) aprovavel;
				Set<DocumentoComprobatorio> documentos = comprovavel.getDocumentosComprobatorios();
				possuiDocumento = (documentos != null) && !documentos.isEmpty();
			}
		}

		DadosConfiguracaoFluxo config =
				this.configuracaoFluxoServico.obter(isResponsavel, isGestor, possuiDocumento);
		getLogger().info("Dados de configuracao do fluxo obtidos com sucesso: ",
				String.valueOf(config.getId()));
		getLogger().debug("Dados de configuracao do fluxo obtidos com sucesso: ",
				String.valueOf(config));
		return config;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings({ "unchecked" })
	public void marcarEmAlteracao(Aprovavel aprovavel, Integer idInstituicaoAtualizacao) throws BancoobException {
		obterDelegate(aprovavel).marcarEmAlteracao((EntidadeCadastroBase) aprovavel, idInstituicaoAtualizacao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void removerObjetoSessao(Aprovavel aprovavel) throws BancoobException {
		obterDelegate(aprovavel).removerObjetoSessao((EntidadeCadastroBase) aprovavel);
	}

	/**
	 * Obter fabrica delegates.
	 *
	 * @return CAPESCadastroFabricaDelegates
	 */
	private CAPESCadastroFabricaDelegates obterFabricaDelegates() {
		return CAPESCadastroFabricaDelegates.getInstance();
	}

	/**
	 * Obter delegate.
	 *
	 * @param aprovavel o valor de aprovavel
	 * @return EntidadeCadastroDelegate
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@SuppressWarnings({ "rawtypes"})
	private EntidadeCadastroDelegate obterDelegate(Aprovavel aprovavel) throws BancoobException {
		CAPESCadastroFabricaDelegates fabrica = obterFabricaDelegates();
		return obterEntidadeCadastroDelegate(aprovavel, fabrica);
	}

	/**
	 * Obter entidade cadastro delegate.
	 *
	 * @param aprovavel o valor de aprovavel
	 * @param fabricaDelegates o valor de fabrica delegates
	 * @return EntidadeCadastroDelegate
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@SuppressWarnings("rawtypes")
	private EntidadeCadastroDelegate obterEntidadeCadastroDelegate(Aprovavel aprovavel,
			CAPESCadastroFabricaDelegates fabricaDelegates) throws BancoobException {
		return (EntidadeCadastroDelegate) fabricaDelegates.criarDelegate(aprovavel.getClass());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Instituicao obterInstituicaoResponsavel(Aprovavel aprovavel) throws BancoobException {

		Instituicao instituicao = null;
		if (aprovavel.getTipoAutorizacao().isResponsavel()) {
			instituicao = obterInstituicaoUsuarioLogado();
		} else {
			CAPESCadastroFabricaDelegates fabrica = CAPESCadastroFabricaDelegates
					.getInstance();
			PessoaCompartilhamento pessoaCompartilhamento = aprovavel.getPessoaCompartilhamento();

			ResponsavelCadastroDelegate responsavelDelegate = fabrica
					.criarResponsavelCadastroDelegate();
			ResponsavelCadastro responsavel = responsavelDelegate.consultar(pessoaCompartilhamento);

			/* A instituição do responsável é a mesma da transição, porém o responsável não
			 * possui o objeto Instituição. Apenas o idInstituição
			*/
			TransicaoPessoaDelegate transicaoDelegate = fabrica.criarTransicaoPessoaDelegate();
			TransicaoPessoa transicao = transicaoDelegate.obterTransicaoPorPessoaInstituicao(
					pessoaCompartilhamento.getPessoa(), responsavel.getIdInstituicao());
			instituicao = transicao.getInstituicao();
		}
		return instituicao;
	}

	/**
	 * Verifica se é necessário a aprovação da entidade.
	 *
	 * @param entidadeAprovavel
	 *            a entidade sendo aprovada.
	 * @return um DTO com os dados carregados.
	 */
	@Override
	public boolean isNecessarioAutorizacao(Aprovavel entidadeAprovavel) throws BancoobException {

		Integer idInstituicaoUsuario = obterInstituicaoUsuarioLogado().getIdInstituicao();

		boolean isNecessarioAutorizacao = !isInstituicaoResponsavelCadastro(entidadeAprovavel,
				idInstituicaoUsuario);

		if (isNecessarioAutorizacao) {
			UsuarioBancoob usuario = obterUsuario();
			isNecessarioAutorizacao = !isGestor(usuario);
		}

		debug("A alteração precisa de autorização: " + isNecessarioAutorizacao);
		return isNecessarioAutorizacao;
	}

	/**
	 * Verifica se o usuário está no grupo autorizador do grupo de
	 * compartilhamento da instituição.
	 *
	 * @param usuario
	 *            o usuário
	 * @param nomeGrupo
	 *            O nome do grupo.
	 *
	 * @return se o usuário está no grupo autorizador do grupo de
	 *         compartilhamento da instituição.
	 */
	private boolean isUsuarioNoGrupoAutorizador(UsuarioBancoob usuarioBancoob, String nomeGrupo)
			throws BancoobException {
		CTAIntegracaoDelegate ctaIntegracaoDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarCTAIntegracaoDelegate();
		return ctaIntegracaoDelegate.isUsuarioNoGrupoAutorizador(usuarioBancoob, nomeGrupo);
	}

	/**
	 * Recuperar nome do grupo aprovador no CTA para o grupo de compartilhamento
	 * ao qual a instituição faz parte.
	 *
	 * @param idInstituicao
	 *            A instituição da qual se deseja o grupo do cta que é
	 *            aprovador.
	 * @return O nome do grupo aprovador no CTA para o grupo de compartilhamento
	 *         ao qual a instituição faz parte.
	 */
	private String recuperarNomeGrupoCTA(Integer idInstituicao) throws BancoobException {

		String nomeGrupoCta = null;
		GrupoCompartilhamento grupo = this.grupoCompartilhamentoServico
				.obterPorInstituicao(idInstituicao);

		if (grupo != null && grupo.getCompartilhamentoCadastro() != null) {
			nomeGrupoCta = grupo.getCompartilhamentoCadastro().getNomeGrupoCta();
		}

		return nomeGrupoCta;
	}
	
	/**
	 * Recuperar nome grupo cta cadastro instituicao.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @return String
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private String recuperarNomeGrupoCTACadastroInstituicao(Integer idInstituicao) throws BancoobException {
		String nomeGrupoCtaCadastroInstituicao = null;
		GrupoCompartilhamento grupo = this.grupoCompartilhamentoServico.obterPorInstituicao(idInstituicao);

		if (grupo != null && grupo.getCompartilhamentoCadastro() != null) {
			nomeGrupoCtaCadastroInstituicao = grupo.getCompartilhamentoCadastro().getNomeGrupoCtaCadastroInstituicao();
		}
		return nomeGrupoCtaCadastroInstituicao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isGestor(UsuarioBancoob usuario) throws BancoobException {
		Integer idInstituicaoUsuario = Integer.valueOf(usuario.getIdInstituicao());
		boolean isGestor = parametroServico.obterParametroValorBoolean(
				ParametroEnum.GESTOR.getCodigo(), idInstituicaoUsuario);
		return isGestor;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isGestorCadastroInstituicao(UsuarioBancoob usuario) throws BancoobException {
		Integer idInstituicaoUsuario = Integer.valueOf(usuario.getIdInstituicao());
		boolean isGestorCadastro = parametroServico.obterParametroValorBoolean(
				ParametroEnum.GESTOR_CADASTRO_INSTITUICAO.getCodigo(), idInstituicaoUsuario);
		return isGestorCadastro;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isUsuarioGestorCadastro() throws BancoobException {

		return isGestor(obterUsuario());
	}

	/**
	 * Verifica se a instituição do usuário é responsável pelo cadastro da
	 * pessoa.
	 *
	 * @param entidadeAprovavel
	 *            A entidade que precisa de aprovação ou não.
	 * @param idInstituicao
	 *            O identificador da instituição que será verificada.
	 * @return se a instituição do usuário é responsável pelo cadastro da
	 *         pessoa.
	 */
	@Override
	public boolean isInstituicaoResponsavelCadastro(Aprovavel entidadeAprovavel,
			final Integer idInstituicao) throws BancoobException {

		boolean isResponsavel = entidadeAprovavel.getTipoAutorizacao().isResponsavel();

		if (!isResponsavel) {
			PessoaCompartilhamento pessoa = entidadeAprovavel.getPessoaCompartilhamento();

			try {

				ResponsavelCadastro responsavel = this.responsavelCadastroServico.consultar(pessoa);
				Integer idInstituicaoResponsavel = responsavel.getIdInstituicao();
				isResponsavel = idInstituicao.equals(idInstituicaoResponsavel);
				getLogger().info("O cadastro da pessoa ", isResponsavel ? "" : "não ",
						"pertence a instituição do usuário logado: ", String.valueOf(idInstituicao));
			} catch (RegistroNaoEncontradoNegocioException e) {
				getLogger().alerta("Não existe instituição responsável pelo cadastro da pessoa: ",
						String.valueOf(pessoa != null ? pessoa.getIdPessoaCompartilhamento() : ""));
			}
		}

		return isResponsavel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Cache(nomeEntrada = "listaTiposDocumento", timeout = 5000, unicoCooperativa = false)
	public List<TipoDocumentoVO> listaTiposDocumento(String siglaTipoDocumentoPai)
			throws BancoobException {

		GEDIntegracaoDelegate gedIntegracaoDelegate = CAPESIntegracaoFabricaDelegates
				.getInstance().criarGEDIntegracaoDelegate();

		return gedIntegracaoDelegate.listarTipoDocumento();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <A extends Aprovavel> void gravarSolicitacaoAprovacao(
			A aprovavel, TipoOperacaoEnum tipoOperacao) throws BancoobException {

		TipoAutorizacaoEntidadeEnum tipoAutorizacao = TipoAutorizacaoEntidadeEnum.valueOf(aprovavel.getTipoAutorizacao());
		SalvamentoAtualizacaoContext context = new SalvamentoAtualizacaoContext(tipoAutorizacao);
		context.execute(aprovavel, tipoOperacao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluirAutorizacaoRejeitada(Autorizacao autorizacao) throws BancoobException {

		Long idAutorizacao = autorizacao.getIdAutorizacao();
		Set<AutorizacaoDocumento> autorizacaoDocumentos = autorizacao.getDocumentos();
		if ((autorizacaoDocumentos != null) && !autorizacaoDocumentos.isEmpty()) {
			getLogger().debug("Excluindo documentos: ", String.valueOf(idAutorizacao));
			List<Long> idDocumentos = new ArrayList<Long>();
			for (AutorizacaoDocumento autorizacaoDoc : autorizacaoDocumentos) {
				idDocumentos.add(autorizacaoDoc.getDocumento().getIdDocumento());
			}
			GEDIntegracaoDelegate gedIntegracaoDelegate = CAPESIntegracaoFabricaDelegates
					.getInstance().criarGEDIntegracaoDelegate();
			gedIntegracaoDelegate.marcaDocumentosExpurgoPorSistema(idDocumentos);
		}
		getLogger().debug("Excluindo a autorizacao: ", String.valueOf(idAutorizacao));
		this.autorizacaoServico.excluir(idAutorizacao);
	}

	/**
	 * {@inheritDoc}
	 * @param tipoOperacao
	 */
	@Override
	public <A extends CAPESEntidade<Long> & Aprovavel> void
			corrigirAutorizacao(Autorizacao autorizacao, A aprovavel,
					TipoOperacaoEnum tipoOperacao) throws BancoobException {

		TipoAutorizacaoEntidadeEnum tipoAutorizacao = TipoAutorizacaoEntidadeEnum.valueOf(autorizacao
				.getTipoAutorizacao());
		new CorrecaoAtualizacaoContext(tipoAutorizacao).execute(autorizacao, aprovavel, tipoOperacao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void corrigirDocumentos(Autorizacao autorizacao, Aprovavel aprovavel) throws BancoobException {

		if (aprovavel instanceof Comprovavel) {
			//deletando a lista de documentos
			autorizacao.setDocumentos(null);
			this.autorizacaoServico.deletarListaDocumentos(autorizacao.getIdAutorizacao());

			Comprovavel comprovavel = (Comprovavel) aprovavel;
			Set<AutorizacaoDocumento> documentosNovos = new HashSet<AutorizacaoDocumento>();

			// cria lista de documentos novos
			if (comprovavel.getDocumentosComprobatorios() != null) {
				getLogger().debug("Adicionando novos documentos...");
				for (DocumentoComprobatorio doc : comprovavel.getDocumentosComprobatorios()) {
					getLogger().debug("Documento -> IDAUTORIZACAO: " + autorizacao.getId() + "\t DOC: "	+ doc.getIdDocumento());
					AutorizacaoDocumento autorizacaoDocumento = new AutorizacaoDocumento(autorizacao.getId(), doc);
					autorizacaoDocumento.setAutorizacao(autorizacao);
					documentosNovos.add(autorizacaoDocumento);
				}
				
				//inserindo os novos documentos
				autorizacao.setDocumentos(documentosNovos);
				this.autorizacaoServico.alterar(autorizacao);
			}

		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean isPermitidoCorrigir(Autorizacao autorizacao) throws BancoobException {
		boolean permitido = false;
		if(autorizacao != null){
			permitido = autorizacao.getDevolvido() != null && autorizacao.getDevolvido();
			Integer instituicaoUsuario = obterInstituicaoUsuarioLogado().getIdInstituicao();
			if (instituicaoUsuario != null && autorizacao.getInstituicaoOrigem() != null && instituicaoUsuario.equals(autorizacao.getInstituicaoOrigem().getIdInstituicao())) {
				GFTIntegracaoDelegate gftIntegracaoDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarGFTIntegracaoDelegate();
				permitido &= gftIntegracaoDelegate.usuarioPossuiAtividadesNaoExecutadas(IntegracaoUtil
						.criarGFTIntegracaoDTO(autorizacao));
			} 
		}

		return permitido;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean isRegistroBloqueadoAlteracao(Aprovavel aprovavel) throws BancoobException {
		Autorizacao autorizacao = this.autorizacaoServico.obterPorEntidade(aprovavel);

		//Ainda não foi encaminhada para autorização ou está vigente
		if(autorizacao == null || autorizacao.getDataHoraSolicitacao() == null){
			return false;
		}

		return !isPermitidoCorrigir(autorizacao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<DocumentoComprobatorio> obterDocumentosComprobatorios(Comprovavel comprovavel)
			throws BancoobException {
		Set<DocumentoComprobatorio> listaDocumentos = new HashSet<DocumentoComprobatorio>();

		if (comprovavel != null && comprovavel.getIdInstituicaoAtualizacao() != null
				&& comprovavel.getDataHoraInicio() == null) {

			TipoAutorizacaoEntidadeEnum tipoAutorizacao = TipoAutorizacaoEntidadeEnum.valueOf(comprovavel.getTipoAutorizacao());

			ConsultaAutorizacaoDTO criterios = new ConsultaAutorizacaoDTO();
			criterios.setIdRegistro(comprovavel.getId());
			criterios.setTipoAutorizacao(tipoAutorizacao);

			Autorizacao autorizacao =
					this.autorizacaoServico.obterDocumentosComprobatoriosEmAutorizacao(criterios);

			if(autorizacao != null){
				for (AutorizacaoDocumento documento : autorizacao.getDocumentos()) {
					listaDocumentos.add(documento.getDocumento());
				}
			}
		}
		// Nessa fase, os documentos não poderão ser alterados. Nesse caso,
		// nem visualizados.
		/*
		 * else if (comprovavel != null &&
		 * comprovavel.getIdInstituicaoAtualizacao() != null && dataHoraInicio
		 * != null) { GEDIntegracaoDelegate delegateGED =
		 * fabrica.criarIntegracaoGEDDelegate(); List<Long> listaIds =
		 * delegateGED
		 * .listarIDDocumentosAprovados(comprovavel.getIdRegistroControlado(),
		 * comprovavel
		 * .getPessoaCompartilhamento().getPessoa().getTipoPessoa().getId());
		 *
		 * for (Long id : listaIds) { DocumentoComprobatorio docComprobatorio =
		 * new DocumentoComprobatorio(); docComprobatorio.setIdDocumento(id);
		 * listaDocumentos.add(docComprobatorio); } }
		 */

		return listaDocumentos;
	}

}
