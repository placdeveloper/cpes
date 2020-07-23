/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.MapaTipoAnotacaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.MapaTipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;

/**
 * Business delegate para os mapas de tipos de anotação.  
 */
public class MapaTipoAnotacaoDelegate extends
		CAPESCadastroCrudDelegate<MapaTipoAnotacao, MapaTipoAnotacaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected MapaTipoAnotacaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarMapaTipoAnotacaoServico();
	}

	/**
	 * 
	 * @param codigoTipoOrigem
	 * @param nomeOrigem
	 * @param nomeTipoConsulta
	 * @return
	 * @throws BancoobException
	 */
	public MapaTipoAnotacao obterTipoAnotacaoAnotacaoExterna(MapaTipoAnotacao mapaTipoAnotacao) throws BancoobException{
		return getServico().obterTipoAnotacaoAnotacaoExterna(mapaTipoAnotacao);
	}
	
	/**
	 * Obter mapas tipo anotacao por tipo anotacao.
	 *
	 * @param tipoAnotacao o valor de tipo anotacao
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<MapaTipoAnotacao> obterMapasTipoAnotacaoPorTipoAnotacao(TipoAnotacao tipoAnotacao) throws BancoobException {
		return getServico().obterMapasTipoAnotacaoPorTipoAnotacao(tipoAnotacao);
	}

}
