package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.sicoob.capes.negocio.entidades.legado.AvaliacaoFinanceira;
import br.com.sicoob.capes.replicacao.negocio.servicos.AvaliacaoFinanceiraServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESReplicacaoComumServiceLocator;

/**
 * Delegate utilizado na replicação das avaliação financeira.
 * 
 * @author Erico.Junior
 */
public class AvaliacaoFinanceiraDelegate extends EntidadeReplicavelDelegate<AvaliacaoFinanceira, AvaliacaoFinanceiraServico> {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AvaliacaoFinanceiraServico localizarServico() {
		return CAPESReplicacaoComumServiceLocator.getInstance().localizarAvaliacaoFinanceiraServico();
	}

}
