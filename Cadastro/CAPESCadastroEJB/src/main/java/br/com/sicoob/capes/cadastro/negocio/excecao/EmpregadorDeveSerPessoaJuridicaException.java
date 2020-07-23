/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * @author Erico.Junior
 *
 */
public class EmpregadorDeveSerPessoaJuridicaException extends CAPESCadastroNegocioException {
	
	/** Serial UID.*/
	private static final long serialVersionUID = 2858470755100994129L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN038";

	/**
	 * {@inheritDoc}
	 */	
	public EmpregadorDeveSerPessoaJuridicaException() {
		super(CHAVE_MSG);
	}

}
