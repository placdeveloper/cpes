/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exce��o para filtro informado com tamanho menor que o m�nimo.
 * @author erico.junior
 */
public class FiltroTamanhoMinimoException extends CAPESCadastroNegocioException {

	/** Serial UID.*/
	private static final long serialVersionUID = 7600645044911415087L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN018";

	/**
	 * {@inheritDoc}
	 */
	public FiltroTamanhoMinimoException(String parametro) {
		super(CHAVE_MSG, parametro);
	}
}
