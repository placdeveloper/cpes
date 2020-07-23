package br.com.sicoob.capes.cadastro.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.bancoob.negocio.excecao.NegocioException;

/**
 * Excecao de neg�cio padrao para o sistema Cadastro Unico Clientes Comum.
 * 
 * @author Stefanini IT Solutions
 */
@ApplicationException (rollback = true)
public class CAPESCadastroNegocioException extends NegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor das exce��o de neg�cio com a chave da mensagem.
	 * @param chaveMensagem A chave da mensagem.
	 */
	public CAPESCadastroNegocioException(String chaveMensagem) {
		super(chaveMensagem);
	}

	/**
	 * Construtor das exce��o de neg�cio com a chave da mensagem e os par�metros.
	 * @param chaveMensagem A chave da mensagem.
	 * @param parametros Os par�metros da mensagem.
	 */
	public CAPESCadastroNegocioException(String mensagem, Object... parametros) {
		super(mensagem, parametros);
	}

	/**
	 * Construtor das exce��o de neg�cio com a chave da mensagem.
	 * @param mensagem A chave da mensagem.
	 * @param excecao A exce��o de origem 
	 */
	public CAPESCadastroNegocioException(String mensagem, Throwable excecao) {
		super(mensagem, excecao);
	}
	
	/**
	 * Construtor das exce��o de neg�cio com a chave da mensagem.
	 * @param mensagem A chave da mensagem.
	 * @param excecao A exce��o de origem 
	 * @param parametros Par�metros para a mensagem.
	 */
	public CAPESCadastroNegocioException(String mensagem, Throwable excecao, Object... parametros) {
		super(mensagem, parametros, excecao);
	}

	/**
	 * Instancia um novo CAPESCadastroNegocioException.
	 *
	 * @param excecao o valor de excecao
	 */
	public CAPESCadastroNegocioException(Throwable excecao) {
		super(excecao);
	}	
}
