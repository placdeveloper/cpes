package br.com.sicoob.capes.processamento.negocio.servicos.ejb;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sicoob.fechamentoagendado.ejb.FechamentoAgendadoEJB;

/**
 * A Classe FechamentoAutorizacoesVencidasServicoEJBTest.
 */
@Ignore
public class FechamentoAutorizacoesVencidasServicoEJBTest extends CAPESProcessamentoServicoEJBTest<FechamentoAgendadoEJB> {

	/**
	 * O método Teste executar.
	 */
	@Test
	public void testeExecutar() {
		getServico().executar(new String[0]);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getJndiName() {
		return "capes/processamento/FechamentoAutorizacoesVencidasServico";
	}

}