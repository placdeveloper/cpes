package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * A Classe ResultadosNaoEncontradosException.
 */
public class ResultadosNaoEncontradosException extends CAPESCadastroNegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -801155248897482941L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN179";

	/**
	 * Instancia um novo ResultadosNaoEncontradosException.
	 */
	public ResultadosNaoEncontradosException() {
		super(CHAVE_MSG);
	}
}
