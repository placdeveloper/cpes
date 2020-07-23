package br.com.sicoob.capes.cadastro.negocio.delegates.bemantigo;

import br.com.sicoob.capes.cadastro.negocio.delegates.EntidadeCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo.BemImovelAntigoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel;

/**
 * Delegate bens
 * @author juan.damasceno
 *
 */
public class BemImovelAntigoDelegate extends EntidadeCadastroDelegate<BemImovel, BemImovelAntigoServico>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemImovelAntigoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarBemImovelAntigoServico();
	}
}