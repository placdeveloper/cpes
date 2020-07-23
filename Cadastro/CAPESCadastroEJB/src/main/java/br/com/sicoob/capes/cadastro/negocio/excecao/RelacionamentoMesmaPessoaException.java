package br.com.sicoob.capes.cadastro.negocio.excecao;


/**
 * A Classe RelacionamentoMesmaPessoaException.
 */
public class RelacionamentoMesmaPessoaException extends CAPESCadastroNegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1562093807925773637L;
	
	/** A constante CODIGO_MENSAGEM. */
	private static final String CODIGO_MENSAGEM = "MN138";

	/**
	 * Instancia um novo RelacionamentoMesmaPessoaException.
	 */
	public RelacionamentoMesmaPessoaException() {
		super(CODIGO_MENSAGEM);
	}

	/**
	 * Instancia um novo RelacionamentoMesmaPessoaException.
	 *
	 * @param excecao o valor de excecao
	 */
	public RelacionamentoMesmaPessoaException(Throwable excecao) {
		super(CODIGO_MENSAGEM, excecao);
	}

}
