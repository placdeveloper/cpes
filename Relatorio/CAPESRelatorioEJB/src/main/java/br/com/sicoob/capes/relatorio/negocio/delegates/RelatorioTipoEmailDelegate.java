/* 
 * Sicoob
 * RelatorioTipoEmailDelegate.java 
 * Criado em: 05/05/2011
 */
package br.com.sicoob.capes.relatorio.negocio.delegates;

import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioTipoEmailServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.locator.CAPESRelatorioServiceLocator;

/**
 * Business delegate para os tipos de e-mail
 * 
 * 05/05/2011
 * 
 * @author Rodrigo.Chaves
 * 
 */
public class RelatorioTipoEmailDelegate extends CAPESRelatorioDominioDelegate<RelatorioTipoEmailServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RelatorioTipoEmailServico localizarServico() {
		return CAPESRelatorioServiceLocator.getInstance().localizarTipoEmailServico();
	}

}
