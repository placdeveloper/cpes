package br.com.sicoob.capes.api.negocio.testes;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.DadosEtiquetaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.DadosEtiquetaVO;

/**
 * A Classe TesteDadosEtiqueta.
 */
public class TesteDadosEtiqueta {

	/** O atributo dadosEtiquetaServico. */
	DadosEtiquetaServicoRemote dadosEtiquetaServico;

	/**
	 * O método Sets the up.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		dadosEtiquetaServico = (DadosEtiquetaServicoRemote) locator.getObjetoRemoto("capes/api/DadosEtiquetaServico");
	}

	/**
	 * O método Teste listar.
	 */
	@Test
	//Depreciado
	public void testeListar() {
		try {
			List<Integer> ids = new ArrayList<Integer>();
			ids.add(9539);
			List<DadosEtiquetaVO> list = dadosEtiquetaServico.listar(ids, 910);
			Assert.assertNotNull(list);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * O método Teste listar por pessoa legado instituicao.
	 */
	@Test
	public void testeListarPorPessoaLegadoInstituicao() {
		try {
			List<DadosEtiquetaVO> list = dadosEtiquetaServico.listarPorPessoaLegadoInstituicao(910, (short)0, new Date(), new Date(), "N".charAt(0), "0701", "0731");
			Assert.assertNotNull(list);
			Assert.assertTrue(list.size() > 0);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}	
}