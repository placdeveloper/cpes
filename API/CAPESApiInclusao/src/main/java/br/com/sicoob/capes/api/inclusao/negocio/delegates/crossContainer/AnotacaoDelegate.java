package br.com.sicoob.capes.api.inclusao.negocio.delegates.crossContainer;

import br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces.IAnotacaoDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.dto.AnotacaoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.AnotacaoServico;

/**
 * A Classe AnotacaoDelegate.
 * 
 * @author bruno.carneiro
 */
public class AnotacaoDelegate extends CAPESApiInclusaoDelegate<AnotacaoDTO, AnotacaoServico> implements IAnotacaoDelegate {
	
	/**
	 * 
	 */
	protected AnotacaoDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static AnotacaoDelegate getInstance() {
		return valueOf(AnotacaoDelegate.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AnotacaoServico localizarServico() {
		return getLocator().localizarAnotacaoServico();
	}

}