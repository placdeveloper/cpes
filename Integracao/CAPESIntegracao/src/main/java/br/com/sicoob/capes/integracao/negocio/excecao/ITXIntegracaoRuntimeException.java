package br.com.sicoob.capes.integracao.negocio.excecao;

import br.com.sicoob.capes.comum.negocio.enums.IntegracaoSistemasEnum;

/**
 * @author Rodrigo.Chaves
 */
public class ITXIntegracaoRuntimeException extends CAPESIntegracaoRuntimeException {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 7226723030859446805L;

	/**
	 * Construtor
	 * 
	 * @param excecao
	 * @param sistema
	 * @param mensagem
	 */
	public ITXIntegracaoRuntimeException(Exception excecao, String mensagem) {
		super(excecao, IntegracaoSistemasEnum.ITX.getNomeSistema(), mensagem);
	}

	/**
	 * Construtor
	 * 
	 * @param excecao
	 * @param sistema
	 */
	public ITXIntegracaoRuntimeException(Exception excecao) {
		super(excecao, IntegracaoSistemasEnum.ITX.getNomeSistema());
	}

	/**
	 * Construtor
	 * 
	 * @param sistema
	 * @param mensagem
	 */
	public ITXIntegracaoRuntimeException(String mensagem) {
		super(IntegracaoSistemasEnum.ITX.getNomeSistema(), mensagem);
	}

}
