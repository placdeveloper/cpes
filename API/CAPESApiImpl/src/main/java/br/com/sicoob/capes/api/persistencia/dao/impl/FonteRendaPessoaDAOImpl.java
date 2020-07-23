package br.com.sicoob.capes.api.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.api.negocio.vo.FonteRendaPessoaVO;
import br.com.sicoob.capes.api.persistencia.dao.FonteRendaPessoaDAO;

/**
 * A Classe FonteRendaPessoaDAOImpl.
 */
public class FonteRendaPessoaDAOImpl extends CAPESApiDAO<FonteRendaPessoaVO> implements FonteRendaPessoaDAO {

	/**
	 * Instancia um novo FonteRendaPessoaDAOImpl.
	 */
	public FonteRendaPessoaDAOImpl() {
		super("CONSULTAR_FONTE_RENDA_PESSOA");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String obterNomeComandoPesquisar() throws BancoobException {
		return "PESQUISAR_FONTE_RENDA_PESSOA";
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String obterNomeComandoQuantidadePesquisar() throws BancoobException {
		return "PESQUISAR_QUANTIDADE_FONTE_RENDA_PESSOA";
	}

	public List<FonteRendaPessoaVO> listarFonteRendaPessoaPorIdPessoaLegadoIdInstituicao(
			Integer idPessoaLegado, Integer idInstituicao)
			throws BancoobException {
		Connection con = null;
		ResultSet rs = null;
		Comando comando = null;
		List<FonteRendaPessoaVO> retorno = new ArrayList<FonteRendaPessoaVO>();

		try {
			con = estabelecerConexao();
			comando = getComando("CONSULTAR_FONTE_RENDA_POR_IDPESSOALEGADO_IDINSITITUICAO");
			comando.adicionarVariavel(ID_PESSOA_LEGADO, idPessoaLegado);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.configurar();

			rs = comando.executarConsulta(con);

			while (rs.next()) {
				FonteRendaPessoaVO vo = new FonteRendaPessoaVO();
				vo.setIdFonteRenda(rs.getLong("IDFONTERENDA"));
				vo.setCodigoTipoFonteRenda(rs.getShort("CODTIPOFONTERENDA"));
				vo.setDescricaoTipoFonteRenda(rs.getString("DESCTIPOFONTERENDA"));
				vo.setRendaFixa(rs.getBoolean("BOLRENDAFIXA"));
				vo.setDataValidadeRenda(rs.getDate("DATAVALIDADERENDA"));
				vo.setValorReceitaBrutaMensal(rs.getBigDecimal("VALORRECEITABRUTAMENSAL"));
				vo.setDescricao(rs.getString("DESCFONTERENDA"));
				vo.setIdPessoaEmpregador(rs.getInt("IDPESSOAEMPREGADOR"));
				vo.setDataHoraInicio(rs.getDate("DATAHORAINICIO"));
				vo.setBolSimplesNacional(rs.getBoolean("BOLSIMPLESNACIONAL"));
				vo.setBolPossuiAtivo(rs.getBoolean("BOLPOSSUIATIVO"));
				retorno.add(vo);
			}
		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		} finally {
			fecharResultSet(rs);
			fecharConexao(con);
			fecharComando(comando);
		}

		return retorno;
	}

}