package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.exception.LockAcquisitionException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.bancoob.persistencia.excecao.ViolacaoChavePrimariaException;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAutorizacaoDTO;
import br.com.sicoob.capes.cadastro.negocio.excecao.AutorizacaoDuplicadaException;
import br.com.sicoob.capes.cadastro.persistencia.dao.AutorizacaoDAO;
import br.com.sicoob.capes.cadastro.persistencia.excecao.ObterLockException;
import br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.AutorizacaoDocumento;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * @author Rodrigo.Chaves
 */
public class AutorizacaoDAOImpl extends CAPESCadastroCrudDao<Autorizacao> implements
		AutorizacaoDAO {

	/** A constante COMANDO_CONSULTA_QTD_AUTORIZACOES_NAO_ENCAMINHADAS. */
	private static final String COMANDO_CONSULTA_QTD_AUTORIZACOES_NAO_ENCAMINHADAS = "CONSULTA_QTD_AUTORIZACOES_NAO_ENCAMINHADAS";

	/** A constante NOME_COMANDO_PESQUISAR. */
	protected static final String NOME_COMANDO_PESQUISAR = "";

	/** A constante COMANDO_CONSULTA_AUTORIZACOES_PENDENTES. */
	private static final String COMANDO_CONSULTA_AUTORIZACOES_PENDENTES =
			"CONSULTA_AUTORIZACOES_PENDENTES";

	/** A constante COMANDO_CONSULTA_AUTORIZACOES_DEVOLVIDAS. */
	private static final String COMANDO_CONSULTA_AUTORIZACOES_DEVOLVIDAS =
			"COMANDO_CONSULTA_AUTORIZACOES_DEVOLVIDAS";

	/** A constante COMANDO_CONSULTA_AUTORIZACOES_ENCAMINHADAS. */
	private static final String COMANDO_CONSULTA_AUTORIZACOES_ENCAMINHADAS =
			"CONSULTA_AUTORIZACOES_ENCAMINHADAS";

	/** A constante COMANDO_CONSULTA_AUTORIZACOES_NAO_ENCAMINHADAS. */
	private static final String COMANDO_CONSULTA_AUTORIZACOES_NAO_ENCAMINHADAS =
			"CONSULTA_AUTORIZACOES_NAO_ENCAMINHADAS";

	/** A constante COMANDO_CONSULTA_AUTORIZACOES_APTAS_ENCAMINHAR. */
	private static final String COMANDO_CONSULTA_AUTORIZACOES_APTAS_ENCAMINHAR =
			"COMANDO_CONSULTA_AUTORIZACOES_APTAS_ENCAMINHAR";

	/** A constante COMANDO_CONSULTA_DOCUMENTOS_AUTORIZACAO. */
	private static final String COMANDO_CONSULTA_DOCUMENTOS_AUTORIZACAO =
			"CONSULTA_DOCUMENTOS_AUTORIZACAO";

	/** A constante OBTER_AUTORIZACAO. */
	private static final String OBTER_AUTORIZACAO = "OBTER_AUTORIZACAO";

	/** A constante COMANDO_DELETE_DOCUMENTOS_AUTORIZACAO. */
	private static final String COMANDO_DELETE_DOCUMENTOS_AUTORIZACAO =
			"DELETE_DOCUMENTOS_AUTORIZACAO";

	/** A constante OBTER_AUTORIZACOES_VENCIDAS. */
	private static final String OBTER_AUTORIZACOES_VENCIDAS = "OBTER_AUTORIZACOES_VENCIDAS";

	/** A constante OBTER_AUTORIZACAO_LOCK. */
	private static final String OBTER_AUTORIZACAO_LOCK = "OBTER_AUTORIZACAO_LOCK";
	
	/** A constante COMANDO_CONSULTA_IDREGISTROSCONTRALADOS_AUTORIZACOES_PENDENTES_AUTO_ATENDIMENTO. */
	private static final String COMANDO_CONSULTA_IDREGISTROSCONTRALADOS_AUTORIZACOES_PENDENTES_AUTO_ATENDIMENTO =
			"CONSULTA_IDREGISTROSCONTRALADOS_AUTORIZACOES_PENDENTES_AUTO_ATENDIMENTO";
	
	/** A constante COMANDO_CONSULTA_AUTORIZACOES_PENDENTES_AUTO_ATENDIMENTO. */
	private static final String COMANDO_CONSULTA_AUTORIZACOES_PENDENTES_AUTO_ATENDIMENTO =
			"CONSULTA_AUTORIZACOES_PENDENTES_AUTO_ATENDIMENTO";
	
	private static final String PESQUISAR_AUTORIZACAO_POR_ID_REGISTRO = "PESQUISAR_AUTORIZACAO_POR_ID_REGISTRO";
	/**
	 * Construtor
	 *
	 * @param clazz
	 * @param nomeComandoPesquisar
	 */
	public AutorizacaoDAOImpl() {
		super(Autorizacao.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaAutorizacaoDTO pesquisarAutorizacoesPendentes(
			ConsultaAutorizacaoDTO criterios) throws BancoobException {

		getLogger().debug("Realizando pesquisa de autorizacoes pendentes");
		return (ConsultaAutorizacaoDTO) pesquisar(Autorizacao.class, criterios,
				COMANDO_CONSULTA_AUTORIZACOES_PENDENTES);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaAutorizacaoDTO pesquisarAutorizacoesDevolvidas(ConsultaAutorizacaoDTO criterios)
			throws BancoobException {

		getLogger().debug("Realizando pesquisa de autorizacoes devolvidas");
		return (ConsultaAutorizacaoDTO) pesquisar(Autorizacao.class, criterios,
				COMANDO_CONSULTA_AUTORIZACOES_DEVOLVIDAS);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaAutorizacaoDTO pesquisarAutorizacoesEncaminhadas(
			ConsultaAutorizacaoDTO criterios) throws BancoobException {

		getLogger().debug("Realizando pesquisa de autorizacoes encaminhadas");
		return (ConsultaAutorizacaoDTO) pesquisar(Autorizacao.class, criterios,
				COMANDO_CONSULTA_AUTORIZACOES_ENCAMINHADAS);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaAutorizacaoDTO pesquisarAutorizacoesNaoEncaminhadas(
			ConsultaAutorizacaoDTO criterios) throws BancoobException {

		getLogger().debug("Realizando pesquisa de autorizacoes nao encaminhadas");
		return (ConsultaAutorizacaoDTO) pesquisar(Autorizacao.class, criterios,
				COMANDO_CONSULTA_AUTORIZACOES_NAO_ENCAMINHADAS,
				COMANDO_CONSULTA_QTD_AUTORIZACOES_NAO_ENCAMINHADAS);
	}

	/**
	 * @see AutorizacaoDAO#pesquisarAutorizacoesAptasEncaminhar(ConsultaAutorizacaoDTO)
	 */
	@Override
	public List<Autorizacao> pesquisarAutorizacoesAptasEncaminhar(
			ConsultaAutorizacaoDTO criterios) throws BancoobException {
		getLogger().debug("Realizando pesquisa de autorizações aptas a serem encaminhadas");

		return pesquisarLista(Autorizacao.class, criterios,
				COMANDO_CONSULTA_AUTORIZACOES_APTAS_ENCAMINHAR);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isPessoaPendenteAprovacao(PessoaCompartilhamento pessoa) throws BancoobException {
		Comando comando = null;
		Long countRegistros = 0l;
		try {
			EntityManager em = getEntityManager();
			
			comando = getComando("CONSULTA_PESSOA_PENDENTE_APROVACAO");
			comando.adicionarVariavel("idPessoa", pessoa.getPessoa().getId());
			comando.adicionarVariavel("codCompartilhamentoCadastro", pessoa.getCodCompartilhamentoCadastro());
			comando.configurar();
			
			Query query = comando.criarQuery(em);
			countRegistros = (Long) query.getSingleResult();
			
		} catch (NoResultException e) {
			getLogger().debug("Pessoa pendente aprovação não localizada: " +
					pessoa.getPessoa().getId());
		} finally {
			fecharComando(comando);
		}
		return countRegistros == 0 ? false : true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isPessoaPendenteAprovacaoAutorizacao(PessoaCompartilhamento pessoa, Autorizacao autorizacao) {
		Comando comando = null;
		Integer countRegistros = 0;
		try {
			EntityManager em = getEntityManager();
			
			comando = getComando("CONSULTA_PESSOA_PENDENTE_APROVACAO_AUTORIZACAO");
			comando.adicionarVariavel("idPessoa", pessoa.getPessoa().getId());
			comando.adicionarVariavel("codCompartilhamentoCadastro ", pessoa.getCodCompartilhamentoCadastro());
			comando.adicionarVariavel("idAutorizacao ", autorizacao.getId());
				
			comando.configurar();
			
			Query queryLock = em.createNativeQuery(comando.getSqlEmUso());
			queryLock.setParameter("parametro0", pessoa.getPessoa().getId());
			queryLock.setParameter("parametro1", pessoa.getCodCompartilhamentoCadastro());
			queryLock.setParameter("parametro2", autorizacao.getId());
			countRegistros = (Integer) queryLock.getSingleResult();
			
		} catch (NoResultException e) {
			getLogger().debug("Pessoa pendente aprovação não localizada: " +
					pessoa.getPessoa().getId());
		} finally {
			fecharComando(comando);
		}
		return countRegistros == 0 ? false : true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isPessoaPossuiAutorizacao(Integer idPessoa) throws BancoobException {
		Comando comando = null;
		Integer countRegistros = 0;
		try {
			EntityManager em = getEntityManager();
			
			comando = getComando("CONSULTA_IS_PESSOA_POSSUI_AUTORIZACAO");
			comando.adicionarVariavel("idPessoa", idPessoa);
			comando.configurar();
			
			Query queryLock = em.createNativeQuery(comando.getSqlEmUso());
			queryLock.setParameter("parametro0", idPessoa);
			
			countRegistros = (Integer) queryLock.getSingleResult();
			
		} catch (NoResultException e) {
		} finally {
			fecharComando(comando);
		}
		return countRegistros == 0 ? false : true;
	}

	/**
	 * @see AutorizacaoDAO#atualizaDataSolicitacao(Autorizacao)
	 */
	@Override
	public void atualizaDataSolicitacao(Autorizacao autorizacao)throws BancoobException {
		getLogger().debug("Autorizações encaminhadas. Atulizando a data da solicitação");

		alterar(autorizacao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoOperacaoEnum obterTipoOperacaoAutorizacao(Aprovavel aprovavel)
			throws BancoobException {

		Comando comando = null;
		TipoOperacaoEnum tipo = null;
		try {
			comando = getComando("CONSULTA_TIPO_OPERACAO_AUTORIZACAO");
			comando.adicionarVariavel("tipoAutorizacao", aprovavel.getTipoAutorizacao());
			comando.adicionarVariavel("id", aprovavel.getId());
			comando.configurar();

			tipo = (TipoOperacaoEnum) criarQuery(comando).getSingleResult();
		} catch (NoResultException e) {
			getLogger().debug("Autorizacao não localizada: ", aprovavel.getTipoAutorizacao().name(), "(",
					aprovavel.getId().toString(), ")");
		} finally {
			fecharComando(comando);
		}
		return tipo;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Autorizacao obterPorEntidade(Aprovavel aprovavel) throws BancoobException {
		Autorizacao autorizacao = null;
		Comando comando = null;
		try {
			comando = getComando("CONSULTA_AUTORIZACAO_POR_ENTIDADE");
			comando.adicionarVariavel("tipoAutorizacao", aprovavel.getTipoAutorizacao());
			comando.adicionarVariavel("id", aprovavel.getId());
			comando.configurar();

			autorizacao = (Autorizacao) criarQuery(comando).getSingleResult();
		} catch (NoResultException e) {
			getLogger().debug("Autorizacao não localizada: ", aprovavel != null ? 
					aprovavel.getTipoAutorizacao() != null ? aprovavel.getTipoAutorizacao().name() : aprovavel.getClass().getSimpleName() : "Entidade aprovavel nula", 
							"(", aprovavel != null ? String.valueOf(aprovavel.getId()) : "", ")");
		} finally {
			fecharComando(comando);
		}
		return autorizacao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(Autorizacao autorizacao, Collection<AutorizacaoDocumento> documentos)
			throws BancoobException {

		substituirColecaoPersistente(autorizacao.getDocumentos(), documentos);
		super.alterar(autorizacao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Autorizacao obterDocumentosComprobatoriosEmAutorizacao(ConsultaAutorizacaoDTO criterios) throws BancoobException {
		getLogger().debug("Realizando pesquisa de documentos comprobatórios");

		return pesquisarEntidade(Autorizacao.class, criterios, COMANDO_CONSULTA_DOCUMENTOS_AUTORIZACAO);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Autorizacao obter(Serializable chave) throws BancoobException {
		Autorizacao autorizacao = null;
		Comando comando = null;
		try {

			comando = getComando(OBTER_AUTORIZACAO);
			comando.adicionarVariavel("chaveAutorizacao", chave);
			comando.configurar();

			autorizacao = (Autorizacao) criarQuery(comando).getSingleResult();

		} catch (NoResultException e) {
			getLogger().debug("Não foi possível obter a autorização de chave ", chave.toString());
		} finally {
			fecharComando(comando);
		}

		return autorizacao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Autorizacao obterComLock(Serializable chave) throws BancoobException {
		Autorizacao autorizacao = null;
		Comando comando = null;
		try {

			// bloquear o registro
			Long bloquearRegistro = bloquearRegistro(chave);
			if(bloquearRegistro != null && bloquearRegistro != 0){
				comando = getComando(OBTER_AUTORIZACAO);
				comando.adicionarVariavel("chaveAutorizacao", bloquearRegistro);
				comando.configurar();

				autorizacao = (Autorizacao) criarQuery(comando).getSingleResult();
			}

		} catch (NoResultException e) {
			getLogger().debug("Nao foi possivel obter a autorizacao: ", String.valueOf(chave));
		} finally {
			fecharComando(comando);
		}

		return autorizacao;
	}

	/**
	 * Bloquear registro.
	 *
	 * @param chave o valor de chave
	 * @return Long
	 */
	private Long bloquearRegistro(Serializable chave) {
		Comando comando = null;
		Long retorno = 0L;
		try {
			EntityManager em = getEntityManager();

			comando = getComando(OBTER_AUTORIZACAO_LOCK);
			comando.adicionarVariavel("idAutorizacao", chave);
			comando.configurar();

			Query queryLock = em.createNativeQuery(comando.getSqlEmUso());
			queryLock.setParameter(1, chave);
			retorno = ((BigInteger) queryLock.getSingleResult()).longValue();

		} catch (NoResultException ne) {
			getLogger().debug("Não foi possível obter a autorização de chave ", chave.toString());
		} catch (PersistenceException e) {
			if (e.getCause() instanceof LockAcquisitionException) {
				getLogger().debug("Falha ao obter o lock do registro: ", Autorizacao.class.getName(), "#", String.valueOf(chave));
				throw new ObterLockException((LockAcquisitionException) e.getCause());
			} else {
				throw new PersistenciaException(e);
			}
		} finally {
			fecharComando(comando);
		}

		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deletarListaDocumentos(Long idAutorizacao) {

		Comando comando = null;
		try {

			comando = getComando(COMANDO_DELETE_DOCUMENTOS_AUTORIZACAO);
			comando.adicionarVariavel("idAutorizacao", idAutorizacao);
			comando.configurar();

			criarQuery(comando).executeUpdate();
		} finally {
			fecharComando(comando);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Autorizacao incluir(Autorizacao objeto) throws BancoobException {

		try {
			return super.incluir(objeto);
		} catch (ViolacaoChavePrimariaException e) {
			throw new AutorizacaoDuplicadaException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void marcarDevolvido(Autorizacao autorizacao, Boolean devolvido) throws BancoobException {

		Comando comando = null;
		try {
			comando = getComando("MARCAR_AUTORIZACAO_DEVOLVIDA");
			comando.configurar();

			Query query = getEntityManager().createNativeQuery(comando.getSqlEmUso());
			query.setParameter(1, devolvido);
			query.setParameter(2, autorizacao.getIdAutorizacao());
			query.executeUpdate();
		} finally {
			fecharComando(comando);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaDto<Autorizacao> obterListaAutorizacoesVencidas(ConsultaDto<Autorizacao> criterios) throws BancoobException {
		return pesquisar(Autorizacao.class, criterios, OBTER_AUTORIZACOES_VENCIDAS);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaAutorizacaoDTO obterAutorizacoesAutoAtendimento(ConsultaAutorizacaoDTO criterios) throws BancoobException {
		getLogger().debug("Realizando pesquisa de autorizacoes pendentes somente de auto atendimento.");
		criterios.setIdsRegistroControlado(this.filtrarIdRegistrosControladosAutoAtendimento(criterios.getIdsRegistroControlado()));
		
		return (ConsultaAutorizacaoDTO) pesquisar(Autorizacao.class, criterios, COMANDO_CONSULTA_AUTORIZACOES_PENDENTES_AUTO_ATENDIMENTO);
	}

	/**
	 * Filtra idRegistrosControlados controlados de auto atendimento
	 * InternetBank e Mobile.
	 */
	 @SuppressWarnings("unchecked")
	private Set<String> filtrarIdRegistrosControladosAutoAtendimento(Set<String> idsRegistroControlado) throws BancoobException {
		 Comando comando = getComando(COMANDO_CONSULTA_IDREGISTROSCONTRALADOS_AUTORIZACOES_PENDENTES_AUTO_ATENDIMENTO);

		try {
			comando.adicionarVariavel("idsRegistroControladoPessoa", idsRegistroControlado);
			comando.adicionarVariavel("idsRegistroControladoEndereco", idsRegistroControlado);
			comando.adicionarVariavel("idsRegistroControladoFonteRenda", idsRegistroControlado);
			comando.configurar();
			
			Query query = comando.criarNativeQuery(getEntityManager());

			return new HashSet<String>(query.getResultList());
 		} finally {
			fecharComando(comando);
		}
		
	}

	public Autorizacao obterAutorizacaoPorIdRegistroNovo(Long idRegistroNovo, Boolean devolvido) throws BancoobException {
		Comando comando = null;
		Autorizacao autorizacao = null;
		try {
			comando = getComando(PESQUISAR_AUTORIZACAO_POR_ID_REGISTRO);

			comando.adicionarVariavel("idRegistroNovo", idRegistroNovo);
			comando.adicionarVariavel("devolvido", devolvido);

			comando.configurar();

			Query query = comando.criarQuery(getEntityManager());
			autorizacao = (Autorizacao) query.getSingleResult();
		} catch (NoResultException e) {
			getLogger().debug("Nenhum registro encontrado com os parametros informados");
		} finally {
			fecharComando(comando);
		}
		return autorizacao;
	}
}