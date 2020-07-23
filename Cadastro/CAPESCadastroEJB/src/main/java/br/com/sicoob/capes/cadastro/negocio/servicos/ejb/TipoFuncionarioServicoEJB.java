/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoFuncionarioServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoFuncionarioServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoFuncionarioDAO;
import br.com.sicoob.capes.negocio.entidades.TipoFuncionario;

/**
 * @author Erico.Junior
 * 
 */
@Stateless
@Local( { TipoFuncionarioServicoLocal.class })
@Remote( { TipoFuncionarioServicoRemote.class })
public class TipoFuncionarioServicoEJB extends
		CAPESCadastroCrudServicoEJB<TipoFuncionario> implements
		TipoFuncionarioServicoRemote, TipoFuncionarioServicoLocal {

	@Inject
	@Default
	private TipoFuncionarioDAO dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoFuncionarioDAO getDAO() {
		return dao;
	}
	
}
