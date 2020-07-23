/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exceção para clientes não migrados para o CUC.
 * @author erico.junior
 */
public class ClienteNaoMigradoException extends CAPESCadastroNegocioException {

	/** Serial UID.*/
	private static final long serialVersionUID = 4319241615814940502L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN003";

	/**
	 * {@inheritDoc}
	 */
	public ClienteNaoMigradoException() {
		super(CHAVE_MSG);
	}
	
	/**
	 * {@inheritDoc}
	 */	
	public ClienteNaoMigradoException(Throwable excecao) {
		super(CHAVE_MSG, excecao);
	}	
}
