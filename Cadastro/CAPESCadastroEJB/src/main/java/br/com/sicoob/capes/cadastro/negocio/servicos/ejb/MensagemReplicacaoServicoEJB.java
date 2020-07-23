package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.beanutils.BeanComparator;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.MensagemReplicacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.MensagemReplicacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.MensagemReplicacaoServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.vo.MonitoracaoMensagensVO;
import br.com.sicoob.capes.cadastro.persistencia.dao.MensagemReplicacaoDAO;
import br.com.sicoob.capes.negocio.entidades.MensagemReplicacao;

/**
 * EJB contendo servicos relacionados a MensagemReplicacao.
 */
@Stateless
@Local(MensagemReplicacaoServicoLocal.class)
@Remote(MensagemReplicacaoServicoRemote.class)
public class MensagemReplicacaoServicoEJB extends CAPESCadastroCrudServicoEJB<MensagemReplicacao> implements MensagemReplicacaoServicoRemote, MensagemReplicacaoServicoLocal {
	
	/** Fábrica de conexões usada. */
	@Resource(mappedName = "WSMQCAPESQueueConnectionFactory")
	private javax.jms.QueueConnectionFactory fabricaConexoes;

	/** O atributo filaAtualizaCadastro. */
	@Resource(mappedName = "queue/QL.CONT.EXEC.ATUALIZA.CADASTRO")
	private javax.jms.Queue filaAtualizaCadastro;

	/** O atributo dlqAtualizaCadastro. */
	@Resource(mappedName = "queue/QL.CONT.EXEC.ATUALIZA.CADASTRO.DLQ")
	private javax.jms.Queue dlqAtualizaCadastro;

