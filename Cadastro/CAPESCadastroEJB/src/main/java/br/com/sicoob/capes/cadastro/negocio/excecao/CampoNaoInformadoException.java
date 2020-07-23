/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

import javax.ejb.ApplicationException;


/**
 * Exceção para o campo obrigatório não informado. 
 * @author Erico.Junior
 */
@ApplicationException (rollback = true)
public class CampoNaoInformadoException extends CAPESCadastroNegocioException {

	/** Serial UID.*/
	private static final long serialVersionUID = -1628271640775852961L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN020";
	
	/**
	 * Instancia um novo CampoNaoInformadoException.
	 *
	 * @param parametro o valor de parametro
	 */
	public CampoNaoInformadoException(String parametro) {
		super(CHAVE_MSG, parametro);
	}
}
