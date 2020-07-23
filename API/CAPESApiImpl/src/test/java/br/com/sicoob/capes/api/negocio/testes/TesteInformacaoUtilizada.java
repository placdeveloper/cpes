package br.com.sicoob.capes.api.negocio.testes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.InformacaoUtilizadaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.InformacaoUtilizadaVO;
import br.com.sicoob.capes.comum.negocio.enums.TipoInformacaoEnum;

/**
 * A Classe TesteInformacaoUtilizada.
 */
public class TesteInformacaoUtilizada {

	/** O atributo InformacaoUtilizadaServico. */
	private InformacaoUtilizadaServicoRemote InformacaoUtilizadaServico;

	/**
	 * O método Sets the up.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		InformacaoUtilizadaServico = (InformacaoUtilizadaServicoRemote) locator.getObjetoRemoto("capes/api/InformacaoUtilizadaServico");
	}

	/**
	 * O método Teste incluir.
	 */
	@Test
	public void testeIncluir() {
		try {
			InformacaoUtilizadaVO vo = new InformacaoUtilizadaVO();
			vo.setCodigoInformacao(141L);
			vo.setCodigoTipoInformacao(TipoInformacaoEnum.INFORMACAO_PROFISSIONAL.getCodigo());
			vo.setCodigoUtilizaInformacao((short) 1);
			InformacaoUtilizadaServico.incluirInformacaoUtilizada(vo);
			Assert.assertTrue(Boolean.TRUE);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * O método Teste excluir.
	 */
	@Test
	@Ignore
	public void testeExcluir() {
		try {
			InformacaoUtilizadaVO vo = new InformacaoUtilizadaVO();
			vo.setCodigoInformacao(141L);
			vo.setCodigoTipoInformacao(TipoInformacaoEnum.INFORMACAO_PROFISSIONAL.getCodigo());
			vo.setCodigoUtilizaInformacao((short) 1);
			InformacaoUtilizadaServico.excluirInformacaoUtilizada(vo);
			Assert.assertTrue(Boolean.TRUE);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
}