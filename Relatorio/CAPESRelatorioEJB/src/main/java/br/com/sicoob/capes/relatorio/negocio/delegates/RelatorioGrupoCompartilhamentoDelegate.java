/**
 * 
 */
package br.com.sicoob.capes.relatorio.negocio.delegates;

import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioGrupoCompartilhamentoServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.locator.CAPESRelatorioServiceLocator;

/**
 * 
 * @author juan.damasceno
 *
 */
public class RelatorioGrupoCompartilhamentoDelegate 
		extends CAPESRelatorioDominioDelegate<RelatorioGrupoCompartilhamentoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RelatorioGrupoCompartilhamentoServico localizarServico() {
		return CAPESRelatorioServiceLocator.getInstance().localizarGrupoCompartilhamentoServico();
	}
	
}