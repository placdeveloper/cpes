package br.com.sicoob.capes.api.negocio.testes;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.TipoEnderecoServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.TipoEnderecoVO;

public class TesteTipoEndereco {
	private TipoEnderecoServicoRemote servico;
	
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		servico = (TipoEnderecoServicoRemote) locator.getObjetoRemoto("capes/api/TipoEnderecoServico");
	}
	
	@Test
	public void testeListarTipoEndereco() {
		try {
			List<TipoEnderecoVO> tipos = servico.listar();
			Assert.assertTrue(!tipos.isEmpty());
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
}
