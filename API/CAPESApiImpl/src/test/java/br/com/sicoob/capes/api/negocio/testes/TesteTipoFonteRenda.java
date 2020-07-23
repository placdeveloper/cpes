package br.com.sicoob.capes.api.negocio.testes;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.TipoFonteRendaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.TipoFonteRendaVO;

public class TesteTipoFonteRenda {
	private TipoFonteRendaServicoRemote servico;
	
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		servico = (TipoFonteRendaServicoRemote) locator.getObjetoRemoto("capes/api/TipoFonteRendaServico");
	}
	
	@Test
	public void testeListarFonteRenda() {
		try {
			List<TipoFonteRendaVO> tipos = servico.listar();
			Assert.assertTrue(!tipos.isEmpty());
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testeListarFonteRendaPorTipoPessoa() {
		try {
			List<TipoFonteRendaVO> tipos = servico.listarPorTipoPessoa((short) 1);
			Assert.assertTrue(!tipos.isEmpty());
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testeObterPorCodigo() {
		try {
			TipoFonteRendaVO tipo = servico.obterTipoFonteRenda((short) 1);
			Assert.assertNotNull(tipo);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
}