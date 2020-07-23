// 01/07/2013 - 14:58:37
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * @author rodrigo.chaves
 */
public class AutorizacaoDuplicadaException extends CAPESCadastroNegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -4581669732521106035L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN167";

	/**
	 * Construtor
	 * 
	 * @param excecao
	 */
	public AutorizacaoDuplicadaException(Throwable excecao) {
		super(CHAVE_MSG, excecao);
	}

}
