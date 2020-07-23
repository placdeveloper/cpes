package br.com.sicoob.capes.relatorio.negocio.delegates;

import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioVencimentoCadastroServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.locator.CAPESRelatorioServiceLocator;

/**
 * A Classe RelatorioVencimentoCadastroDelegate.
 */
public class RelatorioVencimentoCadastroDelegate extends CAPESRelatorioDelegate<RelatorioVencimentoCadastroServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RelatorioVencimentoCadastroServico localizarServico() {
		return CAPESRelatorioServiceLocator.getInstance()
				.localizarRelVencimentoCadastroServico();
	}
	
}
