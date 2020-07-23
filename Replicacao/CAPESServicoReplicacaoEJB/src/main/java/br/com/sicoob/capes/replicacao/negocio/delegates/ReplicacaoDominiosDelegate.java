/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.replicacao.negocio.servicos.ReplicacaoDominiosServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESServicoReplicacaoServiceLocator;

/**
 * Delegate para a replicação dos domínios do cadastro único no legado.
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
	 * Replica os domínios do cadastro único no SQL (legado).
	 * 
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	public void replicar() throws BancoobException {
		getServico().replicar();
	}	
}
