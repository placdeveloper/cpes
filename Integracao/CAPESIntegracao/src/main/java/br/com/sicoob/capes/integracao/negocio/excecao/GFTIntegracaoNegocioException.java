package br.com.sicoob.capes.integracao.negocio.excecao;

import br.com.sicoob.capes.comum.negocio.enums.IntegracaoSistemasEnum;

/**
 * @author Rodrigo.Chaves
 */
public class GFTIntegracaoNegocioException extends CAPESIntegracaoNegocioException {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -6445328481664325522L;

	/**
	 * Construtor
	 * 
	 * @param mensagem
	 * @param excecao
	 */
	public GFTIntegracaoNegocioException(Throwable excecao) {
		super(excecao, IntegracaoSistemasEnum.GFT.getSiglaSistema());
	}

	/**
	 * Construtor
	 * 
	 * @param excecao
	 * @param mensagem
	 */
	public GFTIntegracaoNegocioException(Throwable excecao, String mensagem) {
		super(excecao, IntegracaoSistemasEnum.GFT.getSiglaSistema(), mensagem);
	}

	/**
	 * Construtor
	 * 
	 * @param excecao
	 * @param mensagem
	 */
	public GFTIntegracaoNegocioException(String mensagem) {
		super(IntegracaoSistemasEnum.GFT.getSiglaSistema(), mensagem);
	}

}