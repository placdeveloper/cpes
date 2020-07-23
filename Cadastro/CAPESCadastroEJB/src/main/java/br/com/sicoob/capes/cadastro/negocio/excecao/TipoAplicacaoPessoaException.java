/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exce��o utilizada para aplica��o de anota��o para o tipo de pessoa incorreto.
 * @author erico.junior
 */
public class TipoAplicacaoPessoaException extends CAPESCadastroNegocioException {

	/** Serial UID.*/
	private static final long serialVersionUID = -1207478626868595441L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN004";
	
	/**
	 * Construtor da exce��o.
	 * @param parametro O tipo de pessoa que n�o pode ser aplicado.
	 */
	public TipoAplicacaoPessoaException(String parametro) {
		super(CHAVE_MSG, parametro);
	}

}
