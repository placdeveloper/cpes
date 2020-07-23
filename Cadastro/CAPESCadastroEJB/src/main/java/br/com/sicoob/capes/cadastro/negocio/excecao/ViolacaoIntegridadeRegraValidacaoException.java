package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * A Classe ViolacaoIntegridadeRegraValidacaoException.
 */
public class ViolacaoIntegridadeRegraValidacaoException extends CAPESCadastroNegocioException {

    /** A constante serialVersionUID. */
    private static final long serialVersionUID = 6966606527512214679L;
    
	/** A constante CHAVE_MENSAGEM. */
	public static final String CHAVE_MENSAGEM = "MN188";
	
	/**
	 * Instancia um novo ViolacaoIntegridadeRegraValidacaoException.
	 */
	public ViolacaoIntegridadeRegraValidacaoException() {
		super(CHAVE_MENSAGEM);
	}

	/**
	 * Instancia um novo ViolacaoIntegridadeRegraValidacaoException.
	 *
	 * @param parametros o valor de parametros
	 */
	public ViolacaoIntegridadeRegraValidacaoException(Object... parametros) {
		super(CHAVE_MENSAGEM, parametros);
	}

	/**
	 * Instancia um novo ViolacaoIntegridadeRegraValidacaoException.
	 *
	 * @param excecao o valor de excecao
	 */
	public ViolacaoIntegridadeRegraValidacaoException(Throwable excecao) {
		super(CHAVE_MENSAGEM, excecao);
	}

	/**
	 * Instancia um novo ViolacaoIntegridadeRegraValidacaoException.
	 *
	 * @param excecao o valor de excecao
	 * @param parametros o valor de parametros
	 */
	public ViolacaoIntegridadeRegraValidacaoException(Throwable excecao,
	        Object... parametros) {
		super(CHAVE_MENSAGEM, excecao, parametros);
	}

}
