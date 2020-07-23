/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * @author erico.junior
 *
 */
@ApplicationException (rollback = true)
public class ValorForaLimiteException extends CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = -1628271640775852961L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN130";

	/**
	 * Instancia um novo ValorForaLimiteException.
	 *
	 * @param campo o valor de campo
	 * @param limiteInferior o valor de limite inferior
	 * @param limiteSuperior o valor de limite superior
	 */
	public ValorForaLimiteException(String campo, String limiteInferior, String limiteSuperior) {
		super(CHAVE_MSG, campo, limiteInferior, limiteSuperior);
	}
}