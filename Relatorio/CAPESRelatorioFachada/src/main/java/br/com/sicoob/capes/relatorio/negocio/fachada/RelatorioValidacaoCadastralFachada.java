package br.com.sicoob.capes.relatorio.negocio.fachada;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoRegraValidacaoCadastralDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.ValidacaoCadastralRegraDelegate;
import br.com.sicoob.capes.comum.negocio.enums.FuncionalidadeValidacaoCadastralEnum;
import br.com.sicoob.capes.negocio.entidades.ValidacaoCadastralRegra;

/**
 * A Classe RelatorioValidacaoCadastralFachada.
 */
@RemoteService
public class RelatorioValidacaoCadastralFachada extends CAPESRelatorioComumFachada {
	
	/** A constante VALIDACAO_CADASTRAL. */
	private static final String VALIDACAO_CADASTRAL = "ValidacaoCadastral";

	/**
	 * Instancia um novo RelatorioValidacaoCadastralFachada.
	 */
	public RelatorioValidacaoCadastralFachada() {
		super(VALIDACAO_CADASTRAL);
	}
	
	/**
	 * Obter definicoes.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		CAPESCadastroFabricaDelegates fabricaCadastro = CAPESCadastroFabricaDelegates.getInstance();
		TipoRegraValidacaoCadastralDelegate tipoRegraValidacaoCadastralDelegate = fabricaCadastro
		        .criarTipoRegraValidacaoCadastralDelegate();

		retorno.getDados().put("funcionalidades", FuncionalidadeValidacaoCadastralEnum.values());
		retorno.getDados().put("tipoRegras", tipoRegraValidacaoCadastralDelegate.listar());

		return retorno;
	}
	
	/**
	 * Obter tipo erros.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterTipoErros(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		FuncionalidadeValidacaoCadastralEnum funcionalidade = (FuncionalidadeValidacaoCadastralEnum) dto.getDados().get("funcionalidade");
		
		if(funcionalidade != null){
			ValidacaoCadastralRegraDelegate delegate = CAPESCadastroFabricaDelegates.getInstance().criarValidacaoCadastralRegraDelegate();
			ConsultaDto<ValidacaoCadastralRegra> criterios = new ConsultaDto<ValidacaoCadastralRegra>();
			ValidacaoCadastralRegra filtro = new ValidacaoCadastralRegra(null);
			filtro.setFuncionalidade(funcionalidade);
			criterios.setFiltro(filtro);

			retorno.getDados().put("listaTipoErros", delegate.listar(criterios));
		}

		return retorno;
	}
	
}