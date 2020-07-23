package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * A Classe PossuiRegraCadastralRestritivaException.
 */
public class PossuiRegraCadastralRestritivaException extends CAPESCadastroNegocioException {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -3995698405237879897L;
	
	/** A constante CHAVE_MENSAGEM. */
	private static final String CHAVE_MENSAGEM = "";

	/**
	 * Instancia um novo PossuiRegraCadastralRestritivaException.
	 */
	public PossuiRegraCadastralRestritivaException() {
		super(CHAVE_MENSAGEM);
	}
	
	/**
	 * Instancia um novo PossuiRegraCadastralRestritivaException.
	 *
	 * @param excecao o valor de excecao
	 */
	public PossuiRegraCadastralRestritivaException(Throwable excecao) {
		super(CHAVE_MENSAGEM, excecao);
	}

}
