package br.com.sicoob.capes.relatorio.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.relatorio.negocio.dto.RelVencimentoCadastroDTO;
import br.com.sicoob.capes.relatorio.negocio.vo.RelVencimentoCadastroVO;
import br.com.sicoob.capes.relatorio.persistencia.dao.RelVencimentoCadastroDAO;

/**
 * A Classe RelVencimentoCadastroDAOImpl.
 */
public class RelVencimentoCadastroDAOImpl extends CAPESRelatorioDao implements RelVencimentoCadastroDAO {

	/**
	 * Instancia um novo RelVencimentoCadastroDAOImpl.
	 */
	public RelVencimentoCadastroDAOImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<RelVencimentoCadastroVO> obterDadosRelatorio(RelVencimentoCadastroDTO proxy) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		List<RelVencimentoCadastroVO> listaRetorno = new ArrayList<RelVencimentoCadastroVO>();

		try {
			conexao = estabelecerConexao();
			comando = getComando("CONSULTAR_DADOS_RELATORIO_VENCIMENTO_CADASTRO");
			comando.adicionarVariavel("objParametros", proxy);
			comando.configurar();

			getLogger().debug("Executando a consulta do relatorio de vencimento de cadastro");
			getLogger().debug(comando.getSqlEmUso());

			rs = comando.executarConsulta(conexao);

			while (rs.next()) {
				RelVencimentoCadastroVO voRetorno = new RelVencimentoCadastroVO();
				voRetorno.setUnidade(rs.getInt("IDUNIDADEINST"));
				voRetorno.setNomeUnidade(rs.getString("DESCNOMECOOP"));
				voRetorno.setCpfCnpj(rs.getString("NUMCPFCNPJFORMATADO"));
				voRetorno.setNome(rs.getString("NOMEPESSOA"));
				Timestamp timeStamp = rs.getTimestamp("DATARENOVACAOCADASTRAL");
				voRetorno.setDataUltimaRenovacao(timeStamp);
				voRetorno.setInstResponsavel(rs.getInt("NUMCOOPERATIVARESP"));
				listaRetorno.add(voRetorno);
			}

		} catch (SQLException e) {
			throw new BancoobException(e);
		} finally {
			fecharResultSet(rs);
			fecharComando(comando);
			fecharConexao(conexao);
		}

		return listaRetorno;

	}
}