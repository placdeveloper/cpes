/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.sicoob.capes.negocio.entidades.legado.Produtividade;
import br.com.sicoob.capes.replicacao.negocio.servicos.ProdutividadeServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESReplicacaoComumServiceLocator;

/**
 * @author Erico.Junior
 * 
 */
public class ProdutividadeDelegate extends
		EntidadeReplicavelDelegate<Produtividade, ProdutividadeServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ProdutividadeServico localizarServico() {
		return CAPESReplicacaoComumServiceLocator.getInstance()
				.localizarProdutividadeServico();
	}
}
