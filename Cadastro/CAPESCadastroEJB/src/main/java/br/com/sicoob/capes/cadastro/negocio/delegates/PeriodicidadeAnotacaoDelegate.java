package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.PeriodicidadeAnotacaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.PeriodicidadeAnotacao;

/**
 * Business delegate para as periodicidades de anotação.
 * 
 * @author Rodrigo.Chaves
 */
public class PeriodicidadeAnotacaoDelegate
		extends
		CAPESCadastroCrudDelegate<PeriodicidadeAnotacao, PeriodicidadeAnotacaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected PeriodicidadeAnotacaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarPeriodicidadeAnotacaoServico();
	}

}
