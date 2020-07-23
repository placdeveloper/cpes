package br.com.sicoob.capes.cadastro.fachada.bem;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbr.negocio.dto.PesquisaDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.capes.cadastro.fachada.EntidadeCadastroFachada;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.BemDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.BemPessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.vo.PosseBemVO;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.bem.Bem;
import br.com.sicoob.capes.negocio.entidades.bem.BemPessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.bem.TipoClassificacaoBem;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Fachada para a tela de associação de pessoas aos bens e métodos auxiliares.
 * 
 * @author bruno.carneiro
 */
public class BemPessoaCompartilhamentoFachada extends EntidadeCadastroFachada<BemPessoaCompartilhamento> {

	private static final String CHAVE_PESSOA_COMPARTILHAMENTO = "pessoaCompartilhamento";
	private static final String CHAVE_BEM = "bem";

	/**
	 * Método contrutor.
	 */
	public BemPessoaCompartilhamentoFachada() {
		super("bemPessoaCompartilhamento");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemPessoaCompartilhamentoDelegate obterDelegate() {
		return CAPESCadastroFabricaDelegates.getInstance().criarBemPessoaCompartilhamentoDelegate();
	}
	
	/**
	 * Obtém o delegate de {@code Bem}
	 * 
	 * @return o {@code BemDelegate}
	 */
	private BemDelegate obterBemDelegate() {
		return CAPESCadastroFabricaDelegates.getInstance().criarBemDelegate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemPessoaCompartilhamento obterEntidade(RequisicaoReqDTO dto) {
		return (BemPessoaCompartilhamento) dto.getDados().get(chaveMapa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = super.obterDefinicoes(dto);
		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public DadosSelGeralDTO obterDadosSelecao(SelGeralReqDTO dto) throws BancoobException {
		dto.setNaoPaginar(true);
		ConsultaDto<BemPessoaCompartilhamento> criterios = montarConsultaDto(dto, BemPessoaCompartilhamento.class);
		DadosSelGeralDTO resultado = new DadosSelGeralDTO();
		resultado.getDados().put(NOME_PADRAO_LISTA, obterDelegate().pesquisar(criterios).getResultado());

		if (dto instanceof PesquisaDTO) {
			Object filtro = ((PesquisaDTO) dto).getFiltro();
			BemPessoaCompartilhamento bemPessoaCompartilhamento = (BemPessoaCompartilhamento) filtro;
			resultado.getDados().put("valores", obterBemDelegate().obterValoresBensPessoa(bemPessoaCompartilhamento.getPessoaCompartilhamento().getId()));
		}
		return resultado;
	}
	
	public RetornoDTO obterDadosListagem(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		try {
		
			Number idPessoaCompartilhamento = (Number) dto.getDados().get("idPessoaCompartilhamento");
			retorno.getDados().put(NOME_PADRAO_LISTA, obterDelegate().obterDadosListagem(idPessoaCompartilhamento.longValue()));
			retorno.getDados().put("valores", obterBemDelegate().obterValoresBensPessoa(idPessoaCompartilhamento.longValue()));

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
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
		return retorno;
	}
	
	public RetornoDTO obterDadosListagemParcerias(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		
		try {
			Number idPessoaCompartilhamento = (Number) dto.getDados().get("idPessoaCompartilhamento");
			retorno.getDados().put(NOME_PADRAO_LISTA, obterDelegate().obterDadosListagemParcerias(idPessoaCompartilhamento.longValue()));
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
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
		
		return retorno;
	}
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO incluirDados(RequisicaoReqDTO dto) throws BancoobException {
		try {
			Bem bem = (Bem) dto.getDados().get(CHAVE_BEM);
			Bem bemIncluido = (Bem) obterBemDelegate(bem).incluir(bem);
			return obterMapaRetorno(CHAVE_BEM, bemIncluido);
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
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO alterarDados(RequisicaoReqDTO dto) throws BancoobException {
		try {
			Bem bem = (Bem) dto.getDados().get(CHAVE_BEM);
			obterBemDelegate(bem).alterar(bem);
			return obterMapaRetorno(CHAVE_BEM, bem);
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
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
		return null;
	}

	/**
	 * Método que chama a pesquisa para o 'Componente' que exibe as posses do
	 * bem.
	 * 
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO consultarPossesBem(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		Number idBem = (Number) dto.getDados().get("idBem");
		TipoClassificacaoBem tipoClassificacao = (TipoClassificacaoBem) dto.getDados().get("tipoClassificacao");

		PosseBemVO posseVO = obterBemDelegate().obterPossesBem(idBem.longValue(), tipoClassificacao);

		retorno.getDados().put("listaProprietarios", posseVO.getProprietarios());
		retorno.getDados().put("listaParticipantes", posseVO.getParticipantes());

		return retorno;
	}

	/**
	 * Método que chama a consulta dos proprietarios do bem, para a exibição
	 * após o carregamento do bem pelo componente de pesquisa de bens.
	 * 
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterInformacoesBem(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		AutorizacaoCadastroDelegate autorizacaoCadastroDelegate = obterFabricaDelegates().criarAutorizacaoCadastroDelegate();

		Bem bem = (Bem) dto.getDados().get(CHAVE_BEM);
		bem.setDocumentosComprobatorios(autorizacaoCadastroDelegate.obterDocumentosComprobatorios(bem));

		// Adiciona a lista de proprietarios do bem.
		retorno.getDados().put(NOME_PADRAO_LISTA, obterBemDelegate().obterProprietariosBem(bem.getId()));

		if(bem.getId() != null) {
			// Verifica se o registro está bloqueado para alteração.
			retorno.getDados().put("isRegistroBloqueadoAlteracao", autorizacaoCadastroDelegate.isRegistroBloqueadoAlteracao(bem));
		}

		// Adiciona o bem com os documentos.
		retorno.getDados().put(CHAVE_BEM, bem);

		return retorno;
	}

	/**
	 * Método que adiciona o bem 'Sem patrimônio'.
	 * 
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO adicionarBemSemPatrimonio(RequisicaoReqDTO dto) throws BancoobException {
		try {
			PessoaCompartilhamento pessoaCompartilhamento = (PessoaCompartilhamento) dto.getDados().get(CHAVE_PESSOA_COMPARTILHAMENTO);
			obterBemDelegate().adicionarBemSemPatrimonio(pessoaCompartilhamento);
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
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
		return new RetornoDTO();
	}

	/**
	 * Método que adiciona o bem 'Recusou-se a informar'.
	 * 
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO adicionarBemRecusouInformar(RequisicaoReqDTO dto) throws BancoobException {
		PessoaCompartilhamento pessoaCompartilhamento = (PessoaCompartilhamento) dto.getDados().get(CHAVE_PESSOA_COMPARTILHAMENTO);
		obterBemDelegate().adicionarBemRecusouInformar(pessoaCompartilhamento);
		return new RetornoDTO();
	}

	/**
	 * Obtém o delegate correto do bem solicitado.
	 * 
	 * @param bem
	 * @return
	 * @throws BancoobException
	 */
	private CAPESCadastroCrudDelegate<CAPESEntidade<?>, ?> obterBemDelegate(Bem bem) throws BancoobException {
		return CAPESCadastroFabricaDelegates.getInstance().criarDelegate(bem.getClass());
	}

	/**
	 * Obtém o mapa com o {@code RetornoDTO}
	 * 
	 * @param chave
	 * @param entidade
	 * @return
	 */
	protected RetornoDTO obterMapaRetorno(String chave, Bem entidade) {
		RetornoDTO retornoDTO = new RetornoDTO();
		Map<String, Object> dados = new HashMap<String, Object>();
		dados.put(chave, entidade);
		retornoDTO.setDados(dados);
		return retornoDTO;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO excluirDados(RequisicaoReqDTO dto) throws BancoobException {
		try {
			Number idBem = (Number) dto.getDados().get("idBem");
			Number idBemPessoaCompartilhamento = (Number) dto.getDados().get("idBemPessoaCompartilhamento");
			Boolean bemInterno = (Boolean) dto.getDados().get("bemInterno");
			if (idBem == null | idBemPessoaCompartilhamento == null) {
				lancarExcessaoValidarEntidade("ao excluir o Bem");
			}
			obterDelegate().excluir(idBem.longValue(), idBemPessoaCompartilhamento.longValue(), bemInterno);
			return obterMapRetorno(this.chaveMapa, new BemPessoaCompartilhamento());
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
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
		return null;
	}
	
	/**
	 * Faz a validação/compartilhamento do cadastro dos associados ao bem;
	 * 
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO verificarCompartilhamentoAssociados(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		try {
			
			Number idBem = (Number) dto.getDados().get("idBem");
			obterBemDelegate().verificarCompartilhamentoAssociados(idBem.longValue());
			
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
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
		return retorno;
	}
	
	public RetornoDTO obterBem(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		try {
			Number idBem = (Number) dto.getDados().get("idBem");
			Bem bem = obterBemDelegate().obter(idBem.longValue());
			if (bem != null) {
				AutorizacaoCadastroDelegate autorizacaoCadastroDelegate = obterFabricaDelegates().criarAutorizacaoCadastroDelegate();
				bem.setDocumentosComprobatorios(autorizacaoCadastroDelegate.obterDocumentosComprobatorios(bem));
			}
			retorno.getDados().put("bem", bem);
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
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
		return retorno;
	}
	
	public RetornoDTO verificarBemEmGarantia(RequisicaoReqDTO dto) throws BancoobException {
		Number idBem = (Number) dto.getDados().get("idBem");
		RetornoDTO retorno = new RetornoDTO();
		retorno.getDados().put("bemEmGarantia", obterBemDelegate().verificarBemEmGarantia(idBem.longValue()));
		return retorno;
	}
	
	/**
	 * Obtém os tipos de classificação do bem.
	 * 
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterClassificacoesBem(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		try {
			retorno.getDados().put("listaTipoClassificacaoBem", obterFabricaDelegates().criarTipoClassificacaoBemDelegate().listar());
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
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
		return retorno;
	}
	
	/**
	 * Desbloqueia o bem solicitado.
	 * 
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO desbloquearBem(RequisicaoReqDTO dto) throws BancoobException {
		try {
			Number idBem = (Number) dto.getDados().get("idBem");
			obterBemDelegate().desbloquearBem(idBem.longValue());
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
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
		return new RetornoDTO();
	}
	
}