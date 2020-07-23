/*
 * SICOOB
 * 
 * ParametroNaoInformadoException.java(br.com.sicoob.capes.api.negocio.excecao.ParametroNaoInformadoException)
 */
package br.com.sicoob.capes.api.negocio.excecao;

/**
 * @author Rodrigo.Chaves
 */
public class ParametroNaoInformadoException extends CAPESApiNegocioException {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 5876267777837830288L;
	
	/** A Constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN004";

	/**
	 * Construtor
	 * 
	 * @param chaveMensagem
	 */
	public ParametroNaoInformadoException(String parametro) {
		super(CHAVE_MSG, parametro);
	}

	
}
