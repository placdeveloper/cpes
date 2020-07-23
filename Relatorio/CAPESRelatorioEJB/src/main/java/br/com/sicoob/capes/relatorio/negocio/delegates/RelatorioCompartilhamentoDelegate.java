package br.com.sicoob.capes.relatorio.negocio.delegates;

import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioCompartilhamentoServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.locator.CAPESRelatorioServiceLocator;

/**
 * A Classe RelatorioCompartilhamentoDelegate.
 */
public class RelatorioCompartilhamentoDelegate extends CAPESRelatorioDelegate<RelatorioCompartilhamentoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RelatorioCompartilhamentoServico localizarServico() {
		return CAPESRelatorioServiceLocator.getInstance()
				.localizarRelCompartilhamentoServico();
	}
	
}
