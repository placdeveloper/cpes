package br.com.sicoob.capes.api.inclusao.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;

/**
 * A Classe CAPESApiInclusaoRuntimeException.
 * 
 * @author bruno.carneiro
 */
@ApplicationException(rollback = true)
public class CAPESApiInclusaoRuntimeException extends BancoobRuntimeException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 3861522710770419762L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "msg.erro.executar.operacao";

	/**
	 * Instancia um novo CAPESApiInclusaoRuntimeException.
	 * 
	 * @param chave
	 *            o valor de chave
	 */
	public CAPESApiInclusaoRuntimeException(Object... parametros) {
		super(CHAVE_MSG, parametros);
	}
	
	/**
	 * Lança uma exceção
	 * 
	 * @param mensagem
	 * @throws BancoobException
	 */
	public static void lancarExcecao(String mensagem) throws BancoobException {
		throw new CAPESApiInclusaoRuntimeException(mensagem);
	}

}