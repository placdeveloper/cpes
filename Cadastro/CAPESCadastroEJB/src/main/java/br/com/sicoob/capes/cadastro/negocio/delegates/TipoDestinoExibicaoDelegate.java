package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoDestinoExibicaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.TipoDestinoExibicao;


/**
 * A Classe TipoDestinoExibicaoDelegate.
 */
public class TipoDestinoExibicaoDelegate extends CAPESCadastroCrudDelegate<TipoDestinoExibicao, TipoDestinoExibicaoServico>  {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoDestinoExibicaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarTipoDestinoExibicaoServico();
	}

	
}
