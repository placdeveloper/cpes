package br.com.sicoob.capes.cadastro.persistencia.excecao;

import br.com.bancoob.persistencia.excecao.PersistenciaException;

/**
 * A Classe ObterLockException.
 */
public class ObterLockException extends PersistenciaException{
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instancia um novo ObterLockException.
	 *
	 * @param e o valor de e
	 */
	public ObterLockException(Exception e){
		super(e);
	}

}