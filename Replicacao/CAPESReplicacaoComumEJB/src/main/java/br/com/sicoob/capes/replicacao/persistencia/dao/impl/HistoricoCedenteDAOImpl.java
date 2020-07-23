/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import java.sql.Connection;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.replicacao.negocio.dto.HistoricoCedenteDTO;
import br.com.sicoob.capes.replicacao.persistencia.dao.CAPESReplicacaoComumDao;
import br.com.sicoob.capes.replicacao.persistencia.dao.HistoricoCedenteDAO;

/**
 * @author erico.junior
 * 
 */
public class HistoricoCedenteDAOImpl extends CAPESReplicacaoComumDao
		implements HistoricoCedenteDAO {

	/** A constante TIPO_OPERACAO_ALTERACAO. */
	private static final int TIPO_OPERACAO_ALTERACAO = 2;
	
	/** A constante TIPO_REGISTRO_CLIENTE. */
	private static final int TIPO_REGISTRO_CLIENTE = 12;
	
	/** A constante NUM_PAC. */
	private static final int NUM_PAC = 0;

	/**
	 * {@inheritDoc}
	 */
	public void incluirHistoricoCendente(HistoricoCedenteDTO dto, Integer numCooperativa)
			throws BancoobException {
		
		Comando comando = null;
		Connection conx = null;
		
		try {
		
			comando = getComando("SPU_HISTORICO_CEDENTE");
			comando.adicionarVariavel("numCooperativa", numCooperativa);
			comando.adicionarVariavel("numPac", NUM_PAC);
			comando.adicionarVariavel("identNegocio", dto.getNumCliente());
			comando.adicionarVariavel("tipoRegistro", TIPO_REGISTRO_CLIENTE);
			comando.adicionarVariavel("tipoOperacao", TIPO_OPERACAO_ALTERACAO);
			comando.adicionarVariavel("numCliente", dto.getNumCliente());
			
			comando.configurar();
			
			conx = estabelecerConexao(numCooperativa);
			comando.executarStoredProcedure(conx);
			
		} finally {
			fecharComando(comando);
			fecharConexao(conx);
		}
		
	}

}
