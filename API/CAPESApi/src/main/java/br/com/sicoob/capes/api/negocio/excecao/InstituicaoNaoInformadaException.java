/*
 * SICOOB
 * 
 * InstituicaoNaoInformadaException.java(br.com.sicoob.capes.api.negocio.excecao.InstituicaoNaoInformadaException)
 */
package br.com.sicoob.capes.api.negocio.excecao;


/**
 * @author erico.junior
 *
 */
public class InstituicaoNaoInformadaException extends CAPESApiNegocioException {

	/** Serial UID.*/
	private static final long serialVersionUID = -1597951768941600159L;
	
	/** A Constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN002";
	
	/**
	 * Cria uma nova instância de instituicao nao informada exception.
	 */
	public InstituicaoNaoInformadaException() {
		super(CHAVE_MSG);
	}

}
