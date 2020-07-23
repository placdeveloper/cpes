package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ObservacaoAnotacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ObservacaoAnotacaoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.ObservacaoAnotacaoDAO;
import br.com.sicoob.capes.negocio.entidades.ObservacaoAnotacao;

/**
 * EJB contendo servicos relacionados a ObservacaoAnotacao.
 */
@Stateless
@Local({ ObservacaoAnotacaoServicoLocal.class })
@Remote({ ObservacaoAnotacaoServicoRemote.class })
public class ObservacaoAnotacaoServicoEJB extends CAPESCadastroCrudServicoEJB<ObservacaoAnotacao> implements ObservacaoAnotacaoServicoRemote, ObservacaoAnotacaoServicoLocal {

	@Inject
	@Default
	private ObservacaoAnotacaoDAO observacaoAnotacaoDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ObservacaoAnotacaoDAO getDAO() {
		return observacaoAnotacaoDAO;
	}

}
