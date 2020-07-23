package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoImplantacaoBemImovelServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.bem.TipoImplantacaoBemImovel;

public class TipoImplantacaoBemImovelDelegate extends CAPESCadastroCrudDelegate<TipoImplantacaoBemImovel, TipoImplantacaoBemImovelServico> {
	
	@Override
	public TipoImplantacaoBemImovelServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarTipoImplantacaoBemImovelServico();
	}
	
}