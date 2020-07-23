package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exceção para a validação do CEP
 */
public class CEPInvalidoException extends CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = -7323375784871475626L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN166";

	/**
	 * Construtor da exceção.
	 */
	public CEPInvalidoException() {
		super(CHAVE_MSG);
	}
}