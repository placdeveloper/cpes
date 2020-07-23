/* 
 * Sicoob
 * EntidadeExistenteException.java 
 * Criado em: 12/05/2011
 */
package br.com.sicoob.capes.cadastro.persistencia.excecao;

import br.com.bancoob.persistencia.excecao.PersistenciaException;

/**
 * Exceção lançada quando, ao tentar realizar uma inclusão, utiliza-se um ID já existente na base de
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
