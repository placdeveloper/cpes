/*
 * SICOOB
 * 
 * CertidaoPessoaDelegate.java(br.com.sicoob.capes.api.negocio.delegates.CertidaoPessoaDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates.crossContainer;

import br.com.sicoob.capes.api.negocio.delegates.interfaces.ICertidaoPessoaDelegate;
import br.com.sicoob.capes.api.negocio.servicos.CertidaoPessoaServico;
import br.com.sicoob.capes.api.negocio.vo.CertidaoPessoaVO;

/**
 * @author Lucas.Borges
 */
public class CertidaoPessoaDelegate extends	AbstractCAPESApiPessoaDelegate<CertidaoPessoaVO,CertidaoPessoaServico> implements ICertidaoPessoaDelegate {
	
	/**
	 * 
	 */
	protected CertidaoPessoaDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static CertidaoPessoaDelegate getInstance() {
		return valueOf(CertidaoPessoaDelegate.class);
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected CertidaoPessoaServico localizarServico() {
		return getLocator().localizarCertidaoPessoaServico();
	}
}