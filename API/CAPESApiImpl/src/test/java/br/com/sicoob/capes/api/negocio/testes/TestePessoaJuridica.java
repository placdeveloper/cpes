package br.com.sicoob.capes.api.negocio.testes;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.excecao.ClienteNaoEncontradoException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.PessoaJuridicaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.PessoaJuridicaVO;

/**
 * A Classe TestePessoaJuridica.
 */
public class TestePessoaJuridica {

	/** O atributo pessoaJuridicaServico. */
	private PessoaJuridicaServicoRemote pessoaJuridicaServico;

	/**
	 * O método Sets the up.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		pessoaJuridicaServico = (PessoaJuridicaServicoRemote) locator.getObjetoRemoto("capes/api/PessoaJuridicaServico");
	}

	/**
	 * O método Teste obter por pessoa instituicao.
	 */
	@Test
	public void testeObterPorPessoaInstituicao() {
		try {
			PessoaJuridicaVO dto = pessoaJuridicaServico.obterByPessoaInstituicao(20858, 910);
			Assert.assertNotNull(dto);
		} catch (ClienteNaoEncontradoException e) {
			Assert.assertEquals("javax.persistence.NoResultException: No entity found for query", e.getMessage());
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * O método Teste obter matriz filiais.
	 */
	@Test
	public void testeObterMatrizFiliais() {
		try {
			List<PessoaJuridicaVO> lista = pessoaJuridicaServico.obterMatrizFiliais("3261463206", 910);
			Assert.assertNotNull(lista);
			Assert.assertTrue(lista.size() > 0);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
	
}