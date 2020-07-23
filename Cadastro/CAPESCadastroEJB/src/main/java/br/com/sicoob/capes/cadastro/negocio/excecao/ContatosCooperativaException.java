package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Excessão para validar os contatos da cooperativa
 */
public class ContatosCooperativaException extends CAPESCadastroNegocioException {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -1890707280279371304L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN180";

	/**
	 * Instancia um novo ContatosCooperativaException.
	 */
	public ContatosCooperativaException() {
		super(CHAVE_MSG);
	}
}