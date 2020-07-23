package br.com.sicoob.capes.api.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.api.negocio.vo.TipoEmpresaVO;
import br.com.sicoob.capes.api.persistencia.dao.TipoEmpresaDAO;

public class TipoEmpresaDAOImpl extends CAPESApiDAO<TipoEmpresaVO> implements TipoEmpresaDAO {

	public List<TipoEmpresaVO> listar() throws BancoobException {

		Connection con = null;
		ResultSet rs = null;
		Comando comando = null;
		List<TipoEmpresaVO> retorno = new ArrayList<TipoEmpresaVO>();

		try {
			con = estabelecerConexao();
			comando = getComando("LISTAR_TIPO_EMPRESA");
			comando.configurar();

			rs = comando.executarConsulta(con);

			while (rs.next()) {
				TipoEmpresaVO vo = new TipoEmpresaVO();
				vo.setCodTipoEmpresa(rs.getShort("CODTIPOEMPRESA"));
				vo.setDescricao(rs.getString("DESTIPOEMPRESA"));
				vo.setValorLimiteInferior(rs.getBigDecimal("VALORLIMITEINFERIORFATURAMENTO"));
				vo.setValorLimiteSuperior(rs.getBigDecimal("VALORLIMITESUPERIORFATURAMENTO"));
				vo.setIsSimplesNacional(rs.getBoolean("BOLHABILITASIMPLESNACIONAL"));
				vo.setPossuiAtivoSuperior(rs.getBoolean("BOLHABILITAPOSSUIATIVO"));
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
