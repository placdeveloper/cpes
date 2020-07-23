package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.FinalidadeEmpreendimentoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.FinalidadeEmpreendimento;

/**
 * @author diego.rezende
 *
 */
public class FinalidadeEmpreendimentoDelegate extends	CAPESCadastroCrudDelegate<FinalidadeEmpreendimento, FinalidadeEmpreendimentoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FinalidadeEmpreendimentoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarFinalidadeEmpreendimentoServico();
	}

}
