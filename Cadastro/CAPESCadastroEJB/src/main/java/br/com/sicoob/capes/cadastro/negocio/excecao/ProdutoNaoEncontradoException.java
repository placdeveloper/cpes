/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;


/**
 * @author Rodrigo.Chaves
 */
public class ProdutoNaoEncontradoException extends CAPESCadastroNegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 5210721649896466945L;

	/** A constante CODIGO_MENSAGEM. */
	private static final String CODIGO_MENSAGEM = "MN137";

	/**
	 * Instancia um novo ProdutoNaoEncontradoException.
	 *
	 * @param informacaoDesejada o valor de informacao desejada
	 * @param nomeProduto o valor de nome produto
	 */
	public ProdutoNaoEncontradoException(String informacaoDesejada, String nomeProduto) {
		
		super(CODIGO_MENSAGEM, informacaoDesejada, nomeProduto);
	}

	/**
	 * Instancia um novo ProdutoNaoEncontradoException.
	 *
	 * @param excecao o valor de excecao
	 * @param informacaoDesejada o valor de informacao desejada
	 * @param nomeProduto o valor de nome produto
	 */
	public ProdutoNaoEncontradoException(
			Throwable excecao, String informacaoDesejada, String nomeProduto) {

		super(CODIGO_MENSAGEM, new String[] {informacaoDesejada, nomeProduto}, excecao);
	}

}
