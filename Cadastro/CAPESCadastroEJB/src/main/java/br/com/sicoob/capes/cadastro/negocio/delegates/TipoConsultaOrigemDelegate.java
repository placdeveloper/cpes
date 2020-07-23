package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoConsultaOrigemServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.TipoConsultaOrigem;

/**
 * A Classe TipoConsultaOrigemDelegate.
 */
public class TipoConsultaOrigemDelegate extends CAPESCadastroCrudDelegate<TipoConsultaOrigem, TipoConsultaOrigemServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoConsultaOrigemServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarTipoConsultaOrigem();
	}

}
