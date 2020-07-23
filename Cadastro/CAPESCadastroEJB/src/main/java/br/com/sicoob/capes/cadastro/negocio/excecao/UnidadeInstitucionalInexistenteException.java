/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * @author erico.junior
 *
 */
public class UnidadeInstitucionalInexistenteException extends CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = -5352116159989256760L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN156";

	/**
	 * Instancia um novo UnidadeInstitucionalInexistenteException.
	 */
	public UnidadeInstitucionalInexistenteException() {
		super(CHAVE_MSG);
	}
}
	


