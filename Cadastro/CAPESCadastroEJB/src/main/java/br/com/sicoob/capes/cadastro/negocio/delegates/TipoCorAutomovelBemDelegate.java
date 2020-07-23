package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoCorAutomovelBemServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.bem.TipoCorAutomovelBem;

public class TipoCorAutomovelBemDelegate extends CAPESCadastroCrudDelegate<TipoCorAutomovelBem, TipoCorAutomovelBemServico> {

	@Override
	public TipoCorAutomovelBemServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarTipoCorAutomovelBemServico();
	}
	
}