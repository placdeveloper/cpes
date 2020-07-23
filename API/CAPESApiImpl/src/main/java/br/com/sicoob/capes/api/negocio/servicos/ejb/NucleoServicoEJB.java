package br.com.sicoob.capes.api.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.NucleoServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.NucleoServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.NucleoVO;
import br.com.sicoob.capes.api.persistencia.dao.CAPESApiDao;
import br.com.sicoob.capes.api.persistencia.dao.NucleoDAO;

/**
 * EJB contendo servicos relacionados a Nucleo.
 */
@Stateless
@Local(NucleoServicoLocal.class)
@Remote(NucleoServicoRemote.class)
public class NucleoServicoEJB extends CAPESApiServicoEJB implements NucleoServicoLocal, NucleoServicoRemote {

	@Inject
	@Default
	private NucleoDAO nucleoDAO;

	/**
	 * {@inheritDoc}
	 */
	public List<NucleoVO> listarNucleos(Integer idInstituicao) throws BancoobException {
		return nucleoDAO.listarNucleos(idInstituicao);
	}

	@Override
	protected CAPESApiDao obterDAO() {
		return nucleoDAO;
	}

}