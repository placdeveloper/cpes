package br.com.sicoob.capes.api.inclusao.negocio.excecao;

import br.com.bancoob.excecao.BancoobException;

/**
 * Exce��o para os registros que est�o pendentes de aprova��o
 *
 * @author Bruno.Carneiro
 */
public class RegistroPendenteAprovacaoException extends CAPESApiInclusaoException {
	private static final long serialVersionUID = 4889731158074309818L;

	/**
	 * Instancia um novo RegistroPendenteAprovacaoException.
	 */
	public RegistroPendenteAprovacaoException(String mensagem) {
		super(mensagem);
	}
	
	/**
	 * Lan�a uma exce��o
	 * 
	 * @param mensagem
	 * @throws BancoobException
	 */
	public static void lancarExcecao(String mensagem) throws BancoobException {
		throw new RegistroPendenteAprovacaoException(mensagem);
	}
}