package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exceção para os percentuais.
 * 
 * @author Bruno.Carneiro
 * 
 */
public class PercentualInvalidoException extends CAPESCadastroNegocioException {
	private static final long serialVersionUID = -9015198770126141146L;

	private static final String CHAVE_MSG = "MN210";

	public PercentualInvalidoException(String nomeCampo, String comparacaoPercentual) {
		super(CHAVE_MSG, nomeCampo, comparacaoPercentual);
	}
}