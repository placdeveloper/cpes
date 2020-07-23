package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * A Classe CodigoCnaeInvalidoException.
 */
public class CodigoCnaeInvalidoException extends CAPESCadastroNegocioException {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 2581891869348959648L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN169";

	/**
	 * Instancia um novo CodigoCnaeInvalidoException.
	 */
	public CodigoCnaeInvalidoException() {
		super(CHAVE_MSG);
	}
}