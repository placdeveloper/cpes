package br.com.sicoob.capes.processamento.negocio.servicos.ejb;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sicoob.fechamentoagendado.ejb.FechamentoAgendadoEJB;

/**
 * A Classe AtualizacaoDataSFNServicoEJBTest.
 */
@Ignore
public class AtualizacaoDataSFNServicoEJBTest extends CAPESProcessamentoServicoEJBTest<FechamentoAgendadoEJB> {

	/**
	 * O método Teste executar.
	 */
	@Test
	public void testeExecutar() {
		String[] numeroCooperativa = new String[1];
		numeroCooperativa[0] = "3353";
		getServico().executar(numeroCooperativa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getJndiName() {
		return "capes/processamento/AtualizacaoDataSFNServico";
	}

}