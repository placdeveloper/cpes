package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exceção para validação dos tamanhos dos campos.
 */
public class TamanhoCampoInvalidoException extends CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = 4156443475746957109L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN173";

	/**
	 * Construtor da exceção.
	 */
	public TamanhoCampoInvalidoException(Object... parametros) {
		super(CHAVE_MSG, parametros);
	}
}