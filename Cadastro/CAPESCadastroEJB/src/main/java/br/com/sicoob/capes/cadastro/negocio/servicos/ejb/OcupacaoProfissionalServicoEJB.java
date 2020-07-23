/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.OcupacaoProfissionalServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.OcupacaoProfissionalServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.OcupacaoProfissionalDAO;
import br.com.sicoob.capes.negocio.entidades.OcupacaoProfissional;

/**
 * EJB contendo servicos relacionados a OcupacaoProfissional.
 */
@Stateless
@Local( { OcupacaoProfissionalServicoLocal.class })
@Remote( { OcupacaoProfissionalServicoRemote.class })
public class OcupacaoProfissionalServicoEJB extends
		CAPESCadastroCrudServicoEJB<OcupacaoProfissional> implements
		OcupacaoProfissionalServicoRemote, OcupacaoProfissionalServicoLocal {

	@Inject
	@Default
	protected OcupacaoProfissionalDAO dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected OcupacaoProfissionalDAO getDAO() {
		return dao;
	}

}
