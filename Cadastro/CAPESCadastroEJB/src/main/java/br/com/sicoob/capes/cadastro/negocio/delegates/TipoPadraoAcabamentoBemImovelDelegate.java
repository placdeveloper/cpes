package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoPadraoAcabamentoBemImovelServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.bem.TipoPadraoAcabamentoBemImovel;

public class TipoPadraoAcabamentoBemImovelDelegate extends CAPESCadastroCrudDelegate<TipoPadraoAcabamentoBemImovel, TipoPadraoAcabamentoBemImovelServico> {
	
	@Override
	public TipoPadraoAcabamentoBemImovelServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarTipoPadraoAcabamentoBemImovelServico();
	}
}