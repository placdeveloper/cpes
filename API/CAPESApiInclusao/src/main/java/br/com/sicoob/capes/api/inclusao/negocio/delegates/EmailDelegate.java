package br.com.sicoob.capes.api.inclusao.negocio.delegates;

import br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces.IEmailDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.dto.EmailDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.EmailServico;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.locator.CAPESApiInclusaoServiceLocator;

/**
 * A Classe EmailDelegate.
 * 
 * @author bruno.carneiro
 */
public class EmailDelegate extends CAPESApiInclusaoDelegate<EmailDTO, EmailServico> implements IEmailDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EmailServico localizarServico() {
		return CAPESApiInclusaoServiceLocator.getInstance().localizarEmailServico();
	}
}