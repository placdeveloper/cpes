package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * A Classe TipoAnotacaoPossuiRestricaoCooperativaException.
 */
public class TipoAnotacaoPossuiRestricaoCooperativaException extends CAPESCadastroNegocioException {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1538995694328350729L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN198";

	/**
	 * Instancia um novo TipoAnotacaoPossuiRestricaoCooperativaException.
	 */
	public TipoAnotacaoPossuiRestricaoCooperativaException() {
		super(CHAVE_MSG);
	}

}