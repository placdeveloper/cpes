/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoEconomicoNovoLimpoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoEconomicoNovoLimpoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.GrupoEconomicoNovoLimpoDAO;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoNovoLimpo;

/**
 * 
 * @author Paulo.Stoppa
 *
 */
@Stateless
@Local({ GrupoEconomicoNovoLimpoServicoLocal.class })
@Remote({ GrupoEconomicoNovoLimpoServicoRemote.class })
public class GrupoEconomicoNovoLimpoServicoEJB extends CAPESCadastroCrudServicoEJB<GrupoEconomicoNovoLimpo>
		implements GrupoEconomicoNovoLimpoServicoLocal, GrupoEconomicoNovoLimpoServicoRemote {

	@Inject
	@Default
	private GrupoEconomicoNovoLimpoDAO grupoEconomicoNovoDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected GrupoEconomicoNovoLimpoDAO getDAO() {
		return grupoEconomicoNovoDAO;
	}

}
