/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.negocio.entidades.legado.Referencia;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.ReferenciaServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.ReferenciaServicoRemote;
import br.com.sicoob.capes.replicacao.persistencia.dao.EntidadeReplicavelDaoIF;
import br.com.sicoob.capes.replicacao.persistencia.dao.ReferenciaDAO;

/**
 * Serviço utilizado na replicação de referências.
 * @author Erico.Junior
 */
@Stateless
@Local( { ReferenciaServicoLocal.class })
@Remote( { ReferenciaServicoRemote.class })
public class ReferenciaServicoEJB extends EntidadeReplicavelServicoEJB<Referencia> implements
		ReferenciaServicoRemote, ReferenciaServicoLocal {

	@Inject
	@Default
	private ReferenciaDAO referenciaDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EntidadeReplicavelDaoIF<Referencia> getDAO() {
		return referenciaDAO;
	}

}
