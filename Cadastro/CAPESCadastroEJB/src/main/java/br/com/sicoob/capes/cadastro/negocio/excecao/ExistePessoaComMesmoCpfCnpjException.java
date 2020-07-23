/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * Exceção para pessoa já existente com o cpf/cnpj informado.
 * 
 * @author erico.junior
 */
@ApplicationException (rollback = true)
public class ExistePessoaComMesmoCpfCnpjException extends
		CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = -2636708283127959410L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN116";

	/**
	 * {@inheritDoc}
	 */
	public ExistePessoaComMesmoCpfCnpjException() {
		super(CHAVE_MSG);
	}
	
	public ExistePessoaComMesmoCpfCnpjException(Throwable e) {
		super(CHAVE_MSG, e);
	}

}
