/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.VinculoEmpregaticioServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.VinculoEmpregaticio;

/**
 * @author Erico.Junior
 * 
 */
public class VinculoEmpregaticioDelegate extends
		CAPESCadastroCrudDominioDelegate<VinculoEmpregaticio, VinculoEmpregaticioServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected VinculoEmpregaticioServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarVinculoEmpregaticioServico();
	}

}
