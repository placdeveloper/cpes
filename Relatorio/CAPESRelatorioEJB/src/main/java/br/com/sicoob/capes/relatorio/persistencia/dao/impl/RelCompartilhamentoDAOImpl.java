package br.com.sicoob.capes.relatorio.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.relatorio.negocio.dto.RelCompartilhamentoDTO;
import br.com.sicoob.capes.relatorio.negocio.vo.RelCompartilhamentoVO;
import br.com.sicoob.capes.relatorio.persistencia.dao.RelCompartilhamentoDAO;

/**
 * A Classe RelCompartilhamentoDAOImpl.
 */
public class RelCompartilhamentoDAOImpl extends CAPESRelatorioDao implements RelCompartilhamentoDAO {
	
	/**
	 * Instancia um novo RelCompartilhamentoDAOImpl.
	 */
	public RelCompartilhamentoDAOImpl(){
		super();
	}	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RelCompartilhamentoVO> obterDadosRelatorio(RelCompartilhamentoDTO proxy) throws BancoobException {
		
		Comando comando = null;	
		Connection conexao = null;
		ResultSet rs = null;
		List<RelCompartilhamentoVO> listaRetorno = new ArrayList<RelCompartilhamentoVO>();
		try {
			conexao = estabelecerConexao();
			comando = getComando("CONSULTAR_DADOS_RELATORIO_COMPARTILHAMENTO");
			comando.adicionarVariavel("datainicio", proxy.getDataInicio());
			comando.adicionarVariavel("datafim", proxy.getDataFim());
			comando.adicionarVariavel("numsingular", proxy.getNumSingular());
			comando.adicionarVariavel("numcentral", proxy.getNumCentral());
			comando.adicionarVariavel("compartilhamento", proxy.getCompartilhamento());
			comando.configurar();
			rs = comando.executarConsulta(conexao);

			while (rs.next()) {
				RelCompartilhamentoVO voRetorno = new RelCompartilhamentoVO();
				voRetorno.setCentral(rs.getInt("NUMCOOPPAIRESP"));
				voRetorno.setResponsavel(rs.getInt("NUMCOOPERATIVARESP"));
				voRetorno.setSolicitante(rs.getInt("NUMCOOPERATIVADEMANDANTE"));
				voRetorno.setUsuario(rs.getString("IDUSUARIOCONSULTA"));
				Timestamp timeStamp = rs.getTimestamp("DATAHORACONSULTA");
				voRetorno.setDataHora(timeStamp);
				voRetorno.setCpfCnpjCadastroCompartilhado(rs.getString("NUMCPFCNPJFORMATADO"));
				voRetorno.setNomeCadastroCompartilhado(rs.getString("NOMEPESSOA"));

				Integer compartilhado = rs.getInt("BOLCOMPARTILHADO");
				if (compartilhado == 0) {
					voRetorno.setCompartilhado("NÃO");
				} else if (compartilhado == 1) {
					voRetorno.setCompartilhado("SIM");
				}
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