package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * A Classe FuncionarioAssociadoClienteHistoricoException.
 */
public class FuncionarioAssociadoClienteHistoricoException extends CAPESCadastroNegocioException {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 148995384972386156L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN203";

	/**
	 * Instancia um novo FuncionarioAssociadoClienteHistoricoException.
	 */
	public FuncionarioAssociadoClienteHistoricoException() {
		super(CHAVE_MSG);
	}

}