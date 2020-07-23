/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exce��o utilizada para atividade econ�mica incorreta para o tipo de pessoa.
 * 
 * @author erico.junior
 */
public class AtividadeEconomicaTipoPessoaException extends
		CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = -1207478626868595441L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN025";

	/**
	 * Construtor da exce��o.
	 * 
	 * @param parametro
	 *            O tipo de pessoa que n�o pode ser aplicado.
	 */
	public AtividadeEconomicaTipoPessoaException(String parametro) {
		super(CHAVE_MSG, parametro);
	}

}
