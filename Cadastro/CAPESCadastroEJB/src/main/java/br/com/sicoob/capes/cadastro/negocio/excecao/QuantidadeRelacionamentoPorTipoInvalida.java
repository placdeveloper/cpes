package br.com.sicoob.capes.cadastro.negocio.excecao;


/**
 * A Classe QuantidadeRelacionamentoPorTipoInvalida.
 */
public class QuantidadeRelacionamentoPorTipoInvalida extends CAPESCadastroNegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -7507869290665693728L;
	
	/** A constante CODIGO_MENSAGEM. */
	private static final String CODIGO_MENSAGEM = "MN140";

	/**
	 * Instancia um novo QuantidadeRelacionamentoPorTipoInvalida.
	 */
	public QuantidadeRelacionamentoPorTipoInvalida() {
		super(CODIGO_MENSAGEM);
	}

	/**
	 * Instancia um novo QuantidadeRelacionamentoPorTipoInvalida.
	 *
	 * @param excecao o valor de excecao
	 */
	public QuantidadeRelacionamentoPorTipoInvalida(Throwable excecao) {
		super(CODIGO_MENSAGEM, excecao);
	}

}
