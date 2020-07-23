package br.com.sicoob.capes.api.negocio.testes;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.filtros.FiltroBemImovel;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.BemImovelServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.BemImovelParticipanteVO;
import br.com.sicoob.capes.api.negocio.vo.BemImovelVO;

/**
 * Classe para os testes do bem imóvel.
 * 
 * @author bruno.carneiro
 */
public class TesteBemImovel {

	private BemImovelServicoRemote bemImovelServico;

	/**
	 * Configura o serviço antes da chamada do teste.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		bemImovelServico = (BemImovelServicoRemote) locator.getObjetoRemoto("capes/api/BemImovelServico");
	}

	/**
	 * 
	 */
	@Test
	public void testeObterPorPessoaInstituicao() {
		try {
			List<BemImovelVO> bens = bemImovelServico.obterPorPessoaInstituicao(20772, 910);
			Assert.assertNotNull(bens);
			Assert.assertTrue("Lista preenchida", bens.size() != 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testeObterPorPessoaInstituicaoTipoBem() {
		try {
			List<BemImovelVO> bens = bemImovelServico.obterPorPessoaInstituicaoTipoBem(20772, 910, (short) 102);
			Assert.assertNotNull(bens);
			Assert.assertTrue("Lista preenchida", bens.size() != 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testeObterAvancadosPorPessoaInstituicao() {
		try {
			List<BemImovelVO> bens = bemImovelServico.obterAvancadosPorPessoaInstituicao(20772, 910);
			Assert.assertNotNull(bens);
			Assert.assertTrue("Lista preenchida", bens.size() != 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testeObterAvancadosPorPessoaInstituicaoTipoBem() {
		try {
			List<BemImovelVO> bens = bemImovelServico.obterAvancadosPorPessoaInstituicao(20772, 910, (short) 102);
			Assert.assertNotNull(bens);
			Assert.assertTrue("Lista preenchida", bens.size() != 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testeObterAvaliadosPorPessoaInstituicao() {
		try {
			List<BemImovelVO> bens = bemImovelServico.obterAvaliadosPorPessoaInstituicao(20772, 910);
			Assert.assertNotNull(bens);
			Assert.assertTrue("Lista preenchida", bens.size() != 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testeObterAvaliadosPorPessoaInstituicaoTipoBem() {
		try {
			List<BemImovelVO> bens = bemImovelServico.obterAvaliadosPorPessoaInstituicao(20772, 910, (short) 102);
			Assert.assertNotNull(bens);
			Assert.assertTrue("Lista preenchida", bens.size() != 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testeObterBasicosAvaliadosPorPessoaInstituicao() {
		try {
			List<BemImovelVO> bens = bemImovelServico.obterBasicosAvaliadosPorPessoaInstituicao(5665372, 910);
			Assert.assertNotNull(bens);
			Assert.assertTrue("Lista preenchida", bens.size() != 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testeObterBasicosAvaliadosPorPessoaInstituicaoTipoBem() {
		try {
			List<BemImovelVO> bens = bemImovelServico.obterBasicosAvaliadosPorPessoaInstituicao(5665372, 910, (short)1);
			Assert.assertNotNull(bens);
			Assert.assertTrue("Lista preenchida", bens.size() != 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testeObterPorIdBem() {
		try {
			BemImovelVO bem = bemImovelServico.obterPorIdBem(141L);
			Assert.assertNotNull(bem);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testePesquisarPorFiltro() {
		try {
			FiltroBemImovel filtro = new FiltroBemImovel();
			filtro.setMatricula("1561");
			filtro.setNirf("5616");
			List<BemImovelVO> lista = bemImovelServico.pesquisar(filtro);
			Assert.assertNotNull(lista);
			Assert.assertTrue(lista.size() > 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testeObterParticipantes() {
		try {
			List<BemImovelParticipanteVO> participantes = bemImovelServico.obterParticipantes(1044L);
			Assert.assertNotNull(participantes);
			Assert.assertTrue(participantes.size() > 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

}