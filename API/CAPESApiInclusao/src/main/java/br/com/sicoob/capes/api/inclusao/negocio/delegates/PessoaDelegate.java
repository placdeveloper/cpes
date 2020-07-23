package br.com.sicoob.capes.api.inclusao.negocio.delegates;

import br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces.IPessoaDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.dto.PessoaDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.PessoaServico;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.locator.CAPESApiInclusaoServiceLocator;

/**
 * A Classe PessoaDelegate.
 * 
 * @author bruno.carneiro
 */
public class PessoaDelegate extends CAPESApiInclusaoDelegate<PessoaDTO, PessoaServico> implements IPessoaDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected PessoaServico localizarServico() {
		return CAPESApiInclusaoServiceLocator.getInstance().localizarPessoaServico();
	}

}