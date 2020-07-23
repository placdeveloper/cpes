package br.com.sicoob.capes.replicacao.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * A Classe MensagemReplicacaoInacessivelException.
 */
@ApplicationException(rollback = true)
public class MensagemReplicacaoInacessivelException extends
		CAPESReplicacaoProcessamentoRuntimeException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 4307357522391010990L;
	
	/** A constante ERRO_MARCAR_MENSAGEM_REPLICACAO. */
	public static final String ERRO_MARCAR_MENSAGEM_REPLICACAO = "erro.marcar.mensagem.replicacao";
	
	/** A constante ERRO_OBTER_MENSAGENS_REPLICACAO. */
	public static final String ERRO_OBTER_MENSAGENS_REPLICACAO = "erro.obter.mensagens.replicacao";

	/**
	 * Instancia um novo MensagemReplicacaoInacessivelException.
	 *
	 * @param exception o valor de exception
	 * @param chaveMensagem o valor de chave mensagem
	 * @param parametros o valor de parametros
	 */
	public MensagemReplicacaoInacessivelException(Exception exception, String chaveMensagem,
			Object... parametros) {
		
		super(chaveMensagem, parametros, exception);
	}

	/**
	 * Instancia um novo MensagemReplicacaoInacessivelException.
	 *
	 * @param chave o valor de chave
	 * @param parametros o valor de parametros
	 */
	public MensagemReplicacaoInacessivelException(String chave, Object... parametros) {
		super(chave, parametros);
	}
}
