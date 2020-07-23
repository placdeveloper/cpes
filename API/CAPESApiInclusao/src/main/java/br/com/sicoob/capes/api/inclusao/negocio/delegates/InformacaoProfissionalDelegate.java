package br.com.sicoob.capes.api.inclusao.negocio.delegates;

import br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces.IInformacaoProfissionalDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.dto.InformacaoProfissionalDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.InformacaoProfissionalServico;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.locator.CAPESApiInclusaoServiceLocator;

/**
 * Delegate com os serviços de informação profissional.
 *
 * @author Bruno.Carneiro
 */
public class InformacaoProfissionalDelegate extends CAPESApiInclusaoDelegate<InformacaoProfissionalDTO, InformacaoProfissionalServico> implements IInformacaoProfissionalDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected InformacaoProfissionalServico localizarServico() {
		return CAPESApiInclusaoServiceLocator.getInstance().localizarInformacaoProfissionalServico();
	}
	
}