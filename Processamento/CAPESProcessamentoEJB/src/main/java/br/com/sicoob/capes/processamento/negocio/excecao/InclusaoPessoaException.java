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
public class InclusaoPessoaException extends BeneficiarioINSSException {

	/** Serial UID.*/
	private static final long serialVersionUID = -2562978209965193337L;
	
	/** A constante CODIGO_ERRO. */
	private static final Integer CODIGO_ERRO = 242;
	
	/**
	 * Instancia um novo InclusaoPessoaException.
	 *
	 * @param excecao o valor de excecao
	 */
	public InclusaoPessoaException(Throwable excecao) {
		super(excecao, CODIGO_ERRO);
	}
}