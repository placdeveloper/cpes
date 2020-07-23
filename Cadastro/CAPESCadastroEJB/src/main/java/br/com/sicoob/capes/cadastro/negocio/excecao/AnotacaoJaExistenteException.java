/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exceção para uma anotação já existente.
 * @author Erico.Junior
 */
public class AnotacaoJaExistenteException extends CAPESCadastroNegocioException {

	/** Serial UID.*/
	private static final long serialVersionUID = -8544833733939734909L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN026";

	/**
	 * {@inheritDoc}
	 */
	public AnotacaoJaExistenteException() {
		super(CHAVE_MSG);
	}	
}