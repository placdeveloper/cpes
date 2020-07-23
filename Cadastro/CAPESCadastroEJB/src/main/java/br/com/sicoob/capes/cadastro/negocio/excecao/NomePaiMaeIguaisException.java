/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exceção utilizada quando o nome da mãe é igual ao nome do pai.
 * 
 * @author erico.junior
 */
public class NomePaiMaeIguaisException extends CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = -1997424730318630992L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN030";

	/**
	 * Instancia um novo NomePaiMaeIguaisException.
	 */
	public NomePaiMaeIguaisException() {
		super(CHAVE_MSG);
	}
}