/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.legado.ListaItem;
import br.com.sicoob.capes.replicacao.persistencia.dao.CAPESReplicacaoComumCrudDao;
import br.com.sicoob.capes.replicacao.persistencia.dao.ListaItemDAO;

/**
 * Dao utilizado para replicação dos itens das listas. 
 * @author Erico.Junior
 */
public class ListaItemDAOImpl extends CAPESReplicacaoComumCrudDao<ListaItem> implements
		ListaItemDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "";

	/**
	 * Instancia um novo ListaItemDAOImpl.
	 */
	public ListaItemDAOImpl() {
		super(ListaItem.class, NOME_COMANDO_PESQUISAR);
	}

}