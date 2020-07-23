/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.sicoob.capes.negocio.entidades.legado.Bem;
import br.com.sicoob.capes.negocio.entidades.legado.BemOnus;
import br.com.sicoob.capes.replicacao.negocio.servicos.BemOnusServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESReplicacaoComumServiceLocator;

/**
 * Delegate para os onus de bem.
 * 
 * @author juan.damasceno
 */
public class BemOnusDelegate extends EntidadeReplicavelDelegate<BemOnus, BemOnusServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemOnusServico localizarServico() {
		return CAPESReplicacaoComumServiceLocator.getInstance().localizarBemOnusServico();
	}
	
	/**
	 * Obter max sequencial por pessoa.
	 *
	 * @param bem o valor de bem
	 * @return Short
	 */
	public Short obterMaxSequencialPorPessoa(Bem bem) {
		return getServico().obterMaxSequencialPorPessoa(bem);
	}
}