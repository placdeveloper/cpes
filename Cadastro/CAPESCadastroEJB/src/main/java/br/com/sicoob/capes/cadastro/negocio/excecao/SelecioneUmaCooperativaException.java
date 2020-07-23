package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * A Classe SelecioneUmaCooperativaException.
 */
public class SelecioneUmaCooperativaException extends CAPESCadastroNegocioException {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 9068698734205602489L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN199";

	/**
	 * Instancia um novo SelecioneUmaCooperativaException.
	 */
	public SelecioneUmaCooperativaException() {
		super(CHAVE_MSG);
	}

}
