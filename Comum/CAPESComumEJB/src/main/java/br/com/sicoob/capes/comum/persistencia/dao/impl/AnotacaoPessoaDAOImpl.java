/*
 * SICOOB
 * 
 * AnotacaoPessoaDAOImpl.java(br.com.sicoob.capes.comum.persistencia.dao.impl.AnotacaoPessoaDAOImpl)
 */
package br.com.sicoob.capes.comum.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.comum.persistencia.dao.AnotacaoPessoaDAO;
import br.com.sicoob.capes.comum.persistencia.dao.CAPESComumDao;

/**
 * The Class AnotacaoPessoaDAOImpl.
 */
public class AnotacaoPessoaDAOImpl extends CAPESComumDao implements AnotacaoPessoaDAO {

	/** 
	 * {@inheritDoc}
	 */
	public List<Map<String, Object>> obterVigentesPorPessoaInstituicaoTipo(Integer idPessoa, String cpfCnpj,
			Integer idInstituicao, Integer idTipoAnotacao) throws BancoobException {

		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		Comando comando = null;// NOPMD
		Connection conn = null;// NOPMD
		ResultSet rSet = null;// NOPMD
		try {
			comando = getComando("PESQUISAR_ANOTACOES_VIGENTES_PESSOA");
			comando.adicionarVariavel("idPessoa", idPessoa);
			comando.adicionarVariavel("cpfCnpj", cpfCnpj);
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.adicionarVariavel("idTipoAnotacao", idTipoAnotacao);
			comando.configurar();
			
			getLogger().debug(comando.getSqlEmUso());

			conn = estabelecerConexao();
			rSet = comando.executarConsulta(conn);
			while (rSet.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("idAnotacao", rSet.getLong("idAnotacao"));
				map.put("quantidade", rSet.getShort("quantidade"));
				map.put("valor", rSet.getBigDecimal("valor"));
				map.put("observacao", rSet.getString("observacao"));
				map.put("flexibilidade", rSet.getBoolean("flexibilidade"));
				map.put("codigoTipoAnotacao", rSet.getInt("codigoTipoAnotacao"));
				map.put("descricaoTipoAnotacao", rSet.getString("descricaoTipoAnotacao"));
				map.put("codigoCategoriaAnotacao", rSet.getShort("codigoCategoriaAnotacao"));
				map.put("descricaoCategoriaAnotacao", rSet.getString("descricaoCategoriaAnotacao"));
				map.put("codigoOrigemInfo", rSet.getShort("codigoOrigemInfo"));
				map.put("descricaoOrigemInfo", rSet.getString("descricaoOrigemInfo"));
				map.put("dataHoraAnotacao", new Date(rSet.getTimestamp("dataHoraAnotacao").getTime()));
				map.put("dataHoraOcorrencia", new Date(rSet.getTimestamp("dataHoraOcorrencia").getTime()));
				result.add(map);
				map = null; // garbage collector
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharResultSet(rSet);
			fecharComando(comando);
			fecharConexao(conn);
		}
		return result;
	}

}
