/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * Exceção para comparação com a data atual se deve ser maior, menor, maior ou igual, etc.
 * Ex: O valor do campo {data de nascimento} deve ser {maior} que a Data Atual.
 *
 * @author Erico.Junior
 */
@ApplicationException (rollback = true)
public class DataAtualException extends CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = -1628271640775852961L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN126";

	/**
	 * Instancia um novo DataAtualException.
	 *
	 * @param data o valor de data
	 * @param operacao o valor de operacao
	 */
	public DataAtualException(String data, String operacao) {
		super(CHAVE_MSG, data, operacao);
	}
}