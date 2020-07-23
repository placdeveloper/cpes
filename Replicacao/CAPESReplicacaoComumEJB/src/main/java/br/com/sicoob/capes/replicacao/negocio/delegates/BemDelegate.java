/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.sicoob.capes.negocio.entidades.legado.Bem;
import br.com.sicoob.capes.replicacao.negocio.servicos.BemServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESReplicacaoComumServiceLocator;

/**
 * Delegate utilizado na replicação dos bens.
 * 
 * @author Erico.Junior
 */
public class BemDelegate extends EntidadeReplicavelDelegate<Bem, BemServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemServico localizarServico() {
		return CAPESReplicacaoComumServiceLocator.getInstance().localizarBemServico();
	}

}