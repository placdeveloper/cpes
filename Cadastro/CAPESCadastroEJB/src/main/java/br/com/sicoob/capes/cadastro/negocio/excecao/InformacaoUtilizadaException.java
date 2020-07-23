package br.com.sicoob.capes.cadastro.negocio.excecao;

import org.apache.commons.lang.StringUtils;

/**
 * A Classe InformacaoUtilizadaException.
 */
public class InformacaoUtilizadaException extends CAPESCadastroNegocioException {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -938439257002679887L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN193";

	/**
	 * Instancia um novo InformacaoUtilizadaException.
	 *
	 * @param parametros o valor de parametros
	 */
	public InformacaoUtilizadaException(Object... parametros) {
		super(CHAVE_MSG, StringUtils.join(parametros, ", "));
	}

}