package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoClassificacaoBemServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.bem.TipoClassificacaoBem;

public class TipoClassificacaoBemDelegate extends CAPESCadastroCrudDelegate<TipoClassificacaoBem, TipoClassificacaoBemServico> {

	@Override
	public TipoClassificacaoBemServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarTipoClassificacaoBemServico();
	}
}
