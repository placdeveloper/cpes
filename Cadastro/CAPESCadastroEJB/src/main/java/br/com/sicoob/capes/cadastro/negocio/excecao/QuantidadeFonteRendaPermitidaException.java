package br.com.sicoob.capes.cadastro.negocio.excecao;

public class QuantidadeFonteRendaPermitidaException extends CAPESCadastroNegocioException {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 3179099234449919189L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN208";

	/**
	 * Instancia um novo RegistroJaCadastradoException.
	 */
	public QuantidadeFonteRendaPermitidaException() {
		super(CHAVE_MSG);
	}

}
