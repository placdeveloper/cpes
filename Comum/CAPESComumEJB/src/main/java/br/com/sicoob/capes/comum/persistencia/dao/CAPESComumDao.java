/*
 * SICOOB
 * 
 * CAPESComumDao.java(br.com.sicoob.capes.comum.persistencia.dao.CAPESComumDao)
 */
package br.com.sicoob.capes.comum.persistencia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.infraestrutura.conexao.CorporativoDataSource;
import br.com.bancoob.persistencia.dao.BancoobDao;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.comum.util.Constantes;

/**
 * Classe padrao com a infraestrutura de acesso a base de dados do sistema CAPESComum
 * 
 * @author Stefanini IT Solutions
 */
public abstract class CAPESComumDao extends BancoobDao {

	/**
	 * @param nomeComandoListar
	 * @param nomeComandoConsultar
	 */
	public CAPESComumDao(String nomeDatasource, String arquivoQueries, String nomeColecaoComandos) {

		super(nomeDatasource, arquivoQueries, nomeColecaoComandos);
	}
	
	/**
	 * Cria uma nova instância de CAPES comum dao.
	 */
	public CAPESComumDao() {
		this(Constantes.Persistencia.DATASOURCE_CAPES, "capes.comum.queries.xml",
		        "COMANDOS_CAPES_COMUM_EJB");
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected Connection estabelecerConexao() {
		try {
			getLogger().info("Datasource para conexão: " + getNomeDatasource());
			CorporativoDataSource dataSource = new CorporativoDataSource(getNomeDatasource(), System.getProperties());
			return dataSource.getConnection();
		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
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

	/**
	 * O método Fechar result set.
	 *
	 * @param rs o valor de rs
	 */
	protected void fecharResultSet(ResultSet rs) {
    	if (rs != null) {
    		try {
    			rs.close();
    		} catch (SQLException e) {
    			getLogger().erro(e, "Falha no fechamento do ResultSet");
    		}
    	}
    }
}