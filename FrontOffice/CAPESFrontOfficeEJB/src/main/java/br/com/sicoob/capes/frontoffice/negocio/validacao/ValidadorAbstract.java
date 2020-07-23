/*
 * SICOOB
 * 
 * ValidadorAbstract.java(br.com.sicoob.capes.frontoffice.negocio.validacao.ValidadorAbstract)
 */
package br.com.sicoob.capes.frontoffice.negocio.validacao;


/**
 * The Class ValidadorAbstract.
 */
public abstract class ValidadorAbstract implements Validador {

	/** O atributo nome campo. */
	private String nomeCampo;

	/**
	 * Cria uma nova instância de validador abstract.
	 * 
	 * @param nomeCampo
	 *            the nome campo
	 */
	public ValidadorAbstract(String nomeCampo) {
		this.nomeCampo = nomeCampo;
	}

	/**
	 * Recupera nome campo.
	 * 
	 * @return nome campo
	 */
	protected String getNomeCampo() {
		return nomeCampo;
	}

}
