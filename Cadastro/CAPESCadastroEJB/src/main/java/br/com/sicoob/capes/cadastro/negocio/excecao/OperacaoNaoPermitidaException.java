package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * A Classe OperacaoNaoPermitidaException.
 */
public class OperacaoNaoPermitidaException extends CAPESCadastroNegocioException {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 3223202611433373423L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN181";

	/**
	 * Instancia um novo OperacaoNaoPermitidaException.
	 */
	public OperacaoNaoPermitidaException() {
		super(CHAVE_MSG);
	}
}