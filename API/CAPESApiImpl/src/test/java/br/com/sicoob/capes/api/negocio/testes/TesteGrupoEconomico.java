package br.com.sicoob.capes.api.negocio.testes;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.GrupoEconomicoServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.GrupoEconomicoVO;
import br.com.sicoob.capes.api.negocio.vo.PessoaGrupoEconomicoVO;

/**
 * A Classe TesteGrupoEconomico.
 */
public class TesteGrupoEconomico {

	/** O atributo grupoEconomicoServico. */
	GrupoEconomicoServicoRemote grupoEconomicoServico;

	/**
	 * O método Sets the up.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		grupoEconomicoServico = (GrupoEconomicoServicoRemote) locator.getObjetoRemoto("capes/api/GrupoEconomicoServico");
	}

	/**
	 * O método Teste obter por pessoa instituicao.
	 */
	@Test
	public void testeObterPorPessoaInstituicao() {
		try {
			GrupoEconomicoVO dto = grupoEconomicoServico.obterByPessoaInstituicao(21796, 523);
			Assert.assertNotNull(dto);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * O método Teste obter pessoas por grupo instituica.
	 */
	@Test
	public void testeObterPessoasPorGrupoInstituica() {
		try {
			List<PessoaGrupoEconomicoVO> lista = grupoEconomicoServico.obterPessoasPorGrupoInstituicao(958710, 910);
			Assert.assertNotNull(lista);
			Assert.assertTrue(lista.size() > 0);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * O método Teste testeobterGruposPorCpfCnpj.
	 */
	@Test
	public void testeobterGruposPorCpfCnpj() {
		try {
			List<String> listaCpfCnpj = new ArrayList<String>();
			listaCpfCnpj.add("05258162591");
			listaCpfCnpj.add("47407138885");
			listaCpfCnpj.add("69273060307");
			listaCpfCnpj.add("05642157329");
			listaCpfCnpj.add("32187315266");

			List<PessoaGrupoEconomicoVO> lista = grupoEconomicoServico.obterGruposPorCpfCnpj(listaCpfCnpj, 910);
			Assert.assertNotNull(lista);
			Assert.assertTrue(lista.size() > 0);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
}