package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoRegraValidacaoCadastralServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoRegraValidacaoCadastralServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.CAPESCadastroCrudDaoIF;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoRegraValidacaoCadastralDAO;
import br.com.sicoob.capes.negocio.entidades.TipoRegraValidacaoCadastral;

/**
 * EJB contendo servicos relacionados a TipoRegraValidacaoCadastral.
 */
@Stateless
@Local(TipoRegraValidacaoCadastralServicoLocal.class)
@Remote(TipoRegraValidacaoCadastralServicoRemote.class)
public class TipoRegraValidacaoCadastralServicoEJB extends
		CAPESCadastroCrudServicoEJB<TipoRegraValidacaoCadastral> implements
		TipoRegraValidacaoCadastralServicoLocal, TipoRegraValidacaoCadastralServicoRemote {
	
	@Inject
	@Default
	private TipoRegraValidacaoCadastralDAO dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESCadastroCrudDaoIF<TipoRegraValidacaoCadastral> getDAO() {
		return dao;
	}

}
