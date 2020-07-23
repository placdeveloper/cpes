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
	 * Obt�m os tipos de ve�culos para exibi��o na combo do componente.
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
	 * Obt�m as marcas dos ve�culos para exibi��o na combo do componente.
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
	 * Obt�m os ve�culos para exibi��o na combo do componente.
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
	 * Obt�m os modelos dos ve�culos para exibi��o na combo do componente.
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
	 * Obt�m o detalhes do modelo selecionado.
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