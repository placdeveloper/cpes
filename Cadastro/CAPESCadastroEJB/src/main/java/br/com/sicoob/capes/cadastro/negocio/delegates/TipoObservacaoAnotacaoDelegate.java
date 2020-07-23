package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoObservacaoAnotacaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.TipoObservacaoAnotacao;


/**
 * A Classe TipoObservacaoAnotacaoDelegate.
 */
public class TipoObservacaoAnotacaoDelegate extends CAPESCadastroCrudDelegate<TipoObservacaoAnotacao, TipoObservacaoAnotacaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoObservacaoAnotacaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarTipoObservacaoAnotacao();
	}

}
