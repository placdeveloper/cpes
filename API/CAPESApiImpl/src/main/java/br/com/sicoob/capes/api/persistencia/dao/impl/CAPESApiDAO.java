package br.com.sicoob.capes.api.persistencia.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.conexao.CorporativoDataSource;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.vo.BancoobVo;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.dao.BancoobDao;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.api.negocio.filtros.FiltroConsultaAPIAbstrato;
import br.com.sicoob.capes.api.persistencia.dao.CAPESApiDaoIF;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.tipos.DateTime;

/**
 * @author Rodrigo.Chaves
 * 
 * @since 1.2.0.0
 * @param <V>
 *            o tipo do objeto a ser retornado pelos metodos genericos
 */
public abstract class CAPESApiDAO<V> extends BancoobDao implements CAPESApiDaoIF<V> {

	/** A constante NOME_COLECAO_COMANDOS. */
	protected static final String NOME_COLECAO_COMANDOS = "COMANDOS_CAPES_API";
	
	/** A constante ARQUIVO_QUERIES. */
	protected static final String ARQUIVO_QUERIES = "capes.api.queries.xml";

	/** A constante CRITERIOS. */
	protected static final String CRITERIOS = "criterios";
	
	/** A constante ID_INSTITUICAO. */
	protected static final String ID_INSTITUICAO = "idInstituicao";
	
	/** A constante ID_PESSOA_LEGADO. */
	protected static final String ID_PESSOA_LEGADO = "idPessoaLegado";
	
	/** A constante ID_PESSOA. */
	protected static final String ID_PESSOA = "idPessoa";

	/** O atributo nomeComando. */
	private String nomeComando;
	
	/** O atributo nomeComandoQuantidade. */
	private String nomeComandoQuantidade;
	
	/** O atributo logger. */
	private ISicoobLogger logger = getLogger();

	/**
	 * Construtor
	 * 
	 * @param nomeDatasource
	 *            o nome do datasource utilizado
	 * @param arquivoQueries
	 *            o nome do arquivo de queries
	 * @param nomeColecaoComandos
	 *            o nome que sera usado na colecao de comandos
	 */
	public CAPESApiDAO(String nomeDatasource, String arquivoQueries, String nomeColecaoComandos) {
		super(nomeDatasource, arquivoQueries, nomeColecaoComandos);
	}

	/**
	 * @param nomeComando
	 * @param nomeComandoQuantidade
	 */
	public CAPESApiDAO(String nomeComando, String nomeComandoQuantidade) {
		this(Constantes.Persistencia.DATASOURCE_CAPES, ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS);
		this.nomeComando = nomeComando;
		this.nomeComandoQuantidade = nomeComandoQuantidade;
	}

	/**
	 * Construtor
	 */
	public CAPESApiDAO() {
		this(null, null);
	}
	
	/**
	 * Construtor
	 */
	public CAPESApiDAO(String nomeComando) {
		this(nomeComando, null);
	}

	/**
	 * Recupera o valor de nomeComando.
	 *
	 * @return o valor de nomeComando
	 */
	protected String getNomeComando() {
		return nomeComando;
	}

