/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exceção lançada quando se tenta baixar uma anotação manualmente quando a ela for automática.
 * @author Erico.Junior
 */
public class BaixaAnotacaoAutomaticaException extends CAPESCadastroNegocioException {

	/** Serial UID.*/
	private static final long serialVersionUID = -3852227547592763624L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN002";

	/**
	 * {@inheritDoc}
	 */
	public BaixaAnotacaoAutomaticaException() {
		super(CHAVE_MSG);
	}
}
