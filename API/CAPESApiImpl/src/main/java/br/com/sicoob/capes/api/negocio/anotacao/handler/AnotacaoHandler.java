/*
 * SICOOB
 * 
 * AnotacaoHandler.java(br.com.sicoob.capes.api.negocio.anotacao.handler.AnotacaoHandler)
 */
package br.com.sicoob.capes.api.negocio.anotacao.handler;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.math.NumberUtils;

import br.com.sicoob.capes.api.negocio.anotacao.builder.AnotacaoBuilder;


/**
 * Handler para as anota��es autom�ticas.
 * @author Erico.Junior
 */
public abstract class AnotacaoHandler {

	/** O atributo anotacoes. */
	private final transient Set<AnotacaoBuilder> anotacoes = new HashSet<AnotacaoBuilder>();
	
	/** O atributo proxima. */
	private transient AnotacaoHandler proxima;
	
	/**
	 * Cria o elo da cadeia de responsabilidade sem o pr�ximo elo.
	 */
	public AnotacaoHandler() {
		super();
	}

	/**
	 * Cria o elo da cadeia de responsabilidade com o pr�ximo elo.
	 * 
	 * @param proxima
	 *            O pr�ximo elo da cadeia de responsabilidade.
	 */
	public AnotacaoHandler(AnotacaoHandler proxima) {
		this.proxima = proxima;
	}	
	
	/**
	 * Recupera os construtores das anota��es.
	 * @return os construtores das anota��es.
	 */
	public Set<AnotacaoBuilder> obterAnotacoesBuilder() {
		
		if(deveTratarRequisicao()){
			anotacoes.add(obterAnotacaoBuilder());	
		}
		
		if (proxima != null) {
			anotacoes.addAll(proxima.obterAnotacoesBuilder());
		}
		
		return anotacoes;
	}
	
	/**
	 * Verifica se o elo deve tratar a requisi��o.
	 * @return se o elo deve tratar a requisi��o ou n�o.
	 */
	protected abstract boolean deveTratarRequisicao();	
	
	/**
	 * Recupera o AnotacaoBuilder para a requisi��o.
	 * @return o AnotacaoBuilder para a requisi��o.
	 */
	protected abstract AnotacaoBuilder obterAnotacaoBuilder();
	
	/**
	 * Verifica se o valor informado � nulo ou zero.
	 * @param valor O valor a ser verificado.
	 * @return se o n�mero informado � nulo ou zero.
	 */
	protected boolean isNuloOuZero(Integer valor) {
		return valor == null || valor.equals(NumberUtils.INTEGER_ZERO);
	}	
}
