/*
 * SICOOB
 * 
 * InformacaoProfissionalServicoEJB.java(br.com.sicoob.capes.api.negocio.servicos.ejb.InformacaoProfissionalServicoEJB)
 */
package br.com.sicoob.capes.api.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.api.negocio.servicos.interfaces.InformacaoProfissionalServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.InformacaoProfissionalServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.InformacaoProfissionalVO;
import br.com.sicoob.capes.api.persistencia.dao.InformacaoProfissionalDAO;

/**
 * The Class InformacaoProfissionalServicoEJB.
 */
@Stateless
@Local(InformacaoProfissionalServicoLocal.class)
@Remote(InformacaoProfissionalServicoRemote.class)
public class InformacaoProfissionalServicoEJB extends AbstractCAPESApiServicoPessoaEJB<InformacaoProfissionalVO> 
			implements InformacaoProfissionalServicoRemote, InformacaoProfissionalServicoLocal {

	@Inject
	@Default
	private InformacaoProfissionalDAO informacaoProfissionalDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected InformacaoProfissionalDAO obterDAO() {
		return informacaoProfissionalDAO;
	}

}