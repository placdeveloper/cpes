package br.com.sicoob.sisbr.capes.anotacoes.testes;

import java.io.Serializable;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;

import br.com.sicoob.capes.cadastro.util.SerializacaoUtils;

/**
 * @author Marcelo.Onofre
 *
 */
public abstract class AbstractTestAtualizarAnotacoes {
	
	/** O atributo sessaoFila. */
	private QueueSession sessaoFila;
	
	/** O atributo conexaoFila. */
	private QueueConnection conexaoFila;
	
	/** O atributo publicador. */
	private QueueSender publicador;

	/**
	 * Obter nome destino.
	 *
	 * @return String
	 */
	protected abstract String obterNomeDestino();
	
	/**
	 * Obter nome fabrica.
	 *
	 * @return String
	 */
	protected abstract String obterNomeFabrica();
	
	/**
	 * Estabelece a conexão e inicia uma sessão JMS
	 * 
	 * @throws Exception
	 */
	protected void estabelecerConexao() throws Exception {
		try {
			String destinationName = obterNomeDestino();
			
			InitialContext contexto = getInitialContext();
			ConnectionFactory fabrica = (ConnectionFactory) contexto.lookup(obterNomeFabrica());
			Connection conexao = ((QueueConnectionFactory) fabrica).createQueueConnection();
			
			conexaoFila = (QueueConnection) conexao;
			sessaoFila = (QueueSession) conexaoFila
					.createQueueSession(true, Session.AUTO_ACKNOWLEDGE);
			Queue filaJMS = sessaoFila.createQueue(destinationName);
			publicador = sessaoFila.createSender(filaJMS);
			
		} catch (Exception excecao) {
			throw new Exception("Erro ao se estabelecer a conexão.", excecao);
		}
	}
	
	/**
	 * O método Finalizar conexao.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	protected void finalizarConexao() throws Exception {
		publicador.close();
		sessaoFila.close();
		conexaoFila.close();
	}
	
	/**
	 * Método que espera 10 segundos dentro de um {@link Thread}
	 */
	protected void esperarExecucaoAnterior() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * contexto inicial de conexão remota via RMI.
	 *  
	 * @return O contexo inicial
	 * @throws javax.naming.NamingException
	 */
	private InitialContext getInitialContext() throws javax.naming.NamingException {
		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
		p.put(Context.URL_PKG_PREFIXES, " org.jboss.naming:org.jnp.interfaces");
		p.put(Context.PROVIDER_URL, "jnp://localhost:1099");
		return new javax.naming.InitialContext(p);
	} 
	
	/**
	 * Método que envia uma mensagem JMS
	 * 
	 * @param dto Dto com o retorno de uma consulta externa realizada pela receita (Mock)
	 */
	protected void send(Serializable dto){
		try {
			String jsonMsg = SerializacaoUtils.serilizarJson(dto);
			
			Message mensagemTexto = sessaoFila.createTextMessage(jsonMsg);
			publicador.send(mensagemTexto);				
			sessaoFila.commit();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}		
}