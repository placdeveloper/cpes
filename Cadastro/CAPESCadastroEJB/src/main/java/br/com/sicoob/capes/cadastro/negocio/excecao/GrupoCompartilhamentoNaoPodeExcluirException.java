/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * @author erico.junior
 *
 */
public class GrupoCompartilhamentoNaoPodeExcluirException extends CAPESCadastroNegocioException {

	/** Serial UID.*/
	private static final long serialVersionUID = -4993586576692632955L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN146";

	/**
	 * {@inheritDoc}
	 */
	public GrupoCompartilhamentoNaoPodeExcluirException() {
		super(CHAVE_MSG);
	}
}
