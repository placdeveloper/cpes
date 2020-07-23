package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.persistencia.dao.CategoriaAnotacaoDAO;
import br.com.sicoob.capes.negocio.entidades.CategoriaAnotacao;

public class CategoriaAnotacaoServicoEJBTest {

	
	@Test
	public void incluirCategoriaAnotacao() throws BancoobException {
		CategoriaAnotacaoServicoEJB ejb = new CategoriaAnotacaoServicoEJB();
		CategoriaAnotacaoDAO dao = EasyMock.createMock(CategoriaAnotacaoDAO.class);
		
		ejb.setCategoriaAnotacaoDAO(dao);
		ejb.incluir(criarCategoriaAnotacao());
	}
	
	@Test
	public void testAlterarCategoriaAnotacao() {
		CategoriaAnotacaoServicoEJB ejb = new CategoriaAnotacaoServicoEJB();
		CategoriaAnotacaoDAO dao = EasyMock.createMock(CategoriaAnotacaoDAO.class);
		ejb.setCategoriaAnotacaoDAO(dao);
		try {
			ejb.alterar(criarCategoriaAnotacao());
		} catch (Exception e) {
			Assert.assertEquals(BancoobException.class, e.getClass());
		}
	}
	

	@Test
	public void testExcluirCategoriaAnotacao() {
		CategoriaAnotacaoServicoEJB ejb = new CategoriaAnotacaoServicoEJB();
		CategoriaAnotacaoDAO dao = EasyMock.createMock(CategoriaAnotacaoDAO.class);
		ejb.setCategoriaAnotacaoDAO(dao);
		try {
			ejb.excluir(criarCategoriaAnotacao());
		} catch (Exception e) {
			Assert.assertEquals(BancoobException.class, e.getClass());
		}
	}
	
	private CategoriaAnotacao criarCategoriaAnotacao() {
		CategoriaAnotacao categoria = new CategoriaAnotacao();
		categoria.setDescCategoriaAnotacao("Anotacao de categoria 1");
		categoria.setIdCategoriaAnotacao(new Short("1"));
		categoria.setId(new Short("1"));
		
		return categoria;
	}
}
