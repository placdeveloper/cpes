package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoOnusBemServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.bem.TipoOnusBem;

public class TipoOnusBemDelegate extends CAPESCadastroCrudDelegate<TipoOnusBem, TipoOnusBemServico> {

	@Override
	public TipoOnusBemServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarTipoOnusBemServico();
	}
}