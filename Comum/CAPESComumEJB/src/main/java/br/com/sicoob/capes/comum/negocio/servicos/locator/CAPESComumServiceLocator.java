/*
 * SICOOB
 * 
 * CAPESComumServiceLocator.java(br.com.sicoob.capes.comum.negocio.servicos.locator.CAPESComumServiceLocator)
 */
package br.com.sicoob.capes.comum.negocio.servicos.locator;

import br.com.bancoob.negocio.servicos.locator.BancoobServiceLocator;
import br.com.sicoob.capes.comum.negocio.servicos.AnotacaoPessoaServico;
import br.com.sicoob.capes.comum.negocio.servicos.ClienteServico;

/**
 * Service Locator usado pelo sistema CAPESComum.
 * 
 * @author Stefanini IT Solutions
 */
public final class CAPESComumServiceLocator extends BancoobServiceLocator {

	/** O atributo locator. */
	private static CAPESComumServiceLocator locator = new CAPESComumServiceLocator();

	/**
	 * Singleton da class
	 * 
	 * @return A instancia da classe
	 */
	public static CAPESComumServiceLocator getInstance() {

		return locator;
	}

	/**
	 * @param nomeAplicacao
	 */
	private CAPESComumServiceLocator() {

		super("capes.comum");
	}

	/**
	 * Localiza o EJB que implementa AnotacaoPessoaServico.
	 * 
	 * @return the anotacao pessoa servico
	 */
	public AnotacaoPessoaServico localizarAnotacaoPessoaServico() {

		return (AnotacaoPessoaServico) localizar("locator.capes.AnotacaoPessoaServico");
	}

	/**
	 * Localiza o EJB que implementa o ClienteServico.
	 * 
	 * @return o serviço de clientes
	 */
	public ClienteServico localizarClienteServico() {
		return (ClienteServico) localizar("locator.capes.ClienteServico");
	}
	
}