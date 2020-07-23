/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * @author Erico.Junior
 *
 */
public class MatriculaUtilizadaEmpresaException extends CAPESCadastroNegocioException {
	
	/** Serial UID.*/
	private static final long serialVersionUID = 2468432062031585467L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN039";

	/**
	 * {@inheritDoc}
	 */	
	public MatriculaUtilizadaEmpresaException(String cpf) {
		super(CHAVE_MSG, cpf);
	}

}
