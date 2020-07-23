/*
 * SICOOB
 * 
 * AnotacaoSisbrParaBaixaNaoEncontradaException.java(br.com.sicoob.capes.api.negocio.excecao.AnotacaoSisbrParaBaixaNaoEncontradaException)
 */
package br.com.sicoob.capes.api.negocio.excecao;


/**
 * Exce��o utilizada quando n�o for encontada anota��o do sisbr para executar a
 * baixa.
 * 
 * @author erico.junior
 */
public class AnotacaoSisbrParaBaixaNaoEncontradaException extends CAPESApiNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = 616591074661268479L;

	/** A Constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN005";

	/**
	 * Cria uma nova inst�ncia de anotacao sisbr para baixa nao encontrada exception.
	 */
	public AnotacaoSisbrParaBaixaNaoEncontradaException() {
		super(CHAVE_MSG);
	}
}
