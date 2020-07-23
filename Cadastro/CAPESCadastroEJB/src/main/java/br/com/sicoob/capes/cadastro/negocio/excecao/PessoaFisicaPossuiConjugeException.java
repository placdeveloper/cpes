package br.com.sicoob.capes.cadastro.negocio.excecao;


/**
 * A Classe RelacionamentoMesmaPessoaException.
 */
public class PessoaFisicaPossuiConjugeException extends CAPESCadastroNegocioException {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -3426109490127435014L;
	
	/** A constante CODIGO_MENSAGEM. */
	private static final String CODIGO_MENSAGEM = "MN212";

	/**
	 * Instancia um novo PessoaFisicaPossuiConjugeException.
	 */
	public PessoaFisicaPossuiConjugeException() {
		super(CODIGO_MENSAGEM);
	}

}
