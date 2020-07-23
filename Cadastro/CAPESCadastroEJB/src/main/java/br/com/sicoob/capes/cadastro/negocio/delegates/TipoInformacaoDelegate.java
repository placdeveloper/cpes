package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoInformacaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.TipoInformacao;

/**
 * A Classe TipoInformacaoDelegate.
 */
public class TipoInformacaoDelegate extends CAPESCadastroCrudDelegate<TipoInformacao, TipoInformacaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoInformacaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarTipoInformacaoServico();
	}

}