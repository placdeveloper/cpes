// 15/10/2013 - 17:49:11
package br.com.sicoob.capes.cadastro.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * @author Rodrigo.Chaves
 */
@ApplicationException(rollback = true)
public class AutorizacaoNegocioException extends CAPESCadastroNegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 8279819487117996776L;

	/**
	 * Construtor
	 * 
	 * @param mensagem
	 * @param parametros
	 */
	public AutorizacaoNegocioException(String mensagem, Object... parametros) {
		super(mensagem, parametros);
	}

	/**
	 * Construtor
	 * 
	 * @param chaveMensagem
	 */
	public AutorizacaoNegocioException(String chaveMensagem) {
		super(chaveMensagem);
	}

	/**
	 * Construtor
	 * 
	 * @param excecao
	 * @param mensagem
	 * @param parametros
	 */
	public AutorizacaoNegocioException(Throwable excecao, String mensagem, Object... parametros) {
		super(mensagem, excecao, parametros);
	}

	/**
	 * Construtor
	 * 
	 * @param excecao
	 * @param mensagem
	 */
	public AutorizacaoNegocioException(Throwable excecao, String mensagem) {
		super(mensagem, excecao);
	}

	/**
	 * Construtor
	 * 
	 * @param excecao
	 */
	public AutorizacaoNegocioException(Throwable excecao) {
		super(excecao);
	}

}
