/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * Exceção utilizada quando a pessoa não é encontrada.
 * @author Erico.Junior
 */
@ApplicationException (rollback = false)
public class PessoaNaoEncontradaException extends CAPESCadastroNegocioException {

	/** Serial UID.*/
	private static final long serialVersionUID = 2977150645249639957L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN010";

	/**
	 * {@inheritDoc}
	 */
	public PessoaNaoEncontradaException() {
		super(CHAVE_MSG);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public PessoaNaoEncontradaException(Throwable excecao) {
		super(CHAVE_MSG, excecao);
	}	
	
}
