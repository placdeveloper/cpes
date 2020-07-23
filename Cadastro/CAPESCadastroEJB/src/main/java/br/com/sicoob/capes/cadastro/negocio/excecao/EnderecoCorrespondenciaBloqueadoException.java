package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exce��o para a tentativa de altera��o de um endere�o de correspond�ncia para
 * um registro que ainda n�o est� vigente.
 * 
 * @author bruno.carneiro
 */
public class EnderecoCorrespondenciaBloqueadoException extends CAPESCadastroNegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -7505335029947107605L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN163";

	/**
	 * Construtor da exce��o.
	 */
	public EnderecoCorrespondenciaBloqueadoException() {
		super(CHAVE_MSG);
	}
}