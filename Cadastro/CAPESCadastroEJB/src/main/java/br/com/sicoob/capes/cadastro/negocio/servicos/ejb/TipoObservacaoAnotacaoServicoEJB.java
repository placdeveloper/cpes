package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoObservacaoAnotacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoObservacaoAnotacaoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoObservacaoAnotacaoDAO;
import br.com.sicoob.capes.negocio.entidades.TipoObservacaoAnotacao;

/**
 * EJB contendo servicos relacionados a TipoObservacaoAnotacao.
 */
@Stateless
@Local(TipoObservacaoAnotacaoServicoLocal.class)
@Remote(TipoObservacaoAnotacaoServicoRemote.class)
public class TipoObservacaoAnotacaoServicoEJB extends CAPESCadastroCrudDominioServicoEJB<TipoObservacaoAnotacao> implements TipoObservacaoAnotacaoServicoRemote,
		TipoObservacaoAnotacaoServicoLocal {
	
	@Inject
	@Default
	protected TipoObservacaoAnotacaoDAO tipoObservacaoAnotacaoDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoObservacaoAnotacaoDAO getDAO() {
		return this.tipoObservacaoAnotacaoDAO;
	}
	
}
