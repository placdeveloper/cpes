/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.negocio.entidades.legado.Cliente;
import br.com.sicoob.capes.replicacao.negocio.dto.AtualizacaoRiscoClienteDTO;
import br.com.sicoob.capes.replicacao.persistencia.dao.ClienteDAO;
import br.com.sicoob.capes.replicacao.persistencia.dao.EntidadeReplicavelDao;

/**
 * Dao utilizada para os clientes.
 * 
 * @author juan.damasceno
 */
public class ClienteDAOImpl extends EntidadeReplicavelDao<Cliente> implements
		ClienteDAO {

	/** A constante COMANDO_ATUALIZAR_RISCO_CLIENTE. */
	private static final String COMANDO_ATUALIZAR_RISCO_CLIENTE = "ATUALIZAR_RISCO_CLIENTE";
	
	/** A constante COMANDO_INCLUIR_HISTORICO_RISCO_CLIENTE. */
	private static final String COMANDO_INCLUIR_HISTORICO_RISCO_CLIENTE = "INCLUIR_HISTORICO_RISCO_CLIENTE";
	
	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_CERTIDAO_PESSOA";
	
	/** A constante NOME_COMANDO_CONSULTAR_IDDB2. */
	private static final String NOME_COMANDO_CONSULTAR_IDDB2 = "CONSULTA_CERTIDAO_POR_ID_DB2";
	
	/** A constante COMANDO_CONSULTAR_CLIENTES_POR_ID. */
	private static final String COMANDO_CONSULTAR_CLIENTES_POR_ID = "CONSULTA_CLIENTES_POR_ID";

	/** A constante ALTERAR_CLIENTES_LOTE. */
	private static final String ALTERAR_CLIENTES_LOTE = "ALTERAR_CLIENTES_LOTE";
	
	/** A constante INCLUIR_CLIENTES_LOTE. */
	private static final String INCLUIR_CLIENTES_LOTE = "INCLUIR_CLIENTES_LOTE";
	
	/**
	 * Instancia um novo ClienteDAOImpl.
	 */
	public ClienteDAOImpl() {
		super(Cliente.class, NOME_COMANDO_PESQUISAR, NOME_COMANDO_CONSULTAR_IDDB2);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Cliente> pesquisarPorListaID(List<Integer> ids) throws BancoobException {
		
		Comando comando = getComando(COMANDO_CONSULTAR_CLIENTES_POR_ID);
		List<Cliente> clientes = null;
		try {
			comando.adicionarVariavel("ids", ids);
			comando.configurar();
			clientes = criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
		return clientes;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void atualizarRiscoCliente(AtualizacaoRiscoClienteDTO dto, Integer numeroCooperativa) throws BancoobException {
		Connection conn = null;
		Comando comando = null;
		
		try {
			conn = estabelecerConexao(numeroCooperativa);
			
			//Inclui o historico do cliente.
			comando = getComando(COMANDO_INCLUIR_HISTORICO_RISCO_CLIENTE);
			comando.adicionarVariavel("numCliente", dto.getNumCliente());
			comando.configurar();
			
			getLogger().debug(comando.getSqlEmUso());
			getLogger().debug("Parametros: [", String.valueOf(dto.getNumCliente()), "]");

			comando.executarAtualizacao(conn);

			//Atualiza o risco do cliente.
			comando = getComando(COMANDO_ATUALIZAR_RISCO_CLIENTE);
			comando.adicionarVariavel("idNivelRisco", dto.getIdNivelRisco());
			comando.adicionarVariavel("motivoRisco", dto.getMotivoRisco());
			comando.adicionarVariavel("numCliente", dto.getNumCliente());
			comando.configurar();

			getLogger().debug(comando.getSqlEmUso());
			getLogger().debug("Parametros: [", dto.getIdNivelRisco(), ", ", dto.getMotivoRisco(), ", ", String.valueOf(dto.getNumCliente()), "]");
			
			int registrosAlterados = comando.executarAtualizacao(conn);
			if (registrosAlterados == 0) {
				throw new NegocioException("O risco do cliente não foi atualizado pois o cliente não foi encontrado no ambiente legado.");
			}
		} finally {
			fecharComando(comando);
			fecharConexao(conn);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void replicarLote(List<Cliente> clientesIncluir, List<Cliente> clientesAlterar,  Integer idInstituicao) throws BancoobException {
		Comando comando = null;
		Connection conn = null;
		
		try {
			comando = getComando(ALTERAR_CLIENTES_LOTE);
			conn = estabelecerConexao(idInstituicao);
			
			List<Object[]> parametros = montaDadosClientesParametrosAlteracao(clientesAlterar);
			
			comando.configurar();
			getLogger().debug(comando.getSqlEmUso());
			
			comando.executarAtualizacaoEmLote(parametros, conn);
			
			if(!clientesIncluir.isEmpty() || !(clientesIncluir == null)) {
				this.replicarLoteInclusao(clientesIncluir, conn);
			}
				
		} finally {
			fecharConexao(conn);
			fecharComando(comando);
		}
		
	}

	/**
	 * Incluir em caso de o cliente nao fora replicado para o legado.
	 * @param clientesIncluir
	 * @param conn
	 */
	private void replicarLoteInclusao(List<Cliente> clientesIncluir, Connection conn) {
		Comando comando = null;
		
		comando = getComando(INCLUIR_CLIENTES_LOTE);
		List<Object[]> parametros = montaDadosClientesParametrosInclusao(clientesIncluir);
		comando.configurar();
		getLogger().debug(comando.getSqlEmUso());
		comando.executarAtualizacaoEmLote(parametros, conn);
	}

	/**
	 * Cria parametros para alteração em lote.
	 * @param clientesAlterar
	 * @return
	 */
	private List<Object[]> montaDadosClientesParametrosInclusao(List<Cliente> clientesAlterar) {
		List<Object[]> listaParametros = new ArrayList<>();
		
		Object[] parametro = new Object[11];
		for (int i = 0; i < clientesAlterar.size(); i++) {
			Cliente cliente = (Cliente) clientesAlterar.get(i);
			
			parametro[0] = cliente.getIdCliente();
			parametro[1] = cliente.getParecerGerencia();
			parametro[2] = cliente.getEmiteAvisoLancamento();
			parametro[3] = cliente.getDataUltimaAtualizacao();
			parametro[4] = cliente.getNumCooperativa();
			parametro[5] = cliente.getNumPac();
			parametro[6] = cliente.getNumNucleo();
			parametro[7] = cliente.getFuncionario().getIdSQL();//duvida
			parametro[8] = cliente.getIdPerfilTarifario();
			parametro[9] = cliente.getDataCadastramentoSFN();
			parametro[10] = cliente.getAutorizaConsulta();
			//parametro[x] = cliente.getPessoa().getIdSQL();
			
			listaParametros.add(parametro);
		}
		
		return listaParametros;
	}
	
	/**
	 * Cria parametros para alteração em lote.
	 * @param clientesAlterar
	 * @return
	 */
	private List<Object[]> montaDadosClientesParametrosAlteracao(List<Cliente> clientesAlterar) {
		List<Object[]> listaParametros = new ArrayList<>();
		
		Object[] parametro = new Object[12];
		for (int i = 0; i < clientesAlterar.size(); i++) {
			Cliente cliente = (Cliente) clientesAlterar.get(i);
			
			parametro[0] = cliente.getIdCliente();
			parametro[1] = cliente.getParecerGerencia();
			parametro[2] = cliente.getEmiteAvisoLancamento();
			parametro[3] = cliente.getDataUltimaAtualizacao();
			parametro[4] = cliente.getNumCooperativa();
			parametro[5] = cliente.getNumPac();
			parametro[6] = cliente.getNumNucleo();
			parametro[7] = cliente.getFuncionario().getIdSQL();//duvida
			parametro[8] = cliente.getIdPerfilTarifario();
			parametro[9] = cliente.getDataCadastramentoSFN();
			parametro[10] = cliente.getAutorizaConsulta();
			parametro[11] = cliente.getPessoa().getIdSQL();
			
			listaParametros.add(parametro);
		}
		
		return listaParametros;
	}
}