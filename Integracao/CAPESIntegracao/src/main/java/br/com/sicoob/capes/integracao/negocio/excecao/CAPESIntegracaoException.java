package br.com.sicoob.capes.integracao.negocio.excecao;

import br.com.bancoob.excecao.BancoobException;

/**
 * Excecao padrao para o sistema CAPESIntegracao.
 * 
 * @author Stefanini IT Solutions
 */
public class CAPESIntegracaoException extends BancoobException {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	public CAPESIntegracaoException(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public CAPESIntegracaoException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * @param chave
	 * @param excecao
	 */
	public CAPESIntegracaoException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * @param excecao
	 */
	public CAPESIntegracaoException(Throwable excecao) {
		super(excecao);
	}
}