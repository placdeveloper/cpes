package br.com.sicoob.capes.replicacao.negocio.excecao;

/**
 * A Classe ProcessamentoMensagemException.
 */
public class ProcessamentoMensagemException extends CAPESReplicacaoProcessamentoRuntimeException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -6767993501646731411L;

	/**
	 * Instancia um novo ProcessamentoMensagemException.
	 *
	 * @param excecao o valor de excecao
	 * @param id o valor de id
	 */
	public ProcessamentoMensagemException(Exception excecao, Integer id) {
		super("erro.processamento.mensagem", new Object[] { id }, excecao);
	}

}
