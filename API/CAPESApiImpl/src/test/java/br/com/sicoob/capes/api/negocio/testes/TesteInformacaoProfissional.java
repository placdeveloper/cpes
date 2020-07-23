package br.com.sicoob.capes.api.negocio.testes;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.InformacaoProfissionalServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.InformacaoProfissionalVO;

/**
 * A Classe TesteInformacaoProfissional.
 */
public class TesteInformacaoProfissional {

	/** O atributo infProfissionalDelegate. */
	InformacaoProfissionalServicoRemote infProfissionalDelegate;

	/**
	 * O método Sets the up.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		infProfissionalDelegate = (InformacaoProfissionalServicoRemote) locator.getObjetoRemoto("capes/api/InformacaoProfissionalServico");
	}

	/**
	 * O método Teste obter por id.
	 */
	@Test
	public void testeObterPorID() {
		try {
			InformacaoProfissionalVO dto = infProfissionalDelegate.obterByID(141);
			Assert.assertNotNull(dto);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por pessoa instituicao.
	 */
	@Test
	public void testeObterPorPessoaInstituicao() {
		try {
			List<InformacaoProfissionalVO> list = infProfissionalDelegate.obterByPessoaInstituicao(21070, 910);
			Assert.assertNotNull(list);
			Assert.assertTrue("Lista errada", list.size() == 1);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

}