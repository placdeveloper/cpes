/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.sicoob.capes.negocio.entidades.legado.Nucleo;
import br.com.sicoob.capes.replicacao.negocio.servicos.NucleoServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESReplicacaoComumServiceLocator;

/**
 * Delegate para os servi�os de nucleos no projeto de replica��o.
 * 
 */
public class NucleoDelegate extends EntidadeReplicavelDelegate<Nucleo, NucleoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected NucleoServico localizarServico() {
		return CAPESReplicacaoComumServiceLocator.getInstance().localizarNucleoServico();
	}

}
