/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exce��o lan�ada quando a anota��o n�o possui detalhe.
 * @author erico.junior
 */
public class AnotacaoSemDetalheException extends CAPESCadastroNegocioException {

	/** Serial UID.*/
	private static final long serialVersionUID = -1352075604752978469L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN175";

	/**
	 * {@inheritDoc}
	 */
	public AnotacaoSemDetalheException() {
		super(CHAVE_MSG);
	}
}
