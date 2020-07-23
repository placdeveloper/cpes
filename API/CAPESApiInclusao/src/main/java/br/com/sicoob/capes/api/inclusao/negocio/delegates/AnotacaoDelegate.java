package br.com.sicoob.capes.api.inclusao.negocio.delegates;

import br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces.IAnotacaoDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.dto.AnotacaoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.AnotacaoServico;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.locator.CAPESApiInclusaoServiceLocator;

/**
 * A Classe AnotacaoDelegate.
 * 
 * @author bruno.carneiro
 */
public class AnotacaoDelegate extends CAPESApiInclusaoDelegate<AnotacaoDTO, AnotacaoServico> implements IAnotacaoDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AnotacaoServico localizarServico() {
		return CAPESApiInclusaoServiceLocator.getInstance().localizarAnotacaoServico();
	}

}