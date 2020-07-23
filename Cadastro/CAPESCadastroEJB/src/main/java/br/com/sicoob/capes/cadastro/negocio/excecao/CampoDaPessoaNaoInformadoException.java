package br.com.sicoob.capes.cadastro.negocio.excecao;

import org.apache.commons.lang.StringUtils;

/**
 * A Classe CampoDaPessoaNaoInformadoException.
 */
public class CampoDaPessoaNaoInformadoException extends CAPESCadastroNegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN034";
	
	/**
	 * Instancia um novo CampoDaPessoaNaoInformadoException.
	 *
	 * @param parametros o valor de parametros
	 */
	public CampoDaPessoaNaoInformadoException(String... parametros) {
		super(CHAVE_MSG, StringUtils.join(parametros, ", "));
	}

	
}