	/**
	 * Recupera o valor de nomeComandoQuantidade.
	 *
	 * @return o valor de nomeComandoQuantidade
	 */
	protected String getNomeComandoQuantidade() {
		return nomeComandoQuantidade;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Connection estabelecerConexao() {
		try {
			CorporativoDataSource datasource = new CorporativoDataSource(getNomeDatasource(), System.getProperties());
			return datasource.getConnection();
		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@PersistenceContext(unitName = "emCapes")
	public void setEntityManager(EntityManager manager) {
		super.setEntityManager(manager);
	}

	/**
	 * Fecha o {@link ResultSet} silenciosamente
	 * 
	 * @param rs
	 *            o ResultSet a ser fechado
	 */
	protected void fecharResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				getLogger().erro(e, "Falha no fechamento do ResultSet");
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public V obterPorId(Serializable id) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando(getNomeComando());
			comando.adicionarVariavel("id", id);
			comando.configurar();
			return (V) comando.criarQuery(getEntityManager()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			fecharComando(comando);
		}
	}
	
	/**
	 * Obtém o nome do comando para o método 'PESQUISAR'
	 * 
	 * @return
	 * @throws BancoobException
	 */
	protected String obterNomeComandoPesquisar() throws BancoobException {
		return getNomeComando();
	}

	/**
	 * Obtém o nome do comando de quantidade para o método 'PESQUISAR PAGINADO'
	 * 
	 * @return
	 * @throws BancoobException
	 */
	protected String obterNomeComandoQuantidadePesquisar() throws BancoobException {
		return getNomeComandoQuantidade();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public <K extends BancoobVo> List<K> pesquisar(ConsultaDto<? extends FiltroConsultaAPIAbstrato> criterios) throws BancoobException {
		Comando comando = getComando(obterNomeComandoPesquisar());
		try {
			comando.adicionarVariavel(CRITERIOS, criterios);
			comando.configurar();
			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "unchecked" })
	public <K extends BancoobVo> ConsultaDto<K> pesquisarPaginado(ConsultaDto<? extends FiltroConsultaAPIAbstrato> criterios) throws BancoobException {
		ConsultaDto<K> consultaDto = new ConsultaDto<K>();
		consultaDto.setTamanhoPagina(criterios.getTamanhoPagina());
		consultaDto.setPagina(criterios.getPagina());

		if (obterNomeComandoPesquisar() == null && obterNomeComandoQuantidadePesquisar() == null) {
			throw new UnsupportedOperationException();
		}

		Comando comando = null;
		Comando comandoQuantidade = null;
		try {
			comando = getComando(obterNomeComandoPesquisar());
			comando.adicionarVariavel(CRITERIOS, criterios);
			comando.configurar();

			Query query = criarQuery(comando);
			query.setFirstResult(criterios.getPagina() * criterios.getTamanhoPagina());
			query.setMaxResults(criterios.getTamanhoPagina());

			consultaDto.setResultado(query.getResultList());
			comandoQuantidade = getComando(obterNomeComandoQuantidadePesquisar());
			comandoQuantidade.adicionarVariavel(CRITERIOS, criterios);
			comandoQuantidade.configurar();
			int quantidade = ((Number) criarQuery(comandoQuantidade).getSingleResult()).intValue();

			consultaDto.setTotalRegistros(quantidade);

			return consultaDto;
		} finally {
			fecharComando(comando);
			fecharComando(comandoQuantidade);
		}
	}

    /**
     * {@inheritDoc}
     */
	@SuppressWarnings("unchecked")
	public List<V> obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando(getNomeComando());
			logger.debug("CAPESApiDAO obterPorPessoaInstituicao - idPessoa:" + idPessoa + " idInstituicao:" + idInstituicao);
			
			comando.adicionarVariavel(ID_PESSOA, idPessoa);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.adicionarVariavel("inserirDataHoraInicio", Boolean.TRUE);
			comando.configurar();

			List<V> resultList = criarQuery(comando).getResultList();
			logger.debug("CAPESApiDAO obterPorPessoaInstituicao - Retorno:" + resultList);
			return resultList;
		} finally {
			fecharComando(comando);
		}
	}

    /**
     * {@inheritDoc}
     */
	@SuppressWarnings("unchecked")
	public final V obterPorPessoaInstituicaoUnico(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando(getNomeComando());
			comando.adicionarVariavel(ID_PESSOA, idPessoa);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.configurar();

			V singleResult = (V) criarQuery(comando).getSingleResult();
			return singleResult;
		} catch (NoResultException e) {
			return null;
		} finally {
			fecharComando(comando);
		}
	}

    /**
     * {@inheritDoc}
     */
	@SuppressWarnings("unchecked")
	public final List<V> obterNaoVigentePorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando(getNomeComando());
			comando.adicionarVariavel(ID_PESSOA, idPessoa);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.adicionarVariavel("inserirDataHoraInicio", Boolean.FALSE);
			comando.configurar();

			return criarQuery(comando).getResultList();
		} catch (NoResultException e) {
			return null;
		} finally {
			fecharComando(comando);
		}
	}
	
	/**
	 * 
	 * @param rs
	 * @param columnName
	 * @return
	 * @throws SQLException
	 */
	protected DateTime getDateTime(ResultSet rs, String columnName) throws SQLException {
		Timestamp date = rs.getTimestamp(columnName);
		return date != null ? new DateTime(date.getTime()) : null;
	}

}