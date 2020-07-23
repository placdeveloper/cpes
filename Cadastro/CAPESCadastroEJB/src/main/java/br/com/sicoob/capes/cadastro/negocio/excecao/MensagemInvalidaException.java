package br.com.sicoob.capes.cadastro.negocio.excecao;

import br.com.bancoob.excecao.BancoobRuntimeException;

/**
 * A Classe MensagemInvalidaException.
 */
public class MensagemInvalidaException extends BancoobRuntimeException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 5251039066154836484L;
	
	/** A constante QUANTIDADE_PARAMETROS_INVALIDA. */
	public static final String QUANTIDADE_PARAMETROS_INVALIDA = "msg.erro.replicacao.quantidade.parametros";
	
	/** A constante IDENTIFICADOR_OPERACAO_INVALIDO. */
	public static final String IDENTIFICADOR_OPERACAO_INVALIDO = "msg.erro.replicacao.identificador.operacao";
	
	/** A constante ID_INSTITUICAO_INVALIDO. */
	public static final String ID_INSTITUICAO_INVALIDO = "msg.erro.replicacao.id.instituicao";
	
	/**
	 * Instancia um novo MensagemInvalidaException.
	 *
	 * @param mensagem o valor de mensagem
	 * @param parametros o valor de parametros
	 */
	public MensagemInvalidaException(String mensagem, Object... parametros) {
		super(mensagem, parametros);
	}

	/**
	 * Instancia um novo MensagemInvalidaException.
	 *
	 * @param excecao o valor de excecao
	 * @param mensagem o valor de mensagem
	 * @param parametros o valor de parametros
	 */
	public MensagemInvalidaException(Exception excecao, String mensagem, Object... parametros) {
		super(mensagem, parametros, excecao);
	}

}
