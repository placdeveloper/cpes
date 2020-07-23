package br.com.sicoob.capes.api.inclusao.negocio.delegates.crossContainer;

import br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces.IInformacaoProfissionalDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.dto.InformacaoProfissionalDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.InformacaoProfissionalServico;

/**
 * Delegate com os serviços de informação profissional.
 *
 * @author Bruno.Carneiro
 */
public class InformacaoProfissionalDelegate extends CAPESApiInclusaoDelegate<InformacaoProfissionalDTO, InformacaoProfissionalServico> implements IInformacaoProfissionalDelegate {
	
	/**
	 * 
	 */
	protected InformacaoProfissionalDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static InformacaoProfissionalDelegate getInstance() {
		return valueOf(InformacaoProfissionalDelegate.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected InformacaoProfissionalServico localizarServico() {
		return getLocator().localizarInformacaoProfissionalServico();
	}
	
}