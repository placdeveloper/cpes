package br.com.sicoob.capes.cadastro.negocio.contexto;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.Synchronization;
import javax.transaction.TransactionSynchronizationRegistry;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobRuntimeException;

/**
 * A Classe GerenciadorTransacaoReplicacao.
 */
public class GerenciadorTransacaoReplicacao implements IGerenciadorTransacao {

	/** O atributo instancia. */
	private static IGerenciadorTransacao instancia;
	
	/** O atributo TRANSACOES. */
	private final Map<Object, String> TRANSACOES = Collections.synchronizedMap(new HashMap<Object, String>());

	/**
	 * Obter instancia.
	 *
	 * @return IGerenciadorTransacao
	 */
	public static IGerenciadorTransacao obterInstancia() {
		if (instancia == null) {
			synchronized (GerenciadorTransacaoReplicacao.class) {
				if (instancia == null) {
					instancia = new GerenciadorTransacaoReplicacao();
				}
			}
		}
		return instancia;
	}

	/**
	 * {@inheritDoc}
	 */
	public String obterIDTransacao() {
		String retorno = this.TRANSACOES.get(obterTransacao().getTransactionKey());
		if (StringUtils.isBlank(retorno)) {
			retorno = UUID.randomUUID().toString();
			this.TRANSACOES.put(obterTransacao().getTransactionKey(), retorno);
		}
		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	public String obter() {
		return TRANSACOES.get(obterTransacao().getTransactionKey());
	}

	/**
	 * {@inheritDoc}
	 */
	public void registrarSynchronization(Synchronization synchronization) {
		obterTransacao().registerInterposedSynchronization(synchronization);
	}

	/**
	 * {@inheritDoc}
	 */
	public void remover() {
		TRANSACOES.remove(obterTransacao().getTransactionKey());
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean existeTransacao() {
		return obterTransacao().getTransactionKey() != null;
	}

	/**
	 * Obter transacao.
	 *
	 * @return TransactionSynchronizationRegistry
	 */
	private TransactionSynchronizationRegistry obterTransacao() {
		try {
			return (TransactionSynchronizationRegistry) new InitialContext().lookup(JNDI_NAME_TRANSACTION_SYNCHRONIZATION_REGISTRY);
		} catch (NamingException e) {
			throw new BancoobRuntimeException("Erro ao obter a transacao.", e);
		}
	}
}