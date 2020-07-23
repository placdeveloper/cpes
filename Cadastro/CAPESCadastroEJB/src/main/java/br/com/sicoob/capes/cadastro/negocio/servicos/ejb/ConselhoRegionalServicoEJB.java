/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ConselhoRegionalServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ConselhoRegionalServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.ConselhoRegionalDAO;
import br.com.sicoob.capes.negocio.entidades.ConselhoRegional;

/**
 * @author Erico.Junior
 * 
 */
@Stateless
@Local( { ConselhoRegionalServicoLocal.class })
@Remote( { ConselhoRegionalServicoRemote.class })
public class ConselhoRegionalServicoEJB extends
		CAPESCadastroCrudServicoEJB<ConselhoRegional> implements
		ConselhoRegionalServicoRemote, ConselhoRegionalServicoLocal {

	@Inject
	@Default
	private ConselhoRegionalDAO dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ConselhoRegionalDAO getDAO() {
		return dao;
	}

}
