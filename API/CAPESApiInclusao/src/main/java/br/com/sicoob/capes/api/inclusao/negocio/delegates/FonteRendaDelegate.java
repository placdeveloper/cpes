package br.com.sicoob.capes.api.inclusao.negocio.delegates;

import br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces.IFonteRendaDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.dto.FonteRendaDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.FonteRendaServico;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.locator.CAPESApiInclusaoServiceLocator;

/**
 * A Classe FonteRendaDelegate.
 * 
 * @author bruno.carneiro
 */
public class FonteRendaDelegate extends CAPESApiInclusaoDelegate<FonteRendaDTO, FonteRendaServico> implements IFonteRendaDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FonteRendaServico localizarServico() {
		return CAPESApiInclusaoServiceLocator.getInstance().localizarFonteRendaServico();
	}

}