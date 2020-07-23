/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exce��o utilizada quando o tipo de anota��o exige registro de valor e este est� nulo.
 * @author erico.junior
 */
public class AnotacaoRegistraQuantidadeException extends CAPESCadastroNegocioException {

	/** Serial UID.*/
	private static final long serialVersionUID = 7855121644402033126L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN190";
	
	/**
	 * Construtor da exce��o.
	 */
	public AnotacaoRegistraQuantidadeException() {
		super(CHAVE_MSG);
	}
}
