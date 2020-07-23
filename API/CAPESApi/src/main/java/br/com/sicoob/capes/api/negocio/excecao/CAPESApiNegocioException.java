/*
 * SICOOB
 * 
 * CAPESApiNegocioException.java(br.com.sicoob.capes.api.negocio.excecao.CAPESApiNegocioException)
 */
package br.com.sicoob.capes.api.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;

/**
 * @author erico.junior
 *
 */
@ApplicationException (rollback = true)
public class CAPESApiNegocioException extends NegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = -7053120465286948150L;

	/**
	 * Cria uma nova instância de CAPES api negocio exception.
	 * 
	 * @param chaveMensagem
	 *            the chave mensagem
	 */
	public CAPESApiNegocioException(String chaveMensagem) {
		super(chaveMensagem);
	}

	/**
	 * Cria uma nova instância de CAPES api negocio exception.
	 * 
	 * @param mensagem
	 *            the mensagem
	 * @param parametros
	 *            the parametros
	 */
	public CAPESApiNegocioException(String mensagem, Object... parametros) {
		super(mensagem, parametros);
	}

	/**
	 * Cria uma nova instância de CAPES api negocio exception.
	 * 
	 * @param mensagem
	 *            the mensagem
	 * @param excecao
	 *            the excecao
	 */
	public CAPESApiNegocioException(String mensagem, Throwable excecao) {
		super(mensagem, excecao);
	}
	
	/**
	 * Cria uma nova instância de CAPES api negocio exception.
	 * 
	 * @param mensagem
	 *            the mensagem
	 * @param excecao
	 *            the excecao
	 * @param parametros
	 *            the parametros
	 */
	public CAPESApiNegocioException(String mensagem, Throwable excecao, Object... parametros) {
		super(mensagem, parametros, excecao);
	}
	
	/**
	 * Lança uma exceção
	 * 
	 * @param mensagem
	 * @throws BancoobException
	 */
	public static void lancarExcecao(String mensagem) throws BancoobException {
		throw new CAPESApiNegocioException(mensagem);
	}
}