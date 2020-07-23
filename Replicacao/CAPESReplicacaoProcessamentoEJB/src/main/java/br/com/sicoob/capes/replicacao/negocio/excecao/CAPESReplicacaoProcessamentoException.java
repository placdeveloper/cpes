package br.com.sicoob.capes.replicacao.negocio.excecao;

import br.com.bancoob.excecao.BancoobException;

/**
 * Excecao padrao para o sistema ReplicacaoClientesProcessamento.
 * 
 * @author Stefanini IT Solutions
 */
public class CAPESReplicacaoProcessamentoException extends BancoobException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	private CAPESReplicacaoProcessamentoException(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	private CAPESReplicacaoProcessamentoException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * @param chave
	 * @param excecao
	 */
	private CAPESReplicacaoProcessamentoException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * @param excecao
	 */
	private CAPESReplicacaoProcessamentoException(Throwable excecao) {
		super(excecao);
	}
}
