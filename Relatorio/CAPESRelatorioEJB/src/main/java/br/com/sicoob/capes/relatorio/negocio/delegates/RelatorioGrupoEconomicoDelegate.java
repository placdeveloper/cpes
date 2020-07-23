package br.com.sicoob.capes.relatorio.negocio.delegates;

import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioGrupoEconomicoServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.locator.CAPESRelatorioServiceLocator;

/**
 * A Classe RelatorioGrupoEconomicoDelegate.
 */
public class RelatorioGrupoEconomicoDelegate extends CAPESRelatorioDelegate<RelatorioGrupoEconomicoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RelatorioGrupoEconomicoServico localizarServico() {
		return CAPESRelatorioServiceLocator.getInstance().localizarGrupoEconomicoServico();
	}

}
