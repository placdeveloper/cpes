/*
 * SICOOB
 * 
 * CAPESComumFabricaDelegates.java(br.com.sicoob.capes.comum.negocio.delegates.CAPESComumFabricaDelegates)
 */
package br.com.sicoob.capes.comum.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobFabricaDelegates;

/**
 * Fabrica dos business delegates a serem usados pelo Sistema CAPESComum.
 * 
 * @author Stefanini IT Solutions
 */
public final class CAPESComumFabricaDelegates extends BancoobFabricaDelegates {

	/** Instancia do Fabrica de Delegates. */
	private static CAPESComumFabricaDelegates fabrica = new CAPESComumFabricaDelegates();

	/**
	 * Retorna a fabrica de delegates a ser utilizada.
	 * 
	 * @return a fabrica de delegates a ser utilizada.
	 */
	public static CAPESComumFabricaDelegates getInstance() {
		return fabrica;
	}

	/**
	 * Construtor privado no-args da classe
	 */
	private CAPESComumFabricaDelegates() {
	}

	/**
	 * Cria um novo objeto AnotacaoPessoaDelegate.
	 * 
	 * @return the anotacao pessoa delegate
	 */
	public AnotacaoPessoaDelegate criarAnotacaoPessoaDelegate() {

		return new AnotacaoPessoaDelegate();
	}
	
	/**
	 * Cria instancia de ClienteDelegate.
	 *
	 * @return o delegate solicitado
	 * @see ClienteDelegate
	 */
	public ClienteDelegate criarClienteDelegate(){
		return new ClienteDelegate();
	}
	
}