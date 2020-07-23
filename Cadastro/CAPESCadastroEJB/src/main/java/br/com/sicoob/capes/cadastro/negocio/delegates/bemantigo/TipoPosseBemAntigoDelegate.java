/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates.bemantigo;

import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo.TipoPosseBemAntigoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.bemantigo.TipoPosseBem;

/**
 * Delegate para os tipos de bens.
 * 
 * @author juan.damasceno
 */
public class TipoPosseBemAntigoDelegate extends
		CAPESCadastroCrudDelegate<TipoPosseBem, TipoPosseBemAntigoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoPosseBemAntigoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarTipoPosseBemAntigoServico();
	}
}
