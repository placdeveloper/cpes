package br.com.sicoob.capes.processamento.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.processamento.persistencia.dao.CAPESProcessamentoDao;
import br.com.sicoob.capes.processamento.persistencia.dao.GrupoEconomicoDao;
import br.com.sicoob.capes.processamento.util.Constantes;

/**
 * 
 * @author Paulo.Stoppa
 *
 */
public class GrupoEconomicoDaoImpl extends CAPESProcessamentoDao implements GrupoEconomicoDao {

	/**
	 * Construtor
	 */
	public GrupoEconomicoDaoImpl() {
		super(br.com.sicoob.capes.comum.util.Constantes.Persistencia.DATASOURCE_CAPES, Constantes.Persistencia.ARQUIVO_QUERIES,
				Constantes.Persistencia.NOME_COLECAO_COMANDOS);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int contarGruposSemCompartilhamentos(Integer idInstituicao) {
		return contarGruposSemCompartilhamentos(idInstituicao, "count_distinct_grupo");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Long> listarPessoasSemCompartilhamento(Integer idInstituicao) {
		ResultSet rset = null;
		Connection con = null;
		Comando comando = null;
		try {
			con = estabelecerConexao();
			comando = getComando("PESQUISAR_GRUPO_ECONOMICO_PESSOA_SEM_COMPARTILHAMENTO");
			comando.adicionarVariavel("projecao", "distinct_idPessoaCompartilhamento");
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.configurar();
			rset = comando.executarConsulta(con);
			List<Long> lista = new ArrayList<>();
			while (rset.next()) {
				lista.add(rset.getLong("IDPESSOACOMPARTILHAMENTO"));
			}
			return lista;
		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharResultSet(rset);
			fecharConexao(con);
			fecharComando(comando);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	private int contarGruposSemCompartilhamentos(Integer idInstituicao, String projecao) {
		ResultSet rset = null;
		Connection con = null;
		Comando comando = null;
		try {
			con = estabelecerConexao();
			comando = getComando("PESQUISAR_GRUPO_ECONOMICO_PESSOA_SEM_COMPARTILHAMENTO");
			comando.adicionarVariavel("projecao", projecao);
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.configurar();
			rset = comando.executarConsulta(con);
			rset.next();
			return rset.getInt("total");
		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharResultSet(rset);
			fecharConexao(con);
			fecharComando(comando);
		}
	}

	/**
	 * define o manager padrão para o dao.
	 * 
	 * @param manager
	 */
	@Override
	@PersistenceContext(unitName = "emCapes")
	public void setEntityManager(EntityManager manager) {
		super.setEntityManager(manager);
	}

}