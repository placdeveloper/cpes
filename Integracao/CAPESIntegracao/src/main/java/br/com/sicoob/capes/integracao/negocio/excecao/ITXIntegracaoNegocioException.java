package br.com.sicoob.capes.integracao.negocio.excecao;

import br.com.sicoob.capes.comum.negocio.enums.IntegracaoSistemasEnum;

/**
 * @author Rodrigo.Chaves
 */
public class ITXIntegracaoNegocioException extends CAPESIntegracaoNegocioException {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -7824787480598473924L;

	/**
	 * Construtor
	 * 
	 * @param sistema
	 * @param mensagem
	 */
	public ITXIntegracaoNegocioException(String mensagem) {
		super(IntegracaoSistemasEnum.ITX.getNomeSistema(), mensagem);
	}

	/**
	 * Construtor
	 * 
	 * @param excecao
	 * @param sistema
	 * @param mensagem
	 */
	public ITXIntegracaoNegocioException(Throwable excecao, String mensagem) {
		super(excecao, IntegracaoSistemasEnum.ITX.getNomeSistema(), mensagem);
	}

	/**
	 * Construtor
	 * 
	 * @param excecao
	 * @param sistema
	 */
	public ITXIntegracaoNegocioException(Throwable excecao) {
		super(excecao, IntegracaoSistemasEnum.ITX.getNomeSistema());
	}

}
