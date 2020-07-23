/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.sicoob.capes.negocio.entidades.legado.UnidadeMedida;
import br.com.sicoob.capes.replicacao.negocio.servicos.UnidadeMedidaServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESReplicacaoComumServiceLocator;

/**
 * Delegate para as unidades de medida.
 * 
 * @author Erico.Junior
 */
public class UnidadeMedidaDelegate extends
		EntidadeDominioReplicavelDelegate<UnidadeMedida, UnidadeMedidaServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected UnidadeMedidaServico localizarServico() {
		return CAPESReplicacaoComumServiceLocator.getInstance().localizarUnidadeMedidaServico();
	}

}
