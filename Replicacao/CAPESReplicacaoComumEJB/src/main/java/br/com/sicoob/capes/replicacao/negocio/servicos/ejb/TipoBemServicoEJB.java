/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.negocio.entidades.legado.TipoBem;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.TipoBemServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.TipoBemServicoRemote;
import br.com.sicoob.capes.replicacao.persistencia.dao.TipoBemDAO;

/**
 * Serviço para os tipos de bens.
 * @author Erico.Junior
 */
@Stateless
@Local( { TipoBemServicoLocal.class })
@Remote( { TipoBemServicoRemote.class })
public class TipoBemServicoEJB extends EntidadeDominioReplicavelServicoEJB<TipoBem> implements
		TipoBemServicoRemote, TipoBemServicoLocal {

	@Inject
	@Default
	private transient TipoBemDAO tipoBemDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoBemDAO getDAO() {
		return tipoBemDAO;
	}	
	
}