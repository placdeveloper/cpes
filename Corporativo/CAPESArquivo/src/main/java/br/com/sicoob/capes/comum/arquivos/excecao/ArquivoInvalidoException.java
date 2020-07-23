/*
 * SICOOB
 * 
 * ArquivoInvalidoException.java(br.com.sicoob.capes.comum.arquivos.excecao.ArquivoInvalidoException)
 */
package br.com.sicoob.capes.comum.arquivos.excecao;

/**
 * Classe principal da hierarquia de exceções do componente de importação e exportação de arquivos texto
 * (capes-arquivo).
 * 
 * Criado em 21/7/2014.
 * 
 * @author rodrigo.chaves
 */
public class ArquivoInvalidoException extends CAPESArquivosException {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 6299695593787473483L;

	/** O atributo forca excecao. */
	private boolean forcaExcecao;

	/**
	 * Cria uma nova instância de arquivo invalido exception.
	 * 
	 * @param chave
	 *            the chave
	 * @param parametros
	 *            the parametros
	 */
	public ArquivoInvalidoException(String chave, Object... parametros) {

		super(chave, parametros);
	}

	/**
	 * Cria uma nova instância de arquivo invalido exception.
	 * 
	 * @param forcaExcecao
	 *            the forca excecao
	 * @param chave
	 *            the chave
	 * @param parametros
	 *            the parametros
	 */
	public ArquivoInvalidoException(boolean forcaExcecao, String chave, Object... parametros) {

		super(chave, parametros);
		this.forcaExcecao = forcaExcecao;
	}

	/**
	 * Cria uma nova instância de arquivo invalido exception.
	 * 
	 * @param chave
	 *            the chave
	 * @param parametros
	 *            the parametros
	 * @param excecao
	 *            the excecao
	 */
	public ArquivoInvalidoException(String chave, Object[] parametros, Throwable excecao) {

		super(chave, parametros, excecao);
	}

	/**
	 * Cria uma nova instância de arquivo invalido exception.
	 * 
	 * @param forcaExcecao
	 *            the forca excecao
	 * @param chave
	 *            the chave
	 * @param parametros
	 *            the parametros
	 * @param excecao
	 *            the excecao
	 */
	public ArquivoInvalidoException(boolean forcaExcecao, String chave, Object[] parametros, Throwable excecao) {

		super(chave, parametros, excecao);
		this.forcaExcecao = forcaExcecao;
	}

	/**
	 * Cria uma nova instância de arquivo invalido exception.
	 * 
	 * @param chave
	 *            the chave
	 * @param excecao
	 *            the excecao
	 */
	public ArquivoInvalidoException(String chave, Throwable excecao) {

		super(chave, excecao);
	}

	/**
	 * Cria uma nova instância de arquivo invalido exception.
	 * 
	 * @param forcaExcecao
	 *            the forca excecao
	 * @param chave
	 *            the chave
	 * @param excecao
	 *            the excecao
	 */
	public ArquivoInvalidoException(boolean forcaExcecao, String chave, Throwable excecao) {

		super(chave, excecao);
		this.forcaExcecao = forcaExcecao;
	}

	/**
	 * Cria uma nova instância de arquivo invalido exception.
	 * 
	 * @param chave
	 *            the chave
	 */
	public ArquivoInvalidoException(String chave) {

		super(chave);
	}

	/**
	 * Cria uma nova instância de arquivo invalido exception.
	 * 
	 * @param forcaExcecao
	 *            the forca excecao
	 * @param chave
	 *            the chave
	 */
	public ArquivoInvalidoException(boolean forcaExcecao, String chave) {

		super(chave);
		this.forcaExcecao = forcaExcecao;
	}

	/**
	 * Cria uma nova instância de arquivo invalido exception.
	 * 
	 * @param excecao
	 *            the excecao
	 */
	public ArquivoInvalidoException(Throwable excecao) {

		super(excecao);
	}

	/**
	 * Cria uma nova instância de arquivo invalido exception.
	 * 
	 * @param forcaExcecao
	 *            the forca excecao
	 * @param excecao
	 *            the excecao
	 */
	public ArquivoInvalidoException(boolean forcaExcecao, Throwable excecao) {

		super(excecao);
		this.forcaExcecao = forcaExcecao;
	}

	/**
	 * Obtém o valor do atributo <code>forcaExcecao</code>.
	 * 
	 * @return boolean - O atributo forcaExcecao.
	 */
	public boolean isForcaExcecao() {

		return forcaExcecao;
	}

}