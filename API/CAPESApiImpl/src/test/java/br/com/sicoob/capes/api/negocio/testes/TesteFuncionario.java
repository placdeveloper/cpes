package br.com.sicoob.capes.api.negocio.testes;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.FuncionarioServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.FuncionarioVO;

/**
 * A Classe TesteFuncionario.
 */
public class TesteFuncionario {

	/** O atributo funcionarioServico. */
	FuncionarioServicoRemote funcionarioServico;

	/**
	 * O método Sets the up.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		funcionarioServico = (FuncionarioServicoRemote) locator.getObjetoRemoto("capes/api/FuncionarioServico");
	}

	/**
	 * O método Teste obter por id.
	 */
	@Test
	public void testeObterPorID() {
		try {
			FuncionarioVO vo = funcionarioServico.obterByID(26);
			Assert.assertNotNull(vo);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por pessoa instituicao.
	 */
	@Test
	public void testeObterPorPessoaInstituicao() {
		try {
			FuncionarioVO vo = funcionarioServico.obterByPessoaInstituicao(28449, 910);
			Assert.assertNotNull(vo);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por cpf pessoa instituicao.
	 */
	@Test
	public void testeObterPorCpfPessoaInstituicao() {
		try {
			FuncionarioVO vo = funcionarioServico.obterByCpfPessoaInstituicao("83944276850", 910);
			Assert.assertNotNull(vo);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por instituicao funcao.
	 */
	@Test
	public void testeObterPorInstituicaoFuncao() {
		try {
			List<FuncionarioVO> lista = funcionarioServico.obterByInstituicaoFuncao(523, new Short("2"), 0);
			Assert.assertNotNull(lista);
			Assert.assertTrue(lista.size() > 0);
		} catch (NumberFormatException e) {
			fail(e.getMessage());
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * O método Teste obter gerente.
	 */
	@Test
	public void testeObterGerente() {
		try {
			FuncionarioVO vo = funcionarioServico.obterGerente(21070, 910);
			Assert.assertNotNull(vo);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
}