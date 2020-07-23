/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * @author Erico.Junior
 * 
 */
public class NucleoAlteracaoException extends CAPESCadastroNegocioException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7327325462224515799L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN042";

	/**
	 * {@inheritDoc}
	 */
	public NucleoAlteracaoException(String texto) {
		super(CHAVE_MSG, texto);
	}
}
