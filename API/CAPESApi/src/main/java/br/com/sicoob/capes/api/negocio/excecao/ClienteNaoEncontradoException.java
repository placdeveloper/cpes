/*
 * SICOOB
 * 
 * ClienteNaoEncontradoException.java(br.com.sicoob.capes.api.negocio.excecao.ClienteNaoEncontradoException)
 */
package br.com.sicoob.capes.api.negocio.excecao;

import br.com.bancoob.persistencia.excecao.PersistenciaException;

/**
 * Exceção utilizada quando o cliente não foi encontrado. 
 * Esta é uma exceção de persistência.
 * @author erico.junior
 */
public class ClienteNaoEncontradoException extends PersistenciaException {

	/** Serial UID. */
	private static final long serialVersionUID = 7618735849859901094L;

	/**
	 * Construtor da Exceção.
	 * @param excecao A exceção de origem.
	 */
	public ClienteNaoEncontradoException(Exception excecao) {
		super(excecao);
	}
}
