/* 
 * Sicoob
 * EntidadeExistenteException.java 
 * Criado em: 12/05/2011
 */
package br.com.sicoob.capes.cadastro.persistencia.excecao;

import br.com.bancoob.persistencia.excecao.PersistenciaException;

/**
 * Exce��o lan�ada quando, ao tentar realizar uma inclus�o, utiliza-se um ID j� existente na base de
 * dados.
 * 
 * 12/05/2011
 * 
 * @author Rodrigo.Chaves
 * 
 */
public class EntidadeExistenteException extends PersistenciaException {

	/** Serial UID */
	private static final long serialVersionUID = 7552171412061194519L;

	/**
	 * Construtor
	 * 
	 * @param excecao
	 */
	public EntidadeExistenteException(Exception excecao) {
		super(excecao);
	}

}
