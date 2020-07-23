package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * A Classe TipoTelefoneNaoPermitidoException.
 */
public class TipoTelefoneNaoPermitidoException extends CAPESCadastroNegocioException {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -8354320013766246683L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN194";

	/**
	 * Instancia um novo TipoTelefoneNaoPermitidoException.
	 *
	 * @param parametros o valor de parametros
	 */
	public TipoTelefoneNaoPermitidoException(Object... parametros) {
		super(CHAVE_MSG, parametros);
	}
}