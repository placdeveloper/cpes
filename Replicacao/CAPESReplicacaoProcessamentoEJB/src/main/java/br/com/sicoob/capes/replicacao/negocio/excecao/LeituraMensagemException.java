package br.com.sicoob.capes.replicacao.negocio.excecao;

import javax.ejb.ApplicationException;
import javax.jms.JMSException;
import javax.jms.Message;

/**
 * Exceção lançada quando ocorre um erro na leitura de uma {@link Message}.
 * 
 * @see JMSException
 * @author Rodrigo.Chaves
 */
@ApplicationException(rollback = true)
public class LeituraMensagemException extends CAPESReplicacaoProcessamentoRuntimeException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 5198256728405020414L;

	/**
	 * Instancia um novo LeituraMensagemException.
	 *
	 * @param excecao o valor de excecao
	 * @param mensagem o valor de mensagem
	 */
	public LeituraMensagemException(JMSException excecao, String mensagem) {
		super("erro.leitura.mensagem", new Object[] { mensagem }, excecao);
	}

}
