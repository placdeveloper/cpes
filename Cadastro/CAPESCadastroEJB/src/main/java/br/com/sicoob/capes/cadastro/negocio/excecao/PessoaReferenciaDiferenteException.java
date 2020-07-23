package br.com.sicoob.capes.cadastro.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * A Classe PessoaReferenciaDiferenteException.
 */
@ApplicationException (rollback = true)
public class PessoaReferenciaDiferenteException extends CAPESCadastroNegocioException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN136";
	
	/**
	 * Instancia um novo PessoaReferenciaDiferenteException.
	 */
	public PessoaReferenciaDiferenteException() {
		super(CHAVE_MSG);
	}
}