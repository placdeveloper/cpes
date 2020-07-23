/*
 * SICOOB
 * 
 * CampoArquivoInvalidoException.java(br.com.sicoob.capes.comum.arquivos.excecao.CampoArquivoInvalidoException)
 */
package br.com.sicoob.capes.comum.arquivos.excecao;

/**
 * Classe de exceção da hierarquia de exceções do componente de importação e
 * exportação de arquivos texto (capes-arquivo) que indica algum problema
 * em algum campo do arquivo.
 *
 * Criado em 21/7/2014
 * @author rodrigo.chaves
 */
public class CampoArquivoInvalidoException extends ArquivoInvalidoException {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -8840641802346811437L;

	/**
	 * Cria uma nova instância de campo arquivo invalido exception.
	 * 
	 * @param forcaExcecao
	 *            the forca excecao
	 * @param chave
	 *            the chave
	 * @param parametros
	 *            the parametros
	 */
	public CampoArquivoInvalidoException(boolean forcaExcecao, String chave, Object... parametros) {

		super(forcaExcecao, chave, parametros);
	}

	/**
	 * Cria uma nova instância de campo arquivo invalido exception.
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
	public CampoArquivoInvalidoException(boolean forcaExcecao, String chave, Object[] parametros, Throwable excecao) {

		super(forcaExcecao, chave, parametros, excecao);
	}

	/**
	 * Cria uma nova instância de campo arquivo invalido exception.
	 * 
	 * @param forcaExcecao
	 *            the forca excecao
	 * @param chave
	 *            the chave
	 * @param excecao
	 *            the excecao
	 */
	public CampoArquivoInvalidoException(boolean forcaExcecao, String chave, Throwable excecao) {

		super(forcaExcecao, chave, excecao);
	}

	/**
	 * Cria uma nova instância de campo arquivo invalido exception.
	 * 
	 * @param forcaExcecao
	 *            the forca excecao
	 * @param chave
	 *            the chave
	 */
	public CampoArquivoInvalidoException(boolean forcaExcecao, String chave) {

		super(forcaExcecao, chave);
	}

	/**
	 * Cria uma nova instância de campo arquivo invalido exception.
	 * 
	 * @param forcaExcecao
	 *            the forca excecao
	 * @param excecao
	 *            the excecao
	 */
	public CampoArquivoInvalidoException(boolean forcaExcecao, Throwable excecao) {

		super(forcaExcecao, excecao);
	}

	/**
	 * Cria uma nova instância de campo arquivo invalido exception.
	 * 
	 * @param chave
	 *            the chave
	 * @param parametros
	 *            the parametros
	 */
	public CampoArquivoInvalidoException(String chave, Object... parametros) {

		super(chave, parametros);
	}

	/**
	 * Cria uma nova instância de campo arquivo invalido exception.
	 * 
	 * @param chave
	 *            the chave
	 * @param parametros
	 *            the parametros
	 * @param excecao
	 *            the excecao
	 */
	public CampoArquivoInvalidoException(String chave, Throwable excecao, Object... parametros) {

		super(chave, parametros, excecao);
	}

	/**
	 * Cria uma nova instância de campo arquivo invalido exception.
	 * 
	 * @param chave
	 *            the chave
	 * @param excecao
	 *            the excecao
	 */
	public CampoArquivoInvalidoException(String chave, Throwable excecao) {

		super(chave, excecao);
	}

	/**
	 * Cria uma nova instância de campo arquivo invalido exception.
	 * 
	 * @param chave
	 *            the chave
	 */
	public CampoArquivoInvalidoException(String chave) {

		super(chave);
	}

	/**
	 * Cria uma nova instância de campo arquivo invalido exception.
	 * 
	 * @param excecao
	 *            the excecao
	 */
	public CampoArquivoInvalidoException(Throwable excecao) {

		super(excecao);
	}

}