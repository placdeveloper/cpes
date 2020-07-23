package br.com.sicoob.capes.cadastro.persistencia;

import javax.transaction.Status;
import javax.transaction.Synchronization;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.contexto.IGerenciadorTransacao;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.MensagemReplicacaoDelegate;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * A Classe ReplicacaoSynchronization.
 */
public class ReplicacaoSynchronization implements Synchronization {

	/** O atributo logger. */
	private final SicoobLoggerPadrao logger = SicoobLoggerPadrao.getInstance(ReplicacaoSynchronization.class);
	
	/** O atributo idOperacao. */
	private String idOperacao;

	/**
	 * Instancia um novo ReplicacaoSynchronization.
	 *
	 * @param idOperacao o valor de id operacao
	 */
	public ReplicacaoSynchronization(String idOperacao) {
		this.idOperacao = idOperacao;
	}

	/* @see javax.transaction.Synchronization#beforeCompletion() */
	/**
	 * {@inheritDoc}
	 */
	public void beforeCompletion() {
	}

	/* @see javax.transaction.Synchronization#afterCompletion(int) */
	/**
	 * {@inheritDoc}
	 */
	public void afterCompletion(final int status) {
		logger.debug("Transacao concluida: status=", obterDescricaoStatus(status), ", identificadorOperacao=", idOperacao);
		IGerenciadorTransacao gerenciador = obterGerenciadorTransacao();
		if (status == Status.STATUS_COMMITTED) {
			try {
				MensagemReplicacaoDelegate delegate = CAPESCadastroFabricaDelegates.getInstance().criarMensagemReplicacaoDelegate();
				delegate.enviarMensagens(idOperacao);
			} catch (BancoobException e) {
				logger.erro(e, "Erro no envio das mensagens: " + idOperacao);
			} finally {
				gerenciador.remover();
			}
		} else if ((status == Status.STATUS_NO_TRANSACTION) || (status == Status.STATUS_ROLLEDBACK)) {
			gerenciador.remover();
		}
	}

	/**
	 * Obter descricao status.
	 *
	 * @param status o valor de status
	 * @return String
	 */
	private String obterDescricaoStatus(final int status) {
		String desc = String.valueOf(status);
		switch (status) {
		case Status.STATUS_ACTIVE:
			desc += " (STATUS_ACTIVE)";
			break;
		case Status.STATUS_COMMITTED:
			desc += " (STATUS_COMMITTED)";
			break;
		case Status.STATUS_COMMITTING:
			desc += " (STATUS_COMMITTING)";
			break;
		case Status.STATUS_MARKED_ROLLBACK:
			desc += " (STATUS_MARKED_ROLLBACK)";
			break;
		case Status.STATUS_NO_TRANSACTION:
			desc += " (STATUS_NO_TRANSACTION)";
			break;
		case Status.STATUS_PREPARED:
			desc += " (STATUS_PREPARED)";
			break;
		case Status.STATUS_PREPARING:
			desc += " (STATUS_PREPARING)";
			break;
		case Status.STATUS_ROLLEDBACK:
			desc += " (STATUS_ROLLEDBACK)";
			break;
		case Status.STATUS_ROLLING_BACK:
			desc += " (STATUS_ROLLING_BACK)";
			break;
		case Status.STATUS_UNKNOWN:
		default:
			desc += " (STATUS_UNKNOWN)";
		}
		return desc;
	}
	
	/**
	 * Obter gerenciador transacao.
	 *
	 * @return IGerenciadorTransacao
	 */
	private IGerenciadorTransacao obterGerenciadorTransacao() {
		return ReflexaoUtils.construirSingletonPorClasse("br.com.sicoob.capes.cadastro.negocio.contexto.GerenciadorTransacaoReplicacao", "obterInstancia");
	}

}