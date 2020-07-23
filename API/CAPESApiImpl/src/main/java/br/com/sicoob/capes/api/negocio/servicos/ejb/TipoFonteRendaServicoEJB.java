package br.com.sicoob.capes.api.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.TipoFonteRendaServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.TipoFonteRendaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.TipoFonteRendaVO;
import br.com.sicoob.capes.api.persistencia.dao.TipoFonteRendaDAO;

@Stateless
@Local({ TipoFonteRendaServicoLocal.class })
@Remote({ TipoFonteRendaServicoRemote.class })
public class TipoFonteRendaServicoEJB extends CAPESApiServicoEJB implements
		TipoFonteRendaServicoLocal, TipoFonteRendaServicoRemote {

	@Inject
	@Default
	private TipoFonteRendaDAO dao;

	public List<TipoFonteRendaVO> listar() throws BancoobException {
		return obterDAO().listar();
	}
	
	public List<TipoFonteRendaVO> listarPorTipoPessoa(Short codigoTipoPessoa) throws BancoobException {
		return obterDAO().listarPorTipoPessoa(codigoTipoPessoa);
	}
	
	public TipoFonteRendaVO obterTipoFonteRenda(Short codigo) throws BancoobException {
		return obterDAO().obterTipoFonteRenda(codigo);
	}

	@Override
	protected TipoFonteRendaDAO obterDAO() {
		return dao;
	}

}
