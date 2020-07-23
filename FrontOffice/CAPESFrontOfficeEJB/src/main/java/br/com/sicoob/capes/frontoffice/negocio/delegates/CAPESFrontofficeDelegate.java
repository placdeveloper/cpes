/*
 * SICOOB
 * 
 * CAPESFrontofficeDelegate.java(br.com.sicoob.capes.frontoffice.negocio.delegates.CAPESFrontofficeDelegate)
 */
package br.com.sicoob.capes.frontoffice.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobDelegate;
import br.com.sicoob.capes.frontoffice.negocio.servicos.CAPESFrontofficeServico;

/**
 * Business delegate a ser usado pelo Sistema CAPESComum.
 * 
 * @author Stefanini IT Solutions
 */
public abstract class CAPESFrontofficeDelegate<T extends CAPESFrontofficeServico> extends
		BancoobDelegate<T> {

}
