package br.com.sicoob.capes.integracao.negocio.excecao;

import br.com.sicoob.capes.comum.negocio.enums.IntegracaoSistemasEnum;

/**
 * 
 * @author Bruno.Carneiro
 */
public class GEDIntegracaoRuntimeException extends CAPESIntegracaoRuntimeException {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 1388003418075386169L;

	/**
	 * Construtor
	 * 
	 * @param excecao
	 */
	public GEDIntegracaoRuntimeException(Exception excecao) {
		super(excecao, IntegracaoSistemasEnum.GED.getSiglaSistema());
	}

	/**
	 * Construtor
	 * 
	 * @param excecao
	 * @param mensagem
	 */
	public GEDIntegracaoRuntimeException(Exception excecao, String mensagem) {
		super(excecao, IntegracaoSistemasEnum.GED.getSiglaSistema(), mensagem);
	}

}