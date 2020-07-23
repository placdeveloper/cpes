/*
 * SICOOB
 * 
 * CAPESFrontofficeFabricaDelegates.java(br.com.sicoob.capes.frontoffice.negocio.delegates.CAPESFrontofficeFabricaDelegates)
 */
package br.com.sicoob.capes.frontoffice.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobFabricaDelegates;

/**
 * Fabrica dos business delegates a serem usados pelo Sistema CAPESComum.
 * 
 * @author Stefanini IT Solutions
 */
public final class CAPESFrontofficeFabricaDelegates extends BancoobFabricaDelegates {

	/** Instancia do Fabrica de Delegates. */
	private static CAPESFrontofficeFabricaDelegates fabrica;

	/**
	 * Retorna a fabrica de delegates a ser utilizada.
	 * 
	 * @return a fabrica de delegates a ser utilizada.
	 */
	public static CAPESFrontofficeFabricaDelegates getInstance() {
		if (fabrica == null) {
			synchronized (CAPESFrontofficeFabricaDelegates.class) {
				if (fabrica == null) {
					fabrica = new CAPESFrontofficeFabricaDelegates();
				}
			}
		}
		return fabrica;
	}

	/**
	 * Construtor privado no-args da classe
	 */
	private CAPESFrontofficeFabricaDelegates() {
	}
}