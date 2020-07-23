/**
 * 
 */
package br.com.sicoob.capes.processamento.persistencia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.infraestrutura.conexao.BancoobDataSource;
import br.com.bancoob.persistencia.dao.BancoobDao;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.processamento.util.Constantes;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * @author Erico.Junior
 *
 */
public abstract class CAPESProcessamentoDao extends BancoobDao implements CAPESProcessamentoDaoIF {

	/**
	 * @param nomeComandoListar
	 * @param nomeComandoConsultar
	 */
	public CAPESProcessamentoDao() {
		this(Constantes.Persistencia.DATASOURCE, 
				Constantes.Persistencia.ARQUIVO_QUERIES,
				Constantes.Persistencia.NOME_COLECAO_COMANDOS);
	}
	
	/**
	 * Construtor
	 * 
	 * @param datasource
	 *            o nome do datasource a ser utilizado
	 * @param arquivoQueries
	 *            o nome do arquivo de queries a ser utilizado
	 * @param nomeColecaoComandos
	 *            o nome da colecao onde serao armazenadas as queries
	 */
	protected CAPESProcessamentoDao(String datasource, String arquivoQueries,
            String nomeColecaoComandos) {
		super(datasource, arquivoQueries, nomeColecaoComandos);
    }

	/**
	 * Estabelece a conexao com o banco de dados.
	 * 
	 * @return a conexao com o banco de dados.
	 */
	@Override
	protected Connection estabelecerConexao() {
		try {
			BancoobDataSource datasource = new BancoobDataSource(
					getNomeDatasource(), System.getProperties());


			return datasource.getConnection();
		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		}
	}

	/**
	 * Fecha o ResultSet.
	 * @param rs a conexão com a base de dados.
	 */
	protected void fecharResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				SicoobLoggerPadrao.getInstance(getClass()).erro(e, e.getMessage());
			}
		}
	}
	
	@Override
	@PersistenceContext(unitName = "emCapesLegado")
	public void setEntityManager(EntityManager manager) {
		super.setEntityManager(manager);
	}
}