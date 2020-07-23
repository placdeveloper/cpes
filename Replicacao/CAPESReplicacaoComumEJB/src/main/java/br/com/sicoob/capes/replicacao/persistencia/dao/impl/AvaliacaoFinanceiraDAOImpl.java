/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import java.sql.Connection;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.negocio.entidades.legado.AvaliacaoFinanceira;
import br.com.sicoob.capes.replicacao.persistencia.dao.AvaliacaoFinanceiraDAO;
import br.com.sicoob.capes.replicacao.persistencia.dao.EntidadeReplicavelDao;

/**
 * @author Erico.Junior
 * 
 */
public class AvaliacaoFinanceiraDAOImpl extends
		EntidadeReplicavelDao<AvaliacaoFinanceira> implements
		AvaliacaoFinanceiraDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "";
	
	/** A constante COMANDO_ATUALIZAR_HISTORICO_AVALIACAO_FINANCEIRA. */
	private static final String COMANDO_ATUALIZAR_HISTORICO_AVALIACAO_FINANCEIRA = "ATUALIZAR_HISTORICO_AVALIACAO_FINANCEIRA";

	/**
	 * Instancia um novo AvaliacaoFinanceiraDAOImpl.
	 */
	public AvaliacaoFinanceiraDAOImpl() {
		super(AvaliacaoFinanceira.class, NOME_COMANDO_PESQUISAR, null);
	}

	/**
	 * {@inheritDoc}
	 */
	public void atualizarHistorico(Integer numPessoa, Integer numeroCooperativa) throws BancoobException {
		Connection conn = null;
		Comando comando = getComando(COMANDO_ATUALIZAR_HISTORICO_AVALIACAO_FINANCEIRA);
		try {
			comando.adicionarVariavel("numPessoa", numPessoa);
			comando.configurar();
			
			conn = estabelecerConexao(numeroCooperativa);
			
			comando.executarAtualizacao(conn);
			
			getLogger().debug(comando.getSqlEmUso());
			getLogger().debug("Parametros: [numPessoa - " + numPessoa + ", numeroCooperativa - " + numeroCooperativa + "]");
		} finally {
			fecharComando(comando);
			fecharConexao(conn);
		}
	}

}