/*
 * SICOOB
 * 
 * IdentificacaoGeracaoArquivoException.java(br.com.sicoob.capes.comum.arquivos.excecao.IdentificacaoGeracaoArquivoException)
 */
package br.com.sicoob.capes.comum.arquivos.excecao;

/**
 * Classe de exceção da hierarquia de exceções do componente de importação e exportação de arquivos texto
 * (capes-arquivo) que indica que o responsável pela geração do arquivo é inválido
 * 
 * Criado em 21/7/2014
 * 
 * @author rodrigo.chaves
 */
public class IdentificacaoGeracaoArquivoException extends ArquivoInvalidoException {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -1573984856167360762L;

	/**
	 * Cria uma nova instância de identificacao geracao arquivo exception.
	 * 
	 * @param forcaExcecao
	 *            the forca excecao
	 * @param chave
	 *            the chave
	 * @param parametros
	 *            the parametros
	 */
	public IdentificacaoGeracaoArquivoException(boolean forcaExcecao, String chave, Object... parametros) {

		super(forcaExcecao, chave, parametros);
	}

	/**
	 * Cria uma nova instância de identificacao geracao arquivo exception.
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
	public IdentificacaoGeracaoArquivoException(boolean forcaExcecao, String chave, Object[] parametros,
			Throwable excecao) {

		super(forcaExcecao, chave, parametros, excecao);
	}

	/**
	 * Cria uma nova instância de identificacao geracao arquivo exception.
	 * 
	 * @param forcaExcecao
	 *            the forca excecao
	 * @param chave
	 *            the chave
	 * @param excecao
	 *            the excecao
	 */
	public IdentificacaoGeracaoArquivoException(boolean forcaExcecao, String chave, Throwable excecao) {

		super(forcaExcecao, chave, excecao);
	}

	/**
	 * Cria uma nova instância de identificacao geracao arquivo exception.
	 * 
	 * @param forcaExcecao
	 *            the forca excecao
	 * @param chave
	 *            the chave
	 */
	public IdentificacaoGeracaoArquivoException(boolean forcaExcecao, String chave) {

		super(forcaExcecao, chave);
	}

	/**
	 * Cria uma nova instância de identificacao geracao arquivo exception.
	 * 
	 * @param forcaExcecao
	 *            the forca excecao
	 * @param excecao
	 *            the excecao
	 */
	public IdentificacaoGeracaoArquivoException(boolean forcaExcecao, Throwable excecao) {

		super(forcaExcecao, excecao);
	}

	/**
	 * Cria uma nova instância de identificacao geracao arquivo exception.
	 * 
	 * @param chave
	 *            the chave
	 * @param parametros
	 *            the parametros
	 */
	public IdentificacaoGeracaoArquivoException(String chave, Object... parametros) {

		super(chave, parametros);
	}

	/**
	 * Cria uma nova instância de identificacao geracao arquivo exception.
	 * 
	 * @param chave
	 *            the chave
	 * @param parametros
	 *            the parametros
	 * @param excecao
	 *            the excecao
	 */
	public IdentificacaoGeracaoArquivoException(String chave, Object[] parametros, Throwable excecao) {

		super(chave, parametros, excecao);
	}

	/**
	 * Cria uma nova instância de identificacao geracao arquivo exception.
	 * 
	 * @param chave
	 *            the chave
	 * @param excecao
	 *            the excecao
	 */
	public IdentificacaoGeracaoArquivoException(String chave, Throwable excecao) {

		super(chave, excecao);
	}

	/**
	 * Cria uma nova instância de identificacao geracao arquivo exception.
	 * 
	 * @param chave
	 *            the chave
	 */
	public IdentificacaoGeracaoArquivoException(String chave) {

		super(chave);
	}

	/**
	 * Cria uma nova instância de identificacao geracao arquivo exception.
	 * 
	 * @param excecao
	 *            the excecao
	 */
	public IdentificacaoGeracaoArquivoException(Throwable excecao) {

		super(excecao);
	}

}