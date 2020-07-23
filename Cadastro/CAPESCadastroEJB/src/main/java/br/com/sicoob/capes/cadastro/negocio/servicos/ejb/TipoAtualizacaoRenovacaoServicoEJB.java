package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoAtualizacaoRenovacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoAtualizacaoRenovacaoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.CAPESCadastroCrudDaoIF;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoAtualizacaoRenovacaoDAO;
import br.com.sicoob.capes.negocio.entidades.TipoAtualizacaoRenovacao;

/**
 * EJB contendo servicos relacionados ao dominio TipoAtualizacaoRenovacaoServicoEJB.
 */
@Stateless
@Local(TipoAtualizacaoRenovacaoServicoLocal.class)
@Remote(TipoAtualizacaoRenovacaoServicoRemote.class)
public class TipoAtualizacaoRenovacaoServicoEJB extends CAPESCadastroCrudServicoEJB<TipoAtualizacaoRenovacao> 
implements TipoAtualizacaoRenovacaoServicoLocal, TipoAtualizacaoRenovacaoServicoRemote {
	
	@Inject
	@Default
	protected TipoAtualizacaoRenovacaoDAO dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESCadastroCrudDaoIF<TipoAtualizacaoRenovacao> getDAO() {
		return dao;
	}

}
