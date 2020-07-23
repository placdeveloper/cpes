package br.com.sicoob.capes.api.inclusao.negocio.excecao;

/**
 * A Classe PropriedadeObrigatoriaNaoInformadaException.
 * 
 * @author bruno.carneiro
 */
public class PropriedadeObrigatoriaNaoInformadaException extends CAPESApiInclusaoNegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -1314690964683062264L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "msg.erro.propriedades";

	/**
	 * Instancia um novo PropriedadeObrigatoriaNaoInformadaException.
	 */
	public PropriedadeObrigatoriaNaoInformadaException(String propriedades) {
		super(CHAVE_MSG, propriedades);
	}

}