/**
 * 
 */
package br.com.sicoob.capes.integracao.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.comum.negocio.vo.LocalidadeVO;
import br.com.sicoob.capes.integracao.persistencia.dao.LocalidadeDAO;

public class LocalidadeDAOImpl extends CAPESIntegracaoDao implements LocalidadeDAO {

	private static final String LISTAR_LOCALIDADE = "LISTAR_LOCALIDADE";

	@Override
	public List<LocalidadeVO> listarLocalidade(Integer cooperativa) throws BancoobException {
		
		Comando comando = getComando(LISTAR_LOCALIDADE);
		Connection conexao = estabelecerConexao(cooperativa);
		comando.configurar();
		ResultSet rs = null;
		
		try {
			List<LocalidadeVO> listaRetorno = new ArrayList<LocalidadeVO>();
			rs = comando.executarConsulta(conexao);

			while (rs.next()) {
				LocalidadeVO localidade = new LocalidadeVO();
				
				localidade.setIdLocalidade(Integer.parseInt(rs.getString("idMunicipioRecor")));
				localidade.setNomeLocalidade(rs.getString("descMunicipio"));
				localidade.setSiglaUF(rs.getString("uf"));

				listaRetorno.add(localidade);
			}

			return listaRetorno;

		} catch (Exception e) {
			getLogger().info("Erro ao obter localidades no SqlServer: " + e);
			throw new BancoobException(e);
		} finally {
			fecharResultSet(rs);
			fecharConexao(conexao);
			fecharComando(comando);
		}
	}

}
