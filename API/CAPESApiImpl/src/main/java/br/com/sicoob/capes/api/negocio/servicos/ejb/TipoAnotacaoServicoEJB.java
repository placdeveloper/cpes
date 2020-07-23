package br.com.sicoob.capes.api.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.TipoAnotacaoServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.TipoAnotacaoServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.TipoAnotacaoVO;
import br.com.sicoob.capes.api.persistencia.dao.CAPESApiDao;
import br.com.sicoob.capes.api.persistencia.dao.TipoAnotacaoDAO;

/**
 * EJB contendo servicos relacionados a TipoAnotacao.
 */
@Stateless
@Local(TipoAnotacaoServicoLocal.class)
@Remote(TipoAnotacaoServicoRemote.class)
public class TipoAnotacaoServicoEJB extends CAPESApiServicoEJB implements TipoAnotacaoServicoLocal, TipoAnotacaoServicoRemote {

	@Inject
	@Default
	private TipoAnotacaoDAO tipoAnotacaoDAO;

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<TipoAnotacaoVO> obterTiposAnotacaoAtivos() throws BancoobException {
		return tipoAnotacaoDAO.obterTiposAnotacaoAtivos();
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public TipoAnotacaoVO obterTipoAnotacaoPorId(Integer idTipoAnotacao) throws BancoobException {
		return tipoAnotacaoDAO.obterTipoAnotacaoPorId(idTipoAnotacao);
	}

	@Override
	protected CAPESApiDao obterDAO() {
		return tipoAnotacaoDAO;
	}

}