package br.com.sicoob.capes.api.negocio.excecao;

/**
 * A Classe TipoMensagemNaoInformadaException.
 */
public class TipoMensagemNaoInformadaException extends CAPESApiNegocioException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2662380399194133402L;
	/** A Constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN006";
	
	/**
	 * Cria uma nova instância de instituicao nao informada exception.
	 */
	public TipoMensagemNaoInformadaException(){
		super(CHAVE_MSG);
	}
	
}
