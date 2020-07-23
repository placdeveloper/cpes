/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.UnidadeMedidaServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.UnidadeMedida;

/**
 * Delegate para unidade de medida.
 * @author erico.junior
 */
public class UnidadeMedidaDelegate extends
		CAPESCadastroCrudDelegate<UnidadeMedida, UnidadeMedidaServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected UnidadeMedidaServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarUnidadeMedidaServico();
	}

}
