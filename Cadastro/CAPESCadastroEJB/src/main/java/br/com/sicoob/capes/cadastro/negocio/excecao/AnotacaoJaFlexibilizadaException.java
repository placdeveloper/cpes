/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exce��o para tentativa de flexibiliza��o de anota��es j� flexibilizadas.
 * @author erico.junior
 */
public class AnotacaoJaFlexibilizadaException extends CAPESCadastroNegocioException {

	/** Serial UID.*/
	private static final long serialVersionUID = -8544833733939734909L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN007";

	/**
	 * {@inheritDoc}
	 */
	public AnotacaoJaFlexibilizadaException() {
		super(CHAVE_MSG);
	}	
}
