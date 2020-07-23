package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.io.Serializable;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade.EmpreendimentoFachada;
import br.com.sicoob.capes.cadastro.persistencia.dao.EmpreendimentoDAO;
import br.com.sicoob.capes.negocio.entidades.Empreendimento;

/**
 * A Classe EmpreendimentoServicoEJBTest.
 */
public class EmpreendimentoServicoEJBTest {
	
	/**
	 * O método Incluir test.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	@Ignore
	public void incluirTest() throws BancoobException {
		EmpreendimentoServicoEJB ejb = new EmpreendimentoServicoEJB();
		
		EmpreendimentoFachada empreendimentoFachada = EasyMock.createMock(EmpreendimentoFachada.class);
		EmpreendimentoDAO empreendimentoDAOMock = EasyMock.createMock(EmpreendimentoDAO.class);
		EasyMock.expect(empreendimentoDAOMock.obter(EasyMock.anyObject(Serializable.class))).andReturn(null);
		EasyMock.expect(empreendimentoDAOMock.incluir(EasyMock.anyObject(Empreendimento.class))).andReturn(new Empreendimento());
		empreendimentoFachada.replicarEmpreendimento(EasyMock.anyObject(Empreendimento.class));
		
		ejb.setDao(empreendimentoDAOMock);
		ejb.setEmpreendimentoFacade(empreendimentoFachada);
		
		EasyMock.replay(empreendimentoDAOMock);
		ejb.incluir(new Empreendimento());
		EasyMock.verify(empreendimentoDAOMock);
	}
	
	/**
	 * O método Incluir invalido test.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	public void incluirInvalidoTest() throws BancoobException {
		EmpreendimentoServicoEJB ejb = new EmpreendimentoServicoEJB();
		
		EmpreendimentoDAO empreendimentoDAOMock = EasyMock.createMock(EmpreendimentoDAO.class);
		EasyMock.expect(empreendimentoDAOMock.obter(EasyMock.anyObject(Serializable.class))).andReturn(new Empreendimento());
		
		ejb.setDao(empreendimentoDAOMock);
		
		EasyMock.replay(empreendimentoDAOMock);
		boolean exception = true;
		try {
			ejb.incluir(new Empreendimento());
			Assert.fail("Erro");
		} catch (Exception e) {
			exception = false;
			Assert.assertEquals(NegocioException.class, e.getClass());
		}
		Assert.assertFalse(exception);
		EasyMock.verify(empreendimentoDAOMock);
	}
	
	/**
	 * O método Alterar test.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	@Ignore
	public void alterarTest() throws BancoobException {
		EmpreendimentoServicoEJB ejb = new EmpreendimentoServicoEJB();
		
		EmpreendimentoFachada empreendimentoFachada = EasyMock.createMock(EmpreendimentoFachada.class);
		EmpreendimentoDAO empreendimentoDAOMock = EasyMock.createMock(EmpreendimentoDAO.class);
		empreendimentoDAOMock.alterar(EasyMock.anyObject(Empreendimento.class));
		empreendimentoFachada.replicarEmpreendimento(EasyMock.anyObject(Empreendimento.class));
		
		ejb.setDao(empreendimentoDAOMock);
		ejb.setEmpreendimentoFacade(empreendimentoFachada);
		
		EasyMock.replay(empreendimentoDAOMock);
		ejb.alterar(new Empreendimento());
		EasyMock.verify(empreendimentoDAOMock);
	}
	
	/**
	 * O método Pesquisar proximo codigo test.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	public void pesquisarProximoCodigoTest() throws BancoobException {
		EmpreendimentoServicoEJB ejb = new EmpreendimentoServicoEJB();
		EmpreendimentoDAO empreendimentoDAOMock = EasyMock.createMock(EmpreendimentoDAO.class);
		EasyMock.expect(empreendimentoDAOMock.pesquisarProximoCodigo()).andReturn(1);
		ejb.setDao(empreendimentoDAOMock);
		
		EasyMock.replay(empreendimentoDAOMock);
		ejb.pesquisarProximoCodigo();
		EasyMock.verify(empreendimentoDAOMock);
	}

}
