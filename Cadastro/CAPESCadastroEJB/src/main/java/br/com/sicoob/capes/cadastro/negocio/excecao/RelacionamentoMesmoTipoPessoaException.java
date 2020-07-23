package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * A Classe RelacionamentoMesmoTipoPessoa.
 */
public class RelacionamentoMesmoTipoPessoaException extends CAPESCadastroNegocioException {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 4373754471851030480L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN204";

	/**
	 * Instancia um novo RelacionamentoMesmoTipoPessoa.
	 */
	public RelacionamentoMesmoTipoPessoaException() {
		super(CHAVE_MSG);
	}

}