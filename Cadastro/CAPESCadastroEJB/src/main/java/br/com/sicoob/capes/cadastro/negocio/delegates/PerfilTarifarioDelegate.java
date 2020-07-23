/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.PerfilTarifarioServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.PerfilTarifario;

/**
 * Business delegate para o perfil tarifario.
 * 
 * @author juan.damasceno
 */
public class PerfilTarifarioDelegate extends
		CAPESCadastroCrudDelegate<PerfilTarifario, PerfilTarifarioServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected PerfilTarifarioServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarPerfilTarifarioServico();
	}
}