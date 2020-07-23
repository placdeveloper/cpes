package br.com.sicoob.capes.integracao.negocio.excecao;

import br.com.sicoob.capes.comum.negocio.enums.IntegracaoSistemasEnum;

/**
 * @author Rodrigo.Chaves
 */
public class SCIIntegracaoNegocioException extends CAPESIntegracaoNegocioException {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -7916943347027680786L;

	/**
	 * Construtor
	 * 
	 * @param mensagem
	 */
	public SCIIntegracaoNegocioException(String mensagem) {
		super(IntegracaoSistemasEnum.SCI.getNomeSistema(), mensagem);
	}

	/**
	 * Construtor
	 * 
	 * @param excecao
	 * @param mensagem
	 */
	public SCIIntegracaoNegocioException(Throwable excecao, String mensagem) {
		super(excecao, IntegracaoSistemasEnum.SCI.getNomeSistema(), mensagem);
	}

	/**
	 * Construtor
	 * 
	 * @param excecao
	 */
	public SCIIntegracaoNegocioException(Throwable excecao) {
		super(excecao, IntegracaoSistemasEnum.SCI.getNomeSistema());
	}

}