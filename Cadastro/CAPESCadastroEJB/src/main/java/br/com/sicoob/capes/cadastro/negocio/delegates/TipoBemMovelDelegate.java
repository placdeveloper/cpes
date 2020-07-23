package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoBemMovelServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.bem.TipoBemMovel;

public class TipoBemMovelDelegate extends CAPESCadastroCrudDelegate<TipoBemMovel, TipoBemMovelServico> {
	
	@Override
	public TipoBemMovelServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarTipoBemMovelServico();
	}
}