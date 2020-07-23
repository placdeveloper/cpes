package br.com.sicoob.capes.relatorio.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.relatorio.negocio.vo.FormularioVisitaVO;
import br.com.sicoob.capes.relatorio.persistencia.dao.RelFormularioVisitaDAO;

public class RelFormularioVisitaDAOImpl extends CAPESRelatorioDao implements RelFormularioVisitaDAO {

	@Override
	public FormularioVisitaVO obterDadosFormularioVisitaPF(Long idPessoaCompartilhamento) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet resultSet = null;
		FormularioVisitaVO vo = new FormularioVisitaVO();
		
		try {
			conexao = estabelecerConexao();
			comando = getComando("CONSULTA_PESSOA_FORMULARIO_VISITA_PF");
			comando.adicionarVariavel("idPessoaCompartilhamento", idPessoaCompartilhamento);
			comando.configurar();
			resultSet = comando.executarConsulta(conexao);
			
			while(resultSet.next()){
				vo.setCpfCnpj(resultSet.getString("CPF"));
				vo.setEndereco(concatenaEndereco(resultSet));
				vo.setNomeCompleto(resultSet.getString("NOME"));
				vo.setPoliticamenteExposta(resultSet.getBoolean("ANOTACAO"));
				vo.setProfissao(resultSet.getString("PROFISSAO"));
				vo.setTelefone(resultSet.getString("TELEFONE"));
				vo.setCodTipoPessoa((short) 0);
			}
			
		} catch (SQLException e) {
			throw new BancoobException(e);
		}finally{
			fecharResultSet(resultSet);
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return vo;
	}

	@Override
	public FormularioVisitaVO obterDadosFormularioVisitaPJ(Long idPessoaCompartilhamento) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet resultSet = null;
		FormularioVisitaVO vo = new FormularioVisitaVO();
		
		try {
			conexao = estabelecerConexao();
			comando = getComando("CONSULTA_PESSOA_FORMULARIO_VISITA_PJ");
			comando.adicionarVariavel("idPessoaCompartilhamento", idPessoaCompartilhamento);
			comando.configurar();
			resultSet = comando.executarConsulta(conexao);
			
			while(resultSet.next()){
				vo.setCpfCnpj(resultSet.getString("CNPJ"));
				vo.setEndereco(concatenaEndereco(resultSet));
				vo.setRazaoSocial(resultSet.getString("RAZAOSOCIAL"));
				vo.setDescricaoAtividade(resultSet.getString("CODCNAE"));
				vo.setTelefone(resultSet.getString("TELEFONE"));
				vo.setCodTipoPessoa((short) 1);
			}
			
		} catch (SQLException e) {
			throw new BancoobException(e);
		}finally{
			fecharResultSet(resultSet);
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return vo;
	}
	
	private String concatenaEndereco(ResultSet resultSet) throws SQLException {
		StringBuilder sb = new StringBuilder();
		
		if(resultSet.getString("DESCLOGRADOURO") != null && 
				resultSet.getString("DESCLOGRADOURO").trim().equals("")){
			sb.append(resultSet.getString("DESCLOGRADOURO"));
			sb.append(", ");
		}
		
		if(resultSet.getString("DESCNUMERO")!= null && 
				resultSet.getString("DESCNUMERO").trim().equals("")){
			
			sb.append(resultSet.getString("DESCNUMERO"));
			sb.append(", ");
		}
		
		if(resultSet.getString("DESCCOMPLEMENTO")!= null &&
				resultSet.getString("DESCCOMPLEMENTO").trim().equals("")){
			
			sb.append(resultSet.getString("DESCCOMPLEMENTO"));
			sb.append(", ");
		}
		
		if(resultSet.getString("NOMEBAIRRO")!= null &&
				resultSet.getString("NOMEBAIRRO").trim().equals("")){
			
			sb.append(resultSet.getString("NOMEBAIRRO"));
			sb.append(", ");
		}
		
		if(resultSet.getString("NOMELOCALIDADE")!= null &&
				resultSet.getString("NOMELOCALIDADE").trim().equals("")){
			
			sb.append(resultSet.getString("NOMELOCALIDADE"));
			sb.append(", ");
		}
		
		if(resultSet.getString("SIGLAUF")!= null &&
				resultSet.getString("SIGLAUF").trim().equals("")){
			
			sb.append(resultSet.getString("SIGLAUF"));
			sb.append(", ");
		}
		
		if(resultSet.getString("CODCEP")!= null &&
				resultSet.getString("CODCEP").trim().equals("")){
			
			sb.append(resultSet.getString("CODCEP"));
			sb.append(", ");
		}
		
		return sb.toString();
	}


}
