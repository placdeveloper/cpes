package br.com.sicoob.capes.api.negocio.testes;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.ProdutorServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.ProdutorVO;
import br.com.sicoob.capes.api.negocio.vo.TipoBeneficiarioSicorVO;

/**
 * A Classe TesteProdutor.
 */
public class TesteProdutor {

	/** O atributo produtorServico. */
	private ProdutorServicoRemote produtorServico;

	/**
	 * O método Sets the up.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		produtorServico = (ProdutorServicoRemote) locator.getObjetoRemoto("capes/api/ProdutorServico");
	}

	/**
	 * O método Teste obter por id pessoa.
	 */
	@Test
	public void testeObterPorIdPessoa() {
		try {
			ProdutorVO prod = produtorServico.obterByIdPessoa(20609, 910);
			Assert.assertNotNull(prod);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por id pessoa legado.
	 */
	@Test
	public void testeObterPorIdPessoaLegado() {
		try {
			ProdutorVO prod = produtorServico.obterByIdPessoaLegado(9660, 910);
			Assert.assertNotNull(prod);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por cpf cnpj.
	 */
	@Test
	public void testeObterPorCpfCnpj() {
		try {
			ProdutorVO prod = produtorServico.obterByCpfCnpj("49482270991", 910);
			Assert.assertNotNull(prod);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter produtor cliente.
	 */
	@Test
	public void testeObterProdutorCliente() {
		try {
			ProdutorVO prod = produtorServico.obterProdutorCliente("49482270991", 910);
			Assert.assertNotNull(prod);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por nome.
	 */
	@Test
	public void testeObterPorNome() {
		try {
			List<ProdutorVO> prods = produtorServico.obterByNome("MAR", 523);
			Assert.assertNotNull(prods);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por nome apelido.
	 */
	@Test
	public void testeObterPorNomeApelido() {
		try {
			List<ProdutorVO> prods = produtorServico.obterByNomeApelido("pessoa física", 523);
			Assert.assertNotNull(prods);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por nome apelido paginado.
	 */
	@Test
	public void testeObterPorNomeApelidoPaginado() {
		try {
			ConsultaDto<ProdutorVO> prods = produtorServico.obterByNomeApelido("MARIA", 910, 0, 10);
			Assert.assertNotNull(prods.getResultado());
			Assert.assertTrue(prods.getTotalRegistros() > 0);
			Assert.assertTrue(prods.getTotalRegistros().equals(prods.getResultado().size()));
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por nome paginado.
	 */
	@Test
	public void testeObterPorNomePaginado() {
		try {
			ConsultaDto<ProdutorVO> prods = produtorServico.obterByNome("pessoa física", 910, 0, 10);
			Assert.assertNotNull(prods.getResultado());
			Assert.assertTrue(prods.getTotalRegistros() > 0);
			Assert.assertTrue(prods.getTotalRegistros().equals(prods.getResultado().size()));
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por nome apelido paginado filtrando por categoria ativa.
	 */
	@Test
	public void testeObterPorNomeApelidoPaginadoFiltrandoPorCategoriaAtiva() {
		try {
			ConsultaDto<ProdutorVO> prods = produtorServico.obterByNomeApelido("MARIA", 910, 0, 10, true);
			Assert.assertNotNull(prods.getResultado());
			Assert.assertTrue(prods.getTotalRegistros() > 0);
			Assert.assertTrue(prods.getTotalRegistros().equals(prods.getResultado().size()));
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por nome paginado filtrando por categoria ativa.
	 */
	@Test
	public void testeObterPorNomePaginadoFiltrandoPorCategoriaAtiva() {
		try {
			ConsultaDto<ProdutorVO> prods = produtorServico.obterByNome("pessoa física", 910, 0, 10, true);
			Assert.assertNotNull(prods.getResultado());
			Assert.assertTrue(prods.getTotalRegistros() > 0);
			Assert.assertTrue(prods.getTotalRegistros().equals(prods.getResultado().size()));
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * Método para testar a lista de tipos de benefifiários do SICOR
	 */
	@Test
	public void testeObterListaTiposBeneficiariosSicor() {
		try {
			List<TipoBeneficiarioSicorVO> lista = produtorServico.obterListaTipoBeneficiariosSicor();
			Assert.assertNotNull(lista);
			Assert.assertTrue(lista.size() > 0);
			Assert.assertTrue(lista.size() == 18);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
}