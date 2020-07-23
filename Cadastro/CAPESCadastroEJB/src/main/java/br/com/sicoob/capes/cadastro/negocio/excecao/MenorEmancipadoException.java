/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exceção utilizada quando uma pessoa maior de idade está sendo gravada como emancipada.
 * 
 * @author erico.junior
 */
public class MenorEmancipadoException extends CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = -1997424730318630992L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN029";

	/**
	 * Instancia um novo MenorEmancipadoException.
	 */
	public MenorEmancipadoException() {
		super(CHAVE_MSG);
	}
}