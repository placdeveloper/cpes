package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exce��o para a altera��o de um grupo de compartilhamento de uma institui��o
 * que possui pessoas vinculadas a ela.
 */
public class GrupoCompartilhamentoNaoPodeSerAlteradoException extends CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = 2523650853245102996L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN182";

	/**
	 * Instancia um novo GrupoCompartilhamentoNaoPodeSerAlteradoException.
	 */
	public GrupoCompartilhamentoNaoPodeSerAlteradoException() {
		super(CHAVE_MSG);
	}
}