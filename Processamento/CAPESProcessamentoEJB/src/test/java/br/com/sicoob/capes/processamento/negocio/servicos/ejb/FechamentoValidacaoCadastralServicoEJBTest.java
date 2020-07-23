package br.com.sicoob.capes.processamento.negocio.servicos.ejb;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sicoob.fechamentoagendado.ejb.FechamentoAgendadoEJB;

/**
 * A Classe FechamentoValidacaoCadastralServicoEJBTest.
 */
@Ignore
public class FechamentoValidacaoCadastralServicoEJBTest extends
        CAPESProcessamentoServicoEJBTest<FechamentoAgendadoEJB> {

	/**
	 * O método Test executar.
	 */
	@Test
	public void testExecutar() {
		getServico().executar(new String[0]);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
    protected String getJndiName() {
	    return "capes/processamento/FechamentoValidacaoCadastralServico";
    }

}
