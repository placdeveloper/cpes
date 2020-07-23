package br.com.sicoob.capes.integracao.negocio.excecao;

import br.com.sicoob.capes.comum.negocio.enums.IntegracaoSistemasEnum;

/**
 * @author Rodrigo.Chaves
 */
public class SCIIntegracaoRuntimeException extends CAPESIntegracaoRuntimeException {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -510998269822036845L;

	/**
	 * Construtor
	 * 
	 * @param excecao
	 * @param sistema
	 * @param mensagem
	 */
	public SCIIntegracaoRuntimeException(Exception excecao, String mensagem) {
		super(excecao, IntegracaoSistemasEnum.SCI.getNomeSistema(), mensagem);
	}

	/**
	 * Construtor
	 * 
	 * @param excecao
	 * @param sistema
	 */
	public SCIIntegracaoRuntimeException(Exception excecao) {
		super(excecao, IntegracaoSistemasEnum.SCI.getNomeSistema());
	}

	/**
	 * Construtor
	 * 
	 * @param sistema
	 * @param mensagem
	 */
	public SCIIntegracaoRuntimeException(String mensagem) {
		super(IntegracaoSistemasEnum.SCI.getNomeSistema(), mensagem);
	}

}