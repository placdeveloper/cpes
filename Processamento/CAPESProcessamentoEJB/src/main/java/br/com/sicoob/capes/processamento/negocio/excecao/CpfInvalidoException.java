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
public class CpfInvalidoException extends BeneficiarioINSSException {

	/** Serial UID.*/
	private static final long serialVersionUID = 4061607435184283475L;
	
	/** A constante CODIGO_ERRO. */
	private static final Integer CODIGO_ERRO = 106;
	
	/**
	 * Instancia um novo CpfInvalidoException.
	 *
	 * @param excecao o valor de excecao
	 */
	public CpfInvalidoException(Throwable excecao) {
		super(excecao, CODIGO_ERRO);
	}
}
