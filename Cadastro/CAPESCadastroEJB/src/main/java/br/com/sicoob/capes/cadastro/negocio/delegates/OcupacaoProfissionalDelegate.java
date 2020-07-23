/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.OcupacaoProfissionalServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.OcupacaoProfissional;

/**
 * @author erico.junior
 * 
 */
public class OcupacaoProfissionalDelegate extends
		CAPESCadastroCrudDelegate<OcupacaoProfissional, OcupacaoProfissionalServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected OcupacaoProfissionalServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarOcupacaoProfissionalServico();
	}

}
