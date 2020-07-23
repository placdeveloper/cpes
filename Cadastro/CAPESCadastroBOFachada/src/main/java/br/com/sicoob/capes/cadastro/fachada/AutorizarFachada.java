package br.com.sicoob.capes.cadastro.fachada;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.persistence.QueryTimeoutException;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.helper.AutorizarHelper;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizarDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.ParametroDelegate;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAutorizacaoDTO;
import br.com.sicoob.capes.cadastro.negocio.enums.ParametroEnum;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum;
import br.com.sicoob.capes.cadastro.negocio.vo.CamposTelaVO;
import br.com.sicoob.capes.cadastro.negocio.vo.EncaminharAutorizacaoVO;
import br.com.sicoob.capes.cadastro.util.IntegracaoUtil;
import br.com.sicoob.capes.cadastro.util.SerializacaoUtils;
import br.com.sicoob.capes.comum.negocio.vo.OcorrenciaAtividadeVO;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.GEDIntegracaoDelegate;
import br.com.sicoob.capes.integracao.negocio.delegates.GFTIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.DocumentoComprobatorio;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * Fachada para as autorizações
 *
 * @author Rodrigo.Chaves
 */
@RemoteService
public class AutorizarFachada extends CAPESCadastroBOFachada {

	/** A constante AUTORIZACOES. */
	private static final String AUTORIZACOES = "autorizacoes";

	/** O atributo autorizarDelegate. */
	private AutorizarDelegate autorizarDelegate = CAPESCadastroFabricaDelegates
			.getInstance().criarAutorizarDelegate();

	/** O atributo delegateGFT. */
	private GFTIntegracaoDelegate delegateGFT = CAPESIntegracaoFabricaDelegates
			.getInstance().criarGFTIntegracaoDelegate();

	/** O atributo delegateGED. */
	private GEDIntegracaoDelegate delegateGED = CAPESIntegracaoFabricaDelegates
			.getInstance().criarGEDIntegracaoDelegate();
	
	/** O atributo autorizacaoDelegate. */
	private AutorizacaoDelegate autorizacaoDelegate = CAPESCadastroFabricaDelegates
			.getInstance().criarAutorizacaoDelegate();

	/** O atributo parametroDelegate. */
	private ParametroDelegate parametroDelegate = CAPESCadastroFabricaDelegates
			.getInstance().criarParametroDelegate();
	
	/** A constante LOGGER. */
	protected static final SicoobLoggerPadrao LOGGER = SicoobLoggerPadrao
			.getInstance(AutorizarFachada.class);

	/**
	 * {@inheritDoc}
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {

		RetornoDTO retorno = new RetornoDTO();
		Map<String, Object> dados = retorno.getDados();
		dados.put("listaTipoAutorizacao", listarTipoAutorizacao());
		dados.put("exibirAbaAutoAtendimento", obterParametroExibirAbaAutoAtendimento());
		return retorno;
	}

	private boolean obterParametroExibirAbaAutoAtendimento() {
		Integer idInstituicaoUsuario = Integer.valueOf(obterInstituicaoUsuarioLogado().getIdInstituicao());
		boolean parametroValor = parametroDelegate.obterParametroValorBoolean(
				ParametroEnum.EXIBIR_ABA_AUTOATENDIMENTO_NA_AUTORIZACAO.getCodigo(), idInstituicaoUsuario);
		return parametroValor;
	}

	/**
	 * {@inheritDoc}
	 */
	public DadosSelGeralDTO obterDadosSelecaoAutorizar(SelGeralReqDTO dto) throws BancoobException {

		ConsultaAutorizacaoDTO criterios = montarConsultaDto(dto);
		Autorizacao filtro = (Autorizacao) criterios.getFiltro();

		// autorizações criadas PARA a intituição do usuário
		Instituicao destino =
				filtro.getInstituicaoDestino() == null ? new Instituicao() : filtro
						.getInstituicaoDestino();
		destino.setIdInstituicao(obterInstituicaoUsuarioLogado().getIdInstituicao());
		filtro.setInstituicaoDestino(destino);

		ConsultaDto<Autorizacao> resposta =
				this.autorizarDelegate.pesquisarAutorizacoesPendentes(criterios);
		return montarResultado(resposta);
	}

