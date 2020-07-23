/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;


/**
 * @author Erico.Junior
 *
 */
public class FuncionarioExisteInstituicaoException extends CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = 5305265995720342929L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN157";

	/**
	 * {@inheritDoc}
	 */
	public FuncionarioExisteInstituicaoException() {
		super(CHAVE_MSG);
	}
}
