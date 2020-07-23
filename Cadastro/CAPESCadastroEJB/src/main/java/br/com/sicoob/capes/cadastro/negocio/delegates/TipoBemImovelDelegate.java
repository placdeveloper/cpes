package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoBemImovelServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.bem.TipoBemImovel;

public class TipoBemImovelDelegate extends CAPESCadastroCrudDelegate<TipoBemImovel, TipoBemImovelServico> {
	@Override
	public TipoBemImovelServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarTipoBemImovelServico();
	}
}