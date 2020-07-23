/*
 * SICOOB
 * 
 * ReferenciaPessoaDelegate.java(br.com.sicoob.capes.api.negocio.delegates.ReferenciaPessoaDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates;

import br.com.sicoob.capes.api.negocio.delegates.interfaces.IReferenciaPessoaDelegate;
import br.com.sicoob.capes.api.negocio.servicos.ReferenciaPessoaServico;
import br.com.sicoob.capes.api.negocio.servicos.locator.CAPESApiServiceLocator;
import br.com.sicoob.capes.api.negocio.vo.ReferenciaPessoaVO;


/**
 * @author Erico.Junior
 * 
 */
public class ReferenciaPessoaDelegate
		extends
		AbstractCAPESApiPessoaDelegate<ReferenciaPessoaVO, ReferenciaPessoaServico> implements IReferenciaPessoaDelegate {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected ReferenciaPessoaServico localizarServico() {
		return CAPESApiServiceLocator.getInstance()
				.localizarReferenciaPessoaServico();
	}
}
