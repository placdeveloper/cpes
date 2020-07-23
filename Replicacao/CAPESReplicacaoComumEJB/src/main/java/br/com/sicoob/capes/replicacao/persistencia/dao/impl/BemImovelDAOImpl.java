/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.legado.BemImovel;
import br.com.sicoob.capes.replicacao.persistencia.dao.BemImovelDAO;
import br.com.sicoob.capes.replicacao.persistencia.dao.EntidadeReplicavelDao;

/**
 * DAO para os Bens.
 * 
 * @author juan.damasceno
 */
public class BemImovelDAOImpl extends EntidadeReplicavelDao<BemImovel> implements BemImovelDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_BEM_PESSOA";
	
	/** A constante NOME_COMANDO_CONSULTAR_IDDB2. */
	private static final String NOME_COMANDO_CONSULTAR_IDDB2 = "CONSULTA_BEM_POR_ID_DB2";

	/**
	 * Instancia um novo BemImovelDAOImpl.
	 */
	public BemImovelDAOImpl() {
		super(BemImovel.class, NOME_COMANDO_PESQUISAR, NOME_COMANDO_CONSULTAR_IDDB2);
	}

}
