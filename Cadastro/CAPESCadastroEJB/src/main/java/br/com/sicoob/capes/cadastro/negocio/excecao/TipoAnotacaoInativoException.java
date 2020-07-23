/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exceção para tipos de anotações inativos.
 * 
 * @author Erico.Junior
 */
public class TipoAnotacaoInativoException extends CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = 8232243190296607974L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN009";

	/**
	 * {@inheritDoc}
	 */
	public TipoAnotacaoInativoException() {
		super(CHAVE_MSG);
	}

}
