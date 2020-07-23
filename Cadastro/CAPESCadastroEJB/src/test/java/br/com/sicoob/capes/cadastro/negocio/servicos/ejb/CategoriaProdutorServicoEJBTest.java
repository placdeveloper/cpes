package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.math.BigDecimal;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.persistencia.dao.CategoriaProdutorDAO;
import br.com.sicoob.capes.negocio.entidades.CategoriaProdutor;

public class CategoriaProdutorServicoEJBTest {

	
	@Test
	public void incluirCategoriaProdutor() throws BancoobException {
		CategoriaProdutorServicoEJB ejb = new CategoriaProdutorServicoEJB();
		CategoriaProdutorDAO dao = EasyMock.createMock(CategoriaProdutorDAO.class);
		
		ejb.setCategoriaProdutorDAO(dao);
		ejb.incluir(criarCategoriaProdutor());
	}
	
	@Test
	public void testAlterarCategoriaAnotacao() {
		CategoriaProdutorServicoEJB ejb = new CategoriaProdutorServicoEJB();
		CategoriaProdutorDAO dao = EasyMock.createMock(CategoriaProdutorDAO.class);
		ejb.setCategoriaProdutorDAO(dao);
		try {
			ejb.alterar(criarCategoriaProdutor());
		} catch (Exception e) {
			Assert.assertEquals(BancoobException.class, e.getClass());
		}
	}
	

	@Test
	public void testExcluirCategoriaAnotacao() {
		CategoriaProdutorServicoEJB ejb = new CategoriaProdutorServicoEJB();
		CategoriaProdutorDAO dao = EasyMock.createMock(CategoriaProdutorDAO.class);
		ejb.setCategoriaProdutorDAO(dao);
		try {
			ejb.excluir(criarCategoriaProdutor());
		} catch (Exception e) {
			Assert.assertEquals(BancoobException.class, e.getClass());
		}
	}

	
	private CategoriaProdutor criarCategoriaProdutor() {
		CategoriaProdutor categoria = new CategoriaProdutor();
		categoria.setDescricao("Produtor de categoria 1");
		categoria.setId(new Short("1"));
		categoria.setAtivo(Boolean.TRUE);
		categoria.setValorLimite(new BigDecimal("222"));
		return categoria;
	}
}
