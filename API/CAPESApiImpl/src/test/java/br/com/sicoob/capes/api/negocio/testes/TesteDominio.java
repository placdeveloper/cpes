package br.com.sicoob.capes.api.negocio.testes;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.DominioServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.DominioVO;
import br.com.sicoob.capes.comum.negocio.enums.TipoDominioEnum;

/**
 * A Classe TesteDominio.
 */
public class TesteDominio {
	
	/** O atributo dominioServico. */
	private DominioServicoRemote dominioServico;

	/**
	 * O método Sets the up.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		dominioServico = (DominioServicoRemote) locator.getObjetoRemoto("capes/api/DominioServico");
	}

	/**
	 * O método Teste obter por tipo dominio.
	 */
	@Test
	public void testeObterPorTipoDominio() {
		try {
			TipoDominioEnum[] tipos = TipoDominioEnum.values();
			for (TipoDominioEnum tipoDominioEnum : tipos) {
				List<DominioVO> lista = dominioServico.obterByTipoDominio(tipoDominioEnum);
				Assert.assertNotNull(lista);
				Assert.assertTrue(lista.size() > 0);
			}
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
}