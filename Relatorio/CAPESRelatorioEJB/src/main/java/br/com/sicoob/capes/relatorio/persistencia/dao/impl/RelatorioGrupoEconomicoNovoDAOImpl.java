package br.com.sicoob.capes.relatorio.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.capes.relatorio.negocio.dto.RelatorioGrupoEconomicoDTO;
import br.com.sicoob.capes.relatorio.negocio.vo.RelatorioGrupoEconomicoNovoVO;
import br.com.sicoob.capes.relatorio.persistencia.dao.RelatorioGrupoEconomicoNovoDAO;
import br.com.sicoob.tipos.DateTime;

/**
 * A implementação da interface {@link RelatorioGrupoEconomicoNovoDAO}.
 */
public class RelatorioGrupoEconomicoNovoDAOImpl extends CAPESRelatorioDao implements RelatorioGrupoEconomicoNovoDAO {

	private static final String RELATORIO_GRUPO_ECONOMICO_NOVO = "CONSULTA_RELATORIO_GRUPO_ECONOMICO_NOVO";

	/**
	 * {@inheritDoc}
	 */
	public List<RelatorioGrupoEconomicoNovoVO> consultaRelatorioGrupoEconomico(RelatorioGrupoEconomicoDTO relatorioGrupoEconomicoDTO)
			throws BancoobException {
		List<RelatorioGrupoEconomicoNovoVO> retorno = new ArrayList<RelatorioGrupoEconomicoNovoVO>();

		Comando comando = null;
		Connection conn = null;
		ResultSet rs = null;

		try {
			comando = getComando(RELATORIO_GRUPO_ECONOMICO_NOVO);
			comando.adicionarVariavel("filtro", relatorioGrupoEconomicoDTO);
			comando.configurar();

			debugarComando(RELATORIO_GRUPO_ECONOMICO_NOVO, comando);

			conn = estabelecerConexao();
			rs = comando.executarConsulta(conn);

			while (rs.next()) {
				final RelatorioGrupoEconomicoNovoVO vo = new RelatorioGrupoEconomicoNovoVO();
				vo.setDescricao(rs.getString("DESCGRUPOECONOMICO"));
				vo.setCpfCnpj(rs.getString("NUMCPFCNPJ"));
				vo.setNomePessoa(rs.getString("NOMEPESSOA"));
				vo.setDataInicioGrupo(getDateTime(rs, "DATAHORACADASTRO"));
				vo.setDataInicioPessoaGrupo(getDateTime(rs, "DATAHORAINICIO"));
				vo.setUsuarioResponsavel(rs.getString("IDUSUARIOAPROV"));
				vo.setOrigem(rs.getString("DESCORIGEM"));
				vo.setDataFimPessoaGrupo(getDateTime(rs, "DATAHORAFIM"));
				retorno.add(vo);
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
	 * @param nomeComando
	 * 
	 */
	private void debugarComando(String nomeComando, Comando comando) {
		if (getLogger().isDebugEnabled()) {
			getLogger().debug(MensagemUtil.getString("debug.comando", nomeComando));
			getLogger().debug(ToStringBuilder.reflectionToString(comando, ToStringStyle.MULTI_LINE_STYLE));
		}
	}

	/**
	 * 
	 * @param rs
	 * @param columnName
	 * @return
	 * @throws SQLException
	 */
	private DateTime getDateTime(ResultSet rs, String columnName) throws SQLException {
		Timestamp date = rs.getTimestamp(columnName);
		return date != null ? new DateTime(date.getTime()) : null;
	}

}