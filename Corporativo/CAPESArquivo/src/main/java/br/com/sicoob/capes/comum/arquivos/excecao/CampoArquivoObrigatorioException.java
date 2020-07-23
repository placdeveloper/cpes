/*
 * SICOOB
 * 
 * CampoArquivoObrigatorioException.java(br.com.sicoob.capes.comum.arquivos.excecao.CampoArquivoObrigatorioException)
 */
package br.com.sicoob.capes.comum.arquivos.excecao;

/**
 * Classe de exce��o da hierarquia de exce��es do componente de importa��o e exporta��o de arquivos texto
 * (capes-arquivo) que indica que um campo obigat�rio n�o foi encontrado no arquivo.
 * 
 * Criado em 21/7/2014
 * 
 * @author rodrigo.chaves
 */
public class CampoArquivoObrigatorioException extends ArquivoInvalidoException {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -6849694727583708571L;

	/**
	 * Cria uma nova inst�ncia de campo arquivo obrigatorio exception.
	 * 
	 * @param forcaExcecao
	 *            the forca excecao
	 * @param chave
	 *            the chave
	 * @param parametros
	 *            the parametros
	 */
	public CampoArquivoObrigatorioException(boolean forcaExcecao, String chave, Object... parametros) {

		super(forcaExcecao, chave, parametros);
	}

	/**
	 * Cria uma nova inst�ncia de campo arquivo obrigatorio exception.
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
	public CampoArquivoObrigatorioException(boolean forcaExcecao, String chave, Object[] parametros, Throwable excecao) {

		super(forcaExcecao, chave, parametros, excecao);
	}

	/**
	 * Cria uma nova inst�ncia de campo arquivo obrigatorio exception.
	 * 
	 * @param forcaExcecao
	 *            the forca excecao
	 * @param chave
	 *            the chave
	 * @param excecao
	 *            the excecao
	 */
	public CampoArquivoObrigatorioException(boolean forcaExcecao, String chave, Throwable excecao) {

		super(forcaExcecao, chave, excecao);
	}

	/**
	 * Cria uma nova inst�ncia de campo arquivo obrigatorio exception.
	 * 
	 * @param forcaExcecao
	 *            the forca excecao
	 * @param chave
	 *            the chave
	 */
	public CampoArquivoObrigatorioException(boolean forcaExcecao, String chave) {

		super(forcaExcecao, chave);
	}

	/**
	 * Cria uma nova inst�ncia de campo arquivo obrigatorio exception.
	 * 
	 * @param forcaExcecao
	 *            the forca excecao
	 * @param excecao
	 *            the excecao
	 */
	public CampoArquivoObrigatorioException(boolean forcaExcecao, Throwable excecao) {

		super(forcaExcecao, excecao);
	}

	/**
	 * Cria uma nova inst�ncia de campo arquivo obrigatorio exception.
	 * 
	 * @param chave
	 *            the chave
	 * @param parametros
	 *            the parametros
	 */
	public CampoArquivoObrigatorioException(String chave, Object... parametros) {

		super(chave, parametros);
	}

	/**
	 * Cria uma nova inst�ncia de campo arquivo obrigatorio exception.
	 * 
	 * @param chave
	 *            the chave
	 * @param parametros
	 *            the parametros
	 * @param excecao
	 *            the excecao
	 */
	public CampoArquivoObrigatorioException(String chave, Object[] parametros, Throwable excecao) {

		super(chave, parametros, excecao);
	}

	/**
	 * Cria uma nova inst�ncia de campo arquivo obrigatorio exception.
	 * 
	 * @param chave
	 *            the chave
	 * @param excecao
	 *            the excecao
	 */
	public CampoArquivoObrigatorioException(String chave, Throwable excecao) {

		super(chave, excecao);
	}

	/**
	 * Cria uma nova inst�ncia de campo arquivo obrigatorio exception.
	 * 
	 * @param chave
	 *            the chave
	 */
	public CampoArquivoObrigatorioException(String chave) {

		super(chave);
	}

	/**
	 * Cria uma nova inst�ncia de campo arquivo obrigatorio exception.
	 * 
	 * @param excecao
	 *            the excecao
	 */
	public CampoArquivoObrigatorioException(Throwable excecao) {

		super(excecao);
	}

}