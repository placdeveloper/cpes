package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * A exce��o telefone inv�lido
 */
public class TelefoneInvalidoException extends CAPESCadastroNegocioException {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -5111096680511592534L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN206";

	/**
	 * Construtor da exce��o.
	 */
	public TelefoneInvalidoException() {
		super(CHAVE_MSG);
	}
}
