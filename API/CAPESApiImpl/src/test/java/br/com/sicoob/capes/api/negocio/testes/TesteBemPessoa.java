package br.com.sicoob.capes.api.negocio.testes;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.BemPessoaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.BemPessoaVO;

/**
 * A Classe TesteBem.
 */
public class TesteBemPessoa {

	/** O atributo bemServico. */
	private BemPessoaServicoRemote bemServico;

	/**
	 * O método Sets the up.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		bemServico = (BemPessoaServicoRemote) locator.getObjetoRemoto("capes/api/BemPessoaServico");
	}

	/**
	 * O método Teste obter por id.
	 */
	@Test
	public void testeObterPorID() {
		try {
			BemPessoaVO bem = bemServico.obterByID(184);
			Assert.assertNotNull(bem);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por pessoa instituicao.
	 */
	@Test
	public void testeObterPorPessoaInstituicao() {
		try {
			List<BemPessoaVO> bens = bemServico.obterByPessoaInstituicao(21682, 522);
			Assert.assertNotNull(bens);
			Assert.assertTrue("Lista errada", bens.size() == 9);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

}