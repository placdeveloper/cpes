package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoInformacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoInformacaoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoInformacaoDAO;
import br.com.sicoob.capes.negocio.entidades.TipoInformacao;

/**
 * EJB contendo servicos relacionados a TipoInformacao.
 */
@Stateless
@Local({ TipoInformacaoServicoLocal.class })
@Remote({ TipoInformacaoServicoRemote.class })
public class TipoInformacaoServicoEJB extends CAPESCadastroCrudServicoEJB<TipoInformacao> implements TipoInformacaoServicoRemote, TipoInformacaoServicoLocal {

	@Inject
	@Default
	protected TipoInformacaoDAO tipoInformacaoDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoInformacaoDAO getDAO() {
		return tipoInformacaoDAO;
	}

}