package br.com.sicoob.capes.api.negocio.delegates.crossContainer;

import br.com.sicoob.capes.api.negocio.delegates.interfaces.IOcupacaoProfissionalDelegate;
import br.com.sicoob.capes.api.negocio.servicos.OcupacaoProfissionalServico;

/**
 * Delegate com os servi�os de ocupa��o profissional.
 * 
 * @author Bruno.Carneiro
 */
public class OcupacaoProfissionalDelegate extends CAPESApiDelegate<OcupacaoProfissionalServico> implements IOcupacaoProfissionalDelegate {
	
	/**
	 * 
	 */
	protected OcupacaoProfissionalDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static OcupacaoProfissionalDelegate getInstance() {
		return valueOf(OcupacaoProfissionalDelegate.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected OcupacaoProfissionalServico localizarServico() {
		return getLocator().localizarOcupacaoProfissionalServico();
	}

}