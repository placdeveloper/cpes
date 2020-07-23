/* 
 * Sicoob
 * RelatorioTipoEnderecoDelegate.java 
 * Criado em: 05/05/2011
 */
package br.com.sicoob.capes.relatorio.negocio.delegates;

import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioTipoEnderecoServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.locator.CAPESRelatorioServiceLocator;

/**
 * Business delegate para os tipos de endereço
 * 
 * 05/05/2011
 * 
 * @author Rodrigo.Chaves
 * 
 */
public class RelatorioTipoEnderecoDelegate extends
		CAPESRelatorioDominioDelegate<RelatorioTipoEnderecoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RelatorioTipoEnderecoServico localizarServico() {
		return CAPESRelatorioServiceLocator.getInstance()
				.localizarTipoEnderecoServico();
	}

}
