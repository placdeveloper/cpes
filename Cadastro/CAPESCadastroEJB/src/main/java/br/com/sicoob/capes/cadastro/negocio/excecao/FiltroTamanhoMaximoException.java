/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exceção para filtro informado com tamanho maior que o máximo.
 * @author erico.junior
 */
public class FiltroTamanhoMaximoException extends CAPESCadastroNegocioException {

	/** Serial UID.*/
	private static final long serialVersionUID = 7600645044911415087L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN033";

	/**
	 * {@inheritDoc}
	 */
	public FiltroTamanhoMaximoException(String parametro) {
		super(CHAVE_MSG, parametro);
	}
}