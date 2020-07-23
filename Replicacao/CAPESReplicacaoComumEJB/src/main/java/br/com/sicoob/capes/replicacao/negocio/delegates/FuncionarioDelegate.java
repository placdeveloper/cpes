/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.Funcionario;
import br.com.sicoob.capes.replicacao.negocio.servicos.FuncionarioServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESReplicacaoComumServiceLocator;

/**
 * Delegate para os clientes.
 * @author juan.damasceno
 */
public class FuncionarioDelegate extends EntidadeReplicavelDelegate<Funcionario, FuncionarioServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FuncionarioServico localizarServico() {
		return CAPESReplicacaoComumServiceLocator.getInstance().localizarFuncionarioServico();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Funcionario obterPorIdDB2(Funcionario entidade, Integer idInstituicao)
			throws BancoobException {
		
		return super.obterPorIdDB2(entidade, idInstituicao);
	}
	
}