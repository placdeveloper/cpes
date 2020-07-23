package br.com.sicoob.capes.integracao.negocio.excecao;

import br.com.sicoob.capes.comum.negocio.enums.IntegracaoSistemasEnum;

/**
 * 
 * @author Rodrigo.Chaves
 */
public class LOCIntegracaoNegocioException extends CAPESIntegracaoNegocioException {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -4186742899045186022L;

	/**
	 * Construtor
	 * 
	 * @param mensagem
	 */
	public LOCIntegracaoNegocioException(String mensagem) {
		super(IntegracaoSistemasEnum.LOC.getNomeSistema(), mensagem);
	}

	/**
	 * Construtor
	 * 
	 * @param excecao
	 * @param mensagem
	 */
	public LOCIntegracaoNegocioException(Throwable excecao, String mensagem) {
		super(excecao, IntegracaoSistemasEnum.LOC.getNomeSistema(), mensagem);
	}

	/**
	 * Construtor
	 * 
	 * @param excecao
	 */
	public LOCIntegracaoNegocioException(Throwable excecao) {
		super(excecao, IntegracaoSistemasEnum.LOC.getNomeSistema());
	}

}