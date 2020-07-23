package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.replicacao.negocio.servicos.ReplicarCadastroClientesServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESServicoReplicacaoServiceLocator;

/**
 * Delegate de ReplicarCadastroClientesServicoEJB.
 * @author Juan.Damasceno
 *
 */
public class ReplicarCadastroClientesDelegate extends
		CAPESServicoReplicacaoDelegate<ReplicarCadastroClientesServico> {
	
	/**
	 * Replica o cadastro de clientes para a base do DB2.
	 * @throws BancoobException 
	 */
	public void replicaCadastroClientes() throws BancoobException {
		getServico().replicaCadastroClientes();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ReplicarCadastroClientesServico localizarServico() {
		return CAPESServicoReplicacaoServiceLocator.getInstance().localizarServicoReplicarCadastroClientes();
	}
}