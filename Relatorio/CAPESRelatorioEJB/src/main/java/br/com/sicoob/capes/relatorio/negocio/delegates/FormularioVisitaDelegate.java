package br.com.sicoob.capes.relatorio.negocio.delegates;

import br.com.sicoob.capes.relatorio.negocio.servicos.FormularioVisitaServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.locator.CAPESRelatorioServiceLocator;

public class FormularioVisitaDelegate extends CAPESRelatorioDelegate<FormularioVisitaServico>{

	@Override
	protected FormularioVisitaServico localizarServico() {
		return CAPESRelatorioServiceLocator.getInstance().localizarRelatorioFormularioVisitaServico();
	}
	
}
