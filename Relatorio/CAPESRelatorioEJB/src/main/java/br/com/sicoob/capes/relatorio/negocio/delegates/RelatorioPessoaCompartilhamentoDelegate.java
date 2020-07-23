/**
 * 
 */
package br.com.sicoob.capes.relatorio.negocio.delegates;

import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioPessoaCompartilhamentoServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.locator.CAPESRelatorioServiceLocator;

/**
 * @author erico.junior
 *
 */
public class RelatorioPessoaCompartilhamentoDelegate extends CAPESRelatorioDelegate<RelatorioPessoaCompartilhamentoServico> {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RelatorioPessoaCompartilhamentoServico localizarServico() {
		return CAPESRelatorioServiceLocator.getInstance().localizarPessoaCompartilhamentoServico();
	}


}