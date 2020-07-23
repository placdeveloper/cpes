package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.capes.negocio.entidades.legado.BancoServidor;
import br.com.sicoob.capes.negocio.entidades.legado.TransicaoReplicacao;
import br.com.sicoob.capes.replicacao.negocio.delegates.CAPESServicoReplicacaoFabricaDelegates;
import br.com.sicoob.capes.replicacao.negocio.delegates.TransicaoReplicacaoDelegate;
import br.com.sicoob.capes.replicacao.negocio.excecao.ModeloDeDadosInvalidoException;
import br.com.sicoob.capes.replicacao.negocio.servicos.command.ReplicacaoCommand;
import br.com.sicoob.capes.replicacao.negocio.servicos.command.ReplicacaoCommandFactory;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.ReplicarCadastroClientesServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.ReplicarCadastroClientesServicoRemote;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.TransicaoReplicacaoServicoLocal;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;

/**
 * EJB que implementa o serviço de replicação.
 * 
 * @author Juan.Damasceno
 *
 */
@Stateless
@Local({ ReplicarCadastroClientesServicoLocal.class })
@Remote({ ReplicarCadastroClientesServicoRemote.class })
public class ReplicarCadastroClientesServicoEJB extends CAPESServicoReplicacaoServicoEJB implements ReplicarCadastroClientesServicoRemote, ReplicarCadastroClientesServicoLocal {

	/** O atributo logger. */
	private ISicoobLogger logger = getLogger();
	
	/** O atributo transicaoReplicacaoServico. */
	@EJB(mappedName = "capes/replicacao/TransicaoReplicacaoServico")
	private TransicaoReplicacaoServicoLocal transicaoReplicacaoServico;
	
	@EJB
	private ReplicarCadastroClientesServicoLocal servicoLocal;
	
	/**
	 * {@inheritDoc}
	 * @throws BancoobException 
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void replicaCadastroClientes() throws BancoobException {
		logger.info("Replicacao do cadastro de clientes iniciada...");

		ReplicacaoCommandFactory commandFactory = ReplicacaoCommandFactory.getInstance();
		commandFactory.criarDelegates();

		TransicaoReplicacaoDelegate transicaoReplicacaoDelegate = getTransicaoReplicacaoDelegate();
		List<TransicaoReplicacao> transicoesNaoReplicadas = transicaoReplicacaoDelegate.listarNaoReplicados();

		while (!transicoesNaoReplicadas.isEmpty()) {
			for (TransicaoReplicacao transicaoReplicacao : transicoesNaoReplicadas) {
				try {
					servicoLocal.replicar(transicaoReplicacao);
				} catch (Exception e) { //NOPMD
					registrarErro(transicaoReplicacao, e);
					servicoLocal.atualizar(transicaoReplicacao);
				}
			}
			transicoesNaoReplicadas = transicaoReplicacaoDelegate.listarNaoReplicados();
		}
		
		logger.info("Replicacao do cadastro de clientes finalizada.");
	}

	/**
	 * @param transicaoReplicacao
	 * @param e
	 */
	private void registrarErro(TransicaoReplicacao transicaoReplicacao, Exception e) {
		logger.erro(e, "Erro replicando dados da pessoa " + transicaoReplicacao.getIdPessoa() + ", tipo operacao : " + transicaoReplicacao.getTipoOperacao());
		transicaoReplicacao.setDescricaoErro(e.getMessage());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void atualizar(TransicaoReplicacao transicaoReplicacao) {
		try {
			transicaoReplicacao.setDataProcessamento(DataUtil.obterDataAtual());
			getTransicaoReplicacaoDelegate().alterar(transicaoReplicacao);
		} catch (Exception e) { // NOPMD
			logger.erro(e, "Erro atualizando TransicaoReplicacao.");
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void replicar(TransicaoReplicacao transicaoReplicacao) throws BancoobException {
		ReplicacaoCommand replicacaoCommand = criarReplicacaoCommand(transicaoReplicacao);
		replicacaoCommand.execute(transicaoReplicacao);
	}
	
	/**
	 * Cria uma instância ReplicacaoCommand dependendo da operação a ser efetuada.
	 * @param transicaoReplicacao a entidade que será replicada.
	 * @return uma instancia ReplicacaoCommand.
	 */
	private ReplicacaoCommand criarReplicacaoCommand(TransicaoReplicacao transicaoReplicacao) {
		ReplicacaoCommandFactory commandFactory = ReplicacaoCommandFactory.getInstance();
		return commandFactory.createCommand(transicaoReplicacao.getTipoOperacao());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void centralizaTransicaoReplicacao(BancoServidor bancoServidor) throws BancoobException {
		logger.info("Centralizacao do cadastro de clientes iniciada...");

		try {
			centraliza(bancoServidor);
		} catch (ModeloDeDadosInvalidoException e) {
			/* Caso o banco ou a tabela não exista, o próximo banco do servidor será utilizado*/
			registrarErroBanco(bancoServidor, e);
		} catch (BancoobException e) {
			registrarErroCentralizacao(bancoServidor, e);
			throw e;
		}

		logger.info("Centralizacao do cadastro de clientes finalizada.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void centraliza(BancoServidor bancoServidor) throws BancoobException {
		logger.debug("Marcando transicoes do banco " , bancoServidor.toString(), " para serem centralizadas");
		int qtdRegistrosAfetados = transicaoReplicacaoServico.marcaTransicoesReplicacaoParaCentralizacao(bancoServidor);
		
		if (qtdRegistrosAfetados > 0) {
			logger.debug("Centralizando transicoes do banco ", bancoServidor.toString());
			transicaoReplicacaoServico.centralizaTransicoesReplicacao(bancoServidor);
			
			logger.debug("Removendo transicoes do banco " , bancoServidor.toString());
			transicaoReplicacaoServico.removeTransicoesReplicacaoCentralizadas(bancoServidor);
		}
	}

	/**
	 * Registra o erro que possa ocorrer durante a centralização do cadastro.
	 * 
	 * @param bancoServidor
	 *            instancia de BancoServidor que contem as informações sobre o banco de dados.
	 * @param e
	 *            Exceção que possa ocorrer.
	 */
	private void registrarErroCentralizacao(BancoServidor bancoServidor, Exception e) {
		registrarErroBanco(bancoServidor, e);
		logger.alerta("O servidor " + bancoServidor.getNomeServer() + " sera utilizado apenas" + " no proximo agendamento da tarefa.");
	}

	/**
	 * O método Registrar erro banco.
	 *
	 * @param bancoServidor o valor de banco servidor
	 * @param e o valor de e
	 */
	private void registrarErroBanco(BancoServidor bancoServidor, Exception e) {
		logger.erro(e, "Erro centralizando as transições do banco " + bancoServidor);
	}

	/**
	 * Retorna o delegate de TransicaoReplicacaoDelegate.
	 * @return o delegate de TransicaoReplicacaoDelegate.
	 */
	protected TransicaoReplicacaoDelegate getTransicaoReplicacaoDelegate() {
		return getFabricaDelegates().criarTransicaoReplicacaoDelegate();
	}

	/**
	 * Retorna uma instancia de ServicoReplicacaoCadastroUnicoClientesFabricaDelegates
	 * @return uma instancia de ServicoReplicacaoCadastroUnicoClientesFabricaDelegates
	 */
	private CAPESServicoReplicacaoFabricaDelegates getFabricaDelegates() {
		return CAPESServicoReplicacaoFabricaDelegates.getInstance();
	}
}
