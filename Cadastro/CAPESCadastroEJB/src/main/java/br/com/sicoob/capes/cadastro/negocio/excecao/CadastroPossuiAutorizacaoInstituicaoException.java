package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Cadastro da pessoa possui autoriza��es em andamento na institui��o
 * 
 * @author Diego.Rezende
 */
public class CadastroPossuiAutorizacaoInstituicaoException extends CAPESCadastroNegocioException {

	/** Serial UID. */
	private static final long serialVersionUID = -3015735275372815421L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN197";

	/**
	 * Construtor da exce��o.
	 */
	public CadastroPossuiAutorizacaoInstituicaoException() {
		super(CHAVE_MSG);
	}
}
