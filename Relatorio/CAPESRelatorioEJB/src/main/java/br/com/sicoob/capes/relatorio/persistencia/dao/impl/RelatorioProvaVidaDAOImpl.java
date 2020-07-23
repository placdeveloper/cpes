/**
 * 
 */
package br.com.sicoob.capes.relatorio.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.relatorio.negocio.dto.RelatorioProvaVidaDTO;
import br.com.sicoob.capes.relatorio.negocio.enums.RelatorioProvaVidaEnum;
import br.com.sicoob.capes.relatorio.persistencia.dao.RelatorioProvaVidaDAO;

/**
 * @author Daniel.Domingues
 *
 */
public class RelatorioProvaVidaDAOImpl extends CAPESRelatorioDao implements RelatorioProvaVidaDAO {

	/**
	 * Obtem os dados da Instituição informada.
	 * 
	 * @param instituicao Instituição do relatorio.
	 * @return RelatorioProvaVidaDTO
	 * @throws BancoobException
	 */
	public RelatorioProvaVidaDTO obterDadosRelatorio(Instituicao instituicao) throws BancoobException {
		RelatorioProvaVidaDTO relatorioDTO = new RelatorioProvaVidaDTO();
		Comando comando = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			comando = getComando("CONSULTA_INSTITUICAO_RELATORIO_PROVA_VIDA");
			comando.adicionarVariavel("instituicao", instituicao);
			comando.configurar();

			conn = estabelecerConexao();
			rs = comando.executarConsulta(conn);
			
			if (rs.next()) {
				relatorioDTO.setCidade(rs.getString(RelatorioProvaVidaEnum.CIDADE.name()));
				relatorioDTO.setNomeCooperativa(rs.getString(RelatorioProvaVidaEnum.NOME_COOPERATIVA.name()));
				relatorioDTO.setNumeroCooperativa(rs.getInt(RelatorioProvaVidaEnum.NUMERO_COOPERATIVA.name()));
				relatorioDTO.setUnidadeInstituicao(rs.getInt(RelatorioProvaVidaEnum.UNIDADE_INSTITUICAO.name()));				
			}

		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharResultSet(rs);
			fecharConexao(conn);
			fecharComando(comando);
		}
		
		return relatorioDTO;
	}
}
