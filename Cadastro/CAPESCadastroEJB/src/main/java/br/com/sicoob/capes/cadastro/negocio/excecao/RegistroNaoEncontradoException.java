/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

import br.com.bancoob.persistencia.excecao.PersistenciaException;

/**
 * Exce��o utilizada quando o registro n�o foi encontrado. 
 * Esta � uma exce��o de persist�ncia. 
 * @author Erico.Junior
 */
public class RegistroNaoEncontradoException extends PersistenciaException {

	/** Serial UID.*/
	private static final long serialVersionUID = 5833278155585906181L;

	/**
	 * Construtor da Exce��o.
	 * @param excecao A exce��o de origem.
	 */
	public RegistroNaoEncontradoException(Exception excecao) {
		super(excecao);
	}

}
