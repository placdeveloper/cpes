package br.com.sicoob.capes.cadastro.negocio.contexto;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.transaction.RollbackException;
import javax.transaction.Synchronization;
import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;

import org.apache.commons.lang.StringUtils;
import org.jboss.tm.TransactionManagerLocator;

import br.com.bancoob.excecao.BancoobRuntimeException;

/**
 * A Classe GerenciadorTransacaoReplicacao.
 */
public class GerenciadorTransacaoReplicacao implements IGerenciadorTransacao {

	/** O atributo instancia. */
	private static IGerenciadorTransacao instancia;
	
	/** O atributo TRANSACOES. */
	private final Map<Object, String> TRANSACOES = Collections.synchronizedMap(new HashMap<Object, String>());
	
	/** O atributo gerenciadorTransacao. */
	private final TransactionManager gerenciadorTransacao = TransactionManagerLocator.getInstance().locate();

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
		String retorno = this.TRANSACOES.get(obterTransacao());
		if (StringUtils.isBlank(retorno)) {
			retorno = UUID.randomUUID().toString();
			this.TRANSACOES.put(obterTransacao(), retorno);
		}
		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	public String obter() {
		return TRANSACOES.get(obterTransacao());
	}

	/**
	 * {@inheritDoc}
	 */
	public void registrarSynchronization(Synchronization synchronization) {
		try {
			obterTransacao().registerSynchronization(synchronization);
		} catch (IllegalStateException e) {
			throw new BancoobRuntimeException("Erro ao registrar o synchronization.", e);
		} catch (RollbackException e) {
			throw new BancoobRuntimeException("Erro ao registrar o synchronization.", e);
		} catch (SystemException e) {
			throw new BancoobRuntimeException("Erro ao registrar o synchronization.", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void remover() {
		TRANSACOES.remove(obterTransacao());
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean existeTransacao() {
		return obterTransacao() != null;
	}

	/**
	 * Obter transacao.
	 *
	 * @return Transaction
	 */
	private Transaction obterTransacao() {
		try {
			return gerenciadorTransacao.getTransaction();
		} catch (SystemException e) {
			throw new BancoobRuntimeException("Erro ao obter a transacao.", e);
		}
	}
}