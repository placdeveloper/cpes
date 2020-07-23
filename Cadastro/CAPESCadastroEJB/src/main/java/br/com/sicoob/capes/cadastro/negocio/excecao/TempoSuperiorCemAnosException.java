/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * 
 * @author erico.junior
 *
 */
@ApplicationException (rollback = true)
public class TempoSuperiorCemAnosException extends CAPESCadastroNegocioException {

	/** Serial UID.*/
	private static final long serialVersionUID = -1207478626868595441L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN128";
	
	/**
	 * Construtor da exceção.
	 * @param parametro O nome do campo.
	 */
	public TempoSuperiorCemAnosException(String parametro) {
		super(CHAVE_MSG, parametro);
	}

}
