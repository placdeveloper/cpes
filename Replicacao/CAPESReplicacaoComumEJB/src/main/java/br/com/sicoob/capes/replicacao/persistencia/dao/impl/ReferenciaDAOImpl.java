/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.legado.Referencia;
import br.com.sicoob.capes.replicacao.persistencia.dao.EntidadeReplicavelDao;
import br.com.sicoob.capes.replicacao.persistencia.dao.ReferenciaDAO;

/**
 * Dao utilizada para as referências das pessoas.
 * 
 * @author Erico.Junior
 */
public class ReferenciaDAOImpl extends EntidadeReplicavelDao<Referencia> implements
		ReferenciaDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_REFERENCIA_PESSOA";
	
	/** A constante NOME_COMANDO_CONSULTAR_IDDB2. */
	private static final String NOME_COMANDO_CONSULTAR_IDDB2 = "CONSULTA_REFERENCIA_POR_ID_DB2";

	/**
	 * Instancia um novo ReferenciaDAOImpl.
	 */
	public ReferenciaDAOImpl() {
		super(Referencia.class, NOME_COMANDO_PESQUISAR, NOME_COMANDO_CONSULTAR_IDDB2);
	}


}
