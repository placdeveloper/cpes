/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.sicoob.capes.negocio.entidades.legado.InformacaoProfissional;
import br.com.sicoob.capes.replicacao.negocio.servicos.InformacaoProfissionalServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESReplicacaoComumServiceLocator;

/**
 * @author Erico.Junior
 *
 */
public class InformacaoProfissionalDelegate extends EntidadeReplicavelDelegate<InformacaoProfissional, InformacaoProfissionalServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected InformacaoProfissionalServico localizarServico() {
		return CAPESReplicacaoComumServiceLocator.getInstance().localizarInformacaoProfissionalServico();
	}
}