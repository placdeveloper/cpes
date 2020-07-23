/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.Lista;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.ListaServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.ListaServicoRemote;
import br.com.sicoob.capes.replicacao.persistencia.dao.ListaDAO;

/**
 * Serviço utilizado nas listas. 
 * 
 * @author Erico.Junior
 */
@Stateless
@Local( { ListaServicoLocal.class })
@Remote( { ListaServicoRemote.class })
public class ListaServicoEJB extends CAPESReplicacaoComumCrudServicoEJB<Lista> implements
		ListaServicoRemote, ListaServicoLocal {

	@Inject
	@Default
	private ListaDAO listaDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ListaDAO getDAO() {
		return listaDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	public Lista obterProdLab(Integer idLista, Integer idCooperativaProdlab) throws BancoobException {
		return getDAO().obter(idLista, idCooperativaProdlab);
	}

}
