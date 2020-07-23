package br.com.sicoob.capes.relatorio.negocio.delegates;

import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioTributacaoServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.locator.CAPESRelatorioServiceLocator;

/**
 * A Classe RelatorioTributacaoDelegate.
 */
public class RelatorioTributacaoDelegate extends CAPESRelatorioDelegate<RelatorioTributacaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RelatorioTributacaoServico localizarServico() {
		return CAPESRelatorioServiceLocator.getInstance().localizarRelatorioTributacaoServico();
	}

}
