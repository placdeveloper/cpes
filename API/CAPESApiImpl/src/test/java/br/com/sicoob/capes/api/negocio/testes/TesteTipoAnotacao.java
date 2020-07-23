package br.com.sicoob.capes.api.negocio.testes;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.TipoAnotacaoServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.TipoAnotacaoVO;

/**
 * A Classe TesteTipoAnotacao.
 */
public class TesteTipoAnotacao {

	/** O atributo tipoAnotacaoServico. */
	private TipoAnotacaoServicoRemote tipoAnotacaoServico;

	/**
	 * O método Sets the up.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		tipoAnotacaoServico = (TipoAnotacaoServicoRemote) locator.getObjetoRemoto("capes/api/TipoAnotacaoServico");
	}

	/**
	 * O método Teste obter tipos anotacao ativos.
	 */
	@Test
	public void testeObterTiposAnotacaoAtivos() {
		try {
			List<TipoAnotacaoVO> tipos = tipoAnotacaoServico.obterTiposAnotacaoAtivos();
			Assert.assertNotNull(tipos);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	/**
	 * O método Teste obter tipo anotacao por id.
	 */
	@Test
	public void testeObterTipoAnotacaoPorId() {
		try {
			TipoAnotacaoVO tipo = tipoAnotacaoServico.obterTipoAnotacaoPorId(3);
			Assert.assertNotNull(tipo);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
}