/*
 * SICOOB
 * 
 * CAPESFrontofficeNegocioException.java(br.com.sicoob.capes.frontoffice.negocio.excecao.CAPESFrontofficeNegocioException)
 */
package br.com.sicoob.capes.frontoffice.negocio.excecao;

import br.com.bancoob.negocio.excecao.NegocioException;

/**
 * The Class CAPESFrontofficeNegocioException.
 */
public class CAPESFrontofficeNegocioException extends NegocioException {

	/** Serial UID */
	private static final long serialVersionUID = 7346272639782812141L;

	/**
	 * Cria uma nova instância de CAPES frontoffice negocio exception.
	 * 
	 * @param mensagem
	 *            the mensagem
	 * @param parametros
	 *            the parametros
	 */
	public CAPESFrontofficeNegocioException(String mensagem, Object... parametros) {
		super(mensagem, parametros);
	}

	/**
	 * Cria uma nova instância de CAPES frontoffice negocio exception.
	 * 
	 * @param mensagem
	 *            the mensagem
	 * @param parametros
	 *            the parametros
	 * @param excecao
	 *            the excecao
	 */
	public CAPESFrontofficeNegocioException(String mensagem, Object[] parametros, Throwable excecao) {
		super(mensagem, parametros, excecao);
	}

	/**
	 * Cria uma nova instância de CAPES frontoffice negocio exception.
	 * 
	 * @param mensagem
	 *            the mensagem
	 * @param excecao
	 *            the excecao
	 */
	public CAPESFrontofficeNegocioException(String mensagem, Throwable excecao) {
		super(mensagem, excecao);
	}

	/**
	 * Cria uma nova instância de CAPES frontoffice negocio exception.
	 * 
	 * @param mensagem
	 *            the mensagem
	 */
	public CAPESFrontofficeNegocioException(String mensagem) {
		super(mensagem);
	}

	/**
	 * Cria uma nova instância de CAPES frontoffice negocio exception.
	 * 
	 * @param excecao
	 *            the excecao
	 */
	public CAPESFrontofficeNegocioException(Throwable excecao) {
		super(excecao);
	}

}
