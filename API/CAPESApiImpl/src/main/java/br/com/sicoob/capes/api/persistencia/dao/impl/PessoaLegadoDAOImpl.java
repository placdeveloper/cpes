package br.com.sicoob.capes.api.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.api.persistencia.dao.PessoaLegadoDAO;

public class PessoaLegadoDAOImpl extends CAPESApiLegadoDAO implements PessoaLegadoDAO {

	public byte[] obterAssinatura(Integer idPessoaLegado, Integer numeroCooperativa) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;

		try {
			comando = getComando("OBTER_ASSINATURA_PESSOA");
			comando.adicionarVariavel("idPessoaLegado", idPessoaLegado);
			comando.configurar();

			conexao = estabelecerConexao(numeroCooperativa);
			rs = comando.executarConsulta(conexao);

			if (rs.next()) {
				return rs.getBytes("ASSINATURAPESSOA");
			}

		} catch (SQLException e) {
			getLogger().erro(e, e.getMessage());
			throw new PersistenciaException(e);
		} catch (PersistenciaException e) {
			getLogger().erro(e, e.getMessage());
			throw e;
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return null;
	}

	public byte[] obterImagem(Integer idPessoaLegado, Integer numeroCooperativa) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;

		try {
			comando = getComando("OBTER_IMAGEM_PESSOA");
			comando.adicionarVariavel("idPessoaLegado", idPessoaLegado);
			comando.configurar();

			conexao = estabelecerConexao(numeroCooperativa);
			rs = comando.executarConsulta(conexao);

			if (rs.next()) {
				return rs.getBytes("FOTOPESSOA");
			}

		} catch (SQLException e) {
			getLogger().erro(e, e.getMessage());
			throw new PersistenciaException(e);
		} catch (PersistenciaException e) {
			getLogger().erro(e, e.getMessage());
			throw e;
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return null;
	}

}