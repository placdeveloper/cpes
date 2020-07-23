package br.com.sicoob.capes.api.negocio.excecao;

/**
 * Exceção para os parâmetros inválidos informados na API.
 * 
 * @author Bruno.Carneiro
 */
public class ParametroInvalidoException extends CAPESApiNegocioException {
	private static final long serialVersionUID = -1275366891757100411L;

	private static final String CHAVE_MSG = "MN007";

	public ParametroInvalidoException() {
		super(CHAVE_MSG);
	}

}