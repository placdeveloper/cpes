package br.com.sicoob.capes.api.negocio.testes;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.filtros.FiltroReferencia;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.ReferenciaPessoaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.ReferenciaPessoaVO;

/**
 * A Classe TesteReferenciaPessoa.
 */
public class TesteReferenciaPessoa {

	/** O atributo referenciaPessoaServico. */
	private ReferenciaPessoaServicoRemote referenciaPessoaServico;

	/**
	 * O m�todo Sets the up.
	 *
	 * @throws Exception lan�a a exce��o Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		referenciaPessoaServico = (ReferenciaPessoaServicoRemote) locator.getObjetoRemoto("capes/api/ReferenciaPessoaServico");
	}

	/**
	 * O m�todo Teste obter por id.
	 */
	@Test
	public void testeObterPorID() {
		try {
			ReferenciaPessoaVO dto = referenciaPessoaServico.obterByID(134);
			Assert.assertNotNull(dto);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * O m�todo Teste obter por pessoa instituicao.
	 */
	@Test
	public void testeObterPorPessoaInstituicao() {
		try {
			List<ReferenciaPessoaVO> list = referenciaPessoaServico.obterByPessoaInstituicao(544525, 910);
			Assert.assertNotNull(list);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testeObterPesquisar() {
		try {
			FiltroReferencia filtro = new FiltroReferencia();
			filtro.setIdPessoa(21012);
			filtro.setIdInstituicao(910);
			List<ReferenciaPessoaVO> lista = referenciaPessoaServico.pesquisar(filtro);
			Assert.assertNotNull(lista);
			Assert.assertTrue(lista.size() > 0);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

}