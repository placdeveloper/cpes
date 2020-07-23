/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Instituição já é gestora do cadastro.
 * 
 * @author Erico.Junior
 */
public class InstituicaoGestoraCadastroException extends CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = -3015735275372815421L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN012";

	/**
	 * Construtor da exceção.
	 */
	public InstituicaoGestoraCadastroException() {
		super(CHAVE_MSG);
	}
}
