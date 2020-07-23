package br.com.sicoob.capes.api.inclusao.negocio.delegates;

import br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces.IRelacionamentoDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.dto.RelacionamentoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.RelacionamentoServico;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.locator.CAPESApiInclusaoServiceLocator;

/**
 * A classe RelacionamentoDelegate
 * 
 * @author Bruno.Carneiro
 */
public class RelacionamentoDelegate extends CAPESApiInclusaoDelegate<RelacionamentoDTO, RelacionamentoServico> implements IRelacionamentoDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RelacionamentoServico localizarServico() {
		return CAPESApiInclusaoServiceLocator.getInstance().localizarRelacionamentoServico();
	}

}