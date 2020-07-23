/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.negocio.entidades.legado.Nucleo;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.NucleoServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.NucleoServicoRemote;
import br.com.sicoob.capes.replicacao.persistencia.dao.NucleoDAO;

/**
 * Serviço utilizado na replicação de pessoas.
 * 
 * @author Erico.Junior
 */
@Stateless
@Local( { NucleoServicoLocal.class })
@Remote( { NucleoServicoRemote.class })
public class NucleoServicoEJB extends EntidadeReplicavelServicoEJB<Nucleo> implements NucleoServicoRemote, NucleoServicoLocal {

	@Inject
	@Default
	private NucleoDAO nucleoDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected NucleoDAO getDAO() {
		return nucleoDAO;
	}

}
