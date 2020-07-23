package br.com.sicoob.capes.replicacao.negocio.excecao;

import br.com.bancoob.excecao.BancoobRuntimeException;

/**
 * A Classe CAPESReplicacaoProcessamentoRuntimeException.
 */
public class CAPESReplicacaoProcessamentoRuntimeException extends BancoobRuntimeException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 2286452486824835764L;

	/**
	 * Instancia um novo CAPESReplicacaoProcessamentoRuntimeException.
	 *
	 * @param excecao o valor de excecao
	 */
	public CAPESReplicacaoProcessamentoRuntimeException(Exception excecao) {
		super(excecao);
	}

	/**
	 * Instancia um novo CAPESReplicacaoProcessamentoRuntimeException.
	 *
	 * @param mensagem o valor de mensagem
	 * @param excecao o valor de excecao
	 */
	public CAPESReplicacaoProcessamentoRuntimeException(String mensagem, Exception excecao) {
		super(mensagem, excecao);
	}

	/**
	 * Instancia um novo CAPESReplicacaoProcessamentoRuntimeException.
	 *
	 * @param chave o valor de chave
	 * @param parametros o valor de parametros
	 * @param excecao o valor de excecao
	 */
	public CAPESReplicacaoProcessamentoRuntimeException(String chave, Object[] parametros,
			Exception excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * Instancia um novo CAPESReplicacaoProcessamentoRuntimeException.
	 *
	 * @param chave o valor de chave
	 * @param parametros o valor de parametros
	 */
	public CAPESReplicacaoProcessamentoRuntimeException(String chave, Object[] parametros) {
		super(chave, parametros);
	}

	/**
	 * Instancia um novo CAPESReplicacaoProcessamentoRuntimeException.
	 *
	 * @param mensagem o valor de mensagem
	 */
	public CAPESReplicacaoProcessamentoRuntimeException(String mensagem) {
		super(mensagem);
	}

}
