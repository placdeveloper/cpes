package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoOnusBemServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoOnusBemServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoOnusBemDAO;
import br.com.sicoob.capes.negocio.entidades.bem.TipoOnusBem;

@Stateless
@Local(TipoOnusBemServicoLocal.class)
@Remote(TipoOnusBemServicoRemote.class)
public class TipoOnusBemServicoEJB extends CAPESCadastroCrudServicoEJB<TipoOnusBem> implements TipoOnusBemServicoLocal, TipoOnusBemServicoRemote {

	@Inject
	@Default
	private TipoOnusBemDAO dao;

	@Override
	protected TipoOnusBemDAO getDAO() {
		return dao;
	}

}