package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobFabricaDelegates;

/**
 * Fabrica dos business delegates a serem usados pelo Sistema ReplicacaoClientesProcessamento.
 * 
 * @author Stefanini IT Solutions
 */
public class CAPESReplicacaoProcessamentoFabricaDelegates extends BancoobFabricaDelegates {

	/** Instancia do Fabrica de Delegates. */
	public static CAPESReplicacaoProcessamentoFabricaDelegates fabrica;

	/**
	 * Retorna a fabrica de delegates a ser utilizada.
	 * 
	 * @return a fabrica de delegates a ser utilizada.
	 */
	public static CAPESReplicacaoProcessamentoFabricaDelegates getInstance() {
		if (fabrica == null) {
			synchronized (CAPESReplicacaoProcessamentoFabricaDelegates.class) {
				if (fabrica == null) {
					fabrica = new CAPESReplicacaoProcessamentoFabricaDelegates();
				}
			}
		}
		return fabrica;
	}

	/**
	 * Construtor privado no-args da classe
	 */
	private CAPESReplicacaoProcessamentoFabricaDelegates() {
	}
	
	/**
	 * Cria instancia de AtualizarCadastroClienteDelegate.
	 *
	 * @return o delegate solicitado
	 * @see AtualizarCadastroClienteDelegate
	 */
	public AtualizarCadastroClienteDelegate criarAtualizarCadastroClienteDelegate() {
		return new AtualizarCadastroClienteDelegate();
	}
}