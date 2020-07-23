package br.com.sicoob.capes.api.negocio.testes;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.InstituicaoResponsavelServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.InstituicaoResponsavelVO;

/**
 * A Classe TesteInstituicaoResponsavel.
 */
public class TesteInstituicaoResponsavel {

	/** O atributo instituicaoResponsavelServico. */
	private InstituicaoResponsavelServicoRemote instituicaoResponsavelServico;

	/**
	 * O método Sets the up.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		instituicaoResponsavelServico = (InstituicaoResponsavelServicoRemote) locator.getObjetoRemoto("capes/api/InstituicaoResponsavelServico");
	}

	/**
	 * O método Teste obter por cpf cnpj.
	 */
	@Test
	public void testeObterPorCpfCnpj() {
		InstituicaoResponsavelVO resp;
		try {
			resp = instituicaoResponsavelServico.obterByCpfCnpj("72208155491", (short) 1);
			Assert.assertNotNull(resp);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por cpf cnpj sem codigo compartilhamento cadastro.
	 */
	@Test
	public void testeObterPorCpfCnpjSemCodigoCompartilhamentoCadastro() {
		InstituicaoResponsavelVO resp;
		try {
			resp = instituicaoResponsavelServico.obterByCpfCnpj("09808101755");
			Assert.assertNotNull(resp);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
}