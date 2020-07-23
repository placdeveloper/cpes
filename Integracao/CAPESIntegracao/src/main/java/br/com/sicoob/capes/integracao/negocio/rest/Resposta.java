package br.com.sicoob.capes.integracao.negocio.rest;

import java.io.Serializable;

/**
 * Classe que representa a resposta de uma requisi��o Restful
 * 
 * @author Bruno.Carneiro
 */
public class Resposta<T> implements Serializable {
	private static final long serialVersionUID = 6322537273469130449L;

	private int codigoResposta;
	private boolean sucesso;
	private String textoResposta;
	private T conteudo;
	private Object erro;

	/**
	 * Retorna o c�digo html de resposta
	 * 
	 * @return
	 */
	public int getCodigoResposta() {
		return codigoResposta;
	}

	/**
	 * Define o c�digo html de resposta
	 * 
	 * @param codigoResposta
	 */
	public void setCodigoResposta(int codigoResposta) {
		this.codigoResposta = codigoResposta;
	}

	/**
	 * Verifica se foi uma requisi��o bem sucedida, isto �, com o c�digo de
	 * resposta entre 200 e 299.
	 * 
	 * @return
	 */
	public boolean isSucesso() {
		return sucesso;
	}

	/**
	 * Define se foi uma requisi��o bem sucedida.
	 * 
	 * @param sucesso
	 */
	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}

	/**
	 * Recupera a resposta da requisi��o em texto puro (raw).
	 * 
	 * @return
	 */
	public String getTextoResposta() {
		return textoResposta;
	}

	/**
	 * Define a resposta da requisi��o em texto puro (raw).
	 * 
	 * @return
	 */
	public void setTextoResposta(String conteudoJson) {
		this.textoResposta = conteudoJson;
	}

	/**
	 * Recupera a resposta a requisi��o transformada em um objeto java.
	 * 
	 * @return
	 */
	public T getConteudo() {
		return conteudo;
	}

	/**
	 * Define a resposta a requisi��o transformada em um objeto java.
	 * 
	 * @return
	 */
	public void setConteudo(T conteudo) {
		this.conteudo = conteudo;
	}

	/**
	 * Recupera a resposta a requisi��o transformada em um objeto java.
	 * 
	 * @return
	 */
	public Object getErro() {
		return erro;
	}

	/**
	 * Define a resposta a requisi��o transformada em um objeto java.
	 * 
	 * @return
	 */
	public void setErro(Object erro) {
		this.erro = erro;
	}
}