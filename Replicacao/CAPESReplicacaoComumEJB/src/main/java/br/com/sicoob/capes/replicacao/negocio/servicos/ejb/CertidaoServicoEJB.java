/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.negocio.entidades.legado.Certidao;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.CertidaoServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.CertidaoServicoRemote;
import br.com.sicoob.capes.replicacao.persistencia.dao.CertidaoDAO;

/**
 * Serviço utilizado na replicação de referências.
 * @author juan.damasceno
 */
@Stateless
@Local( { CertidaoServicoLocal.class })
@Remote( { CertidaoServicoRemote.class })
public class CertidaoServicoEJB extends EntidadeReplicavelServicoEJB<Certidao> implements
	CertidaoServicoRemote, CertidaoServicoLocal {

	@Inject
	@Default
	private transient CertidaoDAO certidaoDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CertidaoDAO getDAO() {
		return certidaoDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	public Short obterMaxSequencialPorPessoa(Pessoa pessoa) {
		return getDAO().obterMaxSequencialPorPessoa(pessoa);
	}

}
