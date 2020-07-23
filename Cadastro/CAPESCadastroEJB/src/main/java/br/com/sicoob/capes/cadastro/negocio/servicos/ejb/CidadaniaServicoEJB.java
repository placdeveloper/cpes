package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.CidadaniaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.CidadaniaServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.CidadaniaDAO;
import br.com.sicoob.capes.negocio.entidades.Cidadania;

/**
 * EJB contendo servicos relacionados a Cidadania.
 */
@Stateless
@Local( { CidadaniaServicoLocal.class })
@Remote( { CidadaniaServicoRemote.class })
public class CidadaniaServicoEJB extends
CAPESCadastroCrudServicoEJB<Cidadania> implements CidadaniaServicoRemote, CidadaniaServicoLocal {

	@Inject
	@Default
	private CidadaniaDAO cidadaniaDAO;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CidadaniaDAO getDAO() {
		return cidadaniaDAO;
	}
	
}
