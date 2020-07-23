package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * A exceção telefone inválido
 */
public class TelefoneInvalidoException extends CAPESCadastroNegocioException {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -5111096680511592534L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN206";

	/**
	 * Construtor da exceção.
	 */
	public TelefoneInvalidoException() {
		super(CHAVE_MSG);
	}
}