	/**
	 * {@inheritDoc}
	 */
	public DadosSelGeralDTO obterDadosSelecaoDevolvidas(SelGeralReqDTO dto) throws BancoobException {

		ConsultaAutorizacaoDTO criterios = montarConsultaDto(dto);
		Autorizacao filtro = (Autorizacao) criterios.getFiltro();

		// autorizações criadas PELA intituição do usuário
		filtro.setInstituicaoOrigem(obterInstituicaoUsuarioLogado());
		ConsultaDto<Autorizacao> resposta =
				this.autorizarDelegate.pesquisarAutorizacoesDevolvidas(criterios);
		return montarResultado(resposta);
	}

	/**
	 * {@inheritDoc}
	 */
	public DadosSelGeralDTO obterDadosSelecaoEncaminhadas(SelGeralReqDTO dto)
			throws BancoobException {

		ConsultaAutorizacaoDTO criterios = montarConsultaDto(dto);
		Autorizacao filtro = (Autorizacao) criterios.getFiltro();

		AutorizacaoCadastroDelegate delegate =
				CAPESCadastroFabricaDelegates.getInstance()
						.criarAutorizacaoCadastroDelegate();
		if (delegate.isGestor(obterUsuarioLogado())) {
			criterios.setTipoProcura("GESTOR");
		}

		// autorizações criadas PELA intituição do usuário
		filtro.setInstituicaoOrigem(obterInstituicaoUsuarioLogado());
		ConsultaDto<Autorizacao> resposta =
				this.autorizarDelegate.pesquisarAutorizacoesEncaminhadas(criterios);
		return montarResultado(resposta);
	}

	/**
	 * Obter dados selecao nao encaminhadas.
	 *
	 * @param dto o valor de dto
	 * @return DadosSelGeralDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public DadosSelGeralDTO obterDadosSelecaoNaoEncaminhadas(SelGeralReqDTO dto)
			throws BancoobException {
		ConsultaAutorizacaoDTO criterios = montarConsultaDto(dto);
		Autorizacao filtro = (Autorizacao) criterios.getFiltro();

		// autorizações criadas PELA intituição do usuário
		filtro.setInstituicaoOrigem(obterInstituicaoUsuarioLogado());
		return montarResultado(this.autorizarDelegate.pesquisarAutorizacoesNaoEncaminhadas(criterios));
	}

	/**
	 * Obter instituicao usuario logado.
	 *
	 * @return Instituicao
	 */
	private Instituicao obterInstituicaoUsuarioLogado() {

		UsuarioBancoob usuario = obterUsuarioLogado();

		Instituicao instituicao = new Instituicao();
		instituicao.setIdInstituicao(Integer.valueOf(usuario.getIdInstituicao()));
		instituicao.setIdUnidadeInst(Integer.valueOf(usuario.getIdUnidadeInstituicao()));
		return instituicao;
	}

	/**
	 * {@inheritDoc}
	 */
	public RetornoDTO obterHistorico(RequisicaoReqDTO dto) throws BancoobException {

		RetornoDTO resultado = new RetornoDTO();
		Autorizacao autorizacaoTela = (Autorizacao) dto.getDados().get("item");
		Autorizacao autorizacao = autorizacaoDelegate.obterAutorizacaoNaoNula(autorizacaoTela.getId());
		resultado.getDados().put("historico", this.delegateGFT.listarHistoricoAutorizacao(IntegracaoUtil.criarGFTIntegracaoDTO(autorizacao)));
		return resultado;
	}

