/* 
 * Sicoob
 * RelatorioProvaVidaDelegate 
 * Criado em: 30/06/2011
 */
package br.com.sicoob.capes.relatorio.negocio.delegates;

import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioProvaVidaServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.locator.CAPESRelatorioServiceLocator;

/**
 * TODO javadoc
 *
 * 30/06/2011
 * @author Rodrigo.Chaves
 * 
 */
public class RelatorioProvaVidaDelegate extends CAPESRelatorioDelegate<RelatorioProvaVidaServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RelatorioProvaVidaServico localizarServico() {
		return CAPESRelatorioServiceLocator.getInstance().localizarRelatorioProvaVidaServico();
	}


}