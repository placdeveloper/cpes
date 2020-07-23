package br.com.sicoob.capes.relatorio.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.relatorio.negocio.dto.RelatorioCadastroCompartilhadoDTO;
import br.com.sicoob.capes.relatorio.negocio.vo.RelatorioCadastroCompartilhadoVO;
import br.com.sicoob.capes.relatorio.persistencia.dao.RelatorioCadastroCompartilhadoDAO;

/**
 * A Classe RelCompartilhamentoDAOImpl.
 */
public class RelatorioCadastroCompartilhadoDAOImpl extends CAPESRelatorioDao implements RelatorioCadastroCompartilhadoDAO {
	
	private static final String COMANDO_PESQUISA_DADOS_RELATORIO = "CONSULTAR_DADOS_RELATORIO_CADASTRO_COMPARTILHADO";
	
	/**
	 * Instancia um novo RelCompartilhamentoDAOImpl.
	 */
	public RelatorioCadastroCompartilhadoDAOImpl(){
		super();
	}	
	
	/**
	 * {@inheritDoc}
	 */
	public List<RelatorioCadastroCompartilhadoVO> obterDadosRelatorio(
			RelatorioCadastroCompartilhadoDTO proxy) throws BancoobException {
		Comando comando = null;	
		Connection conexao = null;
		ResultSet rs = null;
		
		List<RelatorioCadastroCompartilhadoVO> listaRetorno = new ArrayList<RelatorioCadastroCompartilhadoVO>();
		try {
			conexao = estabelecerConexao();
			comando = getComando(COMANDO_PESQUISA_DADOS_RELATORIO);
			comando.adicionarVariavel("objParametros", proxy);
			comando.configurar();
			rs = comando.executarConsulta(conexao);

			while (rs.next()) {
				RelatorioCadastroCompartilhadoVO voRetorno = new RelatorioCadastroCompartilhadoVO();
				voRetorno.setCentral(rs.getInt("NUMCOOPPAI"));
				voRetorno.setSingular(rs.getInt("NUMCOOPERATIVA"));
				voRetorno.setResponsavel(rs.getString("ISRESPONSAVEL"));
				voRetorno.setDataCadastro(rs.getTimestamp("DATAINCLUSAOSISTEMA"));
				voRetorno.setDataCompartilhamento(rs.getTimestamp("DATAHORAINTEGRACAO"));
				voRetorno.setNomePessoa(rs.getString("NOMEPESSOA"));
				voRetorno.setCpfCnpj(rs.getString("NUMCPFCNPJFORMATADO"));
				voRetorno.setCoopSigla(rs.getString("DESCSIGLACOOP"));
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