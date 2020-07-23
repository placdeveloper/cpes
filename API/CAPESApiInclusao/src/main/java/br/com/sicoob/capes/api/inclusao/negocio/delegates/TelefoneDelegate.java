package br.com.sicoob.capes.api.inclusao.negocio.delegates;

import br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces.ITelefoneDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.dto.TelefoneDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.TelefoneServico;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.locator.CAPESApiInclusaoServiceLocator;

/**
 * A Classe TelefoneDelegate.
 * 
 * @author bruno.carneiro
 */
public class TelefoneDelegate extends CAPESApiInclusaoDelegate<TelefoneDTO, TelefoneServico> implements ITelefoneDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TelefoneServico localizarServico() {
		return CAPESApiInclusaoServiceLocator.getInstance().localizarTelefoneServico();
	}

}