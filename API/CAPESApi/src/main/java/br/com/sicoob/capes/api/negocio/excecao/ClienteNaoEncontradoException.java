/*
 * SICOOB
 * 
 * ClienteNaoEncontradoException.java(br.com.sicoob.capes.api.negocio.excecao.ClienteNaoEncontradoException)
 */
package br.com.sicoob.capes.api.negocio.excecao;

import br.com.bancoob.persistencia.excecao.PersistenciaException;

/**
 * Exce��o utilizada quando o cliente n�o foi encontrado. 
 * Esta � uma exce��o de persist�ncia.
 * @author erico.junior
 */
public class ClienteNaoEncontradoException extends PersistenciaException {

	/** Serial UID. */
	private static final long serialVersionUID = 7618735849859901094L;

	/**
	 * Construtor da Exce��o.
	 * @param excecao A exce��o de origem.
	 */
	public ClienteNaoEncontradoException(Exception excecao) {
		super(excecao);
	}
}
