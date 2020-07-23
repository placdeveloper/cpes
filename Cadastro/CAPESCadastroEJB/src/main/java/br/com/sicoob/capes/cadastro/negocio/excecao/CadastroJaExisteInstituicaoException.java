/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exceção para quando o cadastro da pessoa já existe na instituição.
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
