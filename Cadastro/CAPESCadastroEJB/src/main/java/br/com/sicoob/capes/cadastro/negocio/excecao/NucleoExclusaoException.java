/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * @author Erico.Junior
 * 
 */
public class NucleoExclusaoException extends
		CAPESCadastroNegocioException {
	
	/** Serial UID. */
	private static final long serialVersionUID = 4831020927380991776L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN041";

	/**
	 * {@inheritDoc}
	 */
	public NucleoExclusaoException(String texto) {
		super(CHAVE_MSG, texto);
	}
}
