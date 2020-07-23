package br.com.sicoob.capes.api.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.api.negocio.vo.TipoFonteRendaVO;
import br.com.sicoob.capes.api.persistencia.dao.TipoFonteRendaDAO;

public class TipoFonteRendaDAOImpl extends CAPESApiDAO<TipoFonteRendaVO> implements TipoFonteRendaDAO {

	public List<TipoFonteRendaVO> listar() throws BancoobException {
		return listarPorTipoPessoa(null);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<TipoFonteRendaVO> listarPorTipoPessoa(Short codigoTipoPessoa) throws BancoobException {
		Connection con = null;
		ResultSet rs = null;
		Comando comando = null;
		List<TipoFonteRendaVO> retorno = new ArrayList<TipoFonteRendaVO>();

		try {
			con = estabelecerConexao();
			comando = getComando("LISTAR_TIPO_FONTE_RENDA");
			comando.adicionarVariavel("codigoTipoPessoa", codigoTipoPessoa);
			comando.configurar();

			rs = comando.executarConsulta(con);

			while (rs.next()) {
				TipoFonteRendaVO vo = new TipoFonteRendaVO();
				vo.setCodigo(rs.getShort("CODTIPOFONTERENDA"));
				vo.setDescricao(rs.getString("DESCTIPOFONTERENDA"));
				vo.setCodigoTipoPessoa(rs.getShort("CODTIPOPESSOA"));
				vo.setValorObrigatorio(rs.getBoolean("BOLVALOROBRIGATORIO"));
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

	/**
	 * {@inheritDoc}
	 */
	public TipoFonteRendaVO obterTipoFonteRenda(Short codigo) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("OBTER_TIPO_FONTE_RENDA");
			comando.adicionarVariavel("codigoTipoRenda", codigo);
			comando.configurar();

			return (TipoFonteRendaVO) comando.criarQuery(getEntityManager()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			fecharComando(comando);
		}
	}

}