package br.com.sicoob.capes.api.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.GrupoCompartilhamentoServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.GrupoCompartilhamentoServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.GrupoCompartilhamentoVO;
import br.com.sicoob.capes.api.persistencia.dao.CAPESApiDao;
import br.com.sicoob.capes.api.persistencia.dao.GrupoCompartilhamentoDAO;

@Stateless
@Local(GrupoCompartilhamentoServicoLocal.class)
@Remote(GrupoCompartilhamentoServicoRemote.class)
public class GrupoCompartilhamentoServicoEJB extends CAPESApiServicoEJB implements GrupoCompartilhamentoServicoLocal, GrupoCompartilhamentoServicoRemote {

	@Inject
	@Default
	private GrupoCompartilhamentoDAO grupoCompartilhamentoDAO;

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public GrupoCompartilhamentoVO obterGrupoCompartilhamentoInstituicao(Integer idInstituicao) throws BancoobException {
		validarObrigatoriedadeInstituicao(idInstituicao);
		return grupoCompartilhamentoDAO.obterGrupoCompartilhamentoInstituicao(idInstituicao);
	}

	@Override
	protected CAPESApiDao obterDAO() {
		return grupoCompartilhamentoDAO;
	}

}