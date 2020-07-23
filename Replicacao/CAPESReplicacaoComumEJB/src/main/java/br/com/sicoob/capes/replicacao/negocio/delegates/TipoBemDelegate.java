/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.sicoob.capes.negocio.entidades.legado.TipoBem;
import br.com.sicoob.capes.replicacao.negocio.servicos.TipoBemServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESReplicacaoComumServiceLocator;

/**
 * @author erico.junior
 */
public class TipoBemDelegate extends EntidadeDominioReplicavelDelegate<TipoBem, TipoBemServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoBemServico localizarServico() {
		return CAPESReplicacaoComumServiceLocator.getInstance().localizarTipoBemServico();
	}
}