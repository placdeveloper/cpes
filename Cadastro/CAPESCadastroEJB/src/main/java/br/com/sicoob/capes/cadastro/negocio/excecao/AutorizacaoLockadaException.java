package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exce��o para o carregamento de uma autoriza��o que est� 'lockada' para alguma
 * atualiza��o
 */
public class AutorizacaoLockadaException extends CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = 6982178498314013932L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN172";

	/**
	 * {@inheritDoc}
	 */
	public AutorizacaoLockadaException() {
		super(CHAVE_MSG);
	}

	/**
	 * {@inheritDoc}
	 */
	public AutorizacaoLockadaException(Throwable excecao) {
		super(CHAVE_MSG, excecao);
	}
}