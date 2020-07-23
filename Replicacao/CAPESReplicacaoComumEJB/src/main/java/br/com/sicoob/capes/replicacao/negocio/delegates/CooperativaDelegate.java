package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.sicoob.capes.negocio.entidades.legado.Cooperativa;
import br.com.sicoob.capes.replicacao.negocio.servicos.CooperativaServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESReplicacaoComumServiceLocator;

/**
 * A Classe CooperativaDelegate.
 */
public class CooperativaDelegate extends
		CAPESReplicacaoComumCrudDelegate<Cooperativa, CooperativaServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CooperativaServico localizarServico() {
		return CAPESReplicacaoComumServiceLocator.getInstance().localizarCooperativaServico();
	}
	
}
