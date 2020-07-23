package br.com.sicoob.capes.api.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.capes.api.negocio.vo.GrupoEconomicoVO;
import br.com.sicoob.capes.api.negocio.vo.PessoaGrupoEconomicoVO;
import br.com.sicoob.capes.api.persistencia.dao.GrupoEconomicoNovoDAO;

/**
 * A Classe implementação de {@link GrupoEconomicoNovoDAO}
 */
public class GrupoEconomicoNovoDAOImpl extends CAPESApiDAO<GrupoEconomicoVO> implements GrupoEconomicoNovoDAO {

	/**
	 * Instancia um novo GrupoEconomicoDAOImpl.
	 */
	public GrupoEconomicoNovoDAOImpl() {
		super("");
	}

	/**
	 * {@inheritDoc}
	 */
	public GrupoEconomicoVO obterGrupoPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) {
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;
		GrupoEconomicoVO retorno = null;
		try {
			conx = estabelecerConexao();
			comando = getComando("OBTER_GRUPO_ECONOMICO_NOVO");
			comando.adicionarVariavel(ID_PESSOA, idPessoa);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.configurar();
			rset = comando.executarConsulta(conx);
			debugarResultSet(rset);
			if (rset.next()) {
				retorno = new GrupoEconomicoVO();
				retorno.setIdPessoa(rset.getInt("IDPESSOA"));
				retorno.setIdInstituicao(rset.getInt("IDINSTITUICAO"));
				retorno.setIdGrupoEconomicoPessoa(rset.getInt("IDGRUPOECONOMICOPESSOA"));
				retorno.setDataHoraInicio(getDateTime(rset, "DATAHORAINICIO"));
				retorno.setIdGrupoEconomico(rset.getInt("IDGRUPOECONOMICO"));
				retorno.setDescricaoGrupo(rset.getString("DESCGRUPOECONOMICO"));
				retorno.setDataHoraCadastro(getDateTime(rset, "DATAHORACADASTRO"));
			}
		} catch (SQLException excecao) {
			throw new PersistenciaException("grupoEconomico.erroConsultarGrupo", excecao);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conx);
			fecharComando(comando);
		}
		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PessoaGrupoEconomicoVO> obterPessoasPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) {
		return obterPessoas(null, idInstituicao, null, null, idPessoa, false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PessoaGrupoEconomicoVO> obterPessoasGruposPorCpfCnpj(List<String> listaCpfCnpj, Integer idInstituicao) {
		return obterPessoas(null, idInstituicao, listaCpfCnpj, null, null, true);
	}

	@Override
	public List<PessoaGrupoEconomicoVO> obterPessoasPorCpfCnpj(String cpfCnpj, Integer idInstituicao) {
		return obterPessoas(null, idInstituicao, null, cpfCnpj, null, false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PessoaGrupoEconomicoVO> obterPessoasPorGrupo(Integer idGrupoEconomico) {
		return obterPessoas(idGrupoEconomico, null, null, null, null, false);
	}

	/**
	 * 
	 * @param idGrupo
	 * @param idInstituicao
	 * @param listaCpfCnpj
	 * @param cpfCnpj
	 * @param idPessoa
	 * @return
	 */
	private List<PessoaGrupoEconomicoVO> obterPessoas(Integer idGrupo, Integer idInstituicao, List<String> listaCpfCnpj, String cpfCnpj,
			Integer idPessoa, boolean forcarFiltro) {
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;
		List<PessoaGrupoEconomicoVO> listaRetorno = new ArrayList<PessoaGrupoEconomicoVO>();
		try {
			conx = estabelecerConexao();
			comando = getComando("OBTER_PESSOAS_POR_GRUPO_ECONOMICO_NOVO");
			comando.adicionarVariavel("idGrupo", idGrupo);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.adicionarVariavel("listaCpfCnpj", listaCpfCnpj);
			comando.adicionarVariavel("cpfCnpj", cpfCnpj);
			comando.adicionarVariavel(ID_PESSOA, idPessoa);
			comando.adicionarVariavel("forcarFiltro", forcarFiltro);
			comando.configurar();
			rset = comando.executarConsulta(conx);
			debugarResultSet(rset);
			while (rset.next()) {
				PessoaGrupoEconomicoVO vo = new PessoaGrupoEconomicoVO();
				vo.setIdPessoa(rset.getInt("IDPESSOA"));
				vo.setCpfCnpj(rset.getString("NUMCPFCNPJ"));
				vo.setNomePessoa(rset.getString("NOMEPESSOA"));
				vo.setIdGrupoEconomico(rset.getInt("IDGRUPOECONOMICO"));
				vo.setNomeGrupoEconomico(rset.getString("DESCGRUPOECONOMICO"));
				listaRetorno.add(vo);
			}
		} catch (SQLException excecao) {
			throw new PersistenciaException("grupoEconomico.erroConsultarGrupo", excecao);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conx);
			fecharComando(comando);
		}
		return listaRetorno;
	}

	/**
	 * @param rset
	 * @throws SQLException
	 */
	private void debugarResultSet(ResultSet rset) throws SQLException {
		if (getLogger().isDebugEnabled()) {
			ResultSetMetaData metaData = rset.getMetaData();
			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				getLogger().debug(MensagemUtil.getString("debugResultSetMetaData", i, metaData.getColumnName(i), metaData.getColumnLabel(i),
						metaData.getColumnTypeName(i)));
			}
		}
	}

}