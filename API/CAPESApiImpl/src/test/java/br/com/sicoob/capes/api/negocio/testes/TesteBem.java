package br.com.sicoob.capes.api.negocio.testes;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.BemServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.BemProprietarioVO;
import br.com.sicoob.capes.api.negocio.vo.BemVO;
import br.com.sicoob.capes.api.negocio.vo.TipoBemVO;
import br.com.sicoob.capes.api.negocio.vo.TipoClassificacaoBemVO;

public class TesteBem {

	private BemServicoRemote bemServico;

	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		bemServico = (BemServicoRemote) locator.getObjetoRemoto("capes/api/BemServico");
	}

	@Test
	public void testeObterPorPessoaInstituicao() {
		try {
			List<BemVO> bens = bemServico.obterPorPessoaInstituicao(20772, 910);
			Assert.assertNotNull(bens);
			Assert.assertTrue("Lista preenchida", bens.size() != 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testeObterPorIdBem() {
		try {
			BemVO bem = bemServico.obterPorIdBem(141L);
			Assert.assertNotNull(bem);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testeObterClassificacoes() {
		try {
			List<TipoClassificacaoBemVO> tipoClassificacao = bemServico.obterClassificacoesBem();
			Assert.assertNotNull(tipoClassificacao);
			Assert.assertTrue(tipoClassificacao.size() > 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testeObterTiposBemPorClassificacao() {
		try {
			List<TipoBemVO> tipos = bemServico.obterTiposBemPorClassificacao((short) 2);
			Assert.assertNotNull(tipos);
			Assert.assertTrue(tipos.size() > 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testeObterProprietariosBem() {
		try {
			List<BemProprietarioVO> proprietarios = bemServico.obterProprietarios(141L);
			Assert.assertNotNull(proprietarios);
			Assert.assertTrue(proprietarios.size() > 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

}