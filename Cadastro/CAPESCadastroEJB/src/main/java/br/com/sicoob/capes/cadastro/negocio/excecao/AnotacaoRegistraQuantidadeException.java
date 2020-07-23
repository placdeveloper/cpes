/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exceção utilizada quando o tipo de anotação exige registro de valor e este está nulo.
 * @author erico.junior
 */
public class AnotacaoRegistraQuantidadeException extends CAPESCadastroNegocioException {

	/** Serial UID.*/
	private static final long serialVersionUID = 7855121644402033126L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN190";
	
	/**
	 * Construtor da exceção.
	 */
	public AnotacaoRegistraQuantidadeException() {
		super(CHAVE_MSG);
	}
}
