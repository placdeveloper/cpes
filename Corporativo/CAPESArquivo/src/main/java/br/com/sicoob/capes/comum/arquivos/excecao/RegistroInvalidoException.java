/*
 * SICOOB
 * 
 * RegistroInvalidoException.java(br.com.sicoob.capes.comum.arquivos.excecao.RegistroInvalidoException)
 */
package br.com.sicoob.capes.comum.arquivos.excecao;

/**
 * Classe de exceção da hierarquia de exceções do componente de importação e
 * exportação de arquivos texto (capes-arquivo) que indica algum problema
 * no rodapé do arquivo.
 * <br><br>
 *
 * Criado em 21/7/2014.
 * @author rodrigo.chaves
 */
public class RegistroInvalidoException extends ArquivoInvalidoException {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 6707078910158778981L;

	/**
	 * Cria uma nova instância de registro invalido exception.
	 * 
	 * @param forcaExcecao
	 *            the forca excecao
	 * @param chave
	 *            the chave
	 * @param parametros
	 *            the parametros
	 */
	public RegistroInvalidoException(boolean forcaExcecao, String chave, Object... parametros) {

		super(forcaExcecao, chave, parametros);
	}

	/**
	 * Cria uma nova instância de registro invalido exception.
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
	public RegistroInvalidoException(boolean forcaExcecao, String chave, Object[] parametros, Throwable excecao) {

		super(forcaExcecao, chave, parametros, excecao);
	}

	/**
	 * Cria uma nova instância de registro invalido exception.
	 * 
	 * @param forcaExcecao
	 *            the forca excecao
	 * @param chave
	 *            the chave
	 * @param excecao
	 *            the excecao
	 */
	public RegistroInvalidoException(boolean forcaExcecao, String chave, Throwable excecao) {

		super(forcaExcecao, chave, excecao);
	}

	/**
	 * Cria uma nova instância de registro invalido exception.
	 * 
	 * @param forcaExcecao
	 *            the forca excecao
	 * @param chave
	 *            the chave
	 */
	public RegistroInvalidoException(boolean forcaExcecao, String chave) {

		super(forcaExcecao, chave);
	}

	/**
	 * Cria uma nova instância de registro invalido exception.
	 * 
	 * @param forcaExcecao
	 *            the forca excecao
	 * @param excecao
	 *            the excecao
	 */
	public RegistroInvalidoException(boolean forcaExcecao, Throwable excecao) {

		super(forcaExcecao, excecao);
	}

	/**
	 * Cria uma nova instância de registro invalido exception.
	 * 
	 * @param chave
	 *            the chave
	 * @param parametros
	 *            the parametros
	 */
	public RegistroInvalidoException(String chave, Object... parametros) {

		super(chave, parametros);
	}

	/**
	 * Cria uma nova instância de registro invalido exception.
	 * 
	 * @param chave
	 *            the chave
	 * @param parametros
	 *            the parametros
	 * @param excecao
	 *            the excecao
	 */
	public RegistroInvalidoException(String chave, Object[] parametros, Throwable excecao) {

		super(chave, parametros, excecao);
	}

	/**
	 * Cria uma nova instância de registro invalido exception.
	 * 
	 * @param chave
	 *            the chave
	 * @param excecao
	 *            the excecao
	 */
	public RegistroInvalidoException(String chave, Throwable excecao) {

		super(chave, excecao);
	}

	/**
	 * Cria uma nova instância de registro invalido exception.
	 * 
	 * @param chave
	 *            the chave
	 */
	public RegistroInvalidoException(String chave) {

		super(chave);
	}

	/**
	 * Cria uma nova instância de registro invalido exception.
	 * 
	 * @param excecao
	 *            the excecao
	 */
	public RegistroInvalidoException(Throwable excecao) {

		super(excecao);
	}


}