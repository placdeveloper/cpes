/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.replicacao.negocio.servicos.ReplicacaoDominiosServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESServicoReplicacaoServiceLocator;

/**
 * Delegate para a replica��o dos dom�nios do cadastro �nico no legado.
 * @author Erico.Junior
 */
public class ReplicacaoDominiosDelegate extends
		CAPESServicoReplicacaoDelegate<ReplicacaoDominiosServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ReplicacaoDominiosServico localizarServico() {
		return CAPESServicoReplicacaoServiceLocator.getInstance()
				.localizarReplicacaoDominiosServico();
	}
	
	/**
	 * Replica os dom�nios do cadastro �nico no SQL (legado).
	 * 
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	public void replicar() throws BancoobException {
		getServico().replicar();
	}	
}
