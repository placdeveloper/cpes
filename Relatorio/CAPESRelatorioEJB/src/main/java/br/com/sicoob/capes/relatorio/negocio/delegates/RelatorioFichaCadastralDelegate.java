/**
 * 
 */
package br.com.sicoob.capes.relatorio.negocio.delegates;

import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioFichaCadastralServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.locator.CAPESRelatorioServiceLocator;

/**
 * Delegate utilizada para a gera��o do relatorio de ficha cadastral.
 * 
 * @author juan.damasceno
 */
public class RelatorioFichaCadastralDelegate extends
		CAPESRelatorioDelegate<RelatorioFichaCadastralServico> {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RelatorioFichaCadastralServico localizarServico() {
		return CAPESRelatorioServiceLocator.getInstance()
				.localizarRelatorioFichaCadastralServico();
	}

}