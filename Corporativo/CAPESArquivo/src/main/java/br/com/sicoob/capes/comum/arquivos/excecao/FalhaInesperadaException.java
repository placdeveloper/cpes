/*
 * SICOOB
 * 
 * FalhaInesperadaException.java(br.com.sicoob.capes.comum.arquivos.excecao.FalhaInesperadaException)
 */
package br.com.sicoob.capes.comum.arquivos.excecao;


/**
 * Exce��o de runtime (<strong>unchecked exception</strong>) que indica que
 * ocorreu alguma falha n�o esperada.
 *
 * Criado em 21/7/2014
 * @author rodrigo.chaves
 */
public class FalhaInesperadaException extends CAPESArquivosRuntimeException {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 1344678952608148277L;

	/**
	 * Cria uma nova inst�ncia de falha inesperada exception.
	 * 
	 * @param excecao
	 *            the excecao
	 */
	public FalhaInesperadaException(Exception excecao) {

		super(excecao);
	}

	/**
	 * Cria uma nova inst�ncia de falha inesperada exception.
	 * 
	 * @param mensagem
	 *            the mensagem
	 * @param excecao
	 *            the excecao
	 */
	public FalhaInesperadaException(String mensagem, Exception excecao) {

		super(mensagem, excecao);
	}

	/**
	 * Cria uma nova inst�ncia de falha inesperada exception.
	 * 
	 * @param chave
	 *            the chave
	 * @param parametros
	 *            the parametros
	 * @param excecao
	 *            the excecao
	 */
	public FalhaInesperadaException(String chave, Exception excecao, Object... parametros) {

		super(chave, parametros, excecao);
	}

	/**
	 * Cria uma nova inst�ncia de falha inesperada exception.
	 * 
	 * @param chave
	 *            the chave
	 * @param parametros
	 *            the parametros
	 */
	public FalhaInesperadaException(String chave, Object... parametros) {

		super(chave, parametros);
	}

	/**
	 * Cria uma nova inst�ncia de falha inesperada exception.
	 * 
	 * @param mensagem
	 *            the mensagem
	 */
	public FalhaInesperadaException(String mensagem) {

		super(mensagem);
	}

}
