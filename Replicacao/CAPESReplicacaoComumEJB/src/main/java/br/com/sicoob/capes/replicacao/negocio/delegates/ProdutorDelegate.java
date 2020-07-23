/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.sicoob.capes.negocio.entidades.legado.Produtor;
import br.com.sicoob.capes.replicacao.negocio.servicos.ProdutorServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESReplicacaoComumServiceLocator;

/**
 * Delegate utilizado na replicação dos Produtores.
 * 
 * @author Erico.Junior
 */
public class ProdutorDelegate extends EntidadeReplicavelDelegate<Produtor, ProdutorServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ProdutorServico localizarServico() {
		return CAPESReplicacaoComumServiceLocator.getInstance().localizarProdutorServico();
	}

}
