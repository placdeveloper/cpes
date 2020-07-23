package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoUsoBemServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.bem.TipoUsoBemImovel;

public class TipoUsoBemDelegate extends CAPESCadastroCrudDelegate<TipoUsoBemImovel, TipoUsoBemServico> {

	@Override
	public TipoUsoBemServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarTipoUsoBemServico();
	}
}