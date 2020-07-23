package br.com.sicoob.capes.api.negocio.testes;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.GrupoCompartilhamentoServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.GrupoCompartilhamentoVO;

public class TesteGrupoCompartilhamento {

	GrupoCompartilhamentoServicoRemote grupoCompartilhamentoServico;

	/**
	 * O método Sets the up.
	 * 
	 * @throws Exception
	 *             lança a exceção Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		grupoCompartilhamentoServico = (GrupoCompartilhamentoServicoRemote) locator.getObjetoRemoto("capes/api/GrupoCompartilhamentoServico");
	}

	/**
	 * O método Teste obter por idInstituicao.
	 */
	@Test
	public void testeObterPorIdInstituicao() {
		try {
			GrupoCompartilhamentoVO vo = grupoCompartilhamentoServico.obterGrupoCompartilhamentoInstituicao(910);
			Assert.assertNotNull(vo);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
}
