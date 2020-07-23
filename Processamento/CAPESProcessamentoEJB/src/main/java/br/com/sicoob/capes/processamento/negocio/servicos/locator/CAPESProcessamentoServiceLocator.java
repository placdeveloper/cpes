/*
 * SICOOB
 * 
 * CAPESProcessamentoServiceLocator.java(br.com.sicoob.capes.processamento.negocio.servicos.locator.CAPESProcessamentoServiceLocator)
 */
package br.com.sicoob.capes.processamento.negocio.servicos.locator;

import br.com.bancoob.negocio.servicos.locator.BancoobServiceLocator;
import br.com.sicoob.capes.processamento.negocio.servicos.AtualizarAnotacoesConsultasExternasServico;
import br.com.sicoob.capes.processamento.negocio.servicos.InclusaoBeneficiariosINSSServico;
import br.com.sicoob.capes.processamento.negocio.servicos.ProdutoServico;

/**
 * Service Locator usado pelo sistema CAPESProcessamento.
 * 
 * @author Stefanini IT Solutions
 */
public final class CAPESProcessamentoServiceLocator extends BancoobServiceLocator {

	/** O atributo locator. */
	private static CAPESProcessamentoServiceLocator locator = new CAPESProcessamentoServiceLocator();

	/**
	 * Singleton da class
	 * 
	 * @return A instancia da classe
	 */
	public static CAPESProcessamentoServiceLocator getInstance() {
		return locator;
	}

	/**
	 * @param nomeAplicacao
	 */
	private CAPESProcessamentoServiceLocator() {
		super("capes.processamento");
	}
	
	/**
	 * Localiza o EJB que implementa AtualizarAnotacoesConsultasExternasServico.
	 * 
	 * @return the atualizar anotacoes consultas externas servico
	 */
	public AtualizarAnotacoesConsultasExternasServico 
			localizarAtualizarAnotacoesConsultasExternasServico() {
		return (AtualizarAnotacoesConsultasExternasServico) localizar(
				"locator.capes.AtualizarAnotacoesConsultasExternasServico");
	}	

	/**
	 * Localiza o EJB que implementa InclusaoBeneficiariosINSSServico.
	 * 
	 * @return the inclusao beneficiarios inss servico
	 */
	public InclusaoBeneficiariosINSSServico localizarInclusaoBeneficiariosINSSServico() {
		return (InclusaoBeneficiariosINSSServico) localizar("locator.capes.InclusaoBeneficiariosINSSServico");
	}

	/**
	 * Localiza o EJB que implementa ProdutoServico.
	 * 
	 * @return the produto servico
	 */
	public ProdutoServico localizarProdutoServico() {
		return (ProdutoServico) localizar("locator.capes.ProdutoServico");
	}
}
