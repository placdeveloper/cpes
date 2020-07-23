/**
 * 
 */
package br.com.sicoob.capes.processamento.negocio.delegates;

import br.com.sicoob.capes.processamento.negocio.servicos.AtualizarAnotacoesConsultasExternasServico;
import br.com.sicoob.capes.processamento.negocio.servicos.locator.CAPESProcessamentoServiceLocator;

/**
 * 
 * Delegate Atualizar Anotacoe sConsultas Externas Delegate
 * @author Erico.Junior
 * 
 */
public class AtualizarAnotacoesConsultasExternasDelegate extends
		CAPESProcessamentoDelegate<AtualizarAnotacoesConsultasExternasServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AtualizarAnotacoesConsultasExternasServico localizarServico() {
		return CAPESProcessamentoServiceLocator.getInstance()
				.localizarAtualizarAnotacoesConsultasExternasServico();
	}

}
