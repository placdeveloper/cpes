// 11/04/2013 - 16:35:27
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * @author Rodrigo.Chaves
 */
public class ProcessoNaoAceitaDocumentoException extends CAPESCadastroNegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 3774057568536605175L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN165";
	
	/**
	 * Instancia um novo ProcessoNaoAceitaDocumentoException.
	 */
	public ProcessoNaoAceitaDocumentoException() {
		super(CHAVE_MSG);
	}
}
