package br.com.sicoob.capes.api.negocio.delegates;

import br.com.sicoob.capes.api.negocio.delegates.interfaces.IOcupacaoProfissionalDelegate;
import br.com.sicoob.capes.api.negocio.servicos.OcupacaoProfissionalServico;
import br.com.sicoob.capes.api.negocio.servicos.locator.CAPESApiServiceLocator;

/**
 * Delegate com os serviços de ocupação profissional.
 * 
 * @author Bruno.Carneiro
 */
public class OcupacaoProfissionalDelegate extends CAPESApiDelegate<OcupacaoProfissionalServico> implements IOcupacaoProfissionalDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected OcupacaoProfissionalServico localizarServico() {
		return CAPESApiServiceLocator.getInstance().localizarOcupacaoProfissionalServico();
	}

}