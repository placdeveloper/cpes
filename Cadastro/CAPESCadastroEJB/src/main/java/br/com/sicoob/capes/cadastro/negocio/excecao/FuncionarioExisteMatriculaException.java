/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * @author Erico.Junior
 *
 */
public class FuncionarioExisteMatriculaException extends CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = -4083421088169313423L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN158";

	/**
	 * {@inheritDoc}
	 */
	public FuncionarioExisteMatriculaException() {
		super(CHAVE_MSG);
	}
}
