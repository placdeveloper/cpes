package br.com.sicoob.capes.api.negocio.testes;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.ClienteServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.ClienteVO;
import br.com.sicoob.capes.comum.negocio.dto.AtualizacaoRiscoClienteDTO;

/**
 * A Classe TesteCliente.
 */
public class TesteCliente {

	/** O atributo clienteServico. */
	private ClienteServicoRemote clienteServico;

	/**
	 * O método Sets the up.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		clienteServico = (ClienteServicoRemote) locator.getObjetoRemoto("capes/api/ClienteServico");
	}

	/**
	 * O método Teste obter por id pessoa.
	 */
	@Test
	public void testeObterPorIdPessoa() {
		try {
			ClienteVO cliente = clienteServico.obterByIdPessoa(20723, 910);
			Assert.assertNotNull(cliente);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	/**
	 * O método Teste atualizar risco cliente.
	 */
	@Test
	public void testeAtualizarRiscoCliente() {
		try {
			AtualizacaoRiscoClienteDTO dto = new AtualizacaoRiscoClienteDTO();
			dto.setNumCliente(655);
			dto.setIdInstituicao(910);
			dto.setIdNivelRisco("A");
			dto.setMotivoRisco("NÍVEL DE RISCO C");
			clienteServico.atualizarRiscoCliente(dto);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	/**
	 * O método Teste nome paginado.
	 */
	@Test
	public void testeNomePaginado() {
		try {
			ConsultaDto<ClienteVO> consultaDto = clienteServico.obterByNome("pessoa física", 910, 0, 10);
			Assert.assertNotNull(consultaDto.getResultado());
			Assert.assertTrue(consultaDto.getTotalRegistros() > 0);
			Assert.assertTrue(consultaDto.getTotalRegistros().equals(consultaDto.getResultado().size()));
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	/**
	 * O método Teste nome apelido paginado.
	 */
	@Test
	public void testeNomeApelidoPaginado() {
		try {
			ConsultaDto<ClienteVO> consultaDto = clienteServico.obterByNomeApelido("TESTE", 910, 0, 10);
			Assert.assertNotNull(consultaDto.getResultado());
			Assert.assertTrue(consultaDto.getTotalRegistros() > 0);
			Assert.assertTrue(consultaDto.getTotalRegistros().equals(consultaDto.getResultado().size()));
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	/**
	 * O método Teste obter por id pessoa legado.
	 */
	@Test
	public void testeObterPorIdPessoaLegado() {
		try {
			ClienteVO cliente = clienteServico.obterByIdPessoaLegado(4847, 910);
			Assert.assertNotNull(cliente);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por cpf cnpj.
	 */
	@Test
	public void testeObterPorCpfCnpj() {
		try {
			ClienteVO cliente = clienteServico.obterByCpfCnpj("66083167477", 910);
			Assert.assertNotNull(cliente);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por nome.
	 */
	@Test
	public void testeObterPorNome() {
		try {
			List<ClienteVO> clientes = clienteServico.obterByNome("pessoa física", 910);
			Assert.assertNotNull(clientes);
			Assert.assertTrue(clientes.size() > 0);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	/**
	 * O método Teste obter por nome apelido.
	 */
	@Test
	public void testeObterPorNomeApelido() {
		try {
			List<ClienteVO> clientes = clienteServico.obterByNomeApelido("TESTE", 910);
			Assert.assertNotNull(clientes);
			Assert.assertTrue(clientes.size() > 0);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

}