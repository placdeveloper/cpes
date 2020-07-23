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
public class NucleoExisteInstituicaoException extends CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = 8512825382833825832L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN040";

	/**
	 * Instancia um novo NucleoExisteInstituicaoException.
	 */
	public NucleoExisteInstituicaoException() {
		super(CHAVE_MSG);
	}
}