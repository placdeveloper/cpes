package br.com.sicoob.capes.relatorio.negocio.fachada;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;

/**
 * A Classe FichaCadastralFachada.
 */
@RemoteService
public class FichaCadastralFachada extends CAPESRelatorioComumFachada {

	/** A constante RELATORIO_FICHA_CADASTRAL. */
	private static final String RELATORIO_FICHA_CADASTRAL = "relatorioFichaCadastral";

	/**
	 * Instancia um novo FichaCadastralFachada.
	 */
	public FichaCadastralFachada() {
		super(RELATORIO_FICHA_CADASTRAL);
	}

	/**
	 * Imprimir cadastro.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO imprimirCadastro(RequisicaoReqDTO dto)
			throws BancoobException {

//		RelatorioFichaCadastralDelegate delegate = obterFabricaDelegates().criarRelatorioFichaCadastralDelegate();
//		PessoaCompartilhamento pessoaCompartilhamento = (PessoaCompartilhamento) dto.getDados().get("pessoa");
//		CamposFichaCadastralVO vo = (CamposFichaCadastralVO) dto.getDados().get("vo");
//		
//		ContextoHttp.getInstance().adicionarContexto(RELATORIO_FICHA_CADASTRAL,
//				delegate.gerarRelatorio(pessoaCompartilhamento, vo));
		return new RetornoDTO();	
	}

	/**
	 * Imprimir em branco.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO imprimirEmBranco(RequisicaoReqDTO dto)
			throws BancoobException {
		
//		RelatorioFichaCadastralEmBrancoDelegate delegate = obterFabricaDelegates().criarRelatorioFichaCadastralEmBrancoDelegate();
//		PessoaCompartilhamento pessoaCompartilhamento = (PessoaCompartilhamento) dto.getDados().get("pessoa");
//		CamposFichaCadastralVO vo = (CamposFichaCadastralVO) dto.getDados().get("vo");
//		
//		ContextoHttp.getInstance().adicionarContexto(RELATORIO_FICHA_CADASTRAL,
//				delegate.gerarRelatorio(pessoaCompartilhamento, vo));		
		
		return new RetornoDTO();
	}
	
	/**
	 * Imprimir novo cadastro.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO imprimirNovoCadastro(RequisicaoReqDTO dto)throws BancoobException {
//		CamposFichaCadastralVO vo = (CamposFichaCadastralVO) dto.getDados().get("vo");
		
//		if(vo.getEmBranco()){
//			return imprimirNovaFichaEmBranco(dto);
//		}else{
//			RelatorioFichaCadastralDelegate delegate = obterFabricaDelegates().criarRelatorioFichaCadastralDelegate();
//			PessoaCompartilhamento pessoaCompartilhamento = (PessoaCompartilhamento) dto.getDados().get("pessoa");
//			
//			ContextoHttp.getInstance().adicionarContexto(RELATORIO_FICHA_CADASTRAL, 
//					delegate.gerarNovoRelatorio(pessoaCompartilhamento, vo));
			
			return new RetornoDTO();	
//		}
	}
	
	/**
	 * Imprimir nova ficha em branco.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO imprimirNovaFichaEmBranco(RequisicaoReqDTO dto)throws BancoobException {
//		RelatorioFichaCadastralEmBrancoDelegate delegate = obterFabricaDelegates().criarRelatorioFichaCadastralEmBrancoDelegate();
//		PessoaCompartilhamento pessoaCompartilhamento = (PessoaCompartilhamento) dto.getDados().get("pessoa");
//		CamposFichaCadastralVO vo = (CamposFichaCadastralVO) dto.getDados().get("vo");
//		vo.setTipoPessoa(pessoaCompartilhamento.getPessoa().getTipoPessoa().getCodTipoPessoa().intValue());
//		ContextoHttp.getInstance().adicionarContexto(RELATORIO_FICHA_CADASTRAL,
//				delegate.gerarNovoRelatorio(pessoaCompartilhamento, vo));		
		
		return new RetornoDTO();
	}

}
