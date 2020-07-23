/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.TextMessage;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.propriedades.RepositorioPropriedades;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.MensagemReplicacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.dto.MonitoracaoDLQsCapesDTO;
import br.com.sicoob.capes.cadastro.negocio.dto.MonitoracaoFilasCapesDTO;
import br.com.sicoob.capes.cadastro.negocio.excecao.MonitoracaoFilasException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.MonitoracaoFilasCapesServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.MonitoracaoFilasCapesServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.servicos.util.NavegadorFilaJMS;
import br.com.sicoob.capes.cadastro.negocio.servicos.util.ReenvioMensagemJMSReplicacao;
import br.com.sicoob.capes.cadastro.negocio.vo.MensagemVO;
import br.com.sicoob.capes.cadastro.negocio.vo.MonitoracaoFilaCapesVO;
import br.com.sicoob.capes.cadastro.negocio.vo.MonitoracaoMensagensVO;

/**
 * @author erico.junior
 *
 */
@Stateless
@Local( { MonitoracaoFilasCapesServicoLocal.class })
@Remote( { MonitoracaoFilasCapesServicoRemote.class })
public class MonitoracaoFilasCapesServicoEJB extends CAPESCadastroServicoEJB 
	implements MonitoracaoFilasCapesServicoRemote, MonitoracaoFilasCapesServicoLocal {

	/** A constante NOME_MENSAGENS_NAO_PROCESSADAS. */
	private static final String NOME_MENSAGENS_NAO_PROCESSADAS = "MENSAGENS NÃO PROCESSADAS";

	/** A constante NOME_MENSAGENS_NAO_ENVIADAS. */
	private static final String NOME_MENSAGENS_NAO_ENVIADAS = "MENSAGENS NÃO ENVIADAS";

	/** O atributo fabrica conexoes. */
	@Resource(mappedName = "WSMQCAPESQueueConnectionFactory")
	private javax.jms.QueueConnectionFactory fabricaConexoes;

	/** O atributo fila atualiza cadastro. */
	@Resource(mappedName = "queue/QL.CONT.EXEC.ATUALIZA.CADASTRO")
	private javax.jms.Queue filaAtualizaCadastro;

	/** O atributo dlqAtualizaCadastro. */
	@Resource(mappedName = "queue/QL.CONT.EXEC.ATUALIZA.CADASTRO.DLQ")
	private javax.jms.Queue dlqAtualizaCadastro;

	/** O atributo filaConsultasExternas. */
	@Resource(mappedName = "queue/QL.CONT.EXEC.CONS.EXT")
	private javax.jms.Queue filaConsultasExternas;

	/** O atributo dlqConsultasExternas. */
	@Resource(mappedName = "queue/QL.CONT.EXEC.CONS.EXT.DLQ")
	private javax.jms.Queue dlqConsultasExternas;

	/** O atributo propriedades. */
	private transient Properties propriedades = 
			RepositorioPropriedades.getInstance().recuperarPropriedades("capes.cadastro.propriedades");
	
	/** O atributo mensagemDelegate. */
	private MensagemReplicacaoDelegate mensagemDelegate = CAPESCadastroFabricaDelegates
			.getInstance().criarMensagemReplicacaoDelegate();

	/** A constante NIVEL_NORMAL. */
	private static final int NIVEL_NORMAL = 1; 
	
	/** A constante NIVEL_ATENCAO. */
	private static final int NIVEL_ATENCAO = 2;
	
	/** A constante NIVEL_CRITICO. */
	private static final int NIVEL_CRITICO = 3;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public MonitoracaoFilasCapesDTO monitorarFilas() throws BancoobException{
		
		MonitoracaoFilasCapesDTO dto = new MonitoracaoFilasCapesDTO();
		
		try {
			
			MonitoracaoFilaCapesVO filaConsultas = monitorarFila(filaConsultasExternas, true);
			MonitoracaoFilaCapesVO dlqConsultas = monitorarDLQ(dlqConsultasExternas, true, false);
			
			MonitoracaoFilaCapesVO filaReplicacao = monitorarFila(filaAtualizaCadastro);
			MonitoracaoFilaCapesVO dlqReplicacao = monitorarFila(dlqAtualizaCadastro, true, false, false);
			
			MonitoracaoFilaCapesVO msgNaoEnviadas = monitorarMensagensNaoEnviadas();
			MonitoracaoFilaCapesVO msgNaoProcessadas = monitorarMensagensNaoProcessadas();

			dto.setDlqConsultasExternas(dlqConsultas);
			dto.setFilaConsultasExternas(filaConsultas);
			dto.setDlqReplicacao(dlqReplicacao);
			dto.setFilaReplicacao(filaReplicacao);
			dto.setMensagensNaoEnviadas(msgNaoEnviadas);
			dto.setMensagensNaoProcessadas(msgNaoProcessadas);
			dto.setDataConsulta(new Date());
			
		} catch (BancoobException e) {

			throw new MonitoracaoFilasException(e);
		}

		return dto;
	}
	
	
	/**
	 * Monitorar fila.
	 *
	 * @param fila o valor de fila
	 * @param isDLQ o valor de is dlq
	 * @param devePreencherLista o valor de deve preencher lista
	 * @return MonitoracaoFilaCapesVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private MonitoracaoFilaCapesVO monitorarFila(Queue fila, boolean isDLQ, boolean isConsultasExternas, boolean devePreencherLista) 
			throws BancoobException {
		
		MonitoracaoFilaCapesVO vo = new MonitoracaoFilaCapesVO();
		NavegadorFilaJMS navegador = new NavegadorFilaJMS(fabricaConexoes, fila);
		
		try {

			navegador.conectar();
			List<Message> mensagens = navegador.obterListaMensagens();

			Date dataPrimeira = null;
			Date dataUltima = null;
			int qtdMensagens = mensagens.size();
			
			if(qtdMensagens > 0) {
				dataPrimeira = new Date(mensagens.get(0).getJMSTimestamp());
				dataUltima = new Date(mensagens.get(qtdMensagens-1).getJMSTimestamp());
			}

			vo.setDataPrimeira(dataPrimeira);
			vo.setDataUltima(dataUltima);
			vo.setNomeFila(fila.getQueueName());
			vo.setQuantidadeMensagens(qtdMensagens);
			vo.setNivelAlerta(obterNivelAlerta(dataPrimeira, qtdMensagens, isDLQ, isConsultasExternas));
		
			if(devePreencherLista) {
				vo.setMensagens(obterListaMensagens(mensagens, 50));
			}
			
		} catch (JMSException e) {
			getLogger().erro(e, "Não foi possível conectar nas filas");
			throw new BancoobException(e);
		} finally {
			navegador.desconectar();
		}		

		return vo;
	}
	
	/**
	 * Obter lista mensagens.
	 *
	 * @param mensagens o valor de mensagens
	 * @param quantidade o valor de quantidade
	 * @return List
	 * @throws JMSException lança a exceção JMSException
	 */
	private List<MensagemVO> obterListaMensagens(List<Message> mensagens, int quantidade) throws JMSException {
		
		List<MensagemVO> listaRetorno = new ArrayList<MensagemVO>();
		
		if(mensagens != null && !mensagens.isEmpty()) {
			
			MensagemVO vo = null;
			TextMessage message = null;
			int total = mensagens.size();
			
			for (int i = total - 1; i >= 0 && i >= total - quantidade; i--) {
				message = (TextMessage) mensagens.get(i);
				
				vo = new MensagemVO();
				vo.setConteudo(message.getText());
				vo.setDataHora(new Date(message.getJMSTimestamp()));
				listaRetorno.add(vo);
			}
		}
		
		return listaRetorno;
	}

	/**
	 * Obter nivel alerta.
	 *
	 * @param dataPrimeira o valor de data primeira
	 * @param quantidadeMensagens o valor de quantidade mensagens
	 * @param isDLQ o valor de is dlq
	 * @return int
	 */
	private int obterNivelAlerta(Date dataPrimeira, int quantidadeMensagens, boolean isDLQ, boolean isConsultasExternas){
		int nivelAlerta = NIVEL_NORMAL;
		Date dataAtual = new Date();
		int qtdAtencao = 0;
		int qtdCritico = 0;
		int tempoAtencao = 0;
		int tempoCritico = 0;
		
		if (isDLQ) {
			if (isConsultasExternas) {
				qtdAtencao = Integer.valueOf(propriedades.getProperty("quantidade.mensagens.atencao.consultasExternas.dlq"));
				qtdCritico = Integer.valueOf(propriedades.getProperty("quantidade.mensagens.critico.consultasExternas.dlq"));
			} else {
				qtdAtencao = Integer.valueOf(propriedades.getProperty("quantidade.mensagens.atencao.dlq"));
				qtdCritico = Integer.valueOf(propriedades.getProperty("quantidade.mensagens.critico.dlq"));
			}

			tempoAtencao = Integer.valueOf(propriedades.getProperty("tempo.mensagem.atencao.dlq"));
			tempoCritico = Integer.valueOf(propriedades.getProperty("tempo.mensagem.critico.dlq"));
		} else {
			if (isConsultasExternas) {
				qtdAtencao = Integer.valueOf(propriedades.getProperty("quantidade.mensagens.atencao.consultasExternas"));
				qtdCritico = Integer.valueOf(propriedades.getProperty("quantidade.mensagens.critico.consultasExternas"));
			} else {
				qtdAtencao = Integer.valueOf(propriedades.getProperty("quantidade.mensagens.atencao.fila"));
				qtdCritico = Integer.valueOf(propriedades.getProperty("quantidade.mensagens.critico.fila"));
			}

			tempoAtencao = Integer.valueOf(propriedades.getProperty("tempo.mensagem.atencao.fila"));
			tempoCritico = Integer.valueOf(propriedades.getProperty("tempo.mensagem.critico.fila"));
		}
		
		if(quantidadeMensagens > qtdCritico || deveAlertarPorTempo(dataAtual, dataPrimeira, tempoCritico)) {
			nivelAlerta = NIVEL_CRITICO;
		} else if(quantidadeMensagens > qtdAtencao || deveAlertarPorTempo(dataAtual, dataPrimeira, tempoAtencao)) {
			nivelAlerta = NIVEL_ATENCAO;
		}
		
		return nivelAlerta;
	}

	/**
	 * Deve alertar por tempo.
	 *
	 * @param dataAtual o valor de data atual
	 * @param dataPrimeiraMensagem o valor de data primeira mensagem
	 * @param segundos o valor de segundos
	 * @return {@code true}, em caso de sucesso
	 */
	private boolean deveAlertarPorTempo(Date dataAtual, Date dataPrimeiraMensagem, int segundos) {
		
		boolean deveAlertar = false;
		
		if(dataPrimeiraMensagem != null) {
			Date dataLimite = DataUtil.incrementarData(dataPrimeiraMensagem, Calendar.SECOND, segundos);
			deveAlertar = dataLimite.before(dataAtual);
		}
		
		return deveAlertar;
	}

	/**
	 * Monitorar fila.
	 *
	 * @param fila o valor de fila
	 * @return MonitoracaoFilaCapesVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private MonitoracaoFilaCapesVO monitorarFila(Queue fila) throws BancoobException {
		return monitorarFila(fila, false, false, false);
	}
	
	/**
	 * Monitorar fila.
	 *
	 * @param fila o valor de fila
	 * @param isConsultasExternas se a fila é do consultas externas
	 * @return MonitoracaoFilaCapesVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private MonitoracaoFilaCapesVO monitorarFila(Queue fila, boolean isConsultasExternas) throws BancoobException {
		return monitorarFila(fila, false, isConsultasExternas, false);
	}
	
	/**
	 * Monitorar dlq.
	 *
	 * @param fila o valor de fila
	 * @return MonitoracaoFilaCapesVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private MonitoracaoFilaCapesVO monitorarDLQ(Queue fila) throws BancoobException {
		return monitorarFila(fila, true, false, true);
	}
	
	/**
	 * Monitorar dlq.
	 *
	 * @param fila o valor de fila
	 * @return MonitoracaoFilaCapesVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private MonitoracaoFilaCapesVO monitorarDLQ(Queue fila, boolean isConsultasExternas, boolean devePreencherLista) throws BancoobException {
		return monitorarFila(fila, true, isConsultasExternas, devePreencherLista);
	}

	/**
	 * Monitorar mensagens nao enviadas.
	 *
	 * @return MonitoracaoFilaCapesVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private MonitoracaoFilaCapesVO monitorarMensagensNaoEnviadas() throws BancoobException {
		
		return converterVO(mensagemDelegate.monitorarMensagensNaoEnviadas(),
				NOME_MENSAGENS_NAO_ENVIADAS);
	}

	/**
	 * Monitorar mensagens nao processadas.
	 *
	 * @return MonitoracaoFilaCapesVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private MonitoracaoFilaCapesVO monitorarMensagensNaoProcessadas() throws BancoobException {
		return converterVO(mensagemDelegate.monitorarMensagensNaoProcessadas(),
				NOME_MENSAGENS_NAO_PROCESSADAS);
	}
	
	/**
	 * Converter vo.
	 *
	 * @param origem o valor de origem
	 * @param nomeFila o valor de nome fila
	 * @return MonitoracaoFilaCapesVO
	 */
	private MonitoracaoFilaCapesVO converterVO(MonitoracaoMensagensVO origem, String nomeFila) {
		
		MonitoracaoFilaCapesVO destino = new MonitoracaoFilaCapesVO();
		destino.setDataPrimeira(origem.getDataPrimeira());
		destino.setDataUltima(origem.getDataUltima());
		destino.setQuantidadeMensagens(origem.getQuantidadeMensagens());
		destino.setNomeFila(nomeFila);
		destino.setNivelAlerta(obterNivelAlerta(destino.getDataPrimeira(),
				destino.getQuantidadeMensagens(), false, false));
		return destino;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public MonitoracaoDLQsCapesDTO monitorarDLQs() throws BancoobException{
		
		MonitoracaoDLQsCapesDTO dto = new MonitoracaoDLQsCapesDTO();
		
		try {
			
			MonitoracaoFilaCapesVO dlqConsultas = monitorarDLQ(dlqConsultasExternas, true, true);
			MonitoracaoFilaCapesVO dlqReplicacao = monitorarDLQ(dlqAtualizaCadastro);

			dto.setDlqConsultasExternas(dlqConsultas);
			dto.setDlqReplicacao(dlqReplicacao);
			dto.setDataConsulta(new Date());
			
		} catch (BancoobException e) {
			throw new MonitoracaoFilasException(e);
		}

		return dto;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("rawtypes")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void reprocessarDLQCadastro() throws BancoobException {
		ReenvioMensagemJMSReplicacao<?> reenvio = new ReenvioMensagemJMSReplicacao();
		reenvio.processarMensagens(fabricaConexoes, dlqAtualizaCadastro);
	}	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("rawtypes")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void limparFilaDLQConsultasExternas() throws BancoobException {
		ReenvioMensagemJMSReplicacao<?> reenvio = new ReenvioMensagemJMSReplicacao();
		reenvio.limparFilaDLQConsultasExternas(fabricaConexoes, dlqConsultasExternas);
	}

}
