package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoUsoBemServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoUsoBemServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoUsoBemDAO;
import br.com.sicoob.capes.negocio.entidades.bem.TipoUsoBemImovel;

@Stateless
@Local(TipoUsoBemServicoLocal.class)
@Remote(TipoUsoBemServicoRemote.class)
public class TipoUsoBemServicoEJB extends CAPESCadastroCrudServicoEJB<TipoUsoBemImovel> implements TipoUsoBemServicoLocal, TipoUsoBemServicoRemote {

	@Inject
	@Default
	private TipoUsoBemDAO dao;

	@Override
	protected TipoUsoBemDAO getDAO() {
		return dao;
	}
}
