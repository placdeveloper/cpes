package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoRelacionamentoBemImovelServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.bem.TipoRelacionamentoBemImovel;

public class TipoRelacionamentoBemImovelDelegate extends CAPESCadastroCrudDelegate<TipoRelacionamentoBemImovel, TipoRelacionamentoBemImovelServico> {
	
	@Override
	public TipoRelacionamentoBemImovelServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarTipoRelacionamentoBemImovelServico();
	}
}