	/**
	 * {@inheritDoc}
	 */
	public RetornoDTO obterDados(RequisicaoReqDTO requisicao) throws BancoobException {

		Autorizacao autorizacaoTela = (Autorizacao) requisicao.getDados().get("item");
		
		Autorizacao autorizacao = autorizacaoDelegate.obterAutorizacaoNaoNula(autorizacaoTela.getId());
		
		// obtem a delegate
		TipoAutorizacaoEntidadeEnum tipoAutorizacao = TipoAutorizacaoEntidadeEnum.valueOf(autorizacao
				.getTipoAutorizacao());

		// obtem as entidades
		Aprovavel registroAntigo =
				obterRegistro(tipoAutorizacao, autorizacao.getIdRegistroAntigo());
		Aprovavel registroNovo = obterRegistroNovo(autorizacao);

		CamposTelaVO camposTela = AutorizarHelper.gerarCamposTela(registroAntigo, registroNovo);

		List<OcorrenciaAtividadeVO> atividades =
				this.delegateGFT.obterAtividadesAtuais(autorizacao.getIdRegistroControlado(), autorizacao.getSiglaProcesso());
		List<OcorrenciaAtividadeVO> historico =
				this.delegateGFT.listarHistoricoAutorizacao(IntegracaoUtil.criarGFTIntegracaoDTO(autorizacao));
		List<DocumentoComprobatorio> documentosVigentes =
				obterDocumentosAprovados(autorizacao.getIdRegistroControlado(), autorizacao
						.getPessoa().getTipoPessoa().getCodTipoPessoa());

		// monta o retorno
		RetornoDTO retorno = new RetornoDTO();
		retorno.getDados().put("camposTela", camposTela);
		retorno.getDados().put("atividades", getListaNaoNula(atividades));
		retorno.getDados().put("historico", getListaNaoNula(historico));
		retorno.getDados().put("documentosVigentes", getListaNaoNula(documentosVigentes));
		return retorno;
	}

	/**
	 * Recupera o valor de listaNaoNula.
	 *
	 * @param <S> o tipo generico
	 * @param lista o valor de lista
	 * @return o valor de listaNaoNula
	 */
	private <S> List<S> getListaNaoNula(List<S> lista) {
		return lista == null ? new ArrayList<S>() : lista;
	}

	/**
	 * Obter registro.
	 *
	 * @param tipo o valor de tipo
	 * @param id o valor de id
	 * @return Aprovavel
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Aprovavel obterRegistro(TipoAutorizacaoEntidadeEnum tipo, Long id) throws BancoobException {
		Aprovavel entidade = null;
		if (id != null) {
			CAPESCadastroFabricaDelegates fabrica = CAPESCadastroFabricaDelegates.getInstance();
			entidade = (Aprovavel) fabrica.criarDelegate(tipo.getTipo()).obter(id);
		}
		return entidade;
	}

	/**
	 * Obter registro novo.
	 *
	 * @param autorizacao o valor de autorizacao
	 * @return Aprovavel
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Aprovavel obterRegistroNovo(Autorizacao autorizacao) throws BancoobException {

		Aprovavel aprovavel = null;
		TipoAutorizacaoEntidadeEnum tipoAutorizacao = TipoAutorizacaoEntidadeEnum.valueOf(autorizacao
				.getTipoAutorizacao());
		if (StringUtils.isNotBlank(autorizacao.getAlteracao())) {
			aprovavel = (Aprovavel) SerializacaoUtils.deserializarJson(tipoAutorizacao.getTipo(),
					autorizacao.getAlteracao());
		} else {
			aprovavel = obterRegistro(tipoAutorizacao, autorizacao.getIdRegistroNovo());
		}
		return aprovavel;
	}

	/**
	 * {@inheritDoc}
	 */
	public RetornoDTO executarProcedimento(RequisicaoReqDTO dto) throws BancoobException {

		Autorizacao autorizacaoTela = (Autorizacao) dto.getDados().get("item");
		Autorizacao autorizacao = autorizacaoDelegate.obterAutorizacaoNaoNula(autorizacaoTela.getId());

		String justificativa = dto.getDados().get("justificativa").toString();
		OcorrenciaAtividadeVO procedimento = (OcorrenciaAtividadeVO) dto.getDados().get("procedimento");
		this.autorizarDelegate.executarProcedimento(autorizacao, procedimento, justificativa);
		return new RetornoDTO();
	}

	/**
	 * Listar tipo autorizacao.
	 *
	 * @return TipoAutorizacaoEntidadeEnum[]
	 */
	private Object[] listarTipoAutorizacao() {
		return TipoAutorizacaoEntidadeEnum.values();
	}

	/**
	 * Montar consulta dto.
	 *
	 * @param dto o valor de dto
	 * @return ConsultaAutorizacaoDTO
	 */
	private ConsultaAutorizacaoDTO montarConsultaDto(SelGeralReqDTO dto) {

		ConsultaAutorizacaoDTO criterios = new ConsultaAutorizacaoDTO();
		popularConsultaDto(criterios, dto);
		return criterios;
	}

