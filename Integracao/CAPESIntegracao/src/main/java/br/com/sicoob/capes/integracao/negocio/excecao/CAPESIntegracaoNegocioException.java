package br.com.sicoob.capes.integracao.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.bancoob.negocio.excecao.NegocioException;

/**
 * @author Rodrigo.Chaves
 */
@ApplicationException(rollback = true)
public class CAPESIntegracaoNegocioException extends NegocioException {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 8408331784750869959L;

	/**
	 * Construtor
	 * 
	 * @param excecao
	 * @param sistema
	 * @param mensagem
	 */
	public CAPESIntegracaoNegocioException(Throwable excecao, String sistema) {
		super("msg.erro.integracao", new Object[] { sistema, excecao.getMessage() }, excecao);
	}

	/**
	 * Construtor
	 * 
	 * @param excecao
	 * @param sistema
	 * @param mensagem
	 */
	public CAPESIntegracaoNegocioException(Throwable excecao, String sistema, String mensagem) {
		super("msg.erro.integracao", new Object[] { sistema, mensagem + " (" + excecao.getMessage() + ")" }, excecao);
	}

	/**
	 * Construtor
	 * 
	 * @param sistema
	 * @param mensagem
	 * @param excecao
	 */
	public CAPESIntegracaoNegocioException(String sistema, String mensagem) {
		super("msg.erro.integracao", sistema, mensagem);
	}

}