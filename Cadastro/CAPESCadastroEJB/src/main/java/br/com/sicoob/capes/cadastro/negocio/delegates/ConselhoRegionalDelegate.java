/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.ConselhoRegionalServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.ConselhoRegional;

/**
 * @author Erico.Junior
 * 
 */
public class ConselhoRegionalDelegate
		extends	CAPESCadastroCrudDelegate<ConselhoRegional, ConselhoRegionalServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ConselhoRegionalServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarConselhoRegionalServico();
	}

}
