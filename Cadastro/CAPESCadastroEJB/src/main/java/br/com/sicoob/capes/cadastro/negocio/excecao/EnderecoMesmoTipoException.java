/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * Exceção utilizada para mais de um endereço do mesmo tipo para uma pessoa.
 * 
 * @author erico.junior
 */
@ApplicationException (rollback = true)
public class EnderecoMesmoTipoException extends CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = -1207478626868595441L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN123";

	/**
	 * Construtor da exceção.
	 * 
	 * @param parametro
	 *            O tipo de endereço que não pode ser repetido.
	 */
	public EnderecoMesmoTipoException(String parametro) {
		super(CHAVE_MSG, parametro);
	}

}