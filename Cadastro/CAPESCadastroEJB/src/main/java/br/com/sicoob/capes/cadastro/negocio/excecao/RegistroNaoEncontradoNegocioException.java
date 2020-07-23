/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exce��o de neg�cio com a mensagemd e registro n�o encontrado.
 * 
 * @author Erico.Junior
 */
public class RegistroNaoEncontradoNegocioException extends
		CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = 2977150645249639957L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN013";

	/**
	 * {@inheritDoc}
	 */
	public RegistroNaoEncontradoNegocioException(String parametro) {
		super(CHAVE_MSG, parametro);
	}

	/**
	 * {@inheritDoc}
	 */
	public RegistroNaoEncontradoNegocioException(Throwable excecao, String parametro) {
		super(CHAVE_MSG, excecao, parametro);
	}	
}
