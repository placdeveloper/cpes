/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exceção utilizada quando a profissão precisar ser alterada.
 * 
 * @author erico.junior
 */
public class OcupacaoProfissionalException extends CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = -1997424730318630992L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN031";

	/**
	 * Instancia um novo OcupacaoProfissionalException.
	 */
	public OcupacaoProfissionalException() {
		super(CHAVE_MSG);
	}
}