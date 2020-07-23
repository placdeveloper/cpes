package br.com.sicoob.capes.api.negocio.testes;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.CnaeFiscalServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.CnaeFiscalVO;

public class TesteCnaeFiscal {
	
	private CnaeFiscalServicoRemote cnaeFiscalServico;
	
	
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		cnaeFiscalServico = (CnaeFiscalServicoRemote) locator.getObjetoRemoto("capes/api/CnaeFiscalServico");
	}
	
	@Test
	public void testeListarCnaeFiscal(){
		try {
			//No Datasource de Desenvolvimento o size é de 2388
			List<CnaeFiscalVO> lista = cnaeFiscalServico.listar();
			Assert.assertNotNull(lista);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}		
	}
	
	@Test
	public void testeObterCnaeFiscalPorCodigo(){
		try {
			//Testado no Datasource de Desenvolvimento
			CnaeFiscalVO cnaeFiscal = cnaeFiscalServico.obterCnaeFiscalPorCodigo("A0112199");
			Assert.assertNotNull(cnaeFiscal);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testeObterPorDescricao(){
		try {
			List<CnaeFiscalVO> lista = cnaeFiscalServico.obterPorDescricao("NÃO INFORMADO");
			Assert.assertNotNull(lista);
			Assert.assertTrue(lista.size() > 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
	
}
