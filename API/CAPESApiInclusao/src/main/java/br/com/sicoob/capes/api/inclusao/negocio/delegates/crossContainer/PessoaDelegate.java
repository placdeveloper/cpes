package br.com.sicoob.capes.api.inclusao.negocio.delegates.crossContainer;

import br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces.IPessoaDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.dto.PessoaDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.PessoaServico;

/**
 * A Classe PessoaDelegate.
 * 
 * @author bruno.carneiro
 */
public class PessoaDelegate extends CAPESApiInclusaoDelegate<PessoaDTO, PessoaServico> implements IPessoaDelegate {
	
	/**
	 * 
	 */
	protected PessoaDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static PessoaDelegate getInstance() {
		return valueOf(PessoaDelegate.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected PessoaServico localizarServico() {
		return getLocator().localizarPessoaServico();
	}

}