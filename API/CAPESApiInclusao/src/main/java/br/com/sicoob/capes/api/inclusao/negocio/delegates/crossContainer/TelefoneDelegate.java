package br.com.sicoob.capes.api.inclusao.negocio.delegates.crossContainer;

import br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces.ITelefoneDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.dto.TelefoneDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.TelefoneServico;

/**
 * A Classe TelefoneDelegate.
 * 
 * @author bruno.carneiro
 */
public class TelefoneDelegate extends CAPESApiInclusaoDelegate<TelefoneDTO, TelefoneServico> implements ITelefoneDelegate {
	
	/**
	 * 
	 */
	protected TelefoneDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static TelefoneDelegate getInstance() {
		return valueOf(TelefoneDelegate.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TelefoneServico localizarServico() {
		return getLocator().localizarTelefoneServico();
	}

}