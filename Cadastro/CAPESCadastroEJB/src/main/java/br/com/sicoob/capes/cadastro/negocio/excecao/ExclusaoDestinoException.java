package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * A Classe ExclusaoDestinoException.
 */
public class ExclusaoDestinoException extends CAPESCadastroNegocioException {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -8024566815298582465L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN196";

	/**
	 * Instancia um novo ExclusaoDestinoException.
	 */
	public ExclusaoDestinoException() {
		super(CHAVE_MSG);
	}
	
	/**
	 * Instancia um novo ExclusaoDestinoException.
	 */
	public ExclusaoDestinoException(Throwable e) {
		super(CHAVE_MSG, e);
	}

}