package br.com.sicoob.capes.cadastro.negocio.excecao;


/**
 * A Classe ClienteNaoCadastradoException.
 */
public class ClienteNaoCadastradoException extends CAPESCadastroNegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 68596807700754807L;
	
	/** A constante CHAVE_MENSAGEM. */
	private static final String CHAVE_MENSAGEM = "MN150";

	/**
	 * Instancia um novo ClienteNaoCadastradoException.
	 */
	public ClienteNaoCadastradoException() {
		super(CHAVE_MENSAGEM);
	}
	
	/**
	 * Instancia um novo ClienteNaoCadastradoException.
	 *
	 * @param excecao o valor de excecao
	 */
	public ClienteNaoCadastradoException(Throwable excecao) {
		super(CHAVE_MENSAGEM, excecao);
	}


}
