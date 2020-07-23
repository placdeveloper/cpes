package br.com.sicoob.capes.processamento.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobFabricaDelegates;

/**
 * Fabrica dos business delegates a serem usados pelo Sistema
 * CAPESProcessamento.
 * 
 * @author Stefanini IT Solutions
 */
public final class CAPESProcessamentoFabricaDelegates extends BancoobFabricaDelegates {

	/** Instancia do Fabrica de Delegates. */
	private static CAPESProcessamentoFabricaDelegates fabrica = new CAPESProcessamentoFabricaDelegates();

	/**
	 * Retorna a fabrica de delegates a ser utilizada.
	 * 
	 * @return a fabrica de delegates a ser utilizada.
	 */
	public static CAPESProcessamentoFabricaDelegates getInstance() {
		return fabrica;
	}

	/**
	 * Construtor privado no-args da classe
	 */
	private CAPESProcessamentoFabricaDelegates() {
		super();
	}
	
	/**
	 * Cria instancia de AtualizarAnotacoesConsultasExternasDelegate.
	 *
	 * @return o delegate solicitado
	 * @see AtualizarAnotacoesConsultasExternasDelegate
	 */
	public AtualizarAnotacoesConsultasExternasDelegate criarAtualizarAnotacoesConsultasExternasDelegate() {
		return new AtualizarAnotacoesConsultasExternasDelegate();
	}
	
}