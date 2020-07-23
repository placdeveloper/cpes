/*
 * SICOOB
 * 
 * PessoaNaoInformadaException.java(br.com.sicoob.capes.api.negocio.excecao.PessoaNaoInformadaException)
 */
package br.com.sicoob.capes.api.negocio.excecao;


/**
 * @author erico.junior
 *
 */
public class PessoaNaoInformadaException extends CAPESApiNegocioException {

	/** Serial UID.*/
	private static final long serialVersionUID = -2020708427028896016L;
	
	/** A Constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN001";
	
	/**
	 * Cria uma nova instância de pessoa nao informada exception.
	 */
	public PessoaNaoInformadaException() {
		super(CHAVE_MSG);
	}

}
