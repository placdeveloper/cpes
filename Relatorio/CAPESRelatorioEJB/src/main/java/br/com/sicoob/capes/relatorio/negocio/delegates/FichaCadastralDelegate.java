/**
 * 
 */
package br.com.sicoob.capes.relatorio.negocio.delegates;

import br.com.sicoob.capes.relatorio.negocio.servicos.FichaCadastralServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.locator.CAPESRelatorioServiceLocator;

/**
 * Business delegate a consulta da ficha cadastral  
 * @author juan.damasceno
 */
public class FichaCadastralDelegate extends CAPESRelatorioDelegate<FichaCadastralServico> {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FichaCadastralServico localizarServico() {
		return CAPESRelatorioServiceLocator.getInstance().localizarFichaCadastralServico();
	}
}
