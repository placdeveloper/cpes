/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.legado.Lista;
import br.com.sicoob.capes.replicacao.persistencia.dao.CAPESReplicacaoComumCrudDao;
import br.com.sicoob.capes.replicacao.persistencia.dao.ListaDAO;

/**
 * Dao utilizado para replicação das listas. 
 * @author Erico.Junior
 */
public class ListaDAOImpl extends CAPESReplicacaoComumCrudDao<Lista> implements ListaDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "";
	
	/**
	 * Instancia um novo ListaDAOImpl.
	 */
	public ListaDAOImpl() {
		super(Lista.class, NOME_COMANDO_PESQUISAR);
	}

}
