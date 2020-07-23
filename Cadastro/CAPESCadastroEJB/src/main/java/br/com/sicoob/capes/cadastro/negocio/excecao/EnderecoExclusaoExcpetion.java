/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * @author erico.junior
 * 
 */
public class EnderecoExclusaoExcpetion extends
		CAPESCadastroNegocioException {

	/** Serial UID. */	private static final long serialVersionUID = -6721746900163614236L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN152";

	/**
	 * {@inheritDoc}
	 */
	public EnderecoExclusaoExcpetion() {
		super(CHAVE_MSG);
	}

}
