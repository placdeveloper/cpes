package br.com.sicoob.capes.cadastro.negocio.contexto;

import javax.transaction.Synchronization;

/**
 * A Interface IGerenciadorTransacao.
 */
public interface IGerenciadorTransacao {
	
	/** O atributo JNDI_NAME_TRANSACTION_SYNCHRONIZATION_REGISTRY. */
	String JNDI_NAME_TRANSACTION_SYNCHRONIZATION_REGISTRY = "java:comp/TransactionSynchronizationRegistry";
	
	/**
	 * Obter id transacao.
	 *
	 * @return String
	 */
	String obterIDTransacao();
	
	/**
	 * Obter o id da transacao.
	 *
	 * @return String
	 */
	String obter();

	/**
	 * O método Registrar synchronization.
	 *
	 * @param synchronization o valor de synchronization
	 */
	void registrarSynchronization(Synchronization synchronization);
	
	/**
	 * O método Remover.
	 */
	void remover();
	
	/**
	 * Existe transacao.
	 *
	 * @return {@code true}, em caso de sucesso
	 */
	boolean existeTransacao();

}