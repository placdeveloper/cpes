/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.legado.TipoBem;
import br.com.sicoob.capes.replicacao.persistencia.dao.CAPESReplicacaoComumCrudDao;
import br.com.sicoob.capes.replicacao.persistencia.dao.TipoBemDAO;

/**
 * DAO para os tipos de bens
 * 
 * @author erico.junior
 */
public class TipoBemDAOImpl extends CAPESReplicacaoComumCrudDao<TipoBem> implements TipoBemDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "";

	/**
	 * Instancia um novo TipoBemDAOImpl.
	 */
	public TipoBemDAOImpl() {
		super(TipoBem.class, NOME_COMANDO_PESQUISAR);
	}

}
