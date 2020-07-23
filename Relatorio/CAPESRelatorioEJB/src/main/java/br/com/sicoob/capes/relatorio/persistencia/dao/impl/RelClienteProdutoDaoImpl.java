package br.com.sicoob.capes.relatorio.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.relatorio.negocio.dto.RelClienteProdutoProxy;
import br.com.sicoob.capes.relatorio.persistencia.dao.RelClienteProdutoDao;

public class RelClienteProdutoDaoImpl extends CAPESRelatorioDao implements RelClienteProdutoDao {

	private static final String COMANDO_EXECUTAR_RELCLIENTEPRODUTO = "EXECUTAR_RELCLIENTEPRODUTO";

	@Override
	public List<RelClienteProdutoProxy> obterDadosRelatorio(Map<String, Object> parametros) throws BancoobException {

		Comando comando = getComando(COMANDO_EXECUTAR_RELCLIENTEPRODUTO);
		comando.adicionarVariavel("objParametro", parametros);
		Connection conexao = estabelecerConexao((Integer) parametros.get("pNumCooperativa"));
		comando.configurar();
		ResultSet rs = null;
		
		try {
			List<RelClienteProdutoProxy> listaRetorno = new ArrayList<RelClienteProdutoProxy>();
			rs = comando.executarConsulta(conexao);

			while (rs.next()) {
				RelClienteProdutoProxy proxy = new RelClienteProdutoProxy();
				proxy.setCodCliente(Integer.parseInt(rs.getString("CodCliente")));
				proxy.setNomeRazao(rs.getString("NomeRazao"));
				proxy.setTipoPessoa(rs.getString("TipoPessoa"));
				proxy.setContaCapital(rs.getString("ContaCapital"));
				proxy.setContaCorrente(rs.getString("ContaCorrente"));
				proxy.setAplicação(rs.getString("Aplicacoes"));
				proxy.setPoupanca(rs.getString("Poupanca"));
				proxy.setOperacaoCredito(rs.getString("OperacaoCredito"));
				proxy.setCartaoCredito(rs.getString("CartaoCredito"));
				proxy.setDebitoAutomatico(rs.getString("DebitoAutomatico"));

				listaRetorno.add(proxy);
			}

			return listaRetorno;

		} catch (Exception e) {
			getLogger().info("Erro ao executa a sp: SPU_REL_CLIENTE_PRODUTO: " + e);
			throw new BancoobException(e);
		} finally {
			fecharResultSet(rs);
			fecharConexao(conexao);
			fecharComando(comando);
		}

	}
}
