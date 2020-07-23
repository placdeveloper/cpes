package br.com.sicoob.capes.api.negocio.testes;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.api.negocio.filtros.FiltroRelacionamentoPessoa;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.RelacionamentoPessoaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.RelacionamentoPessoaVO;

/**
 * A Classe TesteRelacionamentoPessoa.
 */
public class TesteRelacionamentoPessoa {

	/** O atributo relacionamentoServico. */
	RelacionamentoPessoaServicoRemote relacionamentoServico;

	/**
	 * O método Sets the up.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		relacionamentoServico = (RelacionamentoPessoaServicoRemote) locator.getObjetoRemoto("capes/api/RelacionamentoPessoaServico");
	}

	/**
	 * O método Teste obter conjuges por pessoa instituicao.
	 */
	@Test
	public void testeObterConjugesPorPessoaInstituicao() {
		try {
			List<RelacionamentoPessoaVO> list = relacionamentoServico.obterConjugesByPessoaInstituicao(3891391, 1);
			Assert.assertNotNull(list);
			Assert.assertTrue(list.size() > 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter quadro societario por pessoa instituicao.
	 */
	@Test
	public void testeObterQuadroSocietarioPorPessoaInstituicao() {
		try {
			List<RelacionamentoPessoaVO> list = relacionamentoServico.obterQuadroSocietarioByPessoaInstituicao(5213231, 1);
			Assert.assertNotNull(list);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por pessoa instituicao tipo.
	 */
	@Test
	public void testeObterPorPessoaInstituicaoTipo() {
		try {
			List<RelacionamentoPessoaVO> list = relacionamentoServico.obterByPessoaInstituicaoTipo(5213231, 741, (short) 5);
			Assert.assertNotNull(list);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por id.
	 */
	@Test
	public void testeObterPorID() {
		try {
			RelacionamentoPessoaVO dto = relacionamentoServico.obterByID(2871);
			Assert.assertNotNull(dto);
			Assert.assertNotNull(dto.getPoderes());
			Assert.assertNull(dto.getDadosRegistro());
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por pessoa instituicao.
	 */
	@Test
	public void testeObterPorPessoaInstituicao() {
		try {
			List<RelacionamentoPessoaVO> list = relacionamentoServico.obterByPessoaInstituicao(5213231, 910);
			Assert.assertNotNull(list);
			Assert.assertTrue(list.size() > 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	/**
	 * O método Teste obter relacionamento poderes.
	 */
	@Test
	public void testeObterRelacionamentoPoderes(){
		try {
			List<RelacionamentoPessoaVO> lista = relacionamentoServico.obterByPessoaInstituicao(5213231, 910);
			Assert.assertNotNull(lista);
			Assert.assertNotNull(lista.get(8).getPoderes());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testePesquisar() {
		try {
			FiltroRelacionamentoPessoa filtro = new FiltroRelacionamentoPessoa();
			filtro.setIdPessoa(5213231);
			filtro.setIdInstituicao(910);
			filtro.setCodigoTipoRelacionamento((short) 5);
			List<RelacionamentoPessoaVO> lista = relacionamentoServico.pesquisar(filtro);
			Assert.assertNotNull(lista);
			Assert.assertTrue(lista.size() > 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testePesquisarPaginado() {
		try {
			FiltroRelacionamentoPessoa filtro = new FiltroRelacionamentoPessoa();
			filtro.setIdPessoa(5213231);
			filtro.setIdInstituicao(910);
			//filtro.setCodigoTipoRelacionamento((short) 5);
			ConsultaDto<RelacionamentoPessoaVO> consultaDto = relacionamentoServico.pesquisarPaginado(filtro, 0, 5);
			Assert.assertNotNull(consultaDto.getResultado());
			Assert.assertTrue(consultaDto.getResultado().size() > 0);
			Assert.assertTrue(consultaDto.getTotalRegistros() > 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
}