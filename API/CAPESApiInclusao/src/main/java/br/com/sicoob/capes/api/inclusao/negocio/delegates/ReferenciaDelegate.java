package br.com.sicoob.capes.api.inclusao.negocio.delegates;

import br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces.IReferenciaDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.dto.ReferenciaDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.ReferenciaServico;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.locator.CAPESApiInclusaoServiceLocator;

/**
 * A Classe ReferenciaDelegate.
 * 
 * @author bruno.carneiro
 */
public class ReferenciaDelegate extends CAPESApiInclusaoDelegate<ReferenciaDTO, ReferenciaServico> implements IReferenciaDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ReferenciaServico localizarServico() {
		return CAPESApiInclusaoServiceLocator.getInstance().localizarReferenciaServico();
	}

}