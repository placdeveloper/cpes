/* 
 * Sicoob
 * RelatorioTipoRelacionamentoPessoaDelegate.java 
 * Criado em: 08/08/2011
 */
package br.com.sicoob.capes.relatorio.negocio.delegates;

import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioTipoRelacionamentoPessoaServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.locator.CAPESRelatorioServiceLocator;

/**
 * 08/08/2011
 * 
 * @author Rodrigo.Chaves
 */
public class RelatorioTipoRelacionamentoPessoaDelegate extends
		CAPESRelatorioDominioDelegate<RelatorioTipoRelacionamentoPessoaServico> {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RelatorioTipoRelacionamentoPessoaServico localizarServico() {
		return CAPESRelatorioServiceLocator.getInstance()
				.localizarTipoRelacionamentoPessoaServico();
	}
	
}
