/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.replicacao.persistencia.dao.CAPESReplicacaoComumDao;
import br.com.sicoob.capes.replicacao.persistencia.dao.ReplicacaoCadastroDAO;

/**
 * DAO para a replicação do cadastro de uma pessoa.
 * 
 * @author Erico.Junior
 */
public class ReplicacaoCadastroDAOImpl extends CAPESReplicacaoComumDao implements ReplicacaoCadastroDAO {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer replicarCadastro(Integer numPessoaOrigem, Integer numCooperativaOrigem, Integer numCooperativaDestino) throws BancoobException {
		Integer idPessoaLegado = null;
		Comando comando = null;
		Connection conx = null;
		ResultSet rs = null;

		getLogger().info("Iniciando a chamada da spu_ReplicaPessoa: ", String.valueOf(numCooperativaDestino));

		try {

			comando = getComando("SPU_REPLICACAO_PESSOA");
			comando.adicionarVariavel("numPessoaOrigem", numPessoaOrigem);
			comando.adicionarVariavel("numCooperativaOrigem", numCooperativaOrigem);

			comando.configurar();

			getLogger().info("SQL spu_ReplicaPessoa: ", comando.getSqlEmUso(), "(", String.valueOf(numPessoaOrigem), ", ", String.valueOf(numCooperativaOrigem), ")");

			conx = estabelecerConexao(numCooperativaDestino);
			rs = comando.executarStoredProcedure(conx);

			if (rs.next()) {
				idPessoaLegado = rs.getInt(1);
			}

			getLogger().info("RESULTADO spu_ReplicaPessoa: ", String.valueOf(idPessoaLegado));
		} catch (SQLException e) {
			getLogger().erro(e, "Não foi possível replicar o cadastro da pessoa");
			throw new PersistenciaException(e);
		} finally {
			fecharResultSet(rs);
			fecharComando(comando);
			fecharConexao(conx);
		}

		return idPessoaLegado;
	}
	
	@Override
	public void replicarCadastroAssincrono(Integer numPessoaOrigem, Integer numCooperativaOrigem, Integer numCooperativaDestino) throws BancoobException {
		Comando comando = null;
		Connection conx = null;
		ResultSet rs = null;

		getLogger().info("Iniciando a chamada da spu_ReplicaPessoaAssinc: ", String.valueOf(numCooperativaDestino));

		try {
			comando = getComando("SPU_REPLICACAO_PESSOA_ASSINCRONA");
			comando.adicionarVariavel("numPessoaOrigem", numPessoaOrigem);
			comando.adicionarVariavel("numCooperativaOrigem", numCooperativaOrigem);

			comando.configurar();

			getLogger().info("SQL spu_ReplicaPessoaAssinc: ", comando.getSqlEmUso(), "(", String.valueOf(numPessoaOrigem), ", ", String.valueOf(numCooperativaOrigem), ")");

			conx = estabelecerConexao(numCooperativaDestino);
			rs = comando.executarStoredProcedure(conx);

			getLogger().info("spu_ReplicaPessoaAssinc executada com sucesso");
		} finally {
			fecharResultSet(rs);
			fecharComando(comando);
			fecharConexao(conx);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void atualizarPessoaAlteracaoGrupo(Integer idPessoaOrigem, Integer idPessoaDestino,
			Integer numeroCoopOrigem, Integer numeroCoopDestino) throws BancoobException {
		
		Comando comando = null;
		Connection conn = null;
		
		try {
			comando = getComando("SPU_ATUALIZAR_PESSOA");
			
			// na SP o destino é a origem da informação
			comando.adicionarVariavel("numeroCoopOrigem", numeroCoopDestino);
			comando.adicionarVariavel("numPessoaOrigem", idPessoaDestino);
			comando.adicionarVariavel("numPessoaDestino", idPessoaOrigem);
			comando.configurar();
			
			conn = estabelecerConexao(numeroCoopOrigem);
			comando.executarStoredProcedure(conn);
		} finally {
			fecharConexao(conn);
			fecharComando(comando);
		}
	}
}
