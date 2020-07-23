/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.sicoob.capes.negocio.entidades.legado.Bem;
import br.com.sicoob.capes.negocio.entidades.legado.BemPosse;
import br.com.sicoob.capes.replicacao.negocio.servicos.BemPosseServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESReplicacaoComumServiceLocator;

/**
 * Delegate para as posses do bem.
 * 
 * @author juan.damasceno
 */
public class BemPosseDelegate extends EntidadeReplicavelDelegate<BemPosse, BemPosseServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemPosseServico localizarServico() {
		return CAPESReplicacaoComumServiceLocator.getInstance().localizarBemPosseServico();
	}
	
	/**
	 * Obter max sequencial.
	 *
	 * @param bemSQL o valor de bem sql
	 * @return Short
	 */
	public Short obterMaxSequencial(Bem bemSQL) {
		return getServico().obterMaxSequencial(bemSQL);
	}
}