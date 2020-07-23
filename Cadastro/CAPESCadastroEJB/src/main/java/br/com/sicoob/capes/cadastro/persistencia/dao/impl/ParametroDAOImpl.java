package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.cadastro.negocio.vo.ParametroVO;
import br.com.sicoob.capes.cadastro.persistencia.dao.ParametroDAO;

/**
 * 
 * @author Paulo.Stoppa
 *
 */
public class ParametroDAOImpl extends CAPESCadastroDao implements ParametroDAO {

	/**
	 * @see ParametroDAO#obterParametro(Integer, Integer)
	 */
	@Override
	public ParametroVO obterParametro(Integer codigo, Integer idInstituicao) {
		Comando comando = null;
		Connection connection = null;
		ResultSet resultSet = null;
		ParametroVO retorno = null;
		try {
			comando = getComando("OBTER_VALOR_PARAMETRO");
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.adicionarVariavel("codigo", codigo);
			comando.configurar();
			connection = estabelecerConexao();
			resultSet = comando.executarConsulta(connection);
			if (resultSet.next()) {
				retorno = new ParametroVO();
				retorno.setCodigo(resultSet.getInt("codparametro"));
				retorno.setDescricao(resultSet.getString("descparametro"));
				retorno.setValor(resultSet.getString("descvalor"));
			}

		} catch (SQLException e) {
			throw new PersistenciaException("msg.erro.obterParametro", e);
		} finally {
			fecharResultSet(resultSet);
			fecharConexao(connection);
			fecharComando(comando);
		}
		return retorno;
	}

}
