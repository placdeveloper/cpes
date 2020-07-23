package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * A Classe PessoaComErrosCadastraisRestritivosException.
 */
public class PessoaComErrosCadastraisRestritivosException extends CAPESCadastroNegocioException {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -6232505148589634673L;
	
	/** A constante CHAVE_MENSAGEM. */
	private static final String CHAVE_MENSAGEM = "MN187";
	
	/**
	 * Instancia um novo PessoaComErrosCadastraisRestritivosException.
	 */
	public PessoaComErrosCadastraisRestritivosException() {
		super(CHAVE_MENSAGEM);
	}

}