package br.com.sicoob.capes.replicacao.persistencia.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.dao.BancoobCrudDao;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidadeBase;
import br.com.sicoob.capes.replicacao.persistencia.CAPESServicoReplicacaoDatasource;

/**
 * Classe padrao com a infraestrutura de acesso a base de dados do sistema ServicoReplicacaoCadastroUnicoClientes
 * 
 * @author Jarbasjstefanini
 */
public abstract class CAPESServicoReplicacaoCrudDao<T extends CAPESEntidadeBase<?>>
		extends BancoobCrudDao<T> implements CAPESServicoReplicacaoCrudDaoIF<T> {

	/** Nome da coleção de comandos do sistema cadastro único de clientes. */
	private static final String NOME_COLECAO_COMANDOS = "COMANDOS_SERVICO_REPLICACAO_CADASTRO_UNICO_CLIENTE";

	/** Caminho para o arquivo de queries do Cadastro único de clientes. */
	private static final String ARQUIVO_QUERIES = "capes.servico.replicacao.queries.xml";
	
	/** Caminho para o arquivo de queries do cadastro único de clientes. */
	private static final String DATASOURCE = "jdbc/CUCServicoReplicacaoDS";

	/**
	 * Construtor dos DAOs que realizam operações de CRUD no sistema ServicoReplicacaoCadastroUnicoClientes.
	 * @param clazz A entidade
	 * @param nomeComandoPesquisar O nome do comando para pesquisar.
	 */
	public CAPESServicoReplicacaoCrudDao(Class<T> clazz, String nomeComandoPesquisar) {
		super(DATASOURCE, ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, clazz, nomeComandoPesquisar);
	}

	/**
	 * @param nomeComandoListar
	 * @param nomeComandoConsultar
	 */
	public CAPESServicoReplicacaoCrudDao(String nomeDatasource, String arquivoQueries, 
			String nomeColecaoComandos,
			Class<T> clazz, String nomeComandoPesquisar) {

		super(nomeDatasource, arquivoQueries, nomeColecaoComandos, clazz, nomeComandoPesquisar);
	}

	/**
	 * Estabelece a conexao com o banco de dados.
	 * 
	 * @return a conexao com o banco de dados.
	 */
	@Override
	protected Connection estabelecerConexao() {
		try {
			CAPESServicoReplicacaoDatasource datasource = new CAPESServicoReplicacaoDatasource(getNomeDatasource(), System.getProperties());
			return datasource.getConnection();
		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		}
	}
	
	@Override
	@PersistenceContext(unitName="emCapesLegadoReplicacaoClientes")
	public void setEntityManager(EntityManager manager) {
		super.setEntityManager(manager);
	}
}