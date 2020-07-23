package br.com.sicoob.capes.corporativo.negocio.excecao;

import br.com.bancoob.excecao.BancoobException;

/**
 * A Classe CAPESCorporativoException.
 * 
 * @author bruno.carneiro
 */
public class CAPESCorporativoException extends BancoobException {
	private static final long serialVersionUID = 6789560775107021237L;

	/**
	 * Instancia um novo CAPESCorporativoException
	 * 
	 * @param mensagem
	 * @param parametros
	 */
	public CAPESCorporativoException(String mensagem, Object... parametros) {
		super(mensagem, parametros);
	}
	
	/**
	 * Lança uma exceção
	 * 
	 * @param mensagem
	 * @throws BancoobException
	 */
	public static void lancarExcecao(String mensagem) throws BancoobException {
		throw new CAPESCorporativoException(mensagem);
	}
}