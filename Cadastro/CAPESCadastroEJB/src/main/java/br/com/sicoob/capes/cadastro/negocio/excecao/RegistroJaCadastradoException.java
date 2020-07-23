package br.com.sicoob.capes.cadastro.negocio.excecao;


/**
 * A Classe RegistroJaCadastradoException.
 */
public class RegistroJaCadastradoException extends CAPESCadastroNegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 3352874888729230219L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN192";

	/**
	 * Instancia um novo RegistroJaCadastradoException.
	 */
	public RegistroJaCadastradoException() {
		super(CHAVE_MSG);
	}

	/**
	 * Instancia um novo RegistroJaCadastradoException
	 * 
	 * @param e
	 */
	public RegistroJaCadastradoException(Throwable e) {
		super(CHAVE_MSG, e);
	}

}