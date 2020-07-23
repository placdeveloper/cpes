/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.sicoob.capes.negocio.entidades.legado.Telefone;
import br.com.sicoob.capes.replicacao.negocio.servicos.TelefoneServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESReplicacaoComumServiceLocator;

/**
 * Delegate para a replicação de telefones.
 * 
 * @author erico.junior
 */
public class TelefoneDelegate extends EntidadeReplicavelDelegate<Telefone, TelefoneServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TelefoneServico localizarServico() {
		return CAPESReplicacaoComumServiceLocator.getInstance().localizarTelefoneServico();
	}

}
