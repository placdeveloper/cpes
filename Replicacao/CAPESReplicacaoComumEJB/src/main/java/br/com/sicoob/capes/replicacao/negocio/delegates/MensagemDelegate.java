/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.sicoob.capes.negocio.entidades.legado.Mensagem;
import br.com.sicoob.capes.replicacao.negocio.servicos.MensagemServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESReplicacaoComumServiceLocator;

/**
 * Delegate utilizado na replicação dos bens.
 * 
 * @author juan.damasceno
 */
public class MensagemDelegate extends EntidadeReplicavelDelegate<Mensagem, MensagemServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected MensagemServico localizarServico() {
		return CAPESReplicacaoComumServiceLocator.getInstance().localizarMensagemServico();
	}
}