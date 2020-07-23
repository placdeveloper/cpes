/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exce��o para quando o cadastro da pessoa j� existe na institui��o.
 * 
 * @author Erico.Junior
 */
public class CadastroJaExisteInstituicaoException extends CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = -1352075604752978469L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN036";

	/**
	 * {@inheritDoc}
	 */
	public CadastroJaExisteInstituicaoException() {
		super(CHAVE_MSG);
	}
}
