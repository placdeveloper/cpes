/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.negocio.entidades.legado.Mensagem;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.MensagemServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.MensagemServicoRemote;
import br.com.sicoob.capes.replicacao.persistencia.dao.EntidadeReplicavelDaoIF;
import br.com.sicoob.capes.replicacao.persistencia.dao.MensagemDAO;

/**
 * Serviço utilizado na replicação de mensagens.
 * @author juan.damasceno
 */
@Stateless
@Local( { MensagemServicoLocal.class })
@Remote( { MensagemServicoRemote.class })
public class MensagemServicoEJB extends EntidadeReplicavelServicoEJB<Mensagem> implements
	MensagemServicoRemote, MensagemServicoLocal {

	@Inject
	@Default
	private MensagemDAO mensagemDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EntidadeReplicavelDaoIF<Mensagem> getDAO() {
		return mensagemDAO;
	}

}
