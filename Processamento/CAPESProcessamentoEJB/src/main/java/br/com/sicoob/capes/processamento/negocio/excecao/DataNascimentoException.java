/**
 * 
 */
package br.com.sicoob.capes.processamento.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * @author Erico.Junior
 *
 */
@ApplicationException (rollback = true)
public class DataNascimentoException extends BeneficiarioINSSException {

	/** Serial UID.*/
	private static final long serialVersionUID = 6539702995152746304L;
	
	/** A constante CODIGO_ERRO. */
	private static final Integer CODIGO_ERRO = 225;
	
	/**
	 * Instancia um novo DataNascimentoException.
	 *
	 * @param excecao o valor de excecao
	 */
	public DataNascimentoException(Throwable excecao) {
		super(excecao, CODIGO_ERRO);
	}
}