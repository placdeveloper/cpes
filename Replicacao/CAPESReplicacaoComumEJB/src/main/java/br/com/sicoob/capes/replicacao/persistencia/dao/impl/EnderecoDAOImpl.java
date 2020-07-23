/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.legado.Endereco;
import br.com.sicoob.capes.replicacao.persistencia.dao.EnderecoDAO;
import br.com.sicoob.capes.replicacao.persistencia.dao.EntidadeReplicavelDao;


/**
 * Dao utilizada para endereços das pessoas.
 * 
 * @author Erico.Junior
 */
public class EnderecoDAOImpl extends EntidadeReplicavelDao<Endereco> implements EnderecoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_ENDERECO";
	
	/** A constante NOME_COMANDO_CONSULTAR_IDDB2. */
	private static final String NOME_COMANDO_CONSULTAR_IDDB2 = "CONSULTA_ENDERECO_POR_ID_DB2";

	/**
	 * Instancia um novo EnderecoDAOImpl.
	 */
	public EnderecoDAOImpl() {
		super(Endereco.class, NOME_COMANDO_PESQUISAR, NOME_COMANDO_CONSULTAR_IDDB2);
	}

}
