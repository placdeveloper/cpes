package br.com.sicoob.capes.api.negocio.testes;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.filtros.FiltroBemMovel;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.BemMovelServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.BemImovelVO;
import br.com.sicoob.capes.api.negocio.vo.BemMovelVO;

/**
 * Classe com os testes do bem móvel.
 *
 * @author bruno.carneiro
 */
public class TesteBemMovel {

	private BemMovelServicoRemote bemMovelServico;

	/**
	 * Configura o serviço antes da chamada do teste.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		bemMovelServico = (BemMovelServicoRemote) locator.getObjetoRemoto("capes/api/BemMovelServico");
	}

	@Test
	public void testeObterPorPessoaInstituicao() {
		try {
			List<BemMovelVO> bens = bemMovelServico.obterPorPessoaInstituicao(20772, 910);
			Assert.assertNotNull(bens);
			Assert.assertTrue("Lista preenchida", bens.size() != 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testeObterPorPessoaInstituicaoTipoBem() {
		try {
			List<BemMovelVO> bens = bemMovelServico.obterPorPessoaInstituicao(20772, 910, (short) 1);
			Assert.assertNotNull(bens);
			Assert.assertTrue("Lista preenchida", bens.size() != 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testeObterAvancadosPorPessoaInstituicao() {
		try {
			List<BemMovelVO> bens = bemMovelServico.obterAvancadosPorPessoaInstituicao(20772, 910);
			Assert.assertNotNull(bens);
			Assert.assertTrue("Lista preenchida", bens.size() != 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testeObterAvancadosPorPessoaInstituicaoTipoBem() {
		try {
			List<BemMovelVO> bens = bemMovelServico.obterAvancadosPorPessoaInstituicao(20772, 910, (short) 1);
			Assert.assertNotNull(bens);
			Assert.assertTrue("Lista preenchida", bens.size() != 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testeObterAvaliadosPorPessoaInstituicao() {
		try {
			List<BemMovelVO> bens = bemMovelServico.obterAvaliadosPorPessoaInstituicao(20772, 910);
			Assert.assertNotNull(bens);
			Assert.assertTrue("Lista preenchida", bens.size() != 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testeObterAvaliadosPorPessoaInstituicaoTipoBem() {
		try {
			List<BemMovelVO> bens = bemMovelServico.obterAvaliadosPorPessoaInstituicao(20772, 910, (short) 1);
			Assert.assertNotNull(bens);
			Assert.assertTrue("Lista preenchida", bens.size() != 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testeObterBasicosAvaliadosPorPessoaInstituicao() {
		try {
			List<BemMovelVO> bens = bemMovelServico.obterBasicosAvaliadosPorPessoaInstituicao(5665372, 910);
			Assert.assertNotNull(bens);
			Assert.assertTrue("Lista preenchida", bens.size() != 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testeObterBasicosAvaliadosPorPessoaInstituicaoTipoBem() {
		try {
			List<BemMovelVO> bens = bemMovelServico.obterBasicosAvaliadosPorPessoaInstituicao(5665372, 910, (short)1);
			Assert.assertNotNull(bens);
			Assert.assertTrue("Lista preenchida", bens.size() != 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testeObterPorIdBem() {
		try {
			BemMovelVO bem = bemMovelServico.obterPorIdBem(127L);
			Assert.assertNotNull(bem);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testePesquisarPorFiltro() {
		try {
			FiltroBemMovel filtro = new FiltroBemMovel();
			filtro.setPlaca("61");
			List<BemImovelVO> lista = bemMovelServico.pesquisar(filtro);
			Assert.assertNotNull(lista);
			Assert.assertTrue(lista.size() > 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

}