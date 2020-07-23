/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.util;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;

import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * Objeto utilizado para navegar em filas JMS.
 * @author Erico.Junior
 */
public class NavegadorFilaJMS {

	/** O atributo logger. */
	private final ISicoobLogger logger = SicoobLoggerPadrao.getInstance(this.getClass());

	/** O atributo conexaoFila. */
	private QueueConnection conexaoFila;
	
	/** O atributo sessaoFila. */
	private QueueSession sessaoFila;
	
	/** O atributo navegador. */
	private QueueBrowser navegador;

	/** O atributo fila. */
	private final Queue fila;
	
	/** O atributo nomeFila. */
	private String nomeFila;
	
	/** O atributo fabricaConexoes. */
	private final QueueConnectionFactory fabricaConexoes;

	/**
	 * Construtor.
	 * @param fabricaConexoes
	 *            A fábrica de conexões a ser usada para criar a conexão com a fila.
	 * @param fila
	 *            A fila na qual se vai conectar.
	 */
	public NavegadorFilaJMS(QueueConnectionFactory fabricaConexoes, Queue fila) {
		this.fila = fila;
		this.fabricaConexoes = fabricaConexoes;
	}

	/**
	 * Conecta com a fila.
	 * @throws JMSException
	 *             Erro ao conectar com a fila.
	 */
	public void conectar() throws JMSException {
		try {

			nomeFila = fila.getQueueName();
			conexaoFila = fabricaConexoes.createQueueConnection();
			conexaoFila.start();
			sessaoFila = conexaoFila.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			navegador = sessaoFila.createBrowser(fila);
			
		} catch (JMSException jmsException) {
			logger.erro(jmsException, "Erro ao conectar na fila ('" + nomeFila + "').");
			throw jmsException;
		}
	}

	/**
	 * Fecha os recursos de conexão com a fila.
	 */
	public void desconectar() {
		try {
			if (navegador != null) {
				navegador.close();
			}

			if (sessaoFila != null) {
				sessaoFila.close();
			}

			if (conexaoFila != null) {
				conexaoFila.close();
			}
		} catch (JMSException jmsException) {
			logger.erro(jmsException, "Erro ao fechar recursos de conexão com a fila ('" 
					+ nomeFila + "').");
			throw new BancoobRuntimeException(jmsException);
		}
	}

	/**
	 * Obter mensagens.
	 *
	 * @return Enumeration
	 * @throws JMSException lança a exceção JMSException
	 */
	@SuppressWarnings("unchecked")
	public Enumeration<Message> obterMensagens() throws JMSException {
		return navegador.getEnumeration();
	}

	/**
	 * Obter lista mensagens.
	 *
	 * @return List
	 * @throws JMSException lança a exceção JMSException
	 */
	@SuppressWarnings("unchecked")
	public List<Message> obterListaMensagens() throws JMSException {
		return Collections.list(navegador.getEnumeration()); 
	}
	
	/**
	 * Criar consumidor.
	 *
	 * @param selector o valor de selector
	 * @return MessageConsumer
	 * @throws JMSException lança a exceção JMSException
	 */
	public MessageConsumer criarConsumidor(String selector) throws JMSException {
		return sessaoFila.createConsumer(fila, selector);
	}
}
