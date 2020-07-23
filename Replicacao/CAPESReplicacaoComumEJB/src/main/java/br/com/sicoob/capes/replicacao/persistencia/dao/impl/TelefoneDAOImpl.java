/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.legado.Telefone;
import br.com.sicoob.capes.replicacao.persistencia.dao.EntidadeReplicavelDao;
import br.com.sicoob.capes.replicacao.persistencia.dao.TelefoneDAO;

/**
 * Dao utilizada para os telefones.
 * 
 * @author erico.junior
 */
public class TelefoneDAOImpl extends EntidadeReplicavelDao<Telefone> implements TelefoneDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_TELEFONE";
	
	/** A constante NOME_COMANDO_CONSULTAR_IDDB2. */
	private static final String NOME_COMANDO_CONSULTAR_IDDB2 = "CONSULTA_TELEFONE_POR_ID_DB2";

	/**
	 * Instancia um novo TelefoneDAOImpl.
	 */
	public TelefoneDAOImpl() {
		super(Telefone.class, NOME_COMANDO_PESQUISAR, NOME_COMANDO_CONSULTAR_IDDB2);
	}

}
