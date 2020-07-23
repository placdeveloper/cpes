package br.com.sicoob.capes.api.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.api.negocio.vo.TipoEnderecoVO;
import br.com.sicoob.capes.api.persistencia.dao.TipoEnderecoDAO;

public class TipoEnderecoDAOImpl extends CAPESApiDAO<TipoEnderecoVO> implements TipoEnderecoDAO {

	public List<TipoEnderecoVO> listar() throws BancoobException {

		Connection con = null;
		ResultSet rs = null;
		Comando comando = null;
		List<TipoEnderecoVO> retorno = new ArrayList<TipoEnderecoVO>();

		try {
			con = estabelecerConexao();
			comando = getComando("LISTAR_TIPO_ENDERECO");
			comando.configurar();

			rs = comando.executarConsulta(con);

			while (rs.next()) {
				TipoEnderecoVO vo = new TipoEnderecoVO();
				vo.setCodTipoEndereco(rs.getShort("CODTIPOENDERECO"));
				vo.setDescTipoEndereco(rs.getString("DESCTIPOENDERECO"));
				vo.setIdTipoPessoaContato(rs.getShort("IDTIPOPESSOACONTATO"));
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
