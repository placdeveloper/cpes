/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * A Classe CompartilhamentoNaoPodeExcluirException.
 */
public class CompartilhamentoNaoPodeExcluirException extends CAPESCadastroNegocioException {

	/** Serial UID.*/
	private static final long serialVersionUID = 3447887810658377592L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN168";

	/**
	 * {@inheritDoc}
	 */
	public CompartilhamentoNaoPodeExcluirException() {
		super(CHAVE_MSG);
	}
}
