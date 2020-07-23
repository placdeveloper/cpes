package br.com.sicoob.capes.api.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.TipoEmpresaServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.TipoEmpresaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.TipoEmpresaVO;
import br.com.sicoob.capes.api.persistencia.dao.TipoEmpresaDAO;

@Stateless
@Local({ TipoEmpresaServicoLocal.class })
@Remote({ TipoEmpresaServicoRemote.class })
public class TipoEmpresaServicoEJB extends CAPESApiServicoEJB implements
		TipoEmpresaServicoLocal, TipoEmpresaServicoRemote {

	@Inject
	@Default
	private TipoEmpresaDAO dao;
	
	public List<TipoEmpresaVO> listar() throws BancoobException {
		return obterDAO().listar();
	}

	@Override
	protected TipoEmpresaDAO obterDAO() {
		return dao;
	}

}