	/**
	 * @see IAutorizar#obterDadosSelecaoAptoEncaminhar(RequisicaoReqDTO)
	 */
	public RetornoDTO obterDadosSelecaoAptoEncaminhar(RequisicaoReqDTO dto) throws BancoobException{
		RetornoDTO retorno = new RetornoDTO();
		try {
			ConsultaAutorizacaoDTO criterios = new ConsultaAutorizacaoDTO();
			Pessoa pessoa = (Pessoa)dto.getDados().get("pessoa");
			criterios.setIdPessoaSelecionada(pessoa.getId());

			List<Autorizacao> autorizacoes = this.autorizarDelegate.pesquisarAutorizacoesAptasEncaminhar(criterios);
			List<EncaminharAutorizacaoVO> camposTela = AutorizarHelper.gerarCamposTela(autorizacoes);
			retorno.getDados().put(AUTORIZACOES, camposTela);

		} catch (QueryTimeoutException e) {
			registrarLogQueryTimeOutException(e, dto);
		} catch (NullPointerException e) {
			registrarLogNullPointerException(e, dto);
		} catch (ViolacaoIntegridadeException e) {
			registrarLogViolacaoIntegridadeException(e, dto);
		} catch (EJBTransactionRolledbackException e) {
			registrarLogTransactionRolledbackException(e, dto);
		} catch (EntityNotFoundException e) {
			registrarLogEntityNotFoundException(e, dto);
		} catch (PersistenceException e) {
			registrarLogPersistenceException(e, dto);		
		} catch (NegocioException e) {
			throw(e);
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		}
		return retorno;
	}

	/**
	 * @see IAutorizar#encaminharAutorizacao(Autorizacao)
	 */
	public RetornoDTO encaminharAutorizacoes(RequisicaoReqDTO dto)throws BancoobException{
		try {
			ConsultaAutorizacaoDTO criterios = new ConsultaAutorizacaoDTO();
			criterios.setIdInstituicaoUsuario(obterInstituicaoUsuarioLogado().getIdInstituicao());
			Pessoa pessoa = (Pessoa)dto.getDados().get("pessoa");
			criterios.setIdPessoaSelecionada(pessoa.getId());

			boolean autorizacoesEncaminhadas = false;
			if (!this.autorizarDelegate.atualizarResponsavel(criterios)) {
				autorizacoesEncaminhadas = this.autorizarDelegate.encaminharAutorizacoes(criterios);
			}

			RetornoDTO retorno = obterDadosSelecaoAptoEncaminhar(dto);
			if(retorno.getDados().get(AUTORIZACOES) == null){
				retorno.getDados().put(AUTORIZACOES, new ArrayList<EncaminharAutorizacaoVO>());
			}
			retorno.getDados().put("autorizacoesEncaminhadas", autorizacoesEncaminhadas);

			return retorno;
		} catch (QueryTimeoutException e) {
			registrarLogQueryTimeOutException(e, dto);
		} catch (NullPointerException e) {
			registrarLogNullPointerException(e, dto);
		} catch (ViolacaoIntegridadeException e) {
			registrarLogViolacaoIntegridadeException(e, dto);
		} catch (EJBTransactionRolledbackException e) {
			registrarLogTransactionRolledbackException(e, dto);
		} catch (EntityNotFoundException e) {
			registrarLogEntityNotFoundException(e, dto);
		} catch (PersistenceException e) {
			registrarLogPersistenceException(e, dto);
		} catch (NegocioException e) {
			throw(e);
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		}
		return null;
	}

