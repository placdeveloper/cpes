/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * A data 1 deve ser maior que a data 2.
 * @author erico.junior
 */
public class DataMaiorException extends CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = -1628271640775852961L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN028";

	/**
	 * Instancia um novo DataMaiorException.
	 *
	 * @param data1 o valor de data1
	 * @param data2 o valor de data2
	 */
	public DataMaiorException(String data1, String data2) {
		super(CHAVE_MSG, data1, data2);
	}
}