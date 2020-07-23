package br.com.sicoob.capes.api.inclusao.negocio.excecao;

import br.com.bancoob.excecao.BancoobException;

/**
 * A Classe CAPESApiInclusaoException.
 * 
 * @author bruno.carneiro
 */
public class CAPESApiInclusaoException extends BancoobException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 2128624265261405259L;

	/**
	 * Instancia um novo CAPESApiInclusaoException
	 * 
	 * @param mensagem
	 * @param parametros
	 */
	public CAPESApiInclusaoException(String mensagem, Object... parametros) {
		super(mensagem, parametros);
	}
	
	/**
	 * Lança uma exceção
	 * 
	 * @param mensagem
	 * @throws BancoobException
	 */
	public static void lancarExcecao(String mensagem) throws BancoobException {
		throw new CAPESApiInclusaoException(mensagem);
	}
}