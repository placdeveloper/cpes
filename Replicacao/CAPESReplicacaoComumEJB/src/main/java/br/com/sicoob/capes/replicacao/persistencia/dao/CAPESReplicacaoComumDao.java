package br.com.sicoob.capes.replicacao.persistencia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.bancoob.persistencia.dao.BancoobDao;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.persistencia.CAPESEntidadesLegadoDatasource;

/**
 * Classe padrao com a infraestrutura de acesso a base de dados do sistema
 * ReplicacaoClientesBO
 * 
 * @author Stefanini IT Solutions
 */
public abstract class CAPESReplicacaoComumDao extends BancoobDao {

	/** Nome da coleção de comandos do sistema replicação de clientes. */
	private static final String NOME_COLECAO_COMANDOS = "COMANDOS_REPLICACAO_CLIENTES";

	/** Caminho para o arquivo de queries do replicação de clientes. */
	private static final String ARQUIVO_QUERIES = "capes.replicacao.comum.queries.xml";

	/** Caminho para o datasource. */
	private static final String DATASOURCE = "jdbc/BancoobDS";

	/**
	 * Construtor dos DAOs que no sistema Replicação de clientes.
	 */
	public CAPESReplicacaoComumDao() {
		super(DATASOURCE, ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS);
	}

	/**
	 * Estabelece a conexao com o banco de dados.
	 * 
	 * @return a conexao com o banco de dados.
	 */
	@Override
	protected Connection estabelecerConexao() {
		try {
			CAPESEntidadesLegadoDatasource datasource = 
					new CAPESEntidadesLegadoDatasource(
							getNomeDatasource(), System.getProperties());
			return datasource.getConnection();
		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		}
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