/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exceção para quando o campo 1 é menor que o campo 2.
 * 
 * @author erico.junior
 */
public class CampoMenorException extends CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = -1628271640775852961L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN027";

	/**
	 * Instancia um novo CampoMenorException.
	 *
	 * @param campo1 o valor de campo1
	 * @param campo2 o valor de campo2
	 */
	public CampoMenorException(String campo1, String campo2) {
		super(CHAVE_MSG, campo1, campo2);
	}
}