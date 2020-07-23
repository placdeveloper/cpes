/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.sicoob.capes.negocio.entidades.legado.Bem;
import br.com.sicoob.capes.negocio.entidades.legado.BemRegistro;
import br.com.sicoob.capes.replicacao.negocio.servicos.BemRegistroServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESReplicacaoComumServiceLocator;

/**
 * Delegate para os bens de registro.
 * 
 * @author juan.damasceno
 */
public class BemRegistroDelegate extends EntidadeReplicavelDelegate<BemRegistro, BemRegistroServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemRegistroServico localizarServico() {
		return CAPESReplicacaoComumServiceLocator.getInstance().localizarBemRegistroServico();
	}
	
	/**
	 * Obter max sequencial.
	 *
	 * @param bem o valor de bem
	 * @return Short
	 */
	public Short obterMaxSequencial(Bem bem) {
		return getServico().obterMaxSequencial(bem);
	}
}