/*
 * SICOOB
 * 
 * ReferenciaPessoaServicoEJB.java(br.com.sicoob.capes.api.negocio.servicos.ejb.ReferenciaPessoaServicoEJB)
 */
package br.com.sicoob.capes.api.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.api.negocio.servicos.interfaces.ReferenciaPessoaServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.ReferenciaPessoaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.ReferenciaPessoaVO;
import br.com.sicoob.capes.api.persistencia.dao.ReferenciaPessoaDAO;

/**
 * @author Erico.Junior
 * 
 */
@Stateless
@Local({ ReferenciaPessoaServicoLocal.class })
@Remote({ ReferenciaPessoaServicoRemote.class })
public class ReferenciaPessoaServicoEJB extends AbstractCAPESApiServicoPessoaEJB<ReferenciaPessoaVO> implements ReferenciaPessoaServicoRemote, ReferenciaPessoaServicoLocal {

	@Inject
	@Default
	private ReferenciaPessoaDAO referenciaPessoaDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ReferenciaPessoaDAO obterDAO() {
		return referenciaPessoaDAO;
	}

}