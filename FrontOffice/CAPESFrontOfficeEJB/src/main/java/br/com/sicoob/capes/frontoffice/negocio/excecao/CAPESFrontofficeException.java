/*
 * SICOOB
 * 
 * CAPESFrontofficeException.java(br.com.sicoob.capes.frontoffice.negocio.excecao.CAPESFrontofficeException)
 */
package br.com.sicoob.capes.frontoffice.negocio.excecao;

import br.com.bancoob.excecao.BancoobException;

/**
 * Excecao padrao para o sistema CAPESComum.
 * 
 * @author Stefanini IT Solutions
 */
public class CAPESFrontofficeException extends BancoobException {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	public CAPESFrontofficeException(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public CAPESFrontofficeException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * @param chave
	 * @param excecao
	 */
	public CAPESFrontofficeException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * @param excecao
	 */
	public CAPESFrontofficeException(Throwable excecao) {
		super(excecao);
	}
}
