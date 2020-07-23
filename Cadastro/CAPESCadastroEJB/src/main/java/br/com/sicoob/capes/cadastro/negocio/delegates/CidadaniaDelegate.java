package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.CidadaniaServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.Cidadania;

/**
 * A Classe CidadaniaDelegate.
 */
public class CidadaniaDelegate extends CAPESCadastroCrudDelegate<Cidadania, CidadaniaServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CidadaniaServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarCidadaniaServico();
	}

}
