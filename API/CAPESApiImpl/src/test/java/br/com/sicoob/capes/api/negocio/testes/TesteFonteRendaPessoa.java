package br.com.sicoob.capes.api.negocio.testes;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.filtros.FiltroFonteRenda;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.FonteRendaPessoaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.FonteRendaPessoaVO;

/**
 * A Classe TesteFonteRendaPessoa.
 */
public class TesteFonteRendaPessoa {

	/** O atributo fonteRendaDelegate. */
	private FonteRendaPessoaServicoRemote fonteRendaDelegate;

	/**
	 * O método Sets the up.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		fonteRendaDelegate = (FonteRendaPessoaServicoRemote) locator.getObjetoRemoto("capes/api/FonteRendaPessoaServico");
	}

	/**
	 * O método Test obter por id.
	 */
	@Test
	public void testObterPorID() {
		try {
			FonteRendaPessoaVO fr = fonteRendaDelegate.obterByID(93L);
			Assert.assertNotNull(fr);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testeListaIdPessoaLegadoIdInstituicao() {
		try {
			List<FonteRendaPessoaVO> lista = fonteRendaDelegate.listarFonteRendaPessoaPorIdPessoaLegadoIdInstituicao(12688, 910);
			Assert.assertNotNull(lista);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * O método Test obter por pessoa instituicao.
	 */
	@Test
	public void testObterPorPessoaInstituicao() {
		try {
			List<FonteRendaPessoaVO> frs = fonteRendaDelegate.obterByPessoaInstituicao(544525, 910);
			Assert.assertNotNull(frs);
			Assert.assertTrue(frs.size() > 0);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testePesquisar() {
		try {
			FiltroFonteRenda filtro = new FiltroFonteRenda();
			filtro.setIdPessoa(644553);
			filtro.setIdInstituicao(910);
			List<FonteRendaPessoaVO> lista = fonteRendaDelegate.pesquisar(filtro);
			Assert.assertNotNull(lista);
			Assert.assertTrue(lista.size() > 0);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
}