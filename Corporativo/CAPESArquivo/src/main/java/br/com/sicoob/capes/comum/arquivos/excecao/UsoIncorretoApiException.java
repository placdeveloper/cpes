/*
 * SICOOB
 * 
 * UsoIncorretoApiException.java(br.com.sicoob.capes.comum.arquivos.excecao.UsoIncorretoApiException)
 */
package br.com.sicoob.capes.comum.arquivos.excecao;

/**
 * Exceção de runtime (<strong>unchecked exception</strong>) que indica que ocorreu algum erro por causa do uso
 * incorreto do componente (API).
 * 
 * Criado em 21/7/2014
 * 
 * @author rodrigo.chaves
 */
public class UsoIncorretoApiException extends CAPESArquivosRuntimeException {

	/** A Constante MENSAGEM_PADRAO. */
	private static final String MENSAGEM_PADRAO = "Uso incorreto do componente capes-arquivo: ";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7415961863898403728L;

	/**
	 * Cria uma <code>Exception</code> com a mensagem de erro e a causa.
	 * 
	 * @param msg
	 *            Mensagem descrevendo o erro.
	 * @param causa
	 *            Causa raiz do erro.
	 */
	public UsoIncorretoApiException(String msg, Exception causa) {

		super(MENSAGEM_PADRAO + recuperarMensagem(msg), causa);
	}

	/**
	 * Cria uma <code>Exception</code> com a mensagem de erro.
	 * 
	 * @param msg
	 *            Mensagem descrevendo o erro.
	 */
	public UsoIncorretoApiException(String msg, String... parametros) {

		super(MENSAGEM_PADRAO + recuperarMensagem(msg, parametros));
	}

	/**
	 * Cria uma nova instância de uso incorreto api exception.
	 * 
	 * @param excecao
	 *            the excecao
	 */
	public UsoIncorretoApiException(Exception excecao) {

		super(MENSAGEM_PADRAO, excecao);
	}

	/**
	 * Cria uma nova instância de uso incorreto api exception.
	 * 
	 * @param chave
	 *            the chave
	 * @param excecao
	 *            the excecao
	 * @param parametros
	 *            the parametros
	 */
	public UsoIncorretoApiException(String chave, Exception excecao, Object... parametros) {

		super(MENSAGEM_PADRAO + recuperarMensagem(chave, parametros), excecao);
	}

	/**
	 * Cria uma nova instância de uso incorreto api exception.
	 * 
	 * @param chave
	 *            the chave
	 * @param parametros
	 *            the parametros
	 */
	public UsoIncorretoApiException(String chave, Object... parametros) {

		super(MENSAGEM_PADRAO + recuperarMensagem(chave, parametros));
	}

	/**
	 * Cria uma nova instância de uso incorreto api exception.
	 * 
	 * @param chave
	 *            the chave
	 */
	public UsoIncorretoApiException(String chave) {

		super(MENSAGEM_PADRAO + recuperarMensagem(chave));
	}

}
