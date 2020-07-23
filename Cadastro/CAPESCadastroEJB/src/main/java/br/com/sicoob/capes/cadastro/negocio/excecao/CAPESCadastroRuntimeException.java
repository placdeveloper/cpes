package br.com.sicoob.capes.cadastro.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.bancoob.excecao.BancoobRuntimeException;

/**
 * Excecao padrao para o sistema CadastroUnicoClientesComum.
 * 
 * @author Stefanini IT Solutions
 */
@ApplicationException (rollback = true)
public class CAPESCadastroRuntimeException extends BancoobRuntimeException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	protected CAPESCadastroRuntimeException(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	protected CAPESCadastroRuntimeException(String chave, Object[] parametros, Exception excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * @param chave
	 * @param excecao
	 */
	protected CAPESCadastroRuntimeException(String chave, Exception excecao) {
		super(chave, excecao);
	}

	/**
	 * @param excecao
	 */
	protected CAPESCadastroRuntimeException(Exception excecao) {
		super(excecao);
	}
}
