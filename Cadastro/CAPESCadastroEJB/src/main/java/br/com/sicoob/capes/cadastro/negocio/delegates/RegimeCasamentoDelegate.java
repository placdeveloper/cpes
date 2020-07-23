/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.RegimeCasamentoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.RegimeCasamento;

/**
 * A Classe RegimeCasamentoDelegate.
 */
public class RegimeCasamentoDelegate extends
		CAPESCadastroCrudDelegate<RegimeCasamento, RegimeCasamentoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RegimeCasamentoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarRegimeCasamentoServico();
	}

}