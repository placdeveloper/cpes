package br.com.sicoob.capes.integracao.persistencia.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.dao.BancoobDao;
import br.com.sicoob.capes.comum.util.Constantes;

public abstract class CAPESIntegracaoDao extends BancoobDao {

	private static String ARQUIVO_QUERIES = "capes.cadastro.queries.xml";
	private static String NOME_COLECAO_COMANDOS = "COMANDOS_CAPES_CADASTRO";
	
	/**
	 * Construtor da classe CAPESIntegracaoDao
	 * 
	 */
	public CAPESIntegracaoDao() {
		super(Constantes.Persistencia.DATASOURCE_CAPES, ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS);
	}

	/**
	 * O método Fechar result set.
	 *
	 * @param rset o valor de rset
	 */
	protected void fecharResultSet(ResultSet rset) {
    	
    	if (rset != null) {
    		try {
                rset.close();
    		} catch (SQLException e) {
    			getLogger().erro(e, "Falha no fechamento do ResultSet");
            }
    	}
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@PersistenceContext(unitName = "emCapes")
	public void setEntityManager(EntityManager manager) {
		super.setEntityManager(manager);
	}
}