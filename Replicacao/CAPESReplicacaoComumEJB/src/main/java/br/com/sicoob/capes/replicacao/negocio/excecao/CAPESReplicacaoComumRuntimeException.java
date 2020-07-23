// 30/04/2013 - 16:02:14
package br.com.sicoob.capes.replicacao.negocio.excecao;

import br.com.bancoob.excecao.BancoobRuntimeException;

/**
 * @author Rodrigo.Chaves
 */
public class CAPESReplicacaoComumRuntimeException extends BancoobRuntimeException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -4983934314181187772L;

	/**
	 * Construtor
	 * 
	 * @param excecao
	 */
	public CAPESReplicacaoComumRuntimeException(Exception excecao) {
		super(excecao);
	}

	/**
	 * Construtor
	 * 
	 * @param mensagem
	 * @param excecao
	 */
	public CAPESReplicacaoComumRuntimeException(String mensagem, Exception excecao) {
		super(mensagem, excecao);
	}

	/**
	 * Construtor
	 * 
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public CAPESReplicacaoComumRuntimeException(String chave, Object[] parametros,
			Exception excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * Construtor
	 * 
	 * @param chave
	 * @param parametros
	 */
	public CAPESReplicacaoComumRuntimeException(String chave, Object[] parametros) {
		super(chave, parametros);
	}

	/**
	 * Construtor
	 * 
	 * @param mensagem
	 */
	public CAPESReplicacaoComumRuntimeException(String mensagem) {
		super(mensagem);
	}

}
