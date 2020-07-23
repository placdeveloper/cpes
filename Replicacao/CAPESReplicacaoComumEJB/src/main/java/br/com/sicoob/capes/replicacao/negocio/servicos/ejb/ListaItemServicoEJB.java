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
import br.com.sicoob.capes.negocio.entidades.legado.ListaItem;
import br.com.sicoob.capes.negocio.entidades.legado.pk.ListaItemPK;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.ListaItemServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.ListaItemServicoRemote;
import br.com.sicoob.capes.replicacao.persistencia.dao.ListaItemDAO;

/**
 * Serviço utilizado para os items das listas.
 * 
 * @author Erico.Junior
 */
@Stateless
@Local( { ListaItemServicoLocal.class })
@Remote( { ListaItemServicoRemote.class })
public class ListaItemServicoEJB extends EntidadeDominioReplicavelServicoEJB<ListaItem> implements
		ListaItemServicoRemote, ListaItemServicoLocal {

	@Inject
	@Default
	private ListaItemDAO listaItemDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ListaItemDAO getDAO() {
		return listaItemDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	public ListaItem obterProdLab(ListaItemPK listaItemPK, Integer idCooperativaProdlab) throws BancoobException {
		return getDAO().obter(listaItemPK, idCooperativaProdlab);
	}

	/**
	 * {@inheritDoc}
	 */
	public void incluirProdLab(ListaItem listaItemReplicacao, Integer idCooperativaProdlab) throws BancoobException {
		getDAO().incluir(listaItemReplicacao, idCooperativaProdlab);
	}

	/**
	 * {@inheritDoc}
	 */
	public void alterarProdLab(ListaItem listaItemReplicacao, Integer idCooperativaProdlab) throws BancoobException {
		getDAO().alterar(listaItemReplicacao, idCooperativaProdlab);
	}	
}
