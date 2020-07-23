/*
 * SICOOB
 * 
 * MaximoPessoasException.java(br.com.sicoob.capes.api.negocio.excecao.MaximoPessoasException)
 */
package br.com.sicoob.capes.api.negocio.excecao;

/**
 * @author erico.junior
 *
 */
public class MaximoPessoasException extends CAPESApiNegocioException {

	/** Serial UID.*/
	private static final long serialVersionUID = -2020708427028896016L;
	
	/** A Constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN003";
	
	/**
	 * Cria uma nova instância de maximo pessoas exception.
	 * 
	 * @param maximo
	 *            the maximo
	 */
	public MaximoPessoasException(String maximo) {
		super(CHAVE_MSG, maximo);
	}

}
