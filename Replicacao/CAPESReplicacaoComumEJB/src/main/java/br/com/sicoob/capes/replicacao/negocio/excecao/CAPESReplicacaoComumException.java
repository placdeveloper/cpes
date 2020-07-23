/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.excecao;

import br.com.bancoob.excecao.BancoobException;

/**
 * @author Erico.Junior
 *
 */
public class CAPESReplicacaoComumException extends BancoobException {

	/** Serial UID. */
	private static final long serialVersionUID = -6301485960237864826L;

	/**
	 * @param chave
	 */
	public CAPESReplicacaoComumException(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public CAPESReplicacaoComumException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * @param chave
	 * @param excecao
	 */
	public CAPESReplicacaoComumException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * @param excecao
	 */
	public CAPESReplicacaoComumException(Throwable excecao) {
		super(excecao);
	}

}
