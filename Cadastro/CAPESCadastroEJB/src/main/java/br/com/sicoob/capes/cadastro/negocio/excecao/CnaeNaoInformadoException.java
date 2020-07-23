/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * @author erico.junior
 */
@ApplicationException (rollback = true)
public class CnaeNaoInformadoException extends CAPESCadastroNegocioException {

	/** Serial UID.*/
	private static final long serialVersionUID = 8159856078894407010L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN134";

	/**
	 * {@inheritDoc}
	 */
	public CnaeNaoInformadoException() {
		super(CHAVE_MSG);
	}	
}
