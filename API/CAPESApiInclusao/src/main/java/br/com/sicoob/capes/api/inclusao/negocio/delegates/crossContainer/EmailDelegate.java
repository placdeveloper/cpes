package br.com.sicoob.capes.api.inclusao.negocio.delegates.crossContainer;

import br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces.IEmailDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.dto.EmailDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.EmailServico;

/**
 * A Classe EmailDelegate.
 * 
 * @author bruno.carneiro
 */
public class EmailDelegate extends CAPESApiInclusaoDelegate<EmailDTO, EmailServico> implements IEmailDelegate {
	
	/**
	 * 
	 */
	protected EmailDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static EmailDelegate getInstance() {
		return valueOf(EmailDelegate.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EmailServico localizarServico() {
		return getLocator().localizarEmailServico();
	}
}