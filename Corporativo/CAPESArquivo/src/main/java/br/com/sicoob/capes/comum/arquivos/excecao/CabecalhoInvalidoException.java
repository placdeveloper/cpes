/*
 * SICOOB
 * 
 * CabecalhoInvalidoException.java(br.com.sicoob.capes.comum.arquivos.excecao.CabecalhoInvalidoException)
 */
package br.com.sicoob.capes.comum.arquivos.excecao;

/**
 * Classe de exceção da hierarquia de exceções do componente de importação e
 * exportação de arquivos texto (capes-arquivo) que indica algum problema
 * no cabeçalho do arquivo.
 *
 * Criado em 21/7/2014
 * @author rodrigo.chaves
 */
public class CabecalhoInvalidoException extends RegistroInvalidoException {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -5142960964815606587L;

	/**
	 * Cria uma nova instância de cabecalho invalido exception.
	 * 
	 * @param forcaExcecao
	 *            the forca excecao
	 * @param chave
	 *            the chave
	 * @param parametros
	 *            the parametros
	 */
	public CabecalhoInvalidoException(boolean forcaExcecao, String chave, Object... parametros) {

		super(forcaExcecao, chave, parametros);
	}

	/**
	 * Cria uma nova instância de cabecalho invalido exception.
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
	public CabecalhoInvalidoException(boolean forcaExcecao, String chave, Object[] parametros, Throwable excecao) {

		super(forcaExcecao, chave, parametros, excecao);
	}

	/**
	 * Cria uma nova instância de cabecalho invalido exception.
	 * 
	 * @param forcaExcecao
	 *            the forca excecao
	 * @param chave
	 *            the chave
	 * @param excecao
	 *            the excecao
	 */
	public CabecalhoInvalidoException(boolean forcaExcecao, String chave, Throwable excecao) {

		super(forcaExcecao, chave, excecao);
	}

	/**
	 * Cria uma nova instância de cabecalho invalido exception.
	 * 
	 * @param forcaExcecao
	 *            the forca excecao
	 * @param chave
	 *            the chave
	 */
	public CabecalhoInvalidoException(boolean forcaExcecao, String chave) {

		super(forcaExcecao, chave);
	}

	/**
	 * Cria uma nova instância de cabecalho invalido exception.
	 * 
	 * @param forcaExcecao
	 *            the forca excecao
	 * @param excecao
	 *            the excecao
	 */
	public CabecalhoInvalidoException(boolean forcaExcecao, Throwable excecao) {

		super(forcaExcecao, excecao);
	}

	/**
	 * Cria uma nova instância de cabecalho invalido exception.
	 * 
	 * @param chave
	 *            the chave
	 * @param parametros
	 *            the parametros
	 */
	public CabecalhoInvalidoException(String chave, Object... parametros) {

		super(chave, parametros);
	}

	/**
	 * Cria uma nova instância de cabecalho invalido exception.
	 * 
	 * @param chave
	 *            the chave
	 * @param parametros
	 *            the parametros
	 * @param excecao
	 *            the excecao
	 */
	public CabecalhoInvalidoException(String chave, Object[] parametros, Throwable excecao) {

		super(chave, parametros, excecao);
	}

	/**
	 * Cria uma nova instância de cabecalho invalido exception.
	 * 
	 * @param chave
	 *            the chave
	 * @param excecao
	 *            the excecao
	 */
	public CabecalhoInvalidoException(String chave, Throwable excecao) {

		super(chave, excecao);
	}

	/**
	 * Cria uma nova instância de cabecalho invalido exception.
	 * 
	 * @param chave
	 *            the chave
	 */
	public CabecalhoInvalidoException(String chave) {

		super(chave);
	}

	/**
	 * Cria uma nova instância de cabecalho invalido exception.
	 * 
	 * @param excecao
	 *            the excecao
	 */
	public CabecalhoInvalidoException(Throwable excecao) {

		super(excecao);
	}

}