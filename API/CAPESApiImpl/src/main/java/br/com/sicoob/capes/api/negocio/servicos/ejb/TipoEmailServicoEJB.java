package br.com.sicoob.capes.api.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.TipoEmailServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.TipoEmailServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.TipoEmailVO;
import br.com.sicoob.capes.api.persistencia.dao.TipoEmailDAO;

@Stateless
@Local({ TipoEmailServicoLocal.class })
@Remote({ TipoEmailServicoRemote.class })
public class TipoEmailServicoEJB extends CAPESApiServicoEJB implements
		TipoEmailServicoRemote, TipoEmailServicoLocal {

	@Inject
	@Default
	private TipoEmailDAO dao;

	@Override
	protected TipoEmailDAO obterDAO() {
		return dao;
	}

	public List<TipoEmailVO> listar() throws BancoobException {
		return obterDAO().listar();
	}
}
