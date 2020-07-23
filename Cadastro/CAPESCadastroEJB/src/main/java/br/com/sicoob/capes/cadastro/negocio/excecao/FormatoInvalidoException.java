/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * Exceção quando o dado for inválido.
 * 
 * @author erico.junior
 */
@ApplicationException (rollback = true)
public class FormatoInvalidoException extends CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = 2977150645249639957L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN117";

	/**
	 * {@inheritDoc}
	 */
	public FormatoInvalidoException(String parametro) {
		super(CHAVE_MSG, parametro);
	}
	
	/**
	 * {@inheritDoc}
	 */	
	public FormatoInvalidoException(String parametros, Exception excecao) {
		super(CHAVE_MSG, excecao, parametros);
	}
}
