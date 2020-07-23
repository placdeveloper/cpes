package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoServicoCondominialBemImovelServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.bem.TipoServicoCondominialBemImovel;

public class TipoServicoCondominialBemImovelDelegate extends CAPESCadastroCrudDelegate<TipoServicoCondominialBemImovel, TipoServicoCondominialBemImovelServico> {

	@Override
	public TipoServicoCondominialBemImovelServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarTipoServicoCondominialBemImovelServico();
	}
}
