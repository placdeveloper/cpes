package br.com.sicoob.capes.api.negocio.testes;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.CertidaoPessoaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.CertidaoPessoaVO;

/**
 * A Classe TesteCertidao.
 */
public class TesteCertidao {

	/** O atributo certidaoServico. */
	CertidaoPessoaServicoRemote certidaoServico;

	/**
	 * O método Sets the up.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		certidaoServico = (CertidaoPessoaServicoRemote) locator.getObjetoRemoto("capes/api/CertidaoPessoaServico");
	}

	/**
	 * O método Teste obter por id.
	 */
	@Test
	public void testeObterPorID() {
		try {
			CertidaoPessoaVO cert = certidaoServico.obterByID(682L);
			Assert.assertNotNull(cert);
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
			List<CertidaoPessoaVO> certs = certidaoServico.obterByPessoaInstituicao(20609, 910);
			Assert.assertNotNull(certs);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
}