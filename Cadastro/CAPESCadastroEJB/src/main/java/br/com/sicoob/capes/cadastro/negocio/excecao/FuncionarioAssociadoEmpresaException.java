package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * A Classe FuncionarioAssociadoEmpresaException.
 */
public class FuncionarioAssociadoEmpresaException extends CAPESCadastroNegocioException {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -8184270708439812044L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN189";

	/**
	 * Instancia um novo FuncionarioAssociadoEmpresaException.
	 */
	public FuncionarioAssociadoEmpresaException() {
		super(CHAVE_MSG);
	}
}