	/**
	 * Obter detalhamento gedgft.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDetalhamentoGEDGFT(RequisicaoReqDTO dto) throws BancoobException{
		RetornoDTO resultado = new RetornoDTO();

		try {
			String idRegistroControlado = (String) dto.getDados().get("idRegistroControlado");
			Integer idTipoPessoaSelecionada = (Integer) dto.getDados().get("idTipoPessoaSelecionada");

			List<DocumentoComprobatorio> documentos =
					obterDocumentosAprovados(idRegistroControlado, idTipoPessoaSelecionada.shortValue());

			resultado.getDados().put("listaGFT", this.delegateGFT.recuperarUltimaOcorrenciaFinalizada(idRegistroControlado));
			resultado.getDados().put("listaGED", documentos);
		} catch (QueryTimeoutException e) {
			registrarLogQueryTimeOutException(e, dto);
		} catch (NullPointerException e) {
			registrarLogNullPointerException(e, dto);
		} catch (ViolacaoIntegridadeException e) {
			registrarLogViolacaoIntegridadeException(e, dto);
		} catch (EJBTransactionRolledbackException e) {
			registrarLogTransactionRolledbackException(e, dto);
		} catch (EntityNotFoundException e) {
			registrarLogEntityNotFoundException(e, dto);
		} catch (PersistenceException e) {
			registrarLogPersistenceException(e, dto);
		} catch (NegocioException e) {
			throw(e);
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		}

		return resultado;
	}

	/**
	 * Obter documentos aprovados.
	 *
	 * @param idRegistroControlado o valor de id registro controlado
	 * @param tipoPessoa o valor de tipo pessoa
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected List<DocumentoComprobatorio> obterDocumentosAprovados(String idRegistroControlado,
			Short tipoPessoa) throws BancoobException {

		List<Long> ids = this.delegateGED.listarIDDocumentosAprovados(idRegistroControlado,
				tipoPessoa);
		return criarListaDocumentosComprobatorios(ids);
	}

	/**
	 * Criar lista documentos comprobatorios.
	 *
	 * @param ids o valor de ids
	 * @return List
	 */
	private List<DocumentoComprobatorio> criarListaDocumentosComprobatorios(List<Long> ids) {
		List<DocumentoComprobatorio> listaDocumentoComprobatorio = new ArrayList<DocumentoComprobatorio>();

		int i = 1;
		for (Long idDocumento : ids) {
			DocumentoComprobatorio docVO = new DocumentoComprobatorio();

			docVO.setIdDocumento(idDocumento);
			docVO.setNome("Documento " + i++);

			listaDocumentoComprobatorio.add(docVO);
		}

		return listaDocumentoComprobatorio;
	}
	
	/**
	 * Excluir autorizacao encaminhada.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO excluirAutorizacaoEncaminhada(RequisicaoReqDTO dto) throws BancoobException {
		EncaminharAutorizacaoVO autorizacao = (EncaminharAutorizacaoVO) dto.getDados().get("autorizacao");
		
		try {
			autorizarDelegate.excluirAutorizacaoEncaminhada(autorizacao);
		} catch (QueryTimeoutException e) {
			registrarLogQueryTimeOutException(e, dto);
		} catch (NullPointerException e) {
			registrarLogNullPointerException(e, dto);
		} catch (ViolacaoIntegridadeException e) {
			registrarLogViolacaoIntegridadeException(e, dto);
		} catch (EJBTransactionRolledbackException e) {
			registrarLogTransactionRolledbackException(e, dto);
		} catch (EntityNotFoundException e) {
			registrarLogEntityNotFoundException(e, dto);
		} catch (PersistenceException e) {
			registrarLogPersistenceException(e, dto);
		} catch (NegocioException e) {
			throw(e);
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		}
		
		RetornoDTO retorno = obterDadosSelecaoAptoEncaminhar(dto);
		if (retorno.getDados().get(AUTORIZACOES) == null) {
			retorno.getDados().put(AUTORIZACOES, new ArrayList<EncaminharAutorizacaoVO>());
		}

		return retorno;
	}
	

	/**
	 * {@inheritDoc}
	 */
	public DadosSelGeralDTO obterDadosSelecaoAutorizarAutoAtendimento(SelGeralReqDTO dto) throws BancoobException {

		ConsultaAutorizacaoDTO criterios = montarConsultaDto(dto);
		Autorizacao filtro = (Autorizacao) criterios.getFiltro();

		// autorizações criadas PARA a intituição do usuário
		Instituicao destino = filtro.getInstituicaoDestino() == null ? new Instituicao() : filtro.getInstituicaoDestino();
		destino.setIdInstituicao(obterInstituicaoUsuarioLogado().getIdInstituicao());
		filtro.setInstituicaoDestino(destino);

		ConsultaDto<Autorizacao> resposta = this.autorizarDelegate.pesquisarAutorizacoesPendentesAutoAtendimento(criterios);
				
		return montarResultado(resposta);
	}

}