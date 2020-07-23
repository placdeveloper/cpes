/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoReferenciaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoReferenciaServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.CAPESCadastroCrudDaoIF;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoReferenciaDAO;
import br.com.sicoob.capes.negocio.entidades.TipoReferencia;

/**
 * Implementa as operações do serviço de TipoReferencia.
 * 
 * @author juan.damasceno
 */
@Stateless
@Local( { TipoReferenciaServicoLocal.class })
@Remote( { TipoReferenciaServicoRemote.class })
public class TipoReferenciaServicoEJB extends CAPESCadastroCrudServicoEJB<TipoReferencia>
		implements TipoReferenciaServicoRemote, TipoReferenciaServicoLocal {

	@Inject
	@Default
	private TipoReferenciaDAO tipoReferenciaDao;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESCadastroCrudDaoIF<TipoReferencia> getDAO() {
		return tipoReferenciaDao;
	}

}
