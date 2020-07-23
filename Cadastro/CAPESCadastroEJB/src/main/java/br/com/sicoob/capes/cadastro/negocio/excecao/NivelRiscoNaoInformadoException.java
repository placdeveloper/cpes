package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * A Classe NivelRiscoNaoInformadoException.
 */
public class NivelRiscoNaoInformadoException extends CAPESCadastroNegocioException {

	/** Serial UID.*/
	private static final long serialVersionUID = -901591524214041595L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN170";

	/**
	 * {@inheritDoc}
	 */
	public NivelRiscoNaoInformadoException() {
		super(CHAVE_MSG);
	}	
}