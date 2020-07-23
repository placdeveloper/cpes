package br.com.sicoob.capes.relatorio.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.negocio.entidades.ValidacaoCadastral;
import br.com.sicoob.capes.relatorio.negocio.dto.RelatorioValidacaoCadastralDTO;
import br.com.sicoob.capes.relatorio.negocio.vo.RelatorioValidacaoCadastralAnaliticoVO;
import br.com.sicoob.capes.relatorio.negocio.vo.RelatorioValidacaoCadastralSinteticoVO;
import br.com.sicoob.capes.relatorio.persistencia.dao.RelatorioValidacaoCadastralDAO;

/**
 * A Classe RelatorioValidacaoCadastralDAOImpl.
 */
public class RelatorioValidacaoCadastralDAOImpl extends CAPESRelatorioDao
		implements RelatorioValidacaoCadastralDAO {

	/**
	 * {@inheritDoc}
	 */
	public List<RelatorioValidacaoCadastralAnaliticoVO> obterDadosRelatorioAnalitico(
			RelatorioValidacaoCadastralDTO dto) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		List<RelatorioValidacaoCadastralAnaliticoVO> listaRetorno = new ArrayList<RelatorioValidacaoCadastralAnaliticoVO>();

		try {
			conexao = estabelecerConexao();
			comando = getComando("PESQUISAR_RELATORIO_VALIDACAO_CADASTRAL_ANALITICO");
			comando.adicionarVariavel("objeto", dto);
			comando.configurar();

			getLogger()
					.debug("Executando a consulta do relatorio de validacao cadastral - analitico");
			getLogger().debug(comando.getSqlEmUso());

			rs = comando.executarConsulta(conexao);

			while (rs.next()) {
				RelatorioValidacaoCadastralAnaliticoVO vo = new RelatorioValidacaoCadastralAnaliticoVO();

				vo.setCpfCnpj(rs.getString("NUMCPFCNPJ"));
				vo.setNomePessoa(rs.getString("NOMEPESSOA"));
				vo.setDataUltimaAtualizacao(rs
						.getDate("DATAHORAULTIMAATUALIZACAO"));
				vo.setErro(rs.getString("DESCMENSAGEMERRO"));
				vo.setFuncionalidade(rs.getString("DESCREFERENCIA"));
				vo.setTipoRegra(rs.getString("DESCTIPOREGRA"));

				listaRetorno.add(vo);
			}

		} catch (SQLException e) {
			getLogger()
					.erro(e,
							"Erro ao executar a consulta do relatorio de validacao cadastral - analitico");
			throw new BancoobException(e);
		} finally {
			fecharResultSet(rs);
			fecharComando(comando);
			fecharConexao(conexao);
		}

		return listaRetorno;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<RelatorioValidacaoCadastralSinteticoVO> obterDadosRelatorioSintetico(
			RelatorioValidacaoCadastralDTO dto) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		List<RelatorioValidacaoCadastralSinteticoVO> listaRetorno = new ArrayList<RelatorioValidacaoCadastralSinteticoVO>();

		try {
			conexao = estabelecerConexao();
			comando = getComando("PESQUISAR_RELATORIO_VALIDACAO_CADASTRAL_SINTETICO");
			comando.adicionarVariavel("objeto", dto);
			comando.configurar();

			getLogger()
					.debug("Executando a consulta do relatorio de validacao cadastral - sintetico");
			getLogger().debug(comando.getSqlEmUso());

			rs = comando.executarConsulta(conexao);

			while (rs.next()) {
				RelatorioValidacaoCadastralSinteticoVO vo = new RelatorioValidacaoCadastralSinteticoVO();

				vo.setErro(rs.getString("DESCMENSAGEMERRO"));
				vo.setFuncionalidade(rs.getString("DESCREFERENCIA"));
				vo.setQuantidade(rs.getLong("QUANTIDADE"));
				vo.setTipoRegra(rs.getString("DESCTIPOREGRA"));
				listaRetorno.add(vo);
			}

		} catch (SQLException e) {
			getLogger()
					.erro(e,
							"Erro ao executar a consulta do relatorio de validacao cadastral - sintetico");
			throw new BancoobException(e);
		} finally {
			fecharResultSet(rs);
			fecharComando(comando);
			fecharConexao(conexao);
		}

		return listaRetorno;
	}

	public ValidacaoCadastral consultarUltimoEnvioAprovacao(Long idPessoa) {

		Comando comando = null;

		try {
			comando = getComando("CONSULTA_VALIDACAO_CADASTRAL_RELATORIO_ATUAL");
			comando.adicionarVariavel("idPessoaCompartilhamento", idPessoa);
			comando.configurar();

			return (ValidacaoCadastral) criarQuery(comando).getSingleResult();

		} finally {
			fecharComando(comando);
		}

	}

}