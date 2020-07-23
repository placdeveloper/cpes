package br.com.sicoob.capes.relatorio.negocio.delegates;

import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioCadastroCompartilhadoServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.locator.CAPESRelatorioServiceLocator;

/**
 * A Classe RelatorioCompartilhamentoDelegate.
 */
public class RelatorioCadastroCompartilhadoDelegate extends CAPESRelatorioDelegate<RelatorioCadastroCompartilhadoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RelatorioCadastroCompartilhadoServico localizarServico() {
		return CAPESRelatorioServiceLocator.getInstance().localizarRelatorioCadastroCompartilhadoServico();
	}

}
