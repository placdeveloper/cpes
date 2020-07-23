package br.com.sicoob.capes.api.inclusao.negocio.delegates;

import br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces.IMensagemDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.dto.MensagemDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.MensagemServico;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.locator.CAPESApiInclusaoServiceLocator;

/**
 * A classe com os serviços de mensagens para a pessoa.
 * 
 * @author Bruno.Carneiro
 */
public class MensagemDelegate extends CAPESApiInclusaoDelegate<MensagemDTO, MensagemServico> implements IMensagemDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected MensagemServico localizarServico() {
		return CAPESApiInclusaoServiceLocator.getInstance().localizarMensagemServico();
	}

}