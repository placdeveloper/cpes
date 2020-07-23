/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * Banco não existente na base do genext.
 * 
 * @author juan.damasceno
 */
@ApplicationException (rollback = true)
public class BancoInexistenteException extends CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = -3015735275372815421L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN013";

	/**
	 * Construtor da exceção.
	 */
	public BancoInexistenteException() {
		super(CHAVE_MSG, "Banco");
	}
}
