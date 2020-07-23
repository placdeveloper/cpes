/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.legado.Produtor;
import br.com.sicoob.capes.replicacao.persistencia.dao.EntidadeReplicavelDao;
import br.com.sicoob.capes.replicacao.persistencia.dao.ProdutorDAO;

/**
 * DAO Utilizado para Produtor.
 * 
 * @author Erico.Junior
 */
public class ProdutorDAOImpl extends EntidadeReplicavelDao<Produtor> implements ProdutorDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_PRODUTOR";
	
	/** A constante NOME_COMANDO_CONSULTAR_IDDB2. */
	private static final String NOME_COMANDO_CONSULTAR_IDDB2 = "CONSULTA_PRODUTOR_POR_ID_DB2";

	/**
	 * Instancia um novo ProdutorDAOImpl.
	 */
	public ProdutorDAOImpl() {
		super(Produtor.class, NOME_COMANDO_PESQUISAR, NOME_COMANDO_CONSULTAR_IDDB2);
	}
	
}
