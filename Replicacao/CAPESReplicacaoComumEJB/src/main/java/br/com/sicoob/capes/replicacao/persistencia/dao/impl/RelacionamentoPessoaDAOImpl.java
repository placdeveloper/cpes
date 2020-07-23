/* 
 * Sicoob
 * RelacionamentoPessoaDAOImpl.java 
 * Criado em: 28/10/2011
 */
package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.legado.RelacionamentoPessoa;
import br.com.sicoob.capes.replicacao.persistencia.dao.EntidadeReplicavelDao;
import br.com.sicoob.capes.replicacao.persistencia.dao.RelacionamentoPessoaDAO;

/**
 * DAO utilizada para os relacionamentos entre pessoas.
 *
 * 28/10/2011
 * @author Rodrigo.Chaves
 */
public class RelacionamentoPessoaDAOImpl extends EntidadeReplicavelDao<RelacionamentoPessoa>
		implements RelacionamentoPessoaDAO {

	/** A constante NOME_COMANDO_CONSULTAR_ID_DB2. */
	private static final String NOME_COMANDO_CONSULTAR_ID_DB2 = "CONSULTA_RELACIONAMENTO_POR_ID_DB2";
	
	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_RELACIONAMENTO";

	/**
	 * Instancia um novo RelacionamentoPessoaDAOImpl.
	 */
	public RelacionamentoPessoaDAOImpl() {
		super(RelacionamentoPessoa.class, NOME_COMANDO_PESQUISAR, NOME_COMANDO_CONSULTAR_ID_DB2);
	}

}
