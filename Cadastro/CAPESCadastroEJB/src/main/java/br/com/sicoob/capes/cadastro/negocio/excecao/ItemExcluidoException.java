/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * @author erico.junior
 *
 */
public class ItemExcluidoException extends CAPESCadastroNegocioException {

	/** Serial UID.*/
	private static final long serialVersionUID = 1209161102957923403L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN037";

	/**
	 * {@inheritDoc}
	 */
	public ItemExcluidoException() {
		super(CHAVE_MSG);
	}
	
	/**
	 * Instancia um novo ItemExcluidoException.
	 *
	 * @param causa o valor de causa
	 */
	public ItemExcluidoException(Throwable causa) {
		super(CHAVE_MSG, causa);
	}
}