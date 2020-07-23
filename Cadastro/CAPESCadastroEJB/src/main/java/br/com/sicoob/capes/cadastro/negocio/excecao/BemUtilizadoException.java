package br.com.sicoob.capes.cadastro.negocio.excecao;

import org.apache.commons.lang.StringUtils;

/**
 * Utilizada quando um bem está sendo utilizado por outro sistema.
 * 
 * @author Bruno.Carneiro
 */
public class BemUtilizadoException extends CAPESCadastroNegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -938439257002679887L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN214";

	/**
	 * Instancia um novo BemUtilizadoException.
	 * 
	 * @param parametros
	 *            o valor de parametros
	 */
	public BemUtilizadoException(Object... parametros) {
		super(CHAVE_MSG, StringUtils.join(parametros, ", "));
	}

}