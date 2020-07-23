package br.com.sicoob.capes.api.negocio.testes;

import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.MensagemPessoaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.MensagemPessoaVO;

/**
 * A Classe TesteMensagemPessoa.
 */
public class TesteMensagemPessoa {

	/** O atributo mensagemServico. */
	MensagemPessoaServicoRemote mensagemServico;

	/**
	 * O método Sets the up.
	 * 
	 * @throws Exception
	 *             lança a exceção Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		mensagemServico = (MensagemPessoaServicoRemote) locator.getObjetoRemoto("capes/api/MensagemPessoaServico");
	}

	/**
	 * O método Teste obter ativas por pessoa instituicao.
	 */
	@Test
	public void testeObterAtivasPorPessoaInstituicao() {
		try {
			List<MensagemPessoaVO> lista = mensagemServico.obterAtivasByPessoaInstituicao(985783, 910);
			Assert.assertNotNull(lista);
			Assert.assertTrue(lista.size() > 0);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter mensagens ativas por pessoa instituicao.
	 */
	@Test
	public void testeObterMensagensAtivasPorPessoaInstituicao() {
		try {
			List<MensagemPessoaVO> lista = mensagemServico.obterMensagensAtivasPorPessoaInstituicao(985783, 910, (short)1);
			Assert.assertNotNull(lista);
			Assert.assertTrue(lista.size() > 0);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}

	}

	/**
	 * O método Teste incluir mensagens ev erificar retorno.
	 * 
	 * @throws ParseException
	 */
	@Test
	public void testeIncluirMensagensEVErificarRetorno() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		MensagemPessoaVO pessoa = new MensagemPessoaVO();
		pessoa.setMensagem("Teste");
		pessoa.setDataCadastro(sdf.parse("12/01/2016"));
		pessoa.setDataValidade(sdf.parse("12/08/2016"));
		pessoa.setCodigoDestinoExibicao((short) 1);
		pessoa.setCodigoTipoMensagem((short) 1);
		pessoa.setLoginUsuarioOperacao("TYLER.RAMIREZ");
		pessoa.setNumCpf("26596328473");
		pessoa.setIdInstituicao(935);
		pessoa.setLoginUsuarioOperacao("979833");

		List<MensagemPessoaVO> listaIncluir = Arrays.asList(pessoa);

		try {
			List<MensagemPessoaVO> lista = mensagemServico.incluirMensagensPorCpf(listaIncluir);

			Assert.assertNotNull(lista);
			Assert.assertTrue(lista.size() > 0);
			Assert.assertTrue(lista.get(0).getIdMensagem() != null);

		} catch (BancoobException e) {
			e.printStackTrace();
		}
	}
}