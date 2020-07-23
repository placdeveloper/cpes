/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.EstadoCivilServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.EstadoCivil;

/**
 * @author erico.junior
 * 
 */
public class EstadoCivilDelegate extends
		CAPESCadastroCrudDelegate<EstadoCivil, EstadoCivilServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EstadoCivilServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarEstadoCivilServico();
	}

}