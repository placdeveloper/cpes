/*
 * SICOOB
 * 
 * ReferenciaPessoaDelegate.java(br.com.sicoob.capes.api.negocio.delegates.ReferenciaPessoaDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates.crossContainer;

import br.com.sicoob.capes.api.negocio.delegates.interfaces.IReferenciaPessoaDelegate;
import br.com.sicoob.capes.api.negocio.servicos.ReferenciaPessoaServico;
import br.com.sicoob.capes.api.negocio.vo.ReferenciaPessoaVO;


/**
 * @author Erico.Junior
 * 
 */
public class ReferenciaPessoaDelegate
		extends
		AbstractCAPESApiPessoaDelegate<ReferenciaPessoaVO, ReferenciaPessoaServico> implements IReferenciaPessoaDelegate {
	
	/**
	 * 
	 */
	protected ReferenciaPessoaDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static ReferenciaPessoaDelegate getInstance() {
		return valueOf(ReferenciaPessoaDelegate.class);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected ReferenciaPessoaServico localizarServico() {
		return getLocator()
				.localizarReferenciaPessoaServico();
	}
}
