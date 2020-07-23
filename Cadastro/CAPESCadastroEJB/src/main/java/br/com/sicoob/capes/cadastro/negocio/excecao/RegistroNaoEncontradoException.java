/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

import br.com.bancoob.persistencia.excecao.PersistenciaException;

/**
 * Exceção utilizada quando o registro não foi encontrado. 
 * Esta é uma exceção de persistência. 
 * @author Erico.Junior
 */
public class RegistroNaoEncontradoException extends PersistenciaException {

	/** Serial UID.*/
	private static final long serialVersionUID = 5833278155585906181L;

	/**
	 * Construtor da Exceção.
	 * @param excecao A exceção de origem.
	 */
	public RegistroNaoEncontradoException(Exception excecao) {
		super(excecao);
	}

}
