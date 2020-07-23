package br.com.sicoob.capes.api.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.TipoEnderecoServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.TipoEnderecoServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.TipoEnderecoVO;
import br.com.sicoob.capes.api.persistencia.dao.TipoEnderecoDAO;

@Stateless
@Local({ TipoEnderecoServicoLocal.class })
@Remote({ TipoEnderecoServicoRemote.class })
public class TipoEnderecoServicoEJB extends CAPESApiServicoEJB implements
		TipoEnderecoServicoLocal, TipoEnderecoServicoRemote {

	@Inject
	@Default
	private TipoEnderecoDAO dao;

	public List<TipoEnderecoVO> listar() throws BancoobException {
		return obterDAO().listar();
	}

	@Override
	protected TipoEnderecoDAO obterDAO() {
		return dao;
	}

}
