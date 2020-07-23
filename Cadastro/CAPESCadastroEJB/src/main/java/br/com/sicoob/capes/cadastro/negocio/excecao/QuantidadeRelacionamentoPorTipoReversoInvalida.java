package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * A Classe QuantidadeRelacionamentoPorTipoReversoInvalida.
 */
public class QuantidadeRelacionamentoPorTipoReversoInvalida extends CAPESCadastroNegocioException {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 4038349885216733579L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN202";

	/**
	 * Instancia um novo QuantidadeRelacionamentoPorTipoReversoInvalida.
	 */
	public QuantidadeRelacionamentoPorTipoReversoInvalida() {
		super(CHAVE_MSG);
	}

}