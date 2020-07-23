/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.legado.Produtividade;
import br.com.sicoob.capes.replicacao.persistencia.dao.EntidadeReplicavelDao;
import br.com.sicoob.capes.replicacao.persistencia.dao.ProdutividadeDAO;

/**
 * @author Erico.Junior
 * 
 */
public class ProdutividadeDAOImpl extends EntidadeReplicavelDao<Produtividade>
		implements ProdutividadeDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_PRODUTIVIDADE";
	
	/** A constante NOME_COMANDO_CONSULTAR_IDDB2. */
	private static final String NOME_COMANDO_CONSULTAR_IDDB2 = "CONSULTA_PRODUTIVIDADE_POR_ID_DB2";

	/**
	 * Instancia um novo ProdutividadeDAOImpl.
	 */
	public ProdutividadeDAOImpl() {
		super(Produtividade.class, NOME_COMANDO_PESQUISAR,
				NOME_COMANDO_CONSULTAR_IDDB2);
	}

}
