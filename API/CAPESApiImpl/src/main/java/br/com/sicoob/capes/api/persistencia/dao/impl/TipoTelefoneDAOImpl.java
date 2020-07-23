package br.com.sicoob.capes.api.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.api.negocio.vo.TipoTelefoneVO;
import br.com.sicoob.capes.api.persistencia.dao.TipoTelefoneDAO;

public class TipoTelefoneDAOImpl extends CAPESApiDAO<TipoTelefoneVO> implements TipoTelefoneDAO {

	public List<TipoTelefoneVO> listar() throws BancoobException {

		Connection con = null;
		ResultSet rset = null;
		Comando comando = null;
		List<TipoTelefoneVO> retorno = new ArrayList<TipoTelefoneVO>();
		try {
			con = estabelecerConexao();
			comando = getComando("LISTAR_TIPO_TELEFONE");
			comando.configurar();

			rset = comando.executarConsulta(con);

			while (rset.next()) {
				TipoTelefoneVO vo = new TipoTelefoneVO();
				vo.setCodTipoTelefone(rset.getShort("CODTIPOTELEFONE"));
				vo.setDescTipoTelefone(rset.getString("DESCTIPOTELEFONE"));
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
