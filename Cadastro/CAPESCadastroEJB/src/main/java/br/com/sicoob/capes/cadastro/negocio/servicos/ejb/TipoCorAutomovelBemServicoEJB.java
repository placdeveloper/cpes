package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoCorAutomovelBemServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoCorAutomovelBemServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoCorAutomovelBemDAO;
import br.com.sicoob.capes.negocio.entidades.bem.TipoCorAutomovelBem;

@Stateless
@Local(TipoCorAutomovelBemServicoLocal.class)
@Remote(TipoCorAutomovelBemServicoRemote.class)
public class TipoCorAutomovelBemServicoEJB extends CAPESCadastroCrudServicoEJB<TipoCorAutomovelBem> implements TipoCorAutomovelBemServicoLocal, TipoCorAutomovelBemServicoRemote {

	@Inject
	@Default
	private TipoCorAutomovelBemDAO dao;

	@Override
	protected TipoCorAutomovelBemDAO getDAO() {
		return dao;
	}

}