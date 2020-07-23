/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.sicoob.capes.replicacao.negocio.servicos.AtualizarCadastroClienteServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESReplicacaoProcessamentoServiceLocator;

/**
 * @author Erico.Junior
 * 
 */
@SuppressWarnings("rawtypes")
public class AtualizarCadastroClienteDelegate extends
		CAPESReplicacaoProcessamentoDelegate<AtualizarCadastroClienteServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AtualizarCadastroClienteServico localizarServico() {
		return CAPESReplicacaoProcessamentoServiceLocator.getInstance().localizarAtualizarCadastroClienteServico();
	}
}
