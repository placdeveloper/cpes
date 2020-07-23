package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoLocalizacaoBemServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.bem.TipoLocalizacaoBem;

public class TipoLocalizacaoBemDelegate extends CAPESCadastroCrudDelegate<TipoLocalizacaoBem, TipoLocalizacaoBemServico> {

	@Override
	public TipoLocalizacaoBemServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarTipoLocalizacaoBemServico();
	}
}
