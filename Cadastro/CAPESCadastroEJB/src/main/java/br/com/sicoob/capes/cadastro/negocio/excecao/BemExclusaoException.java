/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * @author Erico.Junior
 * 
 */
public class BemExclusaoException extends
		CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = 9179739977076992679L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN154";

	/**
	 * {@inheritDoc}
	 */
	public BemExclusaoException() {
		super(CHAVE_MSG);
	}

}
