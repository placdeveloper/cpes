package br.com.sicoob.capes.api.negocio.testes;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.TipoEmpresaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.TipoEmpresaVO;

public class TesteTipoEmpresa {
	private TipoEmpresaServicoRemote servico;
	
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		servico = (TipoEmpresaServicoRemote) locator.getObjetoRemoto("capes/api/TipoEmpresaServico");
	}
	
	@Test
	public void testeListarTipoEmpresa() {
		try {
			List<TipoEmpresaVO> tipos = servico.listar();
			Assert.assertTrue(!tipos.isEmpty());
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
}
