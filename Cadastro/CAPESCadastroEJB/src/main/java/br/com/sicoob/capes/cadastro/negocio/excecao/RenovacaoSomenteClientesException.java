package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * A Classe RenovacaoSomenteClientesException.
 */
public class RenovacaoSomenteClientesException extends CAPESCadastroNegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -2965993922887299249L;
	
	/** A constante CHAVE_MENSAGEM. */
	private static final String CHAVE_MENSAGEM = "MN183";

	/**
	 * Instancia um novo RenovacaoSomenteClientesException.
	 */
	public RenovacaoSomenteClientesException() {
		super(CHAVE_MENSAGEM);
	}
	
	/**
	 * Instancia um novo RenovacaoSomenteClientesException.
	 *
	 * @param excecao o valor de excecao
	 */
	public RenovacaoSomenteClientesException(Throwable excecao) {
		super(CHAVE_MENSAGEM, excecao);
	}

}