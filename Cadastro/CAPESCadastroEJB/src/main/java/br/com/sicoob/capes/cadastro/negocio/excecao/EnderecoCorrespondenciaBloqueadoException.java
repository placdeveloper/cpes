package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exceção para a tentativa de alteração de um endereço de correspondência para
 * um registro que ainda não está vigente.
 * 
 * @author bruno.carneiro
 */
public class EnderecoCorrespondenciaBloqueadoException extends CAPESCadastroNegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -7505335029947107605L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN163";

	/**
	 * Construtor da exceção.
	 */
	public EnderecoCorrespondenciaBloqueadoException() {
		super(CHAVE_MSG);
	}
}