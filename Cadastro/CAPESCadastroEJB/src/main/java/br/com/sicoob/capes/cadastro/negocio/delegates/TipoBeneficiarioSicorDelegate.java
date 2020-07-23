package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoBeneficiarioSicorServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.TipoBeneficiarioSicor;

public class TipoBeneficiarioSicorDelegate extends CAPESCadastroCrudDelegate<TipoBeneficiarioSicor, TipoBeneficiarioSicorServico> {

	@Override
	public TipoBeneficiarioSicorServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarTipoBeneficiarioSicorServico();
	}
}