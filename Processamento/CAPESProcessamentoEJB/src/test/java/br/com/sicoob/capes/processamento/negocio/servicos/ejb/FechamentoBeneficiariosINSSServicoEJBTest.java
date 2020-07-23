package br.com.sicoob.capes.processamento.negocio.servicos.ejb;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.sicoob.capes.processamento.negocio.servicos.locator.ServiceLocator;
import br.com.sicoob.fechamentoagendado.ejb.FechamentoAgendadoEJB;

/**
 * A Classe FechamentoBeneficiariosINSSServicoEJBTest.
 */
@Ignore
public class FechamentoBeneficiariosINSSServicoEJBTest {

	/** O atributo fechamento. */
	private FechamentoAgendadoEJB fechamento;

	/**
	 * O m�todo Sets the up.
	 *
	 * @throws Exception lan�a a exce��o Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		fechamento = (FechamentoAgendadoEJB) locator.getObjetoRemoto("capes/processamento/FechamentoBeneficiariosINSSServico");
	}

	/**
	 * O m�todo Teste fechamento beneficiarios inss.
	 */
	@Test
	public void testeFechamentoBeneficiariosINSS() {
		fechamento.executar(new String[]{"3353"});
	}

}