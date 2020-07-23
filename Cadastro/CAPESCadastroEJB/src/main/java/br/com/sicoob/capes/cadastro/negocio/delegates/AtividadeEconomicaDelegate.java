package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.AtividadeEconomicaServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.AtividadeEconomica;

/**
 * A Classe AtividadeEconomicaDelegate.
 */
public class AtividadeEconomicaDelegate extends CAPESCadastroCrudDelegate<AtividadeEconomica, AtividadeEconomicaServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AtividadeEconomicaServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarAtividadeEconomicaServico();
	}

}