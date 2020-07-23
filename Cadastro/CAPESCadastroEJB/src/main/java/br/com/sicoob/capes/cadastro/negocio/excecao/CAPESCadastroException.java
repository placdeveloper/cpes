package br.com.sicoob.capes.cadastro.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.bancoob.excecao.BancoobException;

/**
 * Excecao padrao para o sistema CadastroUnicoClientesComum.
 * 
 * @author Stefanini IT Solutions
 */
@ApplicationException (rollback = true)
public class CAPESCadastroException extends BancoobException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	public CAPESCadastroException(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public CAPESCadastroException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * @param chave
	 * @param excecao
	 */
	public CAPESCadastroException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * @param excecao
	 */
	public CAPESCadastroException(Throwable excecao) {
		super(excecao);
	}
}
