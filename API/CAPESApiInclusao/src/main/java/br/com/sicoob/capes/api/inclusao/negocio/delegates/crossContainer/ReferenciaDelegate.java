package br.com.sicoob.capes.api.inclusao.negocio.delegates.crossContainer;

import br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces.IReferenciaDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.dto.ReferenciaDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.ReferenciaServico;

/**
 * A Classe ReferenciaDelegate.
 * 
 * @author bruno.carneiro
 */
public class ReferenciaDelegate extends CAPESApiInclusaoDelegate<ReferenciaDTO, ReferenciaServico> implements IReferenciaDelegate {
	
	/**
	 * 
	 */
	protected ReferenciaDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static ReferenciaDelegate getInstance() {
		return valueOf(ReferenciaDelegate.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ReferenciaServico localizarServico() {
		return getLocator().localizarReferenciaServico();
	}

}