/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * Agencia n�o encontrada na base do genext.
 * 
 * @author juan.damasceno
 */
@ApplicationException (rollback = true)
public class AgenciaInexistenteException extends CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = -3015735275372815421L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN013";

	/**
	 * Construtor da exce��o.
	 */
	public AgenciaInexistenteException() {
		super(CHAVE_MSG, "Ag�ncia");
	}
}
