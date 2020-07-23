package br.com.sicoob.capes.api.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.api.negocio.vo.TipoEmailVO;
import br.com.sicoob.capes.api.persistencia.dao.TipoEmailDAO;

public class TipoEmailDAOImpl extends CAPESApiDAO<TipoEmailVO> implements TipoEmailDAO {

	public List<TipoEmailVO> listar() throws BancoobException {

		Connection con = null;
		ResultSet rset = null;
		Comando comando = null;
		List<TipoEmailVO> retorno = new ArrayList<TipoEmailVO>();

		try {
			con = estabelecerConexao();
			comando = getComando("LISTAR_TIPO_EMAIL");
			comando.configurar();

			rset = comando.executarConsulta(con);

			while (rset.next()) {
				TipoEmailVO vo = new TipoEmailVO();
				vo.setCodTipoEmail(rset.getShort("CODTIPOEMAIL"));
				vo.setDescTipoEmail(rset.getString("DESCTIPOEMAIL"));
				retorno.add(vo);
			}
		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		} finally {
			fecharResultSet(rset);
			fecharConexao(con);
			fecharComando(comando);
		}

		return retorno;
	}

}
