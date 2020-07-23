package br.com.sicoob.capes.api.negocio.testes;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.TributacaoPessoaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.TributacaoPessoaVO;

/**
 * A Classe TesteTributacaoPessoa.
 */
public class TesteTributacaoPessoa {

	/** O atributo tributacaoServico. */
	TributacaoPessoaServicoRemote tributacaoServico;

	/**
	 * O m�todo Sets the up.
	 *
	 * @throws Exception lan�a a exce��o Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		tributacaoServico = (TributacaoPessoaServicoRemote) locator.getObjetoRemoto("capes/api/TributacaoPessoaServico");
	}

	/**
	 * O m�todo Teste obter por pessoa instituicao.
	 */
	@Test
	public void testeObterPorPessoaInstituicao() {
		try {
			TributacaoPessoaVO dto = tributacaoServico.obterByPessoaInstituicao(21888, 522);
			Assert.assertNotNull(dto);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
}