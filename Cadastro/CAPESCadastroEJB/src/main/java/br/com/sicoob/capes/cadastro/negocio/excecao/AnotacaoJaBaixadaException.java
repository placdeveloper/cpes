/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exceção para tentativa de baixa de uma anotação já baixada.
 * @author erico.junior
 *
 */
public class AnotacaoJaBaixadaException extends CAPESCadastroNegocioException {

	/** Serial UID.*/
	private static final long serialVersionUID = 8159856078894407010L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN008";

	/**
	 * {@inheritDoc}
	 */
	public AnotacaoJaBaixadaException() {
		super(CHAVE_MSG);
	}	
}
