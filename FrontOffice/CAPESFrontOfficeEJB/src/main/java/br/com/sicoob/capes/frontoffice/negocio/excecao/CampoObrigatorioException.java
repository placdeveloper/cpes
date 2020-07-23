/*
 * SICOOB
 * 
 * CampoObrigatorioException.java(br.com.sicoob.capes.frontoffice.negocio.excecao.CampoObrigatorioException)
 */
package br.com.sicoob.capes.frontoffice.negocio.excecao;

/**
 * The Class CampoObrigatorioException.
 */
public class CampoObrigatorioException extends ValidacaoNegocialException {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -8775246614527908102L;
	
	/**
	 * Cria uma nova instância de campo obrigatorio exception.
	 * 
	 * @param nomeCampo
	 *            the nome campo
	 */
	public CampoObrigatorioException(String nomeCampo) {

		super("MN0001", nomeCampo, new Object[] { nomeCampo });
	}
	
}
