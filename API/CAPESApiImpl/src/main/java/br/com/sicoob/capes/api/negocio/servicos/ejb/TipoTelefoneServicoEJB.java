package br.com.sicoob.capes.api.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.TipoTelefoneServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.TipoTelefoneServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.TipoTelefoneVO;
import br.com.sicoob.capes.api.persistencia.dao.TipoTelefoneDAO;

@Stateless
@Local({ TipoTelefoneServicoLocal.class })
@Remote({ TipoTelefoneServicoRemote.class })
public class TipoTelefoneServicoEJB extends CAPESApiServicoEJB implements
		TipoTelefoneServicoRemote, TipoTelefoneServicoLocal {

	@Inject
	@Default
	private TipoTelefoneDAO dao;

	@Override
	protected TipoTelefoneDAO obterDAO() {		
		return dao;
	}

	public List<TipoTelefoneVO> listar() throws BancoobException {
		return obterDAO().listar();
	}

}
