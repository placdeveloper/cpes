package br.com.sicoob.capes.integracao.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobFabricaDelegates;

/**
 * Fabrica dos business delegates a serem usados pelo Sistema CAPESIntegracao.
 * 
 * @author Bruno.Carneiro
 */
public final class CAPESIntegracaoFabricaDelegates extends BancoobFabricaDelegates {

	/** Instancia do Fabrica de Delegates. */
	private static CAPESIntegracaoFabricaDelegates fabrica;

	/**
	 * Retorna a fabrica de delegates a ser utilizada.
	 * 
	 * @return a fabrica de delegates a ser utilizada.
	 */
	public static CAPESIntegracaoFabricaDelegates getInstance() {
		if (fabrica == null) {
			synchronized (CAPESIntegracaoFabricaDelegates.class) {
				if (fabrica == null) {
					fabrica = new CAPESIntegracaoFabricaDelegates();
				}
			}
		}
		return fabrica;
	}

	/**
	 * Construtor privado no-args da classe
	 */
	private CAPESIntegracaoFabricaDelegates() {
	}

	/**
	 * Cria um novo objeto GEDIntegracaoDelegate.
	 * 
	 * @return the GED integracao delegate
	 */
	public GEDIntegracaoDelegate criarGEDIntegracaoDelegate() {
		return new GEDIntegracaoDelegate();
	}

	/**
	 * Cria um novo objeto GFTIntegracaoDelegate.
	 * 
	 * @return the GFT integracao delegate
	 */
	public GFTIntegracaoDelegate criarGFTIntegracaoDelegate() {
		return new GFTIntegracaoDelegate();
	}

	/**
	 * Cria um novo objeto ITXIntegracaoDelegate.
	 * 
	 * @return the ITX integracao delegate
	 */
	public ITXIntegracaoDelegate criarITXIntegracaoDelegate() {
		return new ITXIntegracaoDelegate();
	}

	/**
	 * Cria um novo objeto LOCIntegracaoDelegate.
	 * 
	 * @return the LOC integracao delegate
	 */
	public LOCIntegracaoDelegate criarLOCIntegracaoDelegate() {
		return new LOCIntegracaoDelegate();
	}

	/**
	 * Cria um novo objeto SCIIntegracaoDelegate.
	 * 
	 * @return the SCI integracao delegate
	 */
	public SCIIntegracaoDelegate criarSCIIntegracaoDelegate() {
		return new SCIIntegracaoDelegate();
	}

	/**
	 * Cria um novo objeto ADMIntegracaoDelegate.
	 * 
	 * @return the ADM integracao delegate
	 */
	public ADMIntegracaoDelegate criarADMIntegracaoDelegate() {
		return new ADMIntegracaoDelegate();
	}

	/**
	 * Cria um novo objeto CTAIntegracaoDelegate.
	 * 
	 * @return the CTA integracao delegate
	 */
	public CTAIntegracaoDelegate criarCTAIntegracaoDelegate() {
		return new CTAIntegracaoDelegate();
	}

	/**
	 * Cria instancia de CRLIntegracaoDelegate.
	 * 
	 * @return o delegate solicitado
	 * @see CRLIntegracaoDelegate
	 */
	public CRLIntegracaoDelegate criarCRLIntegracaoDelegate() {
		return new CRLIntegracaoDelegate();
	}
	
	/**
	 * Cria instancia de CTZIntegracaoDelegate.
	 * 
	 * @return o delegate solicitado
	 * @see CTZIntegracaoDelegate
	 */
	public CTZIntegracaoDelegate criarCTZIntegracaoDelegate() {
		return new CTZIntegracaoDelegate();
	}

}