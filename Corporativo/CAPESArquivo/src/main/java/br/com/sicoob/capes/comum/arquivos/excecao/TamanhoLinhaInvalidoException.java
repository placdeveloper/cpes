/*
 * SICOOB
 * 
 * TamanhoLinhaInvalidoException.java(br.com.sicoob.capes.comum.arquivos.excecao.TamanhoLinhaInvalidoException)
 */
package br.com.sicoob.capes.comum.arquivos.excecao;


/**
 * Classe de exceção da hierarquia de exceções do componente de importação e
 * exportação de arquivos texto (capes-arquivo) que indica se alguma linha do
 * arquivo está com o tamanho inválido.
 *
 * Criado em 21/7/2014
 * @author rodrigo.chaves
 */
public class TamanhoLinhaInvalidoException extends ArquivoInvalidoException {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -8272349004270646439L;

	/**
	 * Cria uma nova instância de tamanho linha invalido exception.
	 * 
	 * @param forcaExcecao
	 *            the forca excecao
	 * @param chave
	 *            the chave
	 * @param parametros
	 *            the parametros
	 */
	public TamanhoLinhaInvalidoException(boolean forcaExcecao, String chave, Object... parametros) {

		super(forcaExcecao, chave, parametros);
	}

	/**
	 * Cria uma nova instância de tamanho linha invalido exception.
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
	public TamanhoLinhaInvalidoException(boolean forcaExcecao, String chave, Object[] parametros, Throwable excecao) {

		super(forcaExcecao, chave, parametros, excecao);
	}

	/**
	 * Cria uma nova instância de tamanho linha invalido exception.
	 * 
	 * @param forcaExcecao
	 *            the forca excecao
	 * @param chave
	 *            the chave
	 * @param excecao
	 *            the excecao
	 */
	public TamanhoLinhaInvalidoException(boolean forcaExcecao, String chave, Throwable excecao) {

		super(forcaExcecao, chave, excecao);
	}

	/**
	 * Cria uma nova instância de tamanho linha invalido exception.
	 * 
	 * @param forcaExcecao
	 *            the forca excecao
	 * @param chave
	 *            the chave
	 */
	public TamanhoLinhaInvalidoException(boolean forcaExcecao, String chave) {

		super(forcaExcecao, chave);
	}

	/**
	 * Cria uma nova instância de tamanho linha invalido exception.
	 * 
	 * @param forcaExcecao
	 *            the forca excecao
	 * @param excecao
	 *            the excecao
	 */
	public TamanhoLinhaInvalidoException(boolean forcaExcecao, Throwable excecao) {

		super(forcaExcecao, excecao);
	}

	/**
	 * Cria uma nova instância de tamanho linha invalido exception.
	 * 
	 * @param chave
	 *            the chave
	 * @param parametros
	 *            the parametros
	 */
	public TamanhoLinhaInvalidoException(String chave, Object... parametros) {

		super(chave, parametros);
	}

	/**
	 * Cria uma nova instância de tamanho linha invalido exception.
	 * 
	 * @param chave
	 *            the chave
	 * @param parametros
	 *            the parametros
	 * @param excecao
	 *            the excecao
	 */
	public TamanhoLinhaInvalidoException(String chave, Object[] parametros, Throwable excecao) {

		super(chave, parametros, excecao);
	}

	/**
	 * Cria uma nova instância de tamanho linha invalido exception.
	 * 
	 * @param chave
	 *            the chave
	 * @param excecao
	 *            the excecao
	 */
	public TamanhoLinhaInvalidoException(String chave, Throwable excecao) {

		super(chave, excecao);
	}

	/**
	 * Cria uma nova instância de tamanho linha invalido exception.
	 * 
	 * @param chave
	 *            the chave
	 */
	public TamanhoLinhaInvalidoException(String chave) {

		super(chave);
	}

	/**
	 * Cria uma nova instância de tamanho linha invalido exception.
	 * 
	 * @param excecao
	 *            the excecao
	 */
	public TamanhoLinhaInvalidoException(Throwable excecao) {

		super(excecao);
	}

	
}