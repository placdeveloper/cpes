package br.com.sicoob.capes.frontoffice.negocio.servicos.ejb;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.dto.Parametro;
import br.com.bancoob.srtb.dto.RetornoMensagem;
import br.com.sicoob.capes.api.negocio.delegates.AnotacaoPessoaDelegate;
import br.com.sicoob.capes.api.negocio.delegates.CAPESApiFabricaDelegates;
import br.com.sicoob.capes.api.negocio.vo.AnotacaoPessoaVO;
import br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum;
import br.com.sicoob.capes.frontoffice.negocio.servicos.CAPESTransacaoServico;

/**
 * A Classe ConsultaAnotacaoExistenteServicoEJBTest.
 */
@Ignore
@RunWith(PowerMockRunner.class)
@PrepareForTest(CAPESApiFabricaDelegates.class)
public class ConsultaAnotacaoExistenteServicoEJBTest extends CAPESTransacaoServicoEJBTest {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESTransacaoServico criarServico() {

		ConsultaAnotacaoExistenteServicoEJB ejb = null;
		try {
			AnotacaoPessoaDelegate delegate = EasyMock.createMock(AnotacaoPessoaDelegate.class);
			EasyMock.expect(
					delegate.obterVigentesPorPessoaInstituicaoTipo(EasyMock.anyObject(String.class),
							EasyMock.anyObject(Integer.class), EasyMock.anyObject(Integer.class))).andReturn(criarListaAnotacoes());
			
			CAPESApiFabricaDelegates fabrica = EasyMock.createMock(CAPESApiFabricaDelegates.class);
			EasyMock.expect(fabrica.criarAnotacaoPessoaDelegate()).andReturn(delegate).anyTimes();
			EasyMock.replay(fabrica);
			
			PowerMock.mockStatic(CAPESApiFabricaDelegates.class);
			EasyMock.expect(CAPESApiFabricaDelegates.getInstance()).andReturn(fabrica).anyTimes();
			PowerMock.replay(CAPESApiFabricaDelegates.class);
			EasyMock.replay(delegate);
			ejb = new ConsultaAnotacaoExistenteServicoEJB();
			return ejb;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		return ejb;
	}

	/**
	 * Criar lista anotacoes.
	 *
	 * @return List
	 */
	protected List<AnotacaoPessoaVO> criarListaAnotacoes() {

		return Arrays.asList(new AnotacaoPessoaVO());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Mensagem criarMensagem() {

		Mensagem mensagem = new Mensagem();
		ParametroEnum parametroEnum = ParametroEnum.CPF_CNPJ;
		mensagem.getParametros().add(
				new Parametro(parametroEnum.getRotulo(), parametroEnum.getTipoParametro(), "22222222222", parametroEnum
						.getTipoDadoPersistencia()));
		
		parametroEnum = ParametroEnum.CODIGO_TIPO_ANOTACAO;
		mensagem.getParametros().add(
				new Parametro(parametroEnum.getRotulo(), parametroEnum.getTipoParametro(), 112, parametroEnum
						.getTipoDadoPersistencia()));
		
		parametroEnum = ParametroEnum.INSTITUICAO;
		mensagem.getParametros().add(
				new Parametro(parametroEnum.getRotulo(), parametroEnum.getTipoParametro(), 910, parametroEnum
						.getTipoDadoPersistencia()));
		return mensagem;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void realizarAssert(RetornoMensagem retornoMensagem) {
		
		Assert.assertTrue(StringUtils.isNotBlank(retornoMensagem.getConteudoRetorno()));
		Assert.assertEquals("1\\t\\n", retornoMensagem.getConteudoRetorno());
	}

}
