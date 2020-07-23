package br.com.sicoob.capes.relatorio.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.relatorio.negocio.dto.RelatorioGrupoEconomicoDTO;
import br.com.sicoob.capes.relatorio.negocio.vo.RelatorioGrupoEconomicoVO;
import br.com.sicoob.capes.relatorio.persistencia.dao.RelatorioGrupoEconomicoDAO;
import br.com.sicoob.tipos.DateTime;

/**
 * A Classe RelatorioGrupoEconomicoDAOImpl.
 */
public class RelatorioGrupoEconomicoDAOImpl extends CAPESRelatorioDao implements RelatorioGrupoEconomicoDAO {
	
	/**
	 * {@inheritDoc}
	 */
	public List<RelatorioGrupoEconomicoVO> consultaRelatorioGrupoEconomico(RelatorioGrupoEconomicoDTO relatorioGrupoEconomicoDTO) throws BancoobException {
		List<RelatorioGrupoEconomicoVO> retorno = new ArrayList<RelatorioGrupoEconomicoVO>();
		
		Comando comando = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			comando = getComando("CONSULTA_RELATORIO_GRUPO_ECONOMICO");
			comando.adicionarVariavel("dto", relatorioGrupoEconomicoDTO);
			comando.configurar();

			conn = estabelecerConexao();
			rs = comando.executarConsulta(conn);
			
			while (rs.next()) {
				RelatorioGrupoEconomicoVO vo = new RelatorioGrupoEconomicoVO();
				vo.setDescGrupoEconomico(rs.getString("DESCGRUPOECONOMICO"));
				vo.setDataCadastro(new DateTime(rs.getDate("DATAHORACADASTRO").getTime()));
				vo.setCpfCnpj(rs.getString("NUMCPFCNPJFORMATADO"));
				vo.setNomePessoa(rs.getString("NOMEPESSOA"));
				vo.setDataInicio(new DateTime(rs.getDate("DATAHORAINICIO").getTime()));
				vo.setUsuarioResponsavel(rs.getString("IDUSUARIOAPROV"));
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

}