/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 */
public class PessoaNaoEncontradaNoPeriodoException extends CAPESCadastroNegocioException {

	/** Serial UID.*/
	private static final long serialVersionUID = -1352075604752978469L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN176";

	/**
	 * {@inheritDoc}
	 */
	public PessoaNaoEncontradaNoPeriodoException() {
		super(CHAVE_MSG);
	}
}
