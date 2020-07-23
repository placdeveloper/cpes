package br.com.sicoob.capes.api.inclusao.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;

/**
 * A Classe CAPESApiInclusaoNegocioException.
 * 
 * @author bruno.carneiro
 */
@ApplicationException(rollback = true)
public class CAPESApiInclusaoNegocioException extends NegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -2027118216795171770L;
	
	/**
	 * Instancia um novo CAPESApiInclusaoNegocioException.
	 *
	 * @param chaveMensagem o valor de chave mensagem
	 */
	public CAPESApiInclusaoNegocioException(String chaveMensagem) {
		super(chaveMensagem);
	}

	/**
	 * Instancia um novo CAPESApiInclusaoNegocioException.
	 * 
	 * @param mensagem
	 *            o valor de mensagem
	 * @param parametros
	 *            o valor de parametros
	 */
	public CAPESApiInclusaoNegocioException(String chave, Object... parametros) {
		super(chave, parametros);
	}
	
	/**
	 * Lança uma exceção
	 * 
	 * @param mensagem
	 * @throws BancoobException
	 */
	public static void lancarExcecao(String mensagem) throws BancoobException {
		throw new CAPESApiInclusaoNegocioException(mensagem);
	}
	
}