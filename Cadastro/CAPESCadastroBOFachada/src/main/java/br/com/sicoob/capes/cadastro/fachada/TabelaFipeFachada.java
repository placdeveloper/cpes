package br.com.sicoob.capes.cadastro.fachada;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.ITXIntegracaoDelegate;
import br.com.sicoob.capes.integracao.negocio.enums.TipoVeiculoFipeEnum;

/**
 * Classe com as chamadas do componente da tabela FIPE.
 * 
 * @author Bruno.Carneiro
 */
@RemoteService
public class TabelaFipeFachada extends CAPESCadastroBOFachada {

	private static final transient ITXIntegracaoDelegate itxDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarITXIntegracaoDelegate();

	/**
	 * Obtém os tipos de veículos para exibição na combo do componente.
	 * 
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterTiposVeiculo(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		retorno.getDados().put("listaTiposVeiculo", TipoVeiculoFipeEnum.values());
		return retorno;
	}

	/**
	 * Obtém as marcas dos veículos para exibição na combo do componente.
	 * 
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterMarcas(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		TipoVeiculoFipeEnum tipoVeiculo = (TipoVeiculoFipeEnum) dto.getDados().get("tipoVeiculo");
		retorno.getDados().put("listaMarcas", itxDelegate.obterMarcasFIPE(tipoVeiculo.getValor()));

		return retorno;
	}

	/**
	 * Obtém os veículos para exibição na combo do componente.
	 * 
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterVeiculos(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		TipoVeiculoFipeEnum tipoVeiculo = (TipoVeiculoFipeEnum) dto.getDados().get("tipoVeiculo");
		Integer idMarca = (Integer) dto.getDados().get("idMarca");
		retorno.getDados().put("listaVeiculos", itxDelegate.obterVeiculosFIPE(tipoVeiculo.getValor(), idMarca));

		return retorno;
	}

	/**
	 * Obtém os modelos dos veículos para exibição na combo do componente.
	 * 
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterModelos(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		TipoVeiculoFipeEnum tipoVeiculo = (TipoVeiculoFipeEnum) dto.getDados().get("tipoVeiculo");
		Integer idMarca = (Integer) dto.getDados().get("idMarca");
		Integer idVeiculo = (Integer) dto.getDados().get("idVeiculo");
		retorno.getDados().put("listaModelos", itxDelegate.obterModelosFIPE(tipoVeiculo.getValor(), idMarca, idVeiculo));

		return retorno;
	}

	/**
	 * Obtém o detalhes do modelo selecionado.
	 * 
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterDetalhes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		TipoVeiculoFipeEnum tipoVeiculo = (TipoVeiculoFipeEnum) dto.getDados().get("tipoVeiculo");
		Integer idMarca = (Integer) dto.getDados().get("idMarca");
		Integer idVeiculo = (Integer) dto.getDados().get("idVeiculo");
		Integer idModelo = (Integer) dto.getDados().get("idModelo");
		retorno.getDados().put("detalhes", itxDelegate.obterDetalheFIPE(tipoVeiculo.getValor(), idMarca, idVeiculo, idModelo));

		return retorno;
	}

}