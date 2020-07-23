/**
 * 
 */
package br.com.sicoob.capes.relatorio.negocio.delegates;

import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioTipoAnotacaoServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.locator.CAPESRelatorioServiceLocator;

/**
 * Business delegate para os tipos de anotação.  
 * @author Erico.Junior
 */
public class RelatorioTipoAnotacaoDelegate extends
		CAPESRelatorioDelegate<RelatorioTipoAnotacaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RelatorioTipoAnotacaoServico localizarServico() {
		return CAPESRelatorioServiceLocator.getInstance().localizarTipoAnotacaoServico();
	}

	
}
