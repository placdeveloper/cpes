package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * A Classe PessoaPendenteAprovacaoException.
 */
public class PessoaPendenteAprovacaoException extends CAPESCadastroNegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -2965993922887299249L;
	
	/** A constante CHAVE_MENSAGEM. */
	private static final String CHAVE_MENSAGEM = "MN174";

	/**
	 * Instancia um novo PessoaPendenteAprovacaoException.
	 */
	public PessoaPendenteAprovacaoException() {
		super(CHAVE_MENSAGEM);
	}
	
	/**
	 * Instancia um novo PessoaPendenteAprovacaoException.
	 *
	 * @param excecao o valor de excecao
	 */
	public PessoaPendenteAprovacaoException(Throwable excecao) {
		super(CHAVE_MENSAGEM, excecao);
	}

}
