/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exce��o utilizada para indicar que a data da ocorr�ncia deve ser menor ou igual a data da
 * anota��o.
 * @author erico.junior
 */
public class DataOcorrenciaException extends CAPESCadastroNegocioException {
	
	/** Serial UID.*/
	private static final long serialVersionUID = -3649876422411370842L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN005";

	/**
	 * {@inheritDoc}
	 */	
	public DataOcorrenciaException() {
		super(CHAVE_MSG);
	}

}
