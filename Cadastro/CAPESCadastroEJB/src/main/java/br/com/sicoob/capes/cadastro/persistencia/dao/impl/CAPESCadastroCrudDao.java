package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Session;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.dao.BancoobCrudDao;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.cadastro.negocio.excecao.ItemExcluidoException;
import br.com.sicoob.capes.cadastro.persistencia.CAPESCadastroDatasource;
import br.com.sicoob.capes.cadastro.persistencia.dao.CAPESCadastroCrudDaoIF;
import br.com.sicoob.capes.cadastro.util.CAPESCadastroConstantes.Persistencia;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.comum.persistencia.GerenciadorFiltro;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;

/**
 * Classe padrao com a infraestrutura de acesso a base de dados do sistema CadastroUnicoClientesComum
 * 
 * @author Jarbasjstefanini
 */
/**
 * @param <T>
 * 
 * @author Rodrigo.Chaves
 */
public abstract class CAPESCadastroCrudDao<T extends CAPESEntidade<?>>
		extends BancoobCrudDao<T> implements CAPESCadastroCrudDaoIF<T> {

	/** O atributo classe. */
	private Class<T> classe;
	
	/** O atributo nomeComandoPesquisar. */
	private String nomeComandoPesquisar;
	
	/**
	 * Construtor dos DAOs que realizam operações de CRUD no sistema ConsultasExternas.
	 * @param classe A entidade
	 * @param nomeComandoPesquisar O nome do comando para pesquisar.
	 */
	public CAPESCadastroCrudDao(Class<T> classe, String nomeComandoPesquisar) {
		this(Constantes.Persistencia.DATASOURCE_CAPES, Persistencia.ARQUIVO_QUERIES,
		        Persistencia.NOME_COLECAO_COMANDOS, classe, nomeComandoPesquisar);
		this.classe = classe;
	}

	/**
	 * {@inheritDoc}
	 */
	public CAPESCadastroCrudDao(String datasource, String arquivoQueries,
			String nomeColecaoComandos, Class<T> classe, String nomeComandoPesquisar) {

		super(datasource, arquivoQueries, nomeColecaoComandos, classe, nomeComandoPesquisar);
		this.classe = classe;
		this.nomeComandoPesquisar = nomeComandoPesquisar;
	}

	/**
	 * Estabelece a conexao com o banco de dados.
	 * 
	 * @return a conexao com o banco de dados.
	 */
	@Override
	protected Connection estabelecerConexao() {
		try {
			CAPESCadastroDatasource datasource = 
					new CAPESCadastroDatasource(getNomeDatasource(), 
							System.getProperties());

			return datasource.getConnection();
		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EntityManager getEntityManager() {
		EntityManager entityManager = super.getEntityManager();

		InformacoesUsuarioCAPES informacoes = InformacoesUsuarioCAPES.getInstance();
		if (informacoes != null && informacoes.getCodigoCompartilhamento() != null) {
			Short codigo = informacoes.getCodigoCompartilhamento();
			ativaFiltroSistemaCooperativo(entityManager, codigo);
		} else {
			desativarFiltroSistemaCooperativo(entityManager);
		}

		return entityManager;
	}
	
	/**
	 * O método Desativar filtro sistema cooperativo.
	 */
	protected void desativarFiltroSistemaCooperativo() {
		desativarFiltroSistemaCooperativo(getEntityManager());
	}

	/**
	 * O método Desativar filtro sistema cooperativo.
	 *
	 * @param entityManager o valor de entity manager
	 */
	protected void desativarFiltroSistemaCooperativo(EntityManager entityManager) {
		
		GerenciadorFiltro.desativarFiltro(entityManager,
		        Constantes.Persistencia.FILTRO_COMPARTILHAMENTO_CADASTRO);
	}	
	
	/**
	 * O método Ativa filtro sistema cooperativo.
	 *
	 * @param entityManager o valor de entity manager
	 * @param codigoCompartilhamento o valor de codigo compartilhamento
	 */
	protected void ativaFiltroSistemaCooperativo(EntityManager entityManager,
	        Short codigoCompartilhamento) {
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("codigo", codigoCompartilhamento);
		
		GerenciadorFiltro.ativarFiltro(entityManager,
		        Constantes.Persistencia.FILTRO_COMPARTILHAMENTO_CADASTRO, parametros);
	}
	
	/**
	 * Realiza a susbstituicao dos valores da {@code colecaoPersistente} pelos da {@code novaColecao}
	 * 
	 * Paliativo para solucionar um bug do hibernate ao tentar evitar um objeto na sessão. https://hibernate.atlassian.net/browse/HHH-9013
	 * 
	 * FIXME: AVALIAR A CORREÇÃO DO BUG.
	 * 
	 * @param colecaoPersistente
	 *            a colecao com os dados persistentes
	 * @param novaColecao
	 *            a colecao com os dados que deverão se tornar persistentes
	 *            
	 */
	protected <E extends CAPESEntidade<?>> void substituirColecaoPersistente(Collection<E> colecaoPersistente, Collection<E> novaColecao) {
		Session session = (Session) getEntityManager().getDelegate();
		session.evict(colecaoPersistente);

//		try {
//			session.evict(colecaoPersistente);
//		} catch (IllegalArgumentException ex) {
//			// Ignora a exceção se a mensagem de erro contém o prefixo do bug do hibernate HHH_9013_ERROR_MESSAGE_PREFIX
//			final String mensagemErro = ex.getMessage();
//			if (mensagemErro == null || !mensagemErro.startsWith("Non-entity object instance passed to evict")) {
//				// A exceção não é causada pelo bug, então lançamos a mesma.
//				throw ex;
//			}
//		}
		
		colecaoPersistente.clear();
		colecaoPersistente.addAll(novaColecao);
	}
	
	/**
	 * Substitui as {@code colecoes} de {@code destino} pelas respectivas de
	 * {@code origem}
	 * 
	 * @param destino
	 *            o destino da informacao
	 * @param origem
	 *            a origem da informacao
	 * @param colecoes
	 *            os nomes das colecoes a serem substituidas
	 * @throws ClassCastException
	 *             caso o tipo do objeto das colecoes nao seja da hierarquia de
	 *             {@link CAPESEntidade}
	 * @throws NullPointerException caso algum dos parametros seja {@code null}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void substituirColecoesPersistentes(T destino, T origem, String... colecoes) {
		
		Collection<CAPESEntidade<?>> colecaoDestino, colecaoOrigem = null;
		for (String lista : colecoes) {
			colecaoDestino =
					(Collection<CAPESEntidade<?>>) ReflexaoUtils
							.getValorAtributo(destino, lista);
			colecaoOrigem =
					(Collection<CAPESEntidade<?>>) ReflexaoUtils
							.getValorAtributo(origem, lista);
			if (CollectionUtils.isNotEmpty(colecaoOrigem)
					&& !CollectionUtils.isEqualCollection(colecaoOrigem, colecaoDestino)) {
				substituirColecaoPersistente(colecaoDestino, colecaoOrigem);
			} else if (CollectionUtils.isNotEmpty(colecaoDestino)
					&& CollectionUtils.isEmpty(colecaoOrigem)) {
				substituirColecaoPersistente(colecaoDestino, CollectionUtils.EMPTY_COLLECTION);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluirEntidade(T objeto) throws BancoobException {
		
		T objetoPersistente = obter(objeto.getId());
		
		if (objetoPersistente == null) {
			throw new ItemExcluidoException();
		}
		
		if (objetoPersistente instanceof Aprovavel) {
			Aprovavel aprovavel = (Aprovavel)objeto;
			Aprovavel aprovavelPersistente = (Aprovavel) objetoPersistente;
			aprovavelPersistente.setDataHoraInicio(aprovavel.getDataHoraInicio());
			aprovavelPersistente.setGravarHistorico(aprovavel.getGravarHistorico());
			aprovavelPersistente.setIdInstituicaoAtualizacao(aprovavel.getIdInstituicaoAtualizacao());
			aprovavelPersistente.setIdRegistroControlado(aprovavel.getIdRegistroControlado());
			aprovavelPersistente.setVerificarAutorizacao(aprovavel.getVerificarAutorizacao());
		}
		getEntityManager().remove(getEntityManager().getReference(objeto.getClass(), objeto.getId()));
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
     * @return the clazz
     */
    protected Class<T> getClasse() {
	    return classe;
    }

	/**
	 * O método Fechar result set.
	 *
	 * @param rset o valor de rset
	 */
	protected void fecharResultSet(ResultSet rset) {
    	if (rset != null) {
    		try {
    			rset.close();
    		} catch (SQLException e) {
    			getLogger().erro(e, "Falha no fechamento do ResultSet");
    		}
    	}
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removerObjetoSessao(T objeto) throws BancoobException {
		((Session) getEntityManager().getDelegate()).evict(objeto);
	}

	/**
     * @return the nomeComandoPesquisar
     */
    protected String getNomeComandoPesquisar() {
    	return nomeComandoPesquisar;
    }
	
}