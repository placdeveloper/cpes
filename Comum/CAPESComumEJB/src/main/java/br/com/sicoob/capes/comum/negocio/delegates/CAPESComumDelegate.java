/*
 * SICOOB
 * 
 * CAPESComumDelegate.java(br.com.sicoob.capes.comum.negocio.delegates.CAPESComumDelegate)
 */
package br.com.sicoob.capes.comum.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobDelegate;
import br.com.sicoob.capes.comum.negocio.servicos.CAPESComumServico;

/**
 * Business delegate a ser usado pelo Sistema CAPESComum.
 * 
 * @author Stefanini IT Solutions
 */
public abstract class CAPESComumDelegate<T extends CAPESComumServico> extends
		BancoobDelegate<T> {

}
