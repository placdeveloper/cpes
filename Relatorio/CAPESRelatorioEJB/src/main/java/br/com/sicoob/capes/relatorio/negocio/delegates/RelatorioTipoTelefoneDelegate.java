/* 
 * Sicoob
 * RelatorioTipoTelefoneDelegate.java 
 * Criado em: 05/05/2011
 */
package br.com.sicoob.capes.relatorio.negocio.delegates;

import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioTipoTelefoneServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.locator.CAPESRelatorioServiceLocator;

/**
 * Business delegate para os tipos de telefone
 * 
 * 05/05/2011
 * 
 * @author Rodrigo.Chaves
 * 
 */
public class RelatorioTipoTelefoneDelegate extends
		CAPESRelatorioDominioDelegate<RelatorioTipoTelefoneServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RelatorioTipoTelefoneServico localizarServico() {
		return CAPESRelatorioServiceLocator.getInstance()
				.localizarTipoTelefoneServico();
	}
}
