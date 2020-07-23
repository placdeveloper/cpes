/* 
 * Sicoob
 * RelatorioAnotacaoDelegate 
 * Criado em: 30/06/2011
 */
package br.com.sicoob.capes.relatorio.negocio.delegates;

import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioAnotacaoServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.locator.CAPESRelatorioServiceLocator;

/**
 * TODO javadoc
 *
 * 30/06/2011
 * @author Rodrigo.Chaves
 * 
 */
public class RelatorioAnotacaoDelegate extends
		CAPESRelatorioDelegate<RelatorioAnotacaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RelatorioAnotacaoServico localizarServico() {
		return CAPESRelatorioServiceLocator.getInstance()
				.localizarRelatorioAnotacaoServico();
	}

}