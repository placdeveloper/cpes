package br.com.sicoob.capes.cadastro.negocio.excecao;

public class FaixaRendaAnualException extends CAPESCadastroNegocioException {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 7154570350217943864L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN207";
	
	public FaixaRendaAnualException() {
		super(CHAVE_MSG);
	}

}
