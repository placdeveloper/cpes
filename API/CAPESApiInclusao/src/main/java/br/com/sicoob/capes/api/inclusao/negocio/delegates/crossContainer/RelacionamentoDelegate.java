package br.com.sicoob.capes.api.inclusao.negocio.delegates.crossContainer;

import br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces.IRelacionamentoDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.dto.RelacionamentoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.RelacionamentoServico;

/**
 * A classe RelacionamentoDelegate
 * 
 * @author Bruno.Carneiro
 */
public class RelacionamentoDelegate extends CAPESApiInclusaoDelegate<RelacionamentoDTO, RelacionamentoServico> implements IRelacionamentoDelegate {
	
	/**
	 * 
	 */
	protected RelacionamentoDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static RelacionamentoDelegate getInstance() {
		return valueOf(RelacionamentoDelegate.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RelacionamentoServico localizarServico() {
		return getLocator().localizarRelacionamentoServico();
	}

}