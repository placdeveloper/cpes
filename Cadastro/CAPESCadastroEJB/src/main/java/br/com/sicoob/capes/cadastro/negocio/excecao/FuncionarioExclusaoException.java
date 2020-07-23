/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * @author Erico.Junior
 * 
 */
public class FuncionarioExclusaoException extends
		CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = -7575454984615056681L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN159";

	/**
	 * {@inheritDoc}
	 */
	public FuncionarioExclusaoException() {
		super(CHAVE_MSG);
	}

	/**
	 * Instancia um novo FuncionarioExclusaoException.
	 *
	 * @param ex o valor de ex
	 */
	public FuncionarioExclusaoException(Throwable ex) {
		super(CHAVE_MSG, ex);
	}
}
