package br.com.sicoob.capes.cadastro.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.bancoob.negocio.excecao.NegocioException;

/**
 * Excecao de negócio padrao para o sistema Cadastro Unico Clientes Comum.
 * 
 * @author Stefanini IT Solutions
 */
@ApplicationException (rollback = true)
public class CAPESCadastroNegocioException extends NegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor das exceção de negócio com a chave da mensagem.
	 * @param chaveMensagem A chave da mensagem.
	 */
	public CAPESCadastroNegocioException(String chaveMensagem) {
		super(chaveMensagem);
	}

	/**
	 * Construtor das exceção de negócio com a chave da mensagem e os parâmetros.
	 * @param chaveMensagem A chave da mensagem.
	 * @param parametros Os parâmetros da mensagem.
	 */
	public CAPESCadastroNegocioException(String mensagem, Object... parametros) {
		super(mensagem, parametros);
	}

	/**
	 * Construtor das exceção de negócio com a chave da mensagem.
	 * @param mensagem A chave da mensagem.
	 * @param excecao A exceção de origem 
	 */
	public CAPESCadastroNegocioException(String mensagem, Throwable excecao) {
		super(mensagem, excecao);
	}
	
	/**
	 * Construtor das exceção de negócio com a chave da mensagem.
	 * @param mensagem A chave da mensagem.
	 * @param excecao A exceção de origem 
	 * @param parametros Parâmetros para a mensagem.
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
