/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exceção utilizada para indicar que não foi possível flexibilizar a anotação.
 * @author Erico.Junior
 */
public class FlexibilizacaoAnotacaoException extends CAPESCadastroNegocioException {

	/** Serial UID.*/
	private static final long serialVersionUID = -7168659636003872846L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN001";

	/**
	 * {@inheritDoc}
	 */
	public FlexibilizacaoAnotacaoException() {
		super(CHAVE_MSG);
	}

}
