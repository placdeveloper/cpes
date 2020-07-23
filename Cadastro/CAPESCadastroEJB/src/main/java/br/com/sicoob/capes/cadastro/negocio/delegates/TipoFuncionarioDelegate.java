/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoFuncionarioServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.TipoFuncionario;

/**
 * @author Erico.Junior
 * 
 */
public class TipoFuncionarioDelegate
		extends
		CAPESCadastroCrudDelegate<TipoFuncionario, TipoFuncionarioServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoFuncionarioServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarTipoFuncionarioServico();
	}

}
