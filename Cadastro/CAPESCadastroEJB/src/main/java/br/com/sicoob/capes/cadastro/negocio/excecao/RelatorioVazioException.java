package br.com.sicoob.capes.cadastro.negocio.excecao;


/**
 * A Classe RelatorioVazioException.
 */
public class RelatorioVazioException extends CAPESCadastroNegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -753198895995383918L;
	
	/** A constante CHAVE_MENSAGEM. */
	private static final String CHAVE_MENSAGEM = "MN149";
	
	/**
	 * Instancia um novo RelatorioVazioException.
	 */
	public RelatorioVazioException() {
		super(CHAVE_MENSAGEM);
	}
}
