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
public class EnderecoException extends BeneficiarioINSSException {

	/** Serial UID.*/
	private static final long serialVersionUID = 4061607435184283475L;
	
	/** A constante CODIGO_ERRO. */
	private static final Integer CODIGO_ERRO = 124;
	
	/**
	 * Instancia um novo EnderecoException.
	 *
	 * @param excecao o valor de excecao
	 */
	public EnderecoException(Throwable excecao) {
		super(excecao, CODIGO_ERRO);
	}
}