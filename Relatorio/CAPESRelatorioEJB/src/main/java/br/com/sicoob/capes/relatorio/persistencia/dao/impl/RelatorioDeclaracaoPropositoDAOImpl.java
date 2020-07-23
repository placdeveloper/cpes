package br.com.sicoob.capes.relatorio.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.relatorio.negocio.vo.RelatorioDeclaracaoPropositoVO;
import br.com.sicoob.capes.relatorio.persistencia.dao.RelatorioDeclaracaoPropositoDAO;

/**
 * A Classe RelatorioDeclaracaoPropositoDAOImpl.
 */
public class RelatorioDeclaracaoPropositoDAOImpl extends CAPESRelatorioDao implements RelatorioDeclaracaoPropositoDAO {
	
	/**
	 * {@inheritDoc}
	 */
	public List<RelatorioDeclaracaoPropositoVO> obterDadosRelatorioDeclaracaoProposito(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		List<RelatorioDeclaracaoPropositoVO> listaRetorno = new ArrayList<RelatorioDeclaracaoPropositoVO>();

		try {
			conexao = estabelecerConexao();
			comando = getComando("RELATORIO_DECLARACAO_PROPOSITO");
			comando.adicionarVariavel("idPessoa", idPessoa);
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.configurar();
			
			getLogger().debug("Executando a consulta do relatorio de declaracao de proposito");
			getLogger().debug(comando.getSqlEmUso());
			
			rs = comando.executarConsulta(conexao);

			while (rs.next()) {
				RelatorioDeclaracaoPropositoVO vo = new RelatorioDeclaracaoPropositoVO();
				
				vo.setNomeInstituicao(rs.getString("DESCNOMECOOP"));                 
				vo.setSiglaInstituicao(rs.getString("DESCSIGLACOOP"));
				vo.setNomeCompleto(rs.getString("NOMEPESSOA"));    
				vo.setCpfCnpj(rs.getString("NUMCPFCNPJ"));         
				vo.setIdentificacao(rs.getString("IDENTIFICACAO"));   
				vo.setTelefone(rs.getString("TELEFONE"));  
				
				listaRetorno.add(vo);
			}
			
		} catch (SQLException e) {
			getLogger().erro(e, "Erro ao executar a consulta do relatorio de declaracao de proposito");
			throw new BancoobException(e);
		} finally {
			fecharResultSet(rs);
			fecharComando(comando);
			fecharConexao(conexao);
		}

		return listaRetorno;
	}

}