/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.legado.Funcionario;
import br.com.sicoob.capes.replicacao.persistencia.dao.EntidadeReplicavelDao;
import br.com.sicoob.capes.replicacao.persistencia.dao.FuncionarioDAO;

/**
 * Dao utilizada para os funcionarios.
 * 
 * @author juan.damasceno
 */
public class FuncionarioDAOImpl extends EntidadeReplicavelDao<Funcionario> implements
		FuncionarioDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISAR_FUNCIONARIO_POR_FILTRO";
	
	/** A constante NOME_COMANDO_CONSULTAR_IDDB2. */
	private static final String NOME_COMANDO_CONSULTAR_IDDB2 = null;

	/**
	 * Instancia um novo FuncionarioDAOImpl.
	 */
	public FuncionarioDAOImpl() {
		super(Funcionario.class, NOME_COMANDO_PESQUISAR, NOME_COMANDO_CONSULTAR_IDDB2);
	}
}