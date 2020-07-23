/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * A Renda Bruta Mensagel deve ser maior ou igual a zero.
 * @author erico.junior
 */
@ApplicationException (rollback = true)
public class ValorRendaBrutaMensalException extends CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = -1628271640775852961L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN032";

	/**
	 * Instancia um novo ValorRendaBrutaMensalException.
	 */
	public ValorRendaBrutaMensalException() {
		super(CHAVE_MSG, "Renda Bruta Mensal", "zero");
	}
}