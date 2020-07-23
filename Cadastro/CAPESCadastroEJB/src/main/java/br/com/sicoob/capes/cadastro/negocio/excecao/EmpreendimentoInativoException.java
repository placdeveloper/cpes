/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * @author Erico.Junior
 *
 */
public class EmpreendimentoInativoException extends CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = 4728845120083281675L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN160";

	/**
	 * Instancia um novo EmpreendimentoInativoException.
	 */
	public EmpreendimentoInativoException() {
		super(CHAVE_MSG);
	}
}