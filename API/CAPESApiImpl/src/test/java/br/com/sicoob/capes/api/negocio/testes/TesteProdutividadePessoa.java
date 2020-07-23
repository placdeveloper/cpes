package br.com.sicoob.capes.api.negocio.testes;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.ProdutividadePessoaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.ProdutividadePessoaVO;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtividade;

/**
 * Classe de teste unitário para métodos relacionados a {@link Produtividade}
 * 
 * Dados de teste:
 * Usuário: ALTAIR FERREIRA SANTIAGO
 * Apelido: TATA
 * ID Pessoa: 19848
 * ID Pessoa Compartilhamento: 2788 e 179500
 * ID Pessoa Legado: 92630 e 32131
 * ID Instituição: 61, 523 e 935
 * ID Unidade: 0
 * ID Produtividade: 25
 * CPF: 262994105-15
 *
 */
public class TesteProdutividadePessoa {

	/** O atributo produtividadeServico. */
	private ProdutividadePessoaServicoRemote produtividadeServico;

	/**
	 * O método Sets the up.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		produtividadeServico = (ProdutividadePessoaServicoRemote) locator.getObjetoRemoto("capes/api/ProdutividadePessoaServico");
	}

	/**
	 * O método Teste obter por id.
	 */
	@Test
	public void testeObterPorID() {
		try {
			ProdutividadePessoaVO prod = produtividadeServico.obterByID(701L);
			Assert.assertNotNull(prod);
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
			List<ProdutividadePessoaVO> prods = produtividadeServico.obterByPessoaInstituicao(20609, 910);
			Assert.assertNotNull(prods);
			Assert.assertTrue("Lista vazia", prods.size() > 0);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * O método Teste obter completas por pessoa instituicao.
	 */
	@Test
	public void testeObterCompletasPorPessoaInstituicao() {
		try {
			List<ProdutividadePessoaVO> prods = produtividadeServico.obterTodasPorPessoaInstituicao(20609, 910);
			Assert.assertNotNull(prods);
			Assert.assertTrue("Lista vazia", prods.size() > 0);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
}