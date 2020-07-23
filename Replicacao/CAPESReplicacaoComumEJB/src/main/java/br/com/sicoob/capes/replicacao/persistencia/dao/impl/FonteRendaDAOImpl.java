/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.legado.FonteRenda;
import br.com.sicoob.capes.replicacao.persistencia.dao.EntidadeReplicavelDao;
import br.com.sicoob.capes.replicacao.persistencia.dao.FonteRendaDAO;

/**
 * Dao utilizada para as fontes de rendas das pessoas.
 * 
 * @author Erico.Junior
 */
public class FonteRendaDAOImpl extends EntidadeReplicavelDao<FonteRenda> implements
		FonteRendaDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_FONTE_RENDA";
	
	/** A constante NOME_COMANDO_CONSULTAR_IDDB2. */
	private static final String NOME_COMANDO_CONSULTAR_IDDB2 = "CONSULTA_FONTE_RENDA_POR_ID_DB2";
	
	/**
	 * Instancia um novo FonteRendaDAOImpl.
	 */
	public FonteRendaDAOImpl() {
		super(FonteRenda.class, NOME_COMANDO_PESQUISAR, NOME_COMANDO_CONSULTAR_IDDB2);
	}

}
