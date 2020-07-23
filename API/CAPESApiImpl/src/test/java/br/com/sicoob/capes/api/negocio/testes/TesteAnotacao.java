package br.com.sicoob.capes.api.negocio.testes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.AnotacaoPessoaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.AnotacaoPeriodoVO;
import br.com.sicoob.capes.api.negocio.vo.AnotacaoPessoaVO;

/**
 * A Classe TesteAnotacao.
 */
public class TesteAnotacao {

	/** O atributo anotacaoServico. */
	private AnotacaoPessoaServicoRemote anotacaoServico;

	/**
	 * O método Sets the up.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		anotacaoServico = (AnotacaoPessoaServicoRemote) locator.getObjetoRemoto("capes/api/AnotacaoPessoaServico");
	}

	/**
	 * O método Teste obter vigentes por pessoa instituicao.
	 */
	@Test
	public void testeObterVigentesPorPessoaInstituicao() {
		try {
			List<AnotacaoPessoaVO> anotacoes = anotacaoServico.obterVigentesByPessoaInstituicao(20609, 910);
			Assert.assertNotNull(anotacoes);
			Assert.assertTrue(anotacoes.size() > 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter impeditivas por pessoa instituicao.
	 */
	@Test
	public void testeObterImpeditivasPorPessoaInstituicao() {
		try {
			List<AnotacaoPessoaVO> anotacoes = anotacaoServico.obterImpeditivasByPessoaInstituicao(2852030, 618);
			Assert.assertNotNull(anotacoes);
			Assert.assertTrue(anotacoes.size() > 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter vigentes por pessoa instituicao tipo.
	 */
	@Test
	public void testeObterVigentesPorPessoaInstituicaoTipo() {
		try {
			List<AnotacaoPessoaVO> anotacoes = anotacaoServico.obterVigentesByPessoaInstituicaoTipo(20609, 910, 505);
			Assert.assertNotNull(anotacoes);
			Assert.assertTrue(anotacoes.size() > 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	/**
	 * O método Teste obter vigentes por pessoa instituicao tipo.
	 */
	@Test
	public void testeObterVigentesPorCpfCnpjInstituicaoTipo() {
		try {
			List<AnotacaoPessoaVO> anotacoes = anotacaoServico.obterVigentesByPessoaInstituicaoTipo("0123415561561", 910, 505);
			Assert.assertNotNull(anotacoes);
			Assert.assertTrue(anotacoes.size() > 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	/**
	 * O método Teste obter por id.
	 */
	@Test
	public void testeObterPorId(){
		try {
			AnotacaoPessoaVO anotacao = anotacaoServico.obterAnotacaoPorId(44417L);
			Assert.assertNotNull(anotacao);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	/**
	 * O método Teste obter pessoas por tipo anotacao periodo prova vida.
	 */
	@Test
	public void testeObterPessoasPorTipoAnotacaoPeriodoProvaVida(){
		try {
			List<String> cpfs = new ArrayList<String>();
			cpfs.add("89732762209");
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			List<AnotacaoPeriodoVO> anotacao = anotacaoServico.obterPessoasSicoobPorTipoAnotacaoPeriodo(cpfs, 1, sdf.parse("20/12/2012"));
			Assert.assertNotNull(anotacao);
			Assert.assertTrue(anotacao.size() > 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * O método Teste obter pessoas por tipo anotacao periodo.
	 */
	@Test
	public void testeObterPessoasPorTipoAnotacaoPeriodo(){
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			List<AnotacaoPeriodoVO> anotacao = anotacaoServico.obterPessoasSicoobPorTipoAnotacaoPeriodo(1, sdf.parse("20/12/2012"));
			Assert.assertNotNull(anotacao);
			Assert.assertTrue(anotacao.size() > 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}