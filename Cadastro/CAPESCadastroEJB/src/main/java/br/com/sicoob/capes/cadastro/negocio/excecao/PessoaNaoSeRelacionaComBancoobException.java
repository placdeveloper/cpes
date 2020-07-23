package br.com.sicoob.capes.cadastro.negocio.excecao;


/**
 * A Classe PessoaNaoSeRelacionaComBancoobException.
 */
public class PessoaNaoSeRelacionaComBancoobException extends CAPESCadastroNegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -5596271009050720519L;
	
	/** A constante CHAVE_MENSAGEM. */
	private static final String CHAVE_MENSAGEM = "MN151";

	/**
	 * Instancia um novo PessoaNaoSeRelacionaComBancoobException.
	 */
	public PessoaNaoSeRelacionaComBancoobException() {
		super(CHAVE_MENSAGEM);
	}
	
	/**
	 * Instancia um novo PessoaNaoSeRelacionaComBancoobException.
	 *
	 * @param excecao o valor de excecao
	 */
	public PessoaNaoSeRelacionaComBancoobException(Throwable excecao) {
		super(CHAVE_MENSAGEM, excecao);
	}

}
