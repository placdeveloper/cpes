/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoAfastamentoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoAfastamentoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoAfastamentoDAO;
import br.com.sicoob.capes.negocio.entidades.TipoAfastamento;

/**
 * @author Erico.Junior
 * 
 */
@Stateless
@Local( { TipoAfastamentoServicoLocal.class })
@Remote( { TipoAfastamentoServicoRemote.class })
public class TipoAfastamentoServicoEJB extends
		CAPESCadastroCrudServicoEJB<TipoAfastamento> implements
		TipoAfastamentoServicoRemote, TipoAfastamentoServicoLocal {

	@Inject
	@Default
	private TipoAfastamentoDAO dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoAfastamentoDAO getDAO() {
		return dao;
	}
	
}
