package br.com.sicoob.capes.api.negocio.testes;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.NucleoServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.NucleoVO;

/**
 * A Classe TesteNucleo.
 */
public class TesteNucleo extends CAPESTesteAbstract {

	/** O atributo nucleoServico. */
	private NucleoServicoRemote nucleoServico;

	/**
	 * O método Sets the up.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		nucleoServico = (NucleoServicoRemote) locator.getObjetoRemoto("capes/api/NucleoServico");
	}

	/**
	 * O método Teste listar por id instituicao.
	 */
	@Test
	public void testeListarPorIdInstituicao() {
		List<NucleoVO> lista;
		try {
			lista = nucleoServico.listarNucleos(910);
			Assert.assertNotNull(lista);
			Assert.assertTrue("Lista vazia", lista.size() > 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
}