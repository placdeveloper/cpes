/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exce��o para o tipo da anota��o autom�tica n�o encontrado.
 * 
 * @author erico.junior
 */
public class TipoAnotacaoAutomaticaNaoEncontradoException extends
		CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = 6681704024266845736L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN021";

	/**
	 * Instancia um novo TipoAnotacaoAutomaticaNaoEncontradoException.
	 */
	public TipoAnotacaoAutomaticaNaoEncontradoException() {
		super(CHAVE_MSG);
	}

}
