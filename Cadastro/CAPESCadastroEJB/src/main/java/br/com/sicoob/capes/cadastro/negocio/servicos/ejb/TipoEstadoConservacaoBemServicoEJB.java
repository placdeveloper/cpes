package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoEstadoConservacaoBemServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoEstadoConservacaoBemServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoEstadoConservacaoBemDAO;
import br.com.sicoob.capes.negocio.entidades.bem.TipoEstadoConservacaoBem;

/**
 * Classe com os serviços de tipo estado conservação
 *
 * @author Bruno.Carneiro
 */
@Stateless
@Local(TipoEstadoConservacaoBemServicoLocal.class)
@Remote(TipoEstadoConservacaoBemServicoRemote.class)
public class TipoEstadoConservacaoBemServicoEJB extends CAPESCadastroCrudServicoEJB<TipoEstadoConservacaoBem> implements TipoEstadoConservacaoBemServicoLocal, TipoEstadoConservacaoBemServicoRemote {

	@Inject
	@Default
	private TipoEstadoConservacaoBemDAO dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoEstadoConservacaoBemDAO getDAO() {
		return dao;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TipoEstadoConservacaoBem> listar(boolean bemImovel) throws BancoobException {
		return getDAO().listar(bemImovel);
	}
}