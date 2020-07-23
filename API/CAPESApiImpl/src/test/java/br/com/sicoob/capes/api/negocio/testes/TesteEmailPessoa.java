package br.com.sicoob.capes.api.negocio.testes;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.filtros.FiltroEmail;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.EmailPessoaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.EmailPessoaVO;
import br.com.sicoob.capes.comum.negocio.enums.TipoEmailEnum;

/**
 * A Classe TesteEmailPessoa.
 */
public class TesteEmailPessoa extends CAPESTesteAbstract {

	/** O atributo emailServico. */
	EmailPessoaServicoRemote emailServico;

	/**
	 * O método Sets the up.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Before
	public void setUp() throws Exception {
		super.setPrincipalContexto();
		ServiceLocator locator = ServiceLocator.getInstance();
		emailServico = (EmailPessoaServicoRemote) locator.getObjetoRemoto("capes/api/EmailPessoaServico");
	}

	/**
	 * O método Teste obter por id.
	 */
	@Test
	public void testeObterPorID() {
		try {
			EmailPessoaVO email = emailServico.obterByID(104L);
			Assert.assertNotNull(email);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testeListarEmaisPessoaPorIdPessoaLegadoIdInstituicao() {
		try {
			List<EmailPessoaVO> emails = emailServico.listarEmaisPessoaPorIdPessoaLegadoIdInstituicao(31763, 910);
			Assert.assertNotNull(emails);
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
			List<EmailPessoaVO> emails = emailServico.obterByPessoaInstituicao(31063, 523);
			Assert.assertNotNull(emails);
			Assert.assertTrue("Lista errada", emails.size() == 1);
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
			List<EmailPessoaVO> emails = emailServico.obterNaoVigenteByPessoaInstituicao(31063, 523);
			Assert.assertNotNull(emails);
			Assert.assertTrue("Lista errada", emails.size() == 3);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * O método Teste incluir email.
	 */
	@Test
	public void testeIncluirEmail() {
		try {
			EmailPessoaVO dto = getMockEmail();

			emailServico.incluirEmail(dto);
			Assert.assertNotNull(dto);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testePesquisar() {
		try {
			FiltroEmail filtro = new FiltroEmail();
			filtro.setIdPessoa(31063);
			filtro.setIdInstituicao(523);
			filtro.setCodigoTipoEmail((short) 2);
			filtro.setDescricao("S6QX8ocCKoqlV272mc4@pfvOT.BRrC4");
			List<EmailPessoaVO> emails = emailServico.pesquisar(filtro);
			Assert.assertNotNull(emails);
			Assert.assertTrue(emails.size() > 0);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Recupera o valor de mockEmail.
	 *
	 * @return o valor de mockEmail
	 */
	private EmailPessoaVO getMockEmail() {
		EmailPessoaVO dto = new EmailPessoaVO();
		dto.setDescricaoEmail("Email@capesapi.com");
		dto.setCodigoTipoEmail(TipoEmailEnum.COMERCIAL.getCodigo());
		dto.setCpfCnpj("91408197448");
		dto.setIdInstituicao(910);

		return dto;
	}

}