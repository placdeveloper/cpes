package br.com.sicoob.capes.api.negocio.testes;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.api.negocio.filtros.FiltroOcupacaoProfissional;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.OcupacaoProfissionalServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.OcupacaoProfissionalVO;

/**
 * Classe com os testes do serviço de ocupação profissional.
 * 
 * @author Bruno.Carneiro
 * 
 */
public class TesteOcupacaoProfissional {

	private OcupacaoProfissionalServicoRemote ocupacaoProfissionalServico;

	/**
	 * O método Sets the up.
	 * 
	 * @throws Exception
	 *             lança a exceção Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		ocupacaoProfissionalServico = (OcupacaoProfissionalServicoRemote) locator.getObjetoRemoto("capes/api/OcupacaoProfissionalServico");
	}

	@Test
	public void testeObterPesquisar() {
		try {
			FiltroOcupacaoProfissional filtro = new FiltroOcupacaoProfissional();
			List<OcupacaoProfissionalVO> lista = ocupacaoProfissionalServico.pesquisar(filtro);
			Assert.assertNotNull(lista);
			Assert.assertTrue(lista.size() > 0);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testeObterPesquisarPaginado() {
		try {
			FiltroOcupacaoProfissional filtro = new FiltroOcupacaoProfissional();
			filtro.setCodigoTipoOcupacao((short) 1);
			filtro.setDescricao("escr");
			ConsultaDto<OcupacaoProfissionalVO> consulta = ocupacaoProfissionalServico.pesquisarPaginado(filtro, 1, 5);
			List<OcupacaoProfissionalVO> lista = consulta.getResultado();
			Assert.assertNotNull(lista);
			Assert.assertTrue(lista.size() > 0);
			Assert.assertTrue(lista.size() == 10);
			Assert.assertTrue(consulta.getTotalRegistros() > 0);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

}