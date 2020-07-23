package br.com.sicoob.capes.processamento.negocio.excecao;

import br.com.bancoob.excecao.BancoobException;

/**
 * Excecao padrao para o sistema CAPESProcessamento.
 * 
 * @author Stefanini IT Solutions
 */
public class CAPESProcessamentoException extends BancoobException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	public CAPESProcessamentoException(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public CAPESProcessamentoException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * @param chave
	 * @param excecao
	 */
	public CAPESProcessamentoException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * @param excecao
	 */
	public CAPESProcessamentoException(Throwable excecao) {
		super(excecao);
	}
	
	/**
	 * Construtor das exceção de negócio com a chave da mensagem.
	 * @param chave A chave da mensagem.
	 * @param excecao A exceção de origem 
	 * @param parametros Parâmetros para a mensagem.
	 */
	public CAPESProcessamentoException(String chave, Throwable excecao, Object... parametros) {
		super(chave, parametros, excecao);
	}	
}
