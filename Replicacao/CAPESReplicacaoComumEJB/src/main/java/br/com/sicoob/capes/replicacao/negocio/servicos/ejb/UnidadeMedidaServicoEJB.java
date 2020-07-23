/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.negocio.entidades.legado.UnidadeMedida;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.UnidadeMedidaServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.UnidadeMedidaServicoRemote;
import br.com.sicoob.capes.replicacao.persistencia.dao.UnidadeMedidaDAO;

/**
 * Serviço para as unidades de medida.
 * @author Erico.Junior
 */
@Stateless
@Local( { UnidadeMedidaServicoLocal.class })
@Remote( { UnidadeMedidaServicoRemote.class })
public class UnidadeMedidaServicoEJB extends EntidadeDominioReplicavelServicoEJB<UnidadeMedida> implements
		UnidadeMedidaServicoRemote, UnidadeMedidaServicoLocal {

	@Inject
	@Default
	private transient UnidadeMedidaDAO unidadeMedidaDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected UnidadeMedidaDAO getDAO() {
		return unidadeMedidaDAO;
	}
	
}
