/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.util;

import static br.com.sicoob.capes.cadastro.negocio.excecao.MensagemInvalidaException.IDENTIFICADOR_OPERACAO_INVALIDO;
import static br.com.sicoob.capes.cadastro.negocio.excecao.MensagemInvalidaException.ID_INSTITUICAO_INVALIDO;
import static br.com.sicoob.capes.cadastro.negocio.excecao.MensagemInvalidaException.QUANTIDADE_PARAMETROS_INVALIDA;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.TextMessage;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.MensagemReplicacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.excecao.MensagemInvalidaException;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.MensagemReplicacao;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * @author Erico.Junior
 * 
 */
public class ReenvioMensagemJMSReplicacao<E extends CAPESEntidade<?> & Replicavel & Vigente> {

	/** O atributo logger. */
	private ISicoobLogger logger = SicoobLoggerPadrao.getInstance(getClass());
	
	/** O atributo delegate. */
	private MensagemReplicacaoDelegate delegate = CAPESCadastroFabricaDelegates.getInstance().criarMensagemReplicacaoDelegate();

	/**
	 * O método Processar mensagens.
	 *
	 * @param fabricaConexoes o valor de fabrica conexoes
	 * @param dlqAtualizaCadastro o valor de dlq atualiza cadastro
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void processarMensagens(QueueConnectionFactory fabricaConexoes, Queue dlqAtualizaCadastro) throws BancoobException {

		logger.debug("Verificando mensagens na DLQ");
		NavegadorFilaJMS navegador = new NavegadorFilaJMS(fabricaConexoes, dlqAtualizaCadastro);

		try {

			navegador.conectar();

			List<Message> lista = navegador.obterListaMensagens();
			for (Message message : lista) {

				boolean remover = reenviarMensagem(((TextMessage) message).getText());
				if (remover) {
					String selector = criarSelector(message);
					MessageConsumer consumer = navegador.criarConsumidor(selector);
					consumer.receive();
				}
			}

		} catch (JMSException e) {
			logger.erro(e, "Erro ao processar DLQ de replicação de cadastro.");
			throw new BancoobException(e);
		} finally {
			navegador.desconectar();
		}

	}

	/**
	 * Criar selector.
	 *
	 * @param message o valor de message
	 * @return String
	 * @throws JMSException lança a exceção JMSException
	 */
	private String criarSelector(Message message) throws JMSException {

		StringBuilder sb = new StringBuilder();
		sb.append("JMSMessageID = \'");
		sb.append(message.getJMSMessageID());
		sb.append("\'");

		return sb.toString();
	}

	/**
	 * Reenvia apenas as mensagens que devem ser reenviadas ou as mensagens que
	 * por algum motivo deram erro.
	 * 
	 * @param conteudoMensagem
	 *            A mensagem que será verificada.
	 * @return se a mensagem pode ser removida ou não. Ela poderá ser removida
	 *         quando for reenviada ou quando não é necessário o reenvio.
	 */
	public boolean reenviarMensagem(String conteudoMensagem) {
		boolean remover = true;
		try {
			if (conteudoMensagem != null) {
				List<MensagemReplicacao> replicacoes = obterMensagensReplicacao(conteudoMensagem);
				if ((replicacoes != null) && (!replicacoes.isEmpty())) {
					delegate.reenviarMensagens(replicacoes);
				} else {
					logger.alerta("Mensagem(ns) não localizada(s) na base de dados: ", conteudoMensagem);
				}
			}
		} catch (MensagemInvalidaException e) {
			logger.erro(e, "Mensagem inválida");
		} catch (BancoobException e) {
			throw new BancoobRuntimeException("", e);
		} catch (Exception e) { //NOPMD
			logger.info("Mensagem morta: " + conteudoMensagem);
			logger.erro(e, "Erro ao reenviar a mensagem.");
			remover = false;
		}
		return remover;
	}

	/**
	 * Obter mensagens replicacao.
	 *
	 * @param textoMensagem o valor de texto mensagem
	 * @return List
	 * @throws MensagemInvalidaException lança a exceção MensagemInvalidaException
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<MensagemReplicacao> obterMensagensReplicacao(final String textoMensagem) throws MensagemInvalidaException, BancoobException {

		MensagemReplicacao filtro = obterFiltroRecuperacaoMensagens(textoMensagem);
		return delegate.listarMensagensNaoProcessadasPorFiltro(filtro);
	}

	/**
	 * Gera o filtro para recuperação das mensagens no banco de dados a partir
	 * do conteúdo da mensagem a ser reenviada
	 * 
	 * @param conteudoMensagem
	 *            o conteúdo da mensagem a ser reenviada
	 * @return o filtro
	 * @throws MensagemInvalidaException
	 *             caso não seja possível extrair as informações necessárias
	 *             para a geração do filtro
	 */
	private MensagemReplicacao obterFiltroRecuperacaoMensagens(String conteudoMensagem) throws MensagemInvalidaException {

		Integer idInstituicao = null;
		String dadosMsg[] = conteudoMensagem.split(MensagemReplicacao.SEPARADOR_MENSAGEM);
		if (dadosMsg.length != 2) {
			throw new MensagemInvalidaException(QUANTIDADE_PARAMETROS_INVALIDA, dadosMsg.length);
		}
		if (dadosMsg[0].length() != 36) {
			throw new MensagemInvalidaException(IDENTIFICADOR_OPERACAO_INVALIDO, dadosMsg[0]);
		}
		try {
			idInstituicao = Integer.valueOf(dadosMsg[1]);
		} catch (NumberFormatException excecao) {
			throw new MensagemInvalidaException(excecao, ID_INSTITUICAO_INVALIDO, dadosMsg[1]);
		}

		MensagemReplicacao filtro = new MensagemReplicacao();
		filtro.setIdentificadorOperacao(dadosMsg[0]);
		filtro.setIdInstituicao(idInstituicao);
		return filtro;
	}

	/**
	 * O método Limpar fila dlq consultas externas.
	 *
	 * @param fabricaConexoes o valor de fabrica conexoes
	 * @param dlqConsultasExternas o valor de dlq consultas externas
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void limparFilaDLQConsultasExternas(QueueConnectionFactory fabricaConexoes, Queue dlqConsultasExternas) throws BancoobException {
		logger.debug("Verificando mensagens na DLQ");
		NavegadorFilaJMS navegador = new NavegadorFilaJMS(fabricaConexoes, dlqConsultasExternas);
		try {
			navegador.conectar();

			List<Message> lista = navegador.obterListaMensagens();
			for (Message message : lista) {

				String selector = criarSelector(message);
				MessageConsumer consumer = navegador.criarConsumidor(selector);
				consumer.receive();
			}

		} catch (JMSException e) {
			logger.erro(e, "Erro ao processar DLQ de consultas externas.");
			throw new BancoobException(e);
		} finally {
			navegador.desconectar();
		}
	}

}
