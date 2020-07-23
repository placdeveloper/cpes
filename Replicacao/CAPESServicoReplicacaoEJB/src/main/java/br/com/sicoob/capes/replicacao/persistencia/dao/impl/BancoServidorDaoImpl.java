package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.negocio.entidades.legado.BancoServidor;
import br.com.sicoob.capes.replicacao.negocio.vo.DataHoraServidorVO;
import br.com.sicoob.capes.replicacao.persistencia.dao.BancoServidorDao;
import br.com.sicoob.capes.replicacao.persistencia.dao.CAPESServicoReplicacaoCrudDao;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;

/**
 * Classe que implementa a persistencia de BancoServidor.
 * @author Juan.Damasceno
 *
 */
public class BancoServidorDaoImpl extends CAPESServicoReplicacaoCrudDao<BancoServidor> implements
		BancoServidorDao {

	/** A constante COMANDO_LISTAR_BANCO_SERVIDOR. */
	private static final String COMANDO_LISTAR_BANCO_SERVIDOR = "LISTAR_BANCO_SERVIDOR";
	
	/** A constante COMANDO_CONSULTAR_HORA_SERVIDOR_REMOTO. */
	private static final String COMANDO_CONSULTAR_HORA_SERVIDOR_REMOTO = "CONSULTAR_HORA_SERVIDOR_REMOTO";
	
	/** O atributo logger. */
	private ISicoobLogger logger = getLogger();
	
	/**
	 * Instancia um novo BancoServidorDaoImpl.
	 */
	public BancoServidorDaoImpl() {
		super(BancoServidor.class, COMANDO_LISTAR_BANCO_SERVIDOR);
	}
	
	@Override
	public List<BancoServidor> listar() throws BancoobException {
		Comando comando = null;
		Connection conn = null;
		ResultSet rs = null;

		List<BancoServidor> retorno = new ArrayList<BancoServidor>();
		try {
			comando = getComando(COMANDO_LISTAR_BANCO_SERVIDOR);
			comando.configurar();

			conn = estabelecerConexao();
			rs = comando.executarConsulta(conn);

			while (rs.next()) {
				retorno.add(new BancoServidor(rs.getString("NOMESERVER"), rs.getString("NOMEBANCODADOS"), rs.getInt("NUMCOOPERATIVA")));
			}

		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharResultSet(rs);
			fecharConexao(conn);
			fecharComando(comando);
		}

		return retorno;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DataHoraServidorVO obterDataHoraServidor(BancoServidor bancoServidor) {

		Connection conx = null;
		Comando comando = null;
		ResultSet rset = null;
		DataHoraServidorVO dataHoraServidorVO = null;
		PreparedStatement statement = null;

		try {

			conx = estabelecerConexao();
			comando = getComando(COMANDO_CONSULTAR_HORA_SERVIDOR_REMOTO);
			comando.adicionarVariavel("nomeServidor", bancoServidor.getNomeServer());			
			comando.configurar();
			
			statement = conx.prepareStatement(comando.getSqlEmUso());
			statement.setQueryTimeout(1);
			
			rset = statement.executeQuery();
			
			if (rset.next()) {
				dataHoraServidorVO = new DataHoraServidorVO();
				dataHoraServidorVO.setDataHoraLocal(rset.getTimestamp("local_time"));
				dataHoraServidorVO.setDataHoraRemota(rset.getTimestamp("remote_time"));
			}

		} catch (SQLException excecao) {
			logger.erro(excecao, "Erro tentando conectar ao banco " + bancoServidor.getNomeServer());
			throw new PersistenciaException(excecao);
		} finally {
			fecharComando(comando);
			fecharConexao(conx);
			fecharPreparedStatement(statement);
		}

		return dataHoraServidorVO;
	}
	
	/**
	 * O método Fechar prepared statement.
	 *
	 * @param pstm o valor de pstm
	 */
	private void fecharPreparedStatement(PreparedStatement pstm) {
		if (pstm != null) {
			try {
				pstm.close();
			} catch (SQLException e) {
				getLogger().erro(e, "Erro ao fechar o PreparedStatement");
			}
		}
	}
	
	/**
	 * O método Fechar result set.
	 *
	 * @param rset o valor de rset
	 */
	private void fecharResultSet(ResultSet rset) {
    	if (rset != null) {
    		try {
    			rset.close();
    		} catch (SQLException e) {
    			getLogger().erro(e, "Falha no fechamento do ResultSet");
    		}
    	}
    }
}