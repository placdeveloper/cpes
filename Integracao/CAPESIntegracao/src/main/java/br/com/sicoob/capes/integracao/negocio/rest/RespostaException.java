package br.com.sicoob.capes.integracao.negocio.rest;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.integracao.negocio.excecao.CAPESIntegracaoException;

/**
 * Exceção que representa uma resposta que não é de sucesso.
 * 
 * @author Bruno.Carneiro
 */
public class RespostaException extends CAPESIntegracaoException {
	private static final long serialVersionUID = -7333608772743952560L;

	private Resposta<?> resposta;

	public RespostaException(String message) {
		super(message);
	}

	public RespostaException(String message, Resposta<?> resposta) {
		this(message);
		this.setResposta(resposta);
	}

	public Resposta<?> getResposta() {
		return resposta;
	}

	public void setResposta(Resposta<?> resposta) {
		this.resposta = resposta;
	}

	/**
	 * Lança uma exceção
	 * 
	 * @param mensagem
	 * @throws BancoobException
	 */
	public static void lancarExcecao(String mensagem) throws BancoobException {
		throw new RespostaException(mensagem);
	}

}