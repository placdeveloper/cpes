package br.com.sicoob.capes.api.negocio.testes;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.dto.EnderecoPessoaDTO;
import br.com.sicoob.capes.api.negocio.filtros.FiltroEndereco;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.EnderecoPessoaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.EnderecoPessoaVO;
import br.com.sicoob.capes.comum.negocio.enums.TipoEnderecoEnum;

/**
 * A Classe TesteEnderecoPessoa.
 */
public class TesteEnderecoPessoa extends CAPESTesteAbstract{
	
	/** O atributo enderecoDelegate. */
	EnderecoPessoaServicoRemote enderecoDelegate;

	/**
	 * O método Sets the up.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Before
	public void setUp() throws Exception {
		super.setPrincipalContexto();
		ServiceLocator locator = ServiceLocator.getInstance();
		enderecoDelegate = (EnderecoPessoaServicoRemote) locator.getObjetoRemoto("capes/api/EnderecoPessoaServico");
	}

	/**
	 * O método Teste obter endereco correspondencia por pessoa instituicao.
	 */
	@Test
	public void testeObterEnderecoCorrespondenciaPorPessoaInstituicao() {
		try {
			EnderecoPessoaVO endereco  = enderecoDelegate.obterEnderecoCorrespondenciaByPessoaInstituicao(544715, 523);
			Assert.assertNotNull(endereco);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * O método Teste obter por id.
	 */
	@Test
	public void testeObterPorID() {
		try {
			EnderecoPessoaVO endereco  = enderecoDelegate.obterByID(44L);
			Assert.assertEquals(Long.valueOf(44).longValue(), endereco.getIdEndereco().longValue());
			Assert.assertNotNull(endereco);
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
			List<EnderecoPessoaVO> enderecos = enderecoDelegate.obterByPessoaInstituicao(21148, 910);
			Assert.assertNotNull(enderecos);
			Assert.assertTrue(enderecos.size() > 0);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * Test method for {@link br.com.sicoob.capes.api.negocio.delegates.AbstractCAPESApiPessoaDelegate#obterPorPessoaInstituicao(java.lang.Integer, java.lang.Integer)}.
	 */
	@Test
	public void obterNaoVigentePorPessoaInstituicao() {
		try {
			List<EnderecoPessoaVO> enderecos = enderecoDelegate.obterNaoVigenteByPessoaInstituicao(544525, 523);
			Assert.assertNotNull(enderecos);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * O método Teste incluir endereco.
	 */
	@Test
	public void testeIncluirEndereco() {
		try {
			EnderecoPessoaDTO endereco = getMockEndereco();
			
			enderecoDelegate.incluirEndereco(endereco);
			Assert.assertNotNull(endereco);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testePesquisarEndereco() {
		try {
			FiltroEndereco filtro = new FiltroEndereco();
			filtro.setCep("70680550");
			filtro.setIdPessoa(21148);
			filtro.setIdInstituicao(910);
			List<EnderecoPessoaVO> lista = enderecoDelegate.pesquisar(filtro);
			Assert.assertNotNull(lista);
			Assert.assertTrue(lista.size() > 0);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Recupera o valor de mockEndereco.
	 *
	 * @return o valor de mockEndereco
	 */
	private EnderecoPessoaDTO getMockEndereco() {
		EnderecoPessoaDTO endereco = new EnderecoPessoaDTO();
		
		endereco.setCodigoTipoEndereco(TipoEnderecoEnum.OUTROS.getCodigo());
		endereco.setDescricao("Descrição de endereço capes integração");
		endereco.setIdTipoLogradouro((short)1);
		endereco.setIdPessoaCompartilhamento(527720L);
		endereco.setBairro("Bairro integração");
		endereco.setCep("71070684");
		endereco.setNumero("56111");
		endereco.setIdLocalidade(3104);
		endereco.setComplemento("Teste");
		
		return endereco;
	}

}