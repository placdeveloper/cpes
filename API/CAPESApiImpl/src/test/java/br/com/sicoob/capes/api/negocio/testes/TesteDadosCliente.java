package br.com.sicoob.capes.api.negocio.testes;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.DadosClienteServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.DadosClienteVO;

/**
 * A Classe TesteDadosCliente.
 */
public class TesteDadosCliente {

	/** O atributo dadosClienteServico. */
	DadosClienteServicoRemote dadosClienteServico;

	/**
	 * O método Sets the up.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		dadosClienteServico = (DadosClienteServicoRemote) locator.getObjetoRemoto("capes/api/DadosClienteServico");
	}

	/**
	 * O método Test obter por pessoa instituicao.
	 */
	@Test
	public void testObterPorPessoaInstituicao() {
		try {
			DadosClienteVO dto = dadosClienteServico.obterByPessoaInstituicao(20723, 910);
			Assert.assertNotNull(dto);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * O método Obter por instituicao funcionario.
	 */
	@Test
	public void obterPorInstituicaoFuncionario() {
		try {
			List<DadosClienteVO> lista = dadosClienteServico.obterByInstituicaoFuncionario(910, 27);
			Assert.assertNotNull(lista);
			Assert.assertTrue(lista.size() > 0);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

}