package br.com.sicoob.capes.cadastro.negocio.excecao;

import org.apache.commons.lang.StringUtils;

/**
 * A Classe InformacaoUtilizadaException.
 */
public class PendenciaPessoaRelacionadaException extends CAPESCadastroNegocioException {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -653022713453275405L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN209";

	/**
	 * Instancia um novo InformacaoUtilizadaException.
	 *
	 * @param parametros o valor de parametros
	 */
	public PendenciaPessoaRelacionadaException(Object... parametros) {
		super(CHAVE_MSG, StringUtils.join(parametros, " "));
	}

}