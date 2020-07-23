package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exceção para a exclusão de um produtor.
 */
public class ViolacaoIntegridadeProdutorException extends CAPESCadastroNegocioException {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 2161949416706454381L;

	/** A constante CODIGO_MENSAGEM. */
	private static final String CODIGO_MENSAGEM = "MN171";

	/**
	 * Instancia um novo ViolacaoIntegridadeProdutorException.
	 */
	public ViolacaoIntegridadeProdutorException() {
		super(CODIGO_MENSAGEM);
	}

}
