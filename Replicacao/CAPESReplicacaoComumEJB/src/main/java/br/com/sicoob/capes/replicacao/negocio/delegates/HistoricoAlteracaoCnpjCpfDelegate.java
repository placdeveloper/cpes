/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.sicoob.capes.negocio.entidades.legado.historico.HistoricoAlteracaoCnpjCpf;
import br.com.sicoob.capes.replicacao.negocio.servicos.HistoricoAlteracaoCnpjCpfServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESReplicacaoComumServiceLocator;

/**
 * Delegate para o histórico de alteração de cnpj e cpf.
 * 
 * @author Erico.Junior
 */
public class HistoricoAlteracaoCnpjCpfDelegate extends
	EntidadeReplicavelDelegate<HistoricoAlteracaoCnpjCpf, HistoricoAlteracaoCnpjCpfServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoAlteracaoCnpjCpfServico localizarServico() {
		return CAPESReplicacaoComumServiceLocator.getInstance()
				.localizarHistoricoAlteracaoCnpjCpfServico();
	}

}
