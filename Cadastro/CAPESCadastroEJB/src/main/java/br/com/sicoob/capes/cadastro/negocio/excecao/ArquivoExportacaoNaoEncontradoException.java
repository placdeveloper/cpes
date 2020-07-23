package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * A Classe ArquivoExportacaoNaoEncontradoException.
 */
public class ArquivoExportacaoNaoEncontradoException extends CAPESCadastroNegocioException {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 5906264935955836209L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN195";

	/**
	 * Instancia um novo ArquivoExportacaoNaoEncontradoException.
	 */
	public ArquivoExportacaoNaoEncontradoException() {
		super(CHAVE_MSG);
	}

}