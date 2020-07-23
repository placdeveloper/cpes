package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.cadastro.negocio.vo.CentralSingularVO;
import br.com.sicoob.capes.cadastro.negocio.vo.UnidadeVO;
import br.com.sicoob.capes.cadastro.persistencia.dao.CentraisSingularesDAO;

/**
 * A Classe CentraisSingularesDAOImpl.
 */
public class CentraisSingularesDAOImpl extends CAPESCadastroDao implements CentraisSingularesDAO {
	
	/** A constante COMANDO_OBTER_CENTRAIS. */
	private static final String COMANDO_OBTER_CENTRAIS = "OBTER_CENTRAIS";
	
	/** A constante COMANDO_OBTER_SINGULARES. */
	private static final String COMANDO_OBTER_SINGULARES = "OBTER_SINGULARES";
	
	/** A constante COMANDO_OBTER_PACS. */
	private static final String COMANDO_OBTER_PACS = "OBTER_PACS";
	
	/**
	 * Instancia um novo CentraisSingularesDAOImpl.
	 */
	public CentraisSingularesDAOImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CentralSingularVO> obterListaCentrais(Integer idInstituicaoLogada) throws BancoobException {
		Connection conexao = null;
		ResultSet rset = null;
		Comando comando = null;
		List<CentralSingularVO> lista = new ArrayList<CentralSingularVO>();

		try {
			conexao = estabelecerConexao();
			comando = getComando(COMANDO_OBTER_CENTRAIS);
			comando.adicionarVariavel("idInstituicaoLogada", idInstituicaoLogada);
			comando.configurar();

			rset = comando.executarConsulta(conexao);

			while (rset.next()) {
				CentralSingularVO vo = new CentralSingularVO();
				vo.setIdInstituicao(rset.getInt("IDINSTITUICAO"));
				vo.setNumeroCooperativa(rset.getInt("NUMCOOPERATIVA"));
				lista.add(vo);
			}
		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conexao);
			fecharComando(comando);
		}
		return lista;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CentralSingularVO> obterListaSingulares(Integer numeroCooperativa) throws BancoobException {
		Connection conexao = null;
		ResultSet rset = null;
		Comando comando = null;
		List<CentralSingularVO> lista = new ArrayList<CentralSingularVO>();
		try {
			conexao = estabelecerConexao();
			comando = getComando(COMANDO_OBTER_SINGULARES);
			comando.adicionarVariavel("numeroCooperativa", numeroCooperativa);
			comando.configurar();

			rset = comando.executarConsulta(conexao);
			while (rset.next()) {
				CentralSingularVO vo = new CentralSingularVO();
				vo.setIdInstituicao(rset.getInt("IDINSTITUICAO"));
				vo.setNumeroCooperativa(rset.getInt("NUMCOOPERATIVA"));
				lista.add(vo);
			}
		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conexao);
			fecharComando(comando);
		}
		return lista;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<UnidadeVO> obterListaPacs(Integer numeroCooperativa) throws BancoobException {
		Connection conexao = null;
		ResultSet rset = null;
		Comando comando = null;
		List<UnidadeVO> lista = new ArrayList<UnidadeVO>();
		try {
			conexao = estabelecerConexao();
			comando = getComando(COMANDO_OBTER_PACS);
			comando.adicionarVariavel("numeroCooperativa", numeroCooperativa);
			comando.configurar();

			rset = comando.executarConsulta(conexao);
			while (rset.next()) {
				UnidadeVO vo = new UnidadeVO();
				vo.setCodigo(rset.getInt("IDUNIDADEINST"));
				vo.setDescricao(rset.getString("DESCNOMECOOP"));
				lista.add(vo);
			}
		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conexao);
			fecharComando(comando);
		}
		return lista;
	}

}