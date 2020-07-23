/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.HistoricoAlteracaoCpfCnpjServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.HistoricoAlteracaoCpfCnpj;

/**
 * Delegate utilizada para o histórico de alteração de cpf/cnpj
 * 
 * @author Erico.Junior
 */
public class HistoricoAlteracaoCpfCnpjDelegate
		extends
		CAPESCadastroCrudDelegate<HistoricoAlteracaoCpfCnpj, HistoricoAlteracaoCpfCnpjServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoAlteracaoCpfCnpjServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarHistoricoAlteracaoCpfCnpjServico();
	}

}
