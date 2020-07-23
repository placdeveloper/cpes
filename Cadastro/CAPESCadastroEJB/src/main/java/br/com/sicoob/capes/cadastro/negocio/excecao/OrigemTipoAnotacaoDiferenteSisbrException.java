/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exce��o quando a origem do tipo da anota��o for diferente de SISBR.
 * 
 * @author erico.junior
 */
public class OrigemTipoAnotacaoDiferenteSisbrException extends
		CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = -1997424730318630992L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN022";

	/**
	 * Instancia um novo OrigemTipoAnotacaoDiferenteSisbrException.
	 */
	public OrigemTipoAnotacaoDiferenteSisbrException() {
		super(CHAVE_MSG);
	}
}
