/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.excecao;

import br.com.bancoob.negocio.excecao.NegocioException;

/**
 * @author Erico.Junior
 * 
 */
public class FuncionarioLegadoExclusaoException extends NegocioException {

	/** Serial UID; */
	private static final long serialVersionUID = -2595230401773844973L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "msg.erro.violacao.integridade.exclusao";

	/**
	 * Instancia um novo FuncionarioLegadoExclusaoException.
	 */
	public FuncionarioLegadoExclusaoException() {
		super(CHAVE_MSG);
	}
	
	/**
	 * Instancia um novo FuncionarioLegadoExclusaoException.
	 *
	 * @param causa o valor de causa
	 */
	public FuncionarioLegadoExclusaoException(Throwable causa) {
		super(CHAVE_MSG, causa);
	}
}