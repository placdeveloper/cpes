/*
 * SICOOB
 * 
 * CAPESComumException.java(br.com.sicoob.capes.comum.negocio.excecao.CAPESComumException)
 */
package br.com.sicoob.capes.comum.negocio.excecao;

import br.com.bancoob.excecao.BancoobException;

/**
 * Excecao padrao para o sistema CAPESComum.
 * 
 * @author Stefanini IT Solutions
 */
public class CAPESComumException extends BancoobException {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	public CAPESComumException(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public CAPESComumException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * @param chave
	 * @param excecao
	 */
	public CAPESComumException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * @param excecao
	 */
	public CAPESComumException(Throwable excecao) {
		super(excecao);
	}
}
