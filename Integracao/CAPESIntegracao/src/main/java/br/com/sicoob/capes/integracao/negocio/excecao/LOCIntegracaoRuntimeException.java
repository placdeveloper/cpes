package br.com.sicoob.capes.integracao.negocio.excecao;

import br.com.sicoob.capes.comum.negocio.enums.IntegracaoSistemasEnum;

/**
 * @author Rodrigo.Chaves
 */
public class LOCIntegracaoRuntimeException extends CAPESIntegracaoRuntimeException {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 6212687551004876908L;

	/**
	 * Construtor
	 * 
	 * @param excecao
	 * @param sistema
	 * @param mensagem
	 */
	public LOCIntegracaoRuntimeException(Exception excecao, String mensagem) {
		super(excecao, IntegracaoSistemasEnum.LOC.getNomeSistema(), mensagem);
	}

	/**
	 * Construtor
	 * 
	 * @param excecao
	 * @param sistema
	 */
	public LOCIntegracaoRuntimeException(Exception excecao) {
		super(excecao, IntegracaoSistemasEnum.LOC.getNomeSistema());
	}

	/**
	 * Construtor
	 * 
	 * @param sistema
	 * @param mensagem
	 */
	public LOCIntegracaoRuntimeException(String mensagem) {
		super(IntegracaoSistemasEnum.LOC.getNomeSistema(), mensagem);
	}

}