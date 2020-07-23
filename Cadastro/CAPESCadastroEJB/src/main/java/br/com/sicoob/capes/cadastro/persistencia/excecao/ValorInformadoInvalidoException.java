package br.com.sicoob.capes.cadastro.persistencia.excecao;

import br.com.bancoob.persistencia.excecao.PersistenciaException;

/**
 * A Classe ValorInformadoInvalidoException.
 */
public class ValorInformadoInvalidoException extends PersistenciaException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -1525903439077234468L;

	/** A constante CHAVE_MENSAGEM. */
	private static final String CHAVE_MENSAGEM = "msg.erro.valor.informado.invalido";
	
	/**
	 * Instancia um novo ValorInformadoInvalidoException.
	 *
	 * @param parametros o valor de parametros
	 */
	public ValorInformadoInvalidoException(Object... parametros) {
		super(CHAVE_MENSAGEM, parametros);
	}

	/**
	 * Instancia um novo ValorInformadoInvalidoException.
	 *
	 * @param excecao o valor de excecao
	 * @param parametros o valor de parametros
	 */
	public ValorInformadoInvalidoException(Exception excecao, Object... parametros) {
		super(CHAVE_MENSAGEM, parametros, excecao);
	}

}
