/*
 * SICOOB
 * 
 * InformacaoProfissionalDelegate.java(br.com.sicoob.capes.api.negocio.delegates.InformacaoProfissionalDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates.crossContainer;

import br.com.sicoob.capes.api.negocio.delegates.interfaces.IInformacaoProfissionalDelegate;
import br.com.sicoob.capes.api.negocio.servicos.InformacaoProfissionalServico;
import br.com.sicoob.capes.api.negocio.vo.InformacaoProfissionalVO;

/**
 * @author erico.junior
 * 
 */
public class InformacaoProfissionalDelegate extends AbstractCAPESApiPessoaDelegate<InformacaoProfissionalVO, InformacaoProfissionalServico> implements IInformacaoProfissionalDelegate {
	
	/**
	 * 
	 */
	protected InformacaoProfissionalDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static InformacaoProfissionalDelegate getInstance() {
		return valueOf(InformacaoProfissionalDelegate.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected InformacaoProfissionalServico localizarServico() {
		return getLocator().localizarInformacaoProfissionalServico();
	}
}
