/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exce��o lan�ada quando se tenta baixar uma anota��o manualmente quando a ela for autom�tica.
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
