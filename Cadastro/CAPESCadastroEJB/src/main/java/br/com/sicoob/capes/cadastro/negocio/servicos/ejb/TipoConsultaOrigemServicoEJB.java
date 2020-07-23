package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoConsultaOrigemServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoConsultaOrigemServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.CAPESCadastroCrudDaoIF;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoConsultaOrigemDAO;
import br.com.sicoob.capes.negocio.entidades.TipoConsultaOrigem;

/**
 * EJB contendo servicos relacionados a TipoConsultaOrigem.
 */
@Stateless
@Local(TipoConsultaOrigemServicoLocal.class)
@Remote(TipoConsultaOrigemServicoRemote.class)
public class TipoConsultaOrigemServicoEJB extends CAPESCadastroCrudServicoEJB<TipoConsultaOrigem> 
		implements TipoConsultaOrigemServicoLocal, TipoConsultaOrigemServicoRemote {

	@Inject
	@Default
	protected TipoConsultaOrigemDAO tipoConsultaOrigemDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESCadastroCrudDaoIF<TipoConsultaOrigem> getDAO() {
		return tipoConsultaOrigemDao;
	}

}