package br.com.sicoob.capes.relatorio.negocio.delegates;

import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioDeclaracaoPropositoServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.locator.CAPESRelatorioServiceLocator;

/**
 * A Classe RelatorioDeclaracaoPropositoDelegate.
 */
public class RelatorioDeclaracaoPropositoDelegate extends CAPESRelatorioDelegate<RelatorioDeclaracaoPropositoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RelatorioDeclaracaoPropositoServico localizarServico() {
		return CAPESRelatorioServiceLocator.getInstance().localizarRelatorioDeclaracaoPropositoServico();
	}

}