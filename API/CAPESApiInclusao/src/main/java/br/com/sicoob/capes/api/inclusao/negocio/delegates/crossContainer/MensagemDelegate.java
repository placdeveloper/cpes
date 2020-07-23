package br.com.sicoob.capes.api.inclusao.negocio.delegates.crossContainer;

import br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces.IMensagemDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.dto.MensagemDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.MensagemServico;

/**
 * A classe com os serviços de mensagens para a pessoa.
 * 
 * @author Bruno.Carneiro
 */
public class MensagemDelegate extends CAPESApiInclusaoDelegate<MensagemDTO, MensagemServico> implements IMensagemDelegate {
	
	/**
	 * 
	 */
	protected MensagemDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static MensagemDelegate getInstance() {
		return valueOf(MensagemDelegate.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected MensagemServico localizarServico() {
		return getLocator().localizarMensagemServico();
	}

}