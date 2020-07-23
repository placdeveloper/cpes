/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.sicoob.capes.negocio.entidades.legado.Referencia;
import br.com.sicoob.capes.replicacao.negocio.servicos.ReferenciaServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESReplicacaoComumServiceLocator;

/**
 * Delegate para as refências das pessoas.
 * @author Erico.Junior
 */
public class ReferenciaDelegate extends EntidadeReplicavelDelegate<Referencia, ReferenciaServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ReferenciaServico localizarServico() {
		return CAPESReplicacaoComumServiceLocator.getInstance().localizarReferenciaServico();
	}
}
