package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.propriedades.RepositorioPropriedades;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.negocio.entidades.legado.BancoServidor;
import br.com.sicoob.capes.negocio.entidades.legado.TransicaoReplicacao;
import br.com.sicoob.capes.replicacao.negocio.excecao.ModeloDeDadosInvalidoException;
import br.com.sicoob.capes.replicacao.persistencia.dao.CAPESServicoReplicacaoCrudDao;
import br.com.sicoob.capes.replicacao.persistencia.dao.TransicaoReplicacaoDao;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;

/**
 * Classe que implementa a persistencia de TransicaoReplicacao.
 * @author Juan.Damasceno
 *
 */
public class TransicaoReplicacaoDaoImpl extends CAPESServicoReplicacaoCrudDao<TransicaoReplicacao>
		implements TransicaoReplicacaoDao {
	
	/** A constante ERROR_CODE_NOME_OBJETO_INVALIDO. */
	private static final int ERROR_CODE_NOME_OBJETO_INVALIDO = 208;
	
	/** A constante NOME_COMANDO_REMOVER_TRANSICOES_REPLICADAS. */
	private static final String NOME_COMANDO_REMOVER_TRANSICOES_REPLICADAS = "REMOVE_TRANSICOES_REPLICADAS";
	
	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "CONSULTA_TRANSICAO_REPLICACAO";
	
	/** A constante NOME_COMANDO_CENTRALIZA_TRANSICAO_PESSOA. */
	private static final String NOME_COMANDO_CENTRALIZA_TRANSICAO_PESSOA = "CENTRALIZA_TRANSICAO_PESSOA";
	
	/** A constante NOME_COMANDO_CENTRALIZA_TRANSICAO_PESSOA_SEM_SISTEMA_COOPERATIVO. */
	private static final String NOME_COMANDO_CENTRALIZA_TRANSICAO_PESSOA_SEM_SISTEMA_COOPERATIVO = "CENTRALIZA_TRANSICAO_PESSOA_SEM_SISTEMA_COOPERATIVO";
	
	/** A constante NOME_COMANDO_REMOVE_TRANSICAO_REPLICACAO_CENTRALIZADA. */
	private static final String NOME_COMANDO_REMOVE_TRANSICAO_REPLICACAO_CENTRALIZADA = "REMOVE_TRANSICAO_REPLICACAO_CENTRALIZADA";
	
	/** A constante NOME_COMANDO_MARCA_TRANSICAO_REPLICACAO_CENTRALIZADA. */
	private static final String NOME_COMANDO_MARCA_TRANSICAO_REPLICACAO_CENTRALIZADA = "MARCA_TRANSICAO_REPLICACAO_CENTRALIZADA";
	
	/** O atributo logger. */
	private ISicoobLogger logger = getLogger();

	/**
	 * Construtor do DAO.
	 */
	public TransicaoReplicacaoDaoImpl() {
		super(TransicaoReplicacao.class, NOME_COMANDO_PESQUISAR);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removerReplicados(Date data) throws BancoobException {
		Comando comando = getComando(NOME_COMANDO_REMOVER_TRANSICOES_REPLICADAS);
		comando.adicionarVariavel("data",data);
		comando.configurar();
		
		criarQuery(comando).executeUpdate();
		fecharComando(comando);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TransicaoReplicacao> listarNaoReplicados() {
		getLogger().info("Iniciando a listagem dos nao replicados.");

		ConsultaDto<TransicaoReplicacao> consultaDto = new ConsultaDto<TransicaoReplicacao>();
		consultaDto.setPagina(0);
		consultaDto.setTamanhoPagina(getTamanhoPagina());
		consultaDto.setOrdenacao("dataAtualizacao");
		
		getLogger().debug("EntityManager utilizado para a consulta de nao replicados: ", getNomeDatasource(), " ", String.valueOf(getEntityManager()));

		List<TransicaoReplicacao> pesquisarLista = pesquisarLista(TransicaoReplicacao.class, consultaDto, NOME_COMANDO_PESQUISAR);
		logger.debug("Capes listarNaoReplicados - " + pesquisarLista.size());
		return pesquisarLista;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int centralizaTransicoesReplicacao(BancoServidor bancoServidor) {

		Comando comando = getComando(NOME_COMANDO_CENTRALIZA_TRANSICAO_PESSOA);
		comando.adicionarVariavel("codGrupoCompartilhamento",bancoServidor.getCodGrupoCompartilhamento());
		comando.adicionarVariavel("idInstituicao",bancoServidor.getIdInstituicao());
		
		return executaComandoBancoRemoto(bancoServidor, comando);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int centralizaTransicoesReplicacaoSemSistemaCooperativo(BancoServidor bancoServidor) {

		Comando comando = getComando(NOME_COMANDO_CENTRALIZA_TRANSICAO_PESSOA_SEM_SISTEMA_COOPERATIVO);
		comando.adicionarVariavel("numCooperativa", bancoServidor.getNumCooperativa());

		return executaComandoBancoRemoto(bancoServidor, comando);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int marcaTransicoesReplicacaoParaCentralizacao(BancoServidor bancoServidor) {

		Comando comando = getComando(NOME_COMANDO_MARCA_TRANSICAO_REPLICACAO_CENTRALIZADA);

		return executaComandoBancoRemoto(bancoServidor, comando);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int removeTransicoesReplicacaoCentralizadas(BancoServidor bancoServidor) {

		Comando comando = getComando(NOME_COMANDO_REMOVE_TRANSICAO_REPLICACAO_CENTRALIZADA);

		return executaComandoBancoRemoto(bancoServidor, comando);
	}
	
	/**
	 * Executa um comando no banco de dados passado como parâmetro.
	 * @param bancoServidor o banco onde o comando será executado.
	 * @param comando O comando que será executado.
	 * @return a quantidade de registros afetados.
	 */
	private int executaComandoBancoRemoto(BancoServidor bancoServidor, Comando comando) {
		Connection conx = null;
		int quantidadeRegistros = 0;
		PreparedStatement statement = null;
		
		try {
			
			conx = estabelecerConexao();
			
			comando.adicionarVariavel("nomeServidor", bancoServidor.getNomeServer());
			comando.adicionarVariavel("nomeBancoDados", bancoServidor.getNomeBancoDados());
			comando.configurar();
			
			final String sqlEmUso = comando.getSqlEmUso();
			logger.debug("SQL gerado >>>" + sqlEmUso);
			
			statement = conx.prepareStatement(sqlEmUso);
			
			quantidadeRegistros = statement.executeUpdate();
			
			logger.info(quantidadeRegistros + " foram afetados do banco " + bancoServidor);
			
		} catch (SQLException excecao) {
			tratarExcecao(excecao);
		} finally {
			fecharComando(comando);
			fecharConexao(conx);
			fecharPreparedStatement(statement);
		}
		return quantidadeRegistros;
	}
	
	/**
	 * Trata a exceção.
	 * @param excecao
	 */
	private void tratarExcecao(Exception excecao) {
		
		if (excecao instanceof SQLException) {
			SQLException sqlException = (SQLException) excecao;
			if (sqlException.getErrorCode() == ERROR_CODE_NOME_OBJETO_INVALIDO) {
				throw new ModeloDeDadosInvalidoException(excecao); 
			}
		}
		throw new PersistenciaException(excecao);
	}
	
	/**
	 * Busca no arquivo de propriedades a quantidade de registros por pagina da pesquisa das 
	 * TransicaoReplicacao não replicadas.
	 * 
	 * @return a quantidade de registros por pagina da pesquisa das TransicaoReplicacao 
	 * não replicadas.
	 */
	private Integer getTamanhoPagina() {
		
		String tamanhoPagina = 
			getProperties().getProperty("pesquisa.transicoes.nao.replicadas.registros.por.pagina");
		return Integer.valueOf(tamanhoPagina);
	}
	
	/**
	 * Retorna uma instancia de Properties, contendo as propriedades da aplicação. 
	 * @return uma instancia de Properties, contendo as propriedades da aplicação.
	 */
	private Properties getProperties() {
		return RepositorioPropriedades.getInstance()
			.recuperarPropriedades("capes.servico.replicacao.propriedades");
	}
	
	/**
	 * O método Fechar prepared statement.
	 *
	 * @param pstm o valor de pstm
	 */
	private void fecharPreparedStatement(PreparedStatement pstm) {
		if (pstm != null) {
			try {
				pstm.close();
			} catch (SQLException e) {
				getLogger().erro(e, "Erro ao fechar o PreparedStatement");
			}
		}
	}

}
