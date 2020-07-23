package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobFabricaDelegates;

/**
 * Fabrica dos business delegates a serem usados pelo Sistema ServicoReplicacaoCadastroUnicoClientes.
 * 
 * @author Stefanini IT Solutions
 */
public final class CAPESServicoReplicacaoFabricaDelegates extends BancoobFabricaDelegates {

	/** Instancia do Fabrica de Delegates. */
	private static CAPESServicoReplicacaoFabricaDelegates fabrica;

	/**
	 * Retorna a fabrica de delegates a ser utilizada.
	 * 
	 * @return a fabrica de delegates a ser utilizada.
	 */
	public static CAPESServicoReplicacaoFabricaDelegates getInstance() {
		if (fabrica == null) {
			synchronized (CAPESServicoReplicacaoFabricaDelegates.class) {
				if (fabrica == null) {
					fabrica = new CAPESServicoReplicacaoFabricaDelegates();
				}
			}
		}
		return fabrica;
	}

	/**
	 * Construtor privado no-args da classe
	 */
	private CAPESServicoReplicacaoFabricaDelegates() {
	}
	
	/**
	 * Cria instancia de TransicaoReplicacaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TransicaoReplicacaoDelegate
	 */
	public TransicaoReplicacaoDelegate criarTransicaoReplicacaoDelegate() {
		return new TransicaoReplicacaoDelegate();
	}
	
	/**
	 * Cria instancia de ReplicarCadastroClientesDelegate.
	 *
	 * @return o delegate solicitado
	 * @see ReplicarCadastroClientesDelegate
	 */
	public ReplicarCadastroClientesDelegate criarReplicarCadastroClientesDelegate() {
		return new ReplicarCadastroClientesDelegate();
	}
	
	/**
	 * Cria instancia de VerificarDLQReplicacaoCadastroDelegate.
	 *
	 * @return o delegate solicitado
	 * @see VerificarDLQReplicacaoCadastroDelegate
	 */
	public VerificarDLQReplicacaoCadastroDelegate criarVerificarDLQReplicacaoCadastroDelegate() {
		return new VerificarDLQReplicacaoCadastroDelegate();
	}
	
	/**
	 * Cria instancia de ReplicacaoDominiosDelegate.
	 *
	 * @return o delegate solicitado
	 * @see ReplicacaoDominiosDelegate
	 */
	public ReplicacaoDominiosDelegate criarReplicacaoDominiosDelegate() {
		return new ReplicacaoDominiosDelegate();
	}
}