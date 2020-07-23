package br.com.sicoob.capes.relatorio.negocio.delegates;

import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioValidacaoCadastralServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.locator.CAPESRelatorioServiceLocator;

/**
 * A Classe RelatorioValidacaoCadastralDelegate.
 */
public class RelatorioValidacaoCadastralDelegate extends CAPESRelatorioDelegate<RelatorioValidacaoCadastralServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RelatorioValidacaoCadastralServico localizarServico() {
		return CAPESRelatorioServiceLocator.getInstance().localizarRelatorioValidacaoCadastralServico();
	}
	
}