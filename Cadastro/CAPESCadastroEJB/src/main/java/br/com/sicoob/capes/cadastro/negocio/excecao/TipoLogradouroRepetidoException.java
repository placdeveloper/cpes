/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * @author Erico.Junior
 *
 */
@ApplicationException (rollback = true)
public class TipoLogradouroRepetidoException extends CAPESCadastroNegocioException {
	
	/** Serial UID.*/
	private static final long serialVersionUID = -7278064432629492275L;
		
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN141";
	
	/**
	 * Construtor da exceção.
	 */
	public TipoLogradouroRepetidoException() {
		super(CHAVE_MSG);
	}

}