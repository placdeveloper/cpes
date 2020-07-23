package br.com.sicoob.capes.cadastro.negocio.excecao;


/**
 * A Classe CapitalSocialInvalidoException.
 */
public class CapitalSocialInvalidoException extends CAPESCadastroNegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -5558404834607735226L;
	
	/** A constante CODIGO_MENSAGEM. */
	private static final String CODIGO_MENSAGEM = "MN139";
	
	/**
	 * Instancia um novo CapitalSocialInvalidoException.
	 */
	public CapitalSocialInvalidoException() {
		super(CODIGO_MENSAGEM);
	}

	/**
	 * Instancia um novo CapitalSocialInvalidoException.
	 *
	 * @param excecao o valor de excecao
	 */
	public CapitalSocialInvalidoException(Throwable excecao) {
		super(CODIGO_MENSAGEM, excecao);
	}

}
