package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * A Classe LimitarUsoAnotacaoCooperativaException.
 */
public class LimitarUsoAnotacaoCooperativaException extends CAPESCadastroNegocioException {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -650923517791712307L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN201";

	/**
	 * Instancia um novo LimitarUsoAnotacaoCooperativaException.
	 */
	public LimitarUsoAnotacaoCooperativaException() {
		super(CHAVE_MSG);
	}

}