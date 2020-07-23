package br.com.sicoob.capes.processamento.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.processamento.negocio.dto.PessoaSFNDTO;
import br.com.sicoob.capes.processamento.persistencia.dao.AtualizacaoDataSFNDao;
import br.com.sicoob.capes.processamento.persistencia.dao.CAPESProcessamentoDao;

/**
 * A Classe AtualizacaoDataSFNDaoImpl.
 */
public class AtualizacaoDataSFNDaoImpl extends CAPESProcessamentoDao implements
		AtualizacaoDataSFNDao {

	/**
	 * {@inheritDoc}
	 */
	public List<PessoaSFNDTO> listarPessoasDataSFNAtualizadas(
			Integer numCooperativa) throws BancoobException {
		
		Connection conx = null;
		ResultSet rs = null;
		Comando comando = null;
		List<PessoaSFNDTO> lista = new ArrayList<PessoaSFNDTO>();

		try {

			conx = estabelecerConexao(numCooperativa);
			comando = getComando("LISTAR_PESSOAS_DATA_SFN_ATUALIZADA");
			comando.configurar();

			rs = comando.executarConsulta(conx);
			PessoaSFNDTO dto = null;

			while (rs.next()) {
				dto = new PessoaSFNDTO();
				dto.setIdPessoa(rs.getInt("numcliente"));
				dto.setDataCadastramentoSFN(new DateTimeDB(rs.getDate("dataCadastramentoSFN").getTime()));
				dto.setIdInstituicao(rs.getInt("idInstituicao"));
				dto.setIdUnidadeInst(rs.getInt("idUnidadeInst"));
				lista.add(dto);
			}

			return lista;
		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		} finally {
			fecharComando(comando);
			fecharResultSet(rs);
			fecharConexao(conx);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void atualizarDataSFN(Integer numCooperativa) throws BancoobException {
		Connection conx = null;
		Comando comando = null;
		try {
			conx = estabelecerConexao(numCooperativa);
			comando = getComando("ATUALIZAR_DATA_SFN");
			comando.configurar();
			
			getLogger().debug(comando.getSqlEmUso());
			
			comando.executarAtualizacao(conx);
		} finally {
			fecharComando(comando);
			fecharConexao(conx);
		}
	}
}