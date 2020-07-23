/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb.bemantigo;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo.TipoPosseBemAntigoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo.TipoPosseBemAntigoServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.servicos.ejb.CAPESCadastroCrudServicoEJB;
import br.com.sicoob.capes.cadastro.persistencia.dao.bemantigo.TipoPosseBemAntigoDAO;
import br.com.sicoob.capes.negocio.entidades.bemantigo.TipoPosseBem;

/**
 * Servico para os tipos posse de bens
 * @author juan.damasceno
 */
@Stateless
@Local( { TipoPosseBemAntigoServicoLocal.class })
@Remote( { TipoPosseBemAntigoServicoRemote.class })
public class TipoPosseBemAntigoServicoEJB extends CAPESCadastroCrudServicoEJB<TipoPosseBem> implements
	TipoPosseBemAntigoServicoRemote, TipoPosseBemAntigoServicoLocal {

	@Inject
	@Default
	protected TipoPosseBemAntigoDAO tipoPosseBemDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoPosseBemAntigoDAO getDAO() {
		return tipoPosseBemDAO;
	}

}