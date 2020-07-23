package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.negocio.entidades.legado.Cooperativa;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.CooperativaServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.CooperativaServicoRemote;
import br.com.sicoob.capes.replicacao.persistencia.dao.CAPESReplicacaoComumCrudDaoIF;
import br.com.sicoob.capes.replicacao.persistencia.dao.CooperativaDAO;

/**
 * EJB contendo servicos relacionados a Cooperativa.
 */
@Stateless
@Remote(CooperativaServicoRemote.class)
@Local(CooperativaServicoLocal.class)
public class CooperativaServicoEJB extends CAPESReplicacaoComumCrudServicoEJB<Cooperativa>
		implements CooperativaServicoRemote, CooperativaServicoLocal {

	@Inject
	@Default
	private CooperativaDAO cooperativaDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESReplicacaoComumCrudDaoIF<Cooperativa> getDAO() {
		return cooperativaDAO;
	}

}
