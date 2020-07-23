package br.com.sicoob.capes.integracao.negocio.excecao;

import br.com.sicoob.capes.comum.negocio.enums.IntegracaoSistemasEnum;

/**
 * @author Rodrigo.Chaves
 */
public class GFTIntegracaoRuntimeException extends CAPESIntegracaoRuntimeException {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 2172141685485048957L;

	/**
	 * Construtor
	 * 
	 * @param excecao
	 */
	protected GFTIntegracaoRuntimeException(Exception excecao) {
		super(excecao, IntegracaoSistemasEnum.GFT.getSiglaSistema());
	}

	/**
	 * Construtor
	 * 
	 * @param excecao
	 * @param mensagem
	 */
	public GFTIntegracaoRuntimeException(Exception excecao, String mensagem) {
		super(excecao, IntegracaoSistemasEnum.GFT.getSiglaSistema(), mensagem);
	}

}
