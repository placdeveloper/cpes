package br.com.sicoob.capes.api.negocio.testes;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.filtros.FiltroTelefone;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.TelefonePessoaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.TelefonePessoaVO;
import br.com.sicoob.capes.comum.negocio.enums.TipoTelefoneEnum;

/**
 * A Classe TesteTelefonePessoa.
 */
public class TesteTelefonePessoa extends CAPESTesteAbstract {

	/** O atributo telefoneServico. */
	TelefonePessoaServicoRemote telefoneServico;

	/**
	 * O método Sets the up.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Before
	public void setUp() throws Exception {
		super.setPrincipalContexto();
		ServiceLocator locator = ServiceLocator.getInstance();
		telefoneServico = (TelefonePessoaServicoRemote) locator.getObjetoRemoto("capes/api/TelefonePessoaServico");
	}

	/**
	 * O método Teste obter por id.
	 */
	@Test
	public void testeObterPorID() {
		try {
			TelefonePessoaVO vo = telefoneServico.obterByID(775L);
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
			List<TelefonePessoaVO> lista = telefoneServico.obterByPessoaInstituicao(21581, 910);
			Assert.assertNotNull(lista);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * O método Teste incluir telefone.
	 */
	@Test
	public void testeIncluirTelefone() {
		try {
			TelefonePessoaVO dto = getMockTelefone();
			telefoneServico.incluirTelefone(dto);
			Assert.assertNotNull(dto);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter nao vigente por pessoa instituicao.
	 */
	@Test
	public void testeObterNaoVigentePorPessoaInstituicao() {
		try {
			List<TelefonePessoaVO> lista = telefoneServico.obterNaoVigenteByPessoaInstituicao(21581, 910);
			Assert.assertNotNull(lista);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testePesquisar() {
		try {
			FiltroTelefone filtro = new FiltroTelefone();
			filtro.setIdPessoa(21288);
			filtro.setIdInstituicao(910);
			List<TelefonePessoaVO> lista = telefoneServico.pesquisar(filtro);
			Assert.assertNotNull(lista);
			Assert.assertTrue(lista.size() > 0);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Recupera o valor de mockTelefone.
	 *
	 * @return o valor de mockTelefone
	 */
	private TelefonePessoaVO getMockTelefone() {
		
		TelefonePessoaVO vo = new TelefonePessoaVO();
		vo.setCodigoTipoTelefone(TipoTelefoneEnum.CELULAR.getCodigo());
		vo.setCpfCnpj("88691765330");
		vo.setDdd("61");
		vo.setIdInstituicao(910);
		vo.setTelefone("8111-2566");
		return vo;
	}

}