	@Inject
	@Default
	private MensagemReplicacaoDAO dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected MensagemReplicacaoDAO getDAO() {
		return this.dao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaDto<MensagemReplicacao> pesquisarMensagensNaoEnviadasPorFiltro(
			ConsultaDto<MensagemReplicacao> criterios) throws BancoobException {
		
		return getDAO().pesquisarMensagensNaoEnviadasPorFiltro(criterios);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void alterar(MensagemReplicacao mensagem) throws BancoobException {
		super.alterar(mensagem);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<MensagemReplicacao> listarMensagensNaoProcessadasPorFiltro(MensagemReplicacao filtro)
			throws BancoobException {

		return getDAO().listarMensagensNaoProcessadasPorFiltro(filtro);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<MensagemReplicacao> listarMensagensNaoProcessadasPorFiltro(
			MensagemReplicacao filtro, Boolean incluiMsgComErro) throws BancoobException {

		return getDAO().listarMensagensNaoProcessadasPorFiltro(filtro, incluiMsgComErro);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void reenviarMensagens(List<MensagemReplicacao> mensagens) throws BancoobException {

		if ((mensagens != null) && !mensagens.isEmpty()) {
			
			// ordena as mensagens para enviar na ordem em que foram criadas
			Collections.sort(mensagens, new BeanComparator<MensagemReplicacao>("idMensagemReplicacao"));
			enviarMensagensReplicacao(mensagens);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void enviarMensagens(String... operacoes) throws BancoobException {

		if (operacoes != null) {
			List<MensagemReplicacao> mensagens = new ArrayList<MensagemReplicacao>(); 
			
			// obtendo as mensagens para envio
			String idOperacao = null;
			try {
				for (int i = 0; i < operacoes.length; i++) {
					idOperacao = operacoes[i];
					mensagens.addAll(obterMensagensNaoEnviadas(idOperacao));
				}
			} catch (BancoobException e) {
				getLogger().erro(e, "Falha ao obter as mensagens de replicacao: identificadorOperacao="
						+ idOperacao);
			}

			// ordena as mensagens para enviar na ordem em que foram criadas
			Collections.sort(mensagens, new BeanComparator<MensagemReplicacao>("idMensagemReplicacao"));
			
			// enviando
			if ((mensagens != null) && !mensagens.isEmpty()) {
				enviarMensagensReplicacao(mensagens);
			}
		}
	}

	/**
	 * É usado a delegate para realizar a consulta para não utilizar a trasação corrente.
	 * 
	 * @param idOperacao
	 * @return
	 * @throws BancoobException
	 */
	private List<MensagemReplicacao> obterMensagensNaoEnviadas(final String idOperacao)
			throws BancoobException {

		MensagemReplicacao filtro = new MensagemReplicacao();
		filtro.setIdentificadorOperacao(idOperacao);

		ConsultaDto<MensagemReplicacao> criterios = new ConsultaDto<MensagemReplicacao>();
		criterios.setFiltro(filtro);
		
		MensagemReplicacaoDelegate delegate = CAPESCadastroFabricaDelegates.getInstance().criarMensagemReplicacaoDelegate();
		return delegate.pesquisarMensagensNaoEnviadasPorFiltro(criterios).getResultado();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaDto<MensagemReplicacao> obterMensagensNaoProcessadas(
			ConsultaDto<MensagemReplicacao> criterios) throws BancoobException {
		
		return pesquisar(criterios);
	}

	/**
	 * O método Enviar mensagens replicacao.
	 *
	 * @param mensagens o valor de mensagens
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void enviarMensagensReplicacao(final List<MensagemReplicacao> mensagens) throws BancoobException {
		getLogger().info("Iniciando envio de replicacoes");
		Set<String> mensagensEnviadas = new HashSet<String>();

		for (MensagemReplicacao mensagem : mensagens) {
			String idOperacao = mensagem.getIdentificadorOperacao();
			Integer idInstituicao = mensagem.getIdInstituicao();

			String conteudoMsg = idOperacao + MensagemReplicacao.SEPARADOR_MENSAGEM + idInstituicao;
			try {
				if (!mensagensEnviadas.contains(conteudoMsg)) {
					atualizarDatasEnvio(idOperacao, idInstituicao, new Date());

					// Enviando mensagens
					enviarMensagem(conteudoMsg);
					mensagensEnviadas.add(conteudoMsg);
					getLogger().debug("Replicacao enviada: identificadorOperacao=", idOperacao, ", idInstituicao=", String.valueOf(idInstituicao));
				}
			} catch (BancoobException e) {
				getLogger().erro(e, "Falha na atualizacao da data de envio da replicacao: " + "identificadorOperacao=" + idOperacao + ", idInstituicao=" + idInstituicao);
				throw e;
			}
		}
		getLogger().info("Envio de replicacoes finalizado");
	}
	
	private void enviarMensagem(String conteudo) throws BancoobException {
		String nomeFila = null;
		QueueConnection connection = null;
		QueueSession session = null;
		QueueSender sender = null;

		try {
			nomeFila = filaAtualizaCadastro.getQueueName();

			connection = fabricaConexoes.createQueueConnection();
			session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			sender = session.createSender(filaAtualizaCadastro);
			TextMessage txtMessage = session.createTextMessage(conteudo);
			sender.send(txtMessage);
		} catch (JMSException e) {
			throw new BancoobException("msg.erro.envio.mensagem", new Object[] { nomeFila }, e);
		} finally {
			fecharRecursos(connection, session, sender);
		}
	}

	private void fecharRecursos(QueueConnection queueConnection, QueueSession queueSession, QueueSender sender) throws BancoobException {
		try {
			if (sender != null) {
				sender.close();
			}
			if (queueSession != null) {
				queueSession.close();
			}
			if (queueConnection != null) {
				queueConnection.close();
			}
		} catch (JMSException e) {
			throw new BancoobException("msg.erro.fechamento.recursos", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void atualizarDatasEnvio(String identificadorOperacao, Integer idInstituicao, Date data) throws BancoobException {
		DateTimeDB dataEnvio = data != null ? new DateTimeDB(data.getTime()) : null;

		MensagemReplicacao mensagem = new MensagemReplicacao();
		mensagem.setDataEnvio(dataEnvio);
		mensagem.setIdentificadorOperacao(identificadorOperacao);
		mensagem.setIdInstituicao(idInstituicao);

		getDAO().atualizarDatasEnvio(mensagem);
		getLogger().debug("Datas de envio atualizadas: ", "identificadorOperacao=", identificadorOperacao, ", idInstituicao=", String.valueOf(idInstituicao));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void atualizarDataProcessamento(Integer idMensagemReplicacao, Date data, String erro) throws BancoobException {
		DateTimeDB dataProcessamento = data != null ? new DateTimeDB(data.getTime()) : null;

		MensagemReplicacao mensagem = new MensagemReplicacao();
		mensagem.setDataProcessamento(dataProcessamento);
		mensagem.setErro(erro);
		mensagem.setIdMensagemReplicacao(idMensagemReplicacao);
		getDAO().atualizarDataProcessamentoErro(mensagem);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void limparDadosProcessamento(Integer idMensagemReplicacao) throws BancoobException {
		atualizarDataProcessamento(idMensagemReplicacao, null, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void expurgarMensagensReplicacao() throws BancoobException {
		getDAO().expurgarMensagensReplicacao();
		getDAO().expurgarMensagensReplicacaoComErro();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reprocessarMensagem(MensagemReplicacao mensagem) throws BancoobException {
		Integer idMensagemReplicacao = mensagem.getIdMensagemReplicacao();

		// delegate para forçar a limpeza dos dados de PROCESSAMENTO em outra transação
		MensagemReplicacaoDelegate delegate = CAPESCadastroFabricaDelegates.getInstance().criarMensagemReplicacaoDelegate();
		delegate.limparDadosProcessamento(idMensagemReplicacao);

		MensagemReplicacao filtro = new MensagemReplicacao();
		filtro.setIdMensagemReplicacao(idMensagemReplicacao);
		enviarMensagensReplicacao(getDAO().listarMensagensNaoProcessadasPorFiltro(filtro));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MonitoracaoMensagensVO monitorarMensagensNaoProcessadas() throws BancoobException {
		return getDAO().monitorarMensagensNaoProcessadas();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MonitoracaoMensagensVO monitorarMensagensNaoEnviadas() throws BancoobException {
		return getDAO().monitorarMensagensNaoEnviadas();
	}

}