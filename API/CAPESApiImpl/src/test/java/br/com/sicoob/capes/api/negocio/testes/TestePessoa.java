package br.com.sicoob.capes.api.negocio.testes;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.PessoaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.PessoaVO;
import br.com.sicoob.capes.comum.negocio.vo.PessoaBasicaVO;

/**
 * A Classe TestePessoa.
 */
public class TestePessoa {

	/** O atributo pessoaServico. */
	private PessoaServicoRemote pessoaServico;

	/**
	 * O método Sets the up.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		pessoaServico = (PessoaServicoRemote) locator.getObjetoRemoto("capes/api/PessoaServico");
	}

	/**
	 * O método Teste obter por id pessoa.
	 */
	@Test
	public void testeObterPorIdPessoa() {
		PessoaVO pessoa;
		try {
			pessoa = pessoaServico.obterByIdPessoa(644563, 910);
			Assert.assertNotNull(pessoa);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por id pessoa legado.
	 */
	@Test
	public void testeObterPorIdPessoaLegado() {
		PessoaVO pessoa;
		try {
			pessoa = pessoaServico.obterByIdPessoaLegado(65080, 523);
			Assert.assertNotNull(pessoa);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por cpf cnpj.
	 */
	@Test
	public void testeObterPorCpfCnpj() {
		PessoaVO pessoa;
		try {
			pessoa = pessoaServico.obterByCpfCnpj("01527296741", 910);
			Assert.assertNotNull(pessoa);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por cpf cnpj lista.
	 */
	@Test
	public void testeObterPorCpfCnpjLista() {
		List<PessoaVO> lista;
		try {
			lista = pessoaServico.obterByCpfCnpj("68688765480");
			Assert.assertNotNull(lista);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por nome.
	 */
	@Test
	public void testeObterPorNome() {
		List<PessoaVO> pessoas;
		try {
			pessoas = pessoaServico.obterByNome("Marcelo", 523);
			Assert.assertNotNull(pessoas);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	/**
	 * O método Teste obter por nome instituicao paginado.
	 */
	@Test
	public void testeObterPorNomeInstituicaoPaginado() {
		ConsultaDto<PessoaVO> pessoas;
		try {
			pessoas = pessoaServico.obterByNome("PESSOA FISICA - 655", 910, 0, 20);
			Assert.assertNotNull(pessoas);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por nome lista.
	 */
	@Test
	public void testeObterPorNomeLista() {
		List<PessoaVO> pessoas;
		try {
			pessoas = pessoaServico.obterByNome("Marcelo");
			Assert.assertNotNull(pessoas);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por nome apelido.
	 */
	@Test
	public void testeObterPorNomeApelido() {
		List<PessoaVO> pessoas;
		try {
			pessoas = pessoaServico.obterByNomeApelido("barbosa", 523);
			Assert.assertNotNull(pessoas);
			Assert.assertTrue("Lista vazia", pessoas.size() > 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por nome tipo pessoa.
	 */
	@Test
	public void testeObterPorNomeTipoPessoa() {
		List<PessoaVO> pessoas;
		try {
			pessoas = pessoaServico.obterByNomeTipoPessoa("barbosa", 523, (short) 1);
			Assert.assertNotNull(pessoas);
			Assert.assertTrue("Lista vazia", pessoas.size() > 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por nome apelido tipo pessoa.
	 */
	@Test
	public void testeObterPorNomeApelidoTipoPessoa() {
		List<PessoaVO> pessoas;
		try {
			pessoas = pessoaServico.obterByNomeApelidoTipoPessoa("barbosa", 523, (short) 0);
			Assert.assertNotNull(pessoas);
			Assert.assertTrue("Lista vazia", pessoas.size() > 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por nome paginacao.
	 */
	@Test
	public void testeObterPorNomePaginacao() {
		ConsultaDto<PessoaVO> pessoas;
		try {
			pessoas = pessoaServico.obterByNome("barbosa", 523, 0, 10);
			Assert.assertNotNull(pessoas);
			Assert.assertTrue("Lista vazia", pessoas.getResultado().size() > 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por nome paginacao sem instituicao.
	 */
	@Test
	public void testeObterPorNomePaginacaoSemInstituicao() {
		ConsultaDto<PessoaVO> pessoas;
		try {
			pessoas = pessoaServico.obterByNome("barbosa", 0, 10);
			Assert.assertNotNull(pessoas);
			Assert.assertTrue("Lista vazia", pessoas.getResultado().size() > 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por nome apelido paginacao.
	 */
	@Test
	public void testeObterPorNomeApelidoPaginacao() {
		ConsultaDto<PessoaVO> pessoas;
		try {
			pessoas = pessoaServico.obterByNomeApelido("barbosa", 523, 0, 10);
			Assert.assertNotNull(pessoas);
			Assert.assertTrue("Lista vazia", pessoas.getResultado().size() > 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por nome tipo pessoa paginacao.
	 */
	@Test
	public void testeObterPorNomeTipoPessoaPaginacao() {
		ConsultaDto<PessoaVO> pessoas;
		try {
			pessoas = pessoaServico.obterByNomeTipoPessoa("barbosa", 523, (short) 1, 0, 10);
			Assert.assertNotNull(pessoas);
			Assert.assertTrue("Lista vazia", pessoas.getResultado().size() > 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por nome apelido tipo pessoa paginacao.
	 */
	@Test
	public void testeObterPorNomeApelidoTipoPessoaPaginacao() {
		ConsultaDto<PessoaVO> pessoas;
		try {
			pessoas = pessoaServico.obterByNomeApelidoTipoPessoa("barbosa", 523, (short) 0, 0, 10);
			Assert.assertNotNull(pessoas);
			Assert.assertTrue("Lista vazia", pessoas.getResultado().size() > 0);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por pessoa instituicao.
	 */
	@Test
	public void testeObterPorPessoaInstituicao() {
		PessoaVO pessoa;
		try {
			pessoa = pessoaServico.obterByIdPessoa(20535, 910);
			Assert.assertNotNull(pessoa);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testeObterPorLista() {
		List<PessoaBasicaVO> lista;
		try {
			lista = pessoaServico.obterPorListaPessoaInstituicao(Arrays.asList(1), 910);
			Assert.assertNotNull(lista);
			Assert.assertTrue(lista.size() > 0);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testeAssinatura() {
		try {
			byte[] assinatura = pessoaServico.obterAssinatura(20535, 910);
			Assert.assertNotNull(assinatura);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testeImagem() {
		try {
			byte[] assinatura = pessoaServico.obterImagem(20535, 910);
			Assert.assertNotNull(assinatura);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

}