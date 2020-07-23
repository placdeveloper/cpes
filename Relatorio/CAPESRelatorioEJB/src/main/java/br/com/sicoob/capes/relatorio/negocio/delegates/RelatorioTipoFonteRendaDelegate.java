/**
 * 
 */
package br.com.sicoob.capes.relatorio.negocio.delegates;

import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioTipoFonteRendaServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.locator.CAPESRelatorioServiceLocator;

/**
 * Business delegate para os tipos de fonte de renda. 
 * @author Erico.Junior
 */
public class RelatorioTipoFonteRendaDelegate extends
		CAPESRelatorioDominioDelegate<RelatorioTipoFonteRendaServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RelatorioTipoFonteRendaServico localizarServico() {
		return CAPESRelatorioServiceLocator.getInstance()
				.localizarTipoFonteRendaServico();
	}
	
}
