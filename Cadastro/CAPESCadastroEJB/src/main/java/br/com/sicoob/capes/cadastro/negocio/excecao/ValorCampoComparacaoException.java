/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * Exceção que deve ser utilizada na comparação de dois campos.
 * @author erico.junior
 * MN127=O valor do campo {1} deve ser {2} que {3}.
 */
@ApplicationException (rollback = true)
public class ValorCampoComparacaoException extends CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = -1628271640775852961L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN127";

	/**
	 * Instancia um novo ValorCampoComparacaoException.
	 *
	 * @param campo1 o valor de campo1
	 * @param comparacao o valor de comparacao
	 * @param campo2 o valor de campo2
	 */
	public ValorCampoComparacaoException(String campo1, String comparacao, String campo2) {
		super(CHAVE_MSG, campo1, comparacao, campo2);
	}
}