package br.com.sicoob.capes.api.inclusao.negocio.delegates.crossContainer;

import br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces.IFonteRendaDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.dto.FonteRendaDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.FonteRendaServico;

/**
 * A Classe FonteRendaDelegate.
 * 
 * @author bruno.carneiro
 */
public class FonteRendaDelegate extends CAPESApiInclusaoDelegate<FonteRendaDTO, FonteRendaServico> implements IFonteRendaDelegate {
	
	/**
	 * 
	 */
	protected FonteRendaDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static FonteRendaDelegate getInstance() {
		return valueOf(FonteRendaDelegate.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FonteRendaServico localizarServico() {
		return getLocator().localizarFonteRendaServico();
	}

}