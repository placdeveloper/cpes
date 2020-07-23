package br.com.sicoob.capes.integracao.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.sicoob.capes.comum.negocio.enums.IntegracaoSistemasEnum;

/**
 * @author Rodrigo.Chaves
 */
@ApplicationException(rollback = true)
public class GEDIntegracaoNegocioException extends CAPESIntegracaoNegocioException {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 4269362095350856496L;

	/**
	 * Construtor
	 * 
	 * @param mensagem
	 * @param excecao
	 */
	public GEDIntegracaoNegocioException(Throwable excecao) {
		super(excecao, IntegracaoSistemasEnum.GED.getSiglaSistema());
	}

	/**
	 * Construtor
	 * 
	 * @param excecao
	 * @param mensagem
	 */
	public GEDIntegracaoNegocioException(Throwable excecao, String mensagem) {
		super(excecao, IntegracaoSistemasEnum.GED.getSiglaSistema(), mensagem);
	}

	/**
	 * Construtor
	 * 
	 * @param excecao
	 * @param mensagem
	 */
	public GEDIntegracaoNegocioException(String mensagem) {
		super(IntegracaoSistemasEnum.GED.getSiglaSistema(), mensagem);
	}

}