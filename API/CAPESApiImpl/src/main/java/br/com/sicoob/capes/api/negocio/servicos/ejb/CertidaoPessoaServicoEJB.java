/*
 * SICOOB
 * 
 * CertidaoPessoaServicoEJB.java(br.com.sicoob.capes.api.negocio.servicos.ejb.CertidaoPessoaServicoEJB)
 */
package br.com.sicoob.capes.api.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.api.negocio.servicos.interfaces.CertidaoPessoaServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.CertidaoPessoaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.CertidaoPessoaVO;
import br.com.sicoob.capes.api.persistencia.dao.CertidaoPessoaDAO;

/**
 * The Class CertidaoPessoaServicoEJB.
 */
@Stateless
@Local(CertidaoPessoaServicoLocal.class)
@Remote(CertidaoPessoaServicoRemote.class)
public class CertidaoPessoaServicoEJB extends AbstractCAPESApiServicoPessoaEJB<CertidaoPessoaVO> 
	implements CertidaoPessoaServicoRemote, CertidaoPessoaServicoLocal{
	
	@Inject
	@Default
	private CertidaoPessoaDAO certidaoPessoaDAO;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CertidaoPessoaDAO obterDAO() {
		return certidaoPessoaDAO;
	}
}