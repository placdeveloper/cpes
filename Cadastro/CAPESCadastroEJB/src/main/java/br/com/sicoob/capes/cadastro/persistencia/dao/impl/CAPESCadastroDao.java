package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.dao.BancoobDao;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.cadastro.persistencia.CAPESCadastroDatasource;
import br.com.sicoob.capes.cadastro.util.CAPESCadastroConstantes;
import br.com.sicoob.capes.comum.util.Constantes;

/**
 * Classe padrao com a infraestrutura de acesso a base de dados do sistema CadastroUnicoClientesComum
 * 
 * @author Stefanini IT Solutions
 */
public abstract class CAPESCadastroDao extends BancoobDao {

	/**
	 * @param nomeComandoListar
	 * @param nomeComandoConsultar
	 */
	public CAPESCadastroDao() {

		super(Constantes.Persistencia.DATASOURCE_CAPES,
				CAPESCadastroConstantes.Persistencia.ARQUIVO_QUERIES,
				CAPESCadastroConstantes.Persistencia.NOME_COLECAO_COMANDOS);
	}
	
	/**
	 * Estabelece a conexao com o banco de dados.
	 * 
	 * @return a conexao com o banco de dados.
	 */
	@Override
	protected Connection estabelecerConexao() {
		try {
			CAPESCadastroDatasource datasource = 
					new CAPESCadastroDatasource(getNomeDatasource(), 
							System.getProperties());

			return datasource.getConnection();
		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		}
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