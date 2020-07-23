package br.com.sicoob.capes.api.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.api.negocio.vo.GrupoEconomicoVO;
import br.com.sicoob.capes.api.negocio.vo.PessoaGrupoEconomicoVO;
import br.com.sicoob.capes.api.persistencia.dao.GrupoEconomicoDAO;

/**
 * A Classe GrupoEconomicoDAOImpl.
 */
public class GrupoEconomicoDAOImpl extends CAPESApiDAO<GrupoEconomicoVO> implements GrupoEconomicoDAO {

	/**
	 * Instancia um novo GrupoEconomicoDAOImpl.
	 */
	public GrupoEconomicoDAOImpl() {
		super("PESQUISAR_GRUPO_ECONOMICO");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PessoaGrupoEconomicoVO> obterPessoasPorGrupoInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;
		List<PessoaGrupoEconomicoVO> listaRetorno = new ArrayList<PessoaGrupoEconomicoVO>();

		try {
			conx = estabelecerConexao();

			comando = getComando("OBTER_PESSOAS_GRUPO_ECONOMICO");
			comando.adicionarVariavel("idPessoa", idPessoa);
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.configurar();

			getLogger().debug(comando.getSqlEmUso());

			rset = comando.executarConsulta(conx);

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
			throw new PersistenciaException(excecao);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conx);
			fecharComando(comando);
		}
		
		return listaRetorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PessoaGrupoEconomicoVO> obterGruposPorCpfCnpj(List<String> listaCpfCnpj, Integer idInstituicao) {
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;
		List<PessoaGrupoEconomicoVO> listaRetorno = new ArrayList<PessoaGrupoEconomicoVO>();

		try {
			conx = estabelecerConexao();

			comando = getComando("OBTER_GRUPOS_ECONOMICOS_POR_CPF_CNPJ");
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.adicionarVariavel("listaCpfCnpj", listaCpfCnpj);
			comando.configurar();

			rset = comando.executarConsulta(conx);

			while (rset.next()) {
				PessoaGrupoEconomicoVO vo = new PessoaGrupoEconomicoVO();
				vo.setCpfCnpj(rset.getString("NUMCPFCNPJ"));
				vo.setIdGrupoEconomico(rset.getInt("IDGRUPOECONOMICO"));
				listaRetorno.add(vo);
			}

		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conx);
			fecharComando(comando);
		}

		return listaRetorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PessoaGrupoEconomicoVO> obterPessoasPorGrupo(Integer idGrupoEconomico) {
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;
		List<PessoaGrupoEconomicoVO> listaRetorno = new ArrayList<PessoaGrupoEconomicoVO>();

		try {
			conx = estabelecerConexao();

			comando = getComando("OBTER_PESSOAS_GRUPO_ECONOMICO");
			comando.adicionarVariavel("idGrupoEconomico", idGrupoEconomico);
			comando.configurar();

			getLogger().debug(comando.getSqlEmUso());

			rset = comando.executarConsulta(conx);

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
			throw new PersistenciaException(excecao);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conx);
			fecharComando(comando);
		}
		
		return listaRetorno;
	}

}