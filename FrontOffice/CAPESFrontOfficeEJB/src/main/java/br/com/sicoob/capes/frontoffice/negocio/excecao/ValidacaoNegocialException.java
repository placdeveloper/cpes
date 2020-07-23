/*
 * SICOOB
 * 
 * ValidacaoNegocialException.java(br.com.sicoob.capes.frontoffice.negocio.excecao.ValidacaoNegocialException)
 */
package br.com.sicoob.capes.frontoffice.negocio.excecao;

/**
 * The Class ValidacaoNegocialException.
 */
public abstract class ValidacaoNegocialException extends CAPESFrontofficeNegocioException {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -1846483821651212460L;
	
	/** O atributo nome campo. */
	protected String nomeCampo;

	/**
	 * Cria uma nova instância de validacao negocial exception.
	 * 
	 * @param mensagem
	 *            the mensagem
	 * @param parametros
	 *            the parametros
	 */
	public ValidacaoNegocialException(String mensagem, Object... parametros) {
		super(mensagem, parametros);
	}
	
	/**
	 * Cria uma nova instância de validacao negocial exception.
	 * 
	 * @param mensagem
	 *            the mensagem
	 * @param nomeCampo
	 *            the nome campo
	 * @param parametros
	 *            the parametros
	 */
	public ValidacaoNegocialException(String mensagem, String nomeCampo, Object... parametros) {

		super(mensagem, parametros);
		this.nomeCampo = nomeCampo;
	}

	/**
	 * Cria uma nova instância de validacao negocial exception.
	 * 
	 * @param mensagem
	 *            the mensagem
	 * @param parametros
	 *            the parametros
	 * @param excecao
	 *            the excecao
	 */
	public ValidacaoNegocialException(String mensagem, Object[] parametros, Throwable excecao) {

		super(mensagem, parametros, excecao);
	}
	
	/**
	 * Cria uma nova instância de validacao negocial exception.
	 * 
	 * @param mensagem
	 *            the mensagem
	 * @param nomeCampo
	 *            the nome campo
	 * @param parametros
	 *            the parametros
	 * @param excecao
	 *            the excecao
	 */
	public ValidacaoNegocialException(String mensagem, String nomeCampo, Object[] parametros, Throwable excecao) {
		
		super(mensagem, parametros, excecao);
		this.nomeCampo = nomeCampo;
	}

	/**
	 * Cria uma nova instância de validacao negocial exception.
	 * 
	 * @param mensagem
	 *            the mensagem
	 * @param nomeCampo
	 *            the nome campo
	 * @param excecao
	 *            the excecao
	 */
	public ValidacaoNegocialException(String mensagem, String nomeCampo, Throwable excecao) {

		super(mensagem, excecao);
		this.nomeCampo = nomeCampo;
	}

	/**
	 * Cria uma nova instância de validacao negocial exception.
	 * 
	 * @param mensagem
	 *            the mensagem
	 */
	public ValidacaoNegocialException(String mensagem) {

		super(mensagem);
	}
	
	/**
	 * Cria uma nova instância de validacao negocial exception.
	 * 
	 * @param mensagem
	 *            the mensagem
	 * @param nomeCampo
	 *            the nome campo
	 */
	public ValidacaoNegocialException(String mensagem, String nomeCampo) {
		
		super(mensagem);
		this.nomeCampo = nomeCampo;
	}

	/**
	 * Cria uma nova instância de validacao negocial exception.
	 * 
	 * @param excecao
	 *            the excecao
	 */
	public ValidacaoNegocialException(Throwable excecao) {

		super(excecao);
	}

	/**
	 * Recupera nome campo.
	 * 
	 * @return nome campo
	 */
	public String getNomeCampo() {
		return nomeCampo;
	}

}
