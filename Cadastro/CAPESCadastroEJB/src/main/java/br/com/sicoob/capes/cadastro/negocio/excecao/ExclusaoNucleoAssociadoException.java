package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * A Classe ExclusaoNucleoAssociadoException.
 */
public class ExclusaoNucleoAssociadoException extends CAPESCadastroNegocioException {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 9068698734205602489L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN200";

	/**
	 * Instancia um novo ExclusaoNucleoAssociadoException.
	 */
	public ExclusaoNucleoAssociadoException() {
		super(CHAVE_MSG);
	}
	
	public ExclusaoNucleoAssociadoException(Throwable e) {
		super(CHAVE_MSG, e);
	}

}