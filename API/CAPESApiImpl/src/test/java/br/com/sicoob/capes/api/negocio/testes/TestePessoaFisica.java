package br.com.sicoob.capes.api.negocio.testes;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.PessoaFisicaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.PessoaFisicaVO;

/**
 * A Classe TestePessoaFisica.
 */
public class TestePessoaFisica {

	/** O atributo pessoaFisicaServico. */
	private PessoaFisicaServicoRemote pessoaFisicaServico;

	/**
	 * O método Sets the up.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		pessoaFisicaServico = (PessoaFisicaServicoRemote) locator.getObjetoRemoto("capes/api/PessoaFisicaServico");
	}

	/**
	 * O método Teste obter por pessoa instituicao.
	 */
	@Test
	public void testeObterPorPessoaInstituicao() {
		try {
			PessoaFisicaVO dto = pessoaFisicaServico.obterByPessoaInstituicao(985783, 910);
			Assert.assertNotNull(dto);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testeObterPorCPFInstituicao() {
		try {
			PessoaFisicaVO dto = pessoaFisicaServico.obterPorCPFInstituicao("69273060307", 910);
			Assert.assertNotNull(dto);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
}