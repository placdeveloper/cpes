package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoChassiBemServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.bem.TipoChassiBem;

public class TipoChassiBemDelegate extends CAPESCadastroCrudDelegate<TipoChassiBem, TipoChassiBemServico> {

	@Override
	public TipoChassiBemServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarTipoChassiBemServico();
	}

}