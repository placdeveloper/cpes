/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * @author Erico.Junior
 *
 */
public class MonitoracaoFilasException extends CAPESCadastroException {

	/** Serial UID.*/
	private static final long serialVersionUID = -7230869960421279515L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN177";

	/**
	 * {@inheritDoc}
	 */
	public MonitoracaoFilasException(Exception excecao) {
		super(CHAVE_MSG, excecao);
	}
}
