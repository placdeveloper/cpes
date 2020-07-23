package br.com.sicoob.capes.frontoffice.negocio.servicos.ejb;

import java.util.Arrays;
import java.util.Date;
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
import br.com.sicoob.capes.api.negocio.delegates.CAPESApiFabricaDelegates;
import br.com.sicoob.capes.api.negocio.delegates.EmailPessoaDelegate;
import br.com.sicoob.capes.api.negocio.vo.EmailPessoaVO;
import br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum;
import br.com.sicoob.capes.frontoffice.negocio.servicos.CAPESTransacaoServico;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;

/**
 * A Classe ConsultaAnotacaoExistenteServicoEJBTest.
 */
@Ignore
@RunWith(PowerMockRunner.class)
@PrepareForTest({ CAPESIntegracaoFabricaDelegates.class, CAPESApiFabricaDelegates.class })
public class ConsultaEmailPessoaServicoEJBTest extends CAPESTransacaoServicoEJBTest {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESTransacaoServico criarServico() {

		ConsultaEmailPessoaServicoEJB ejb = null;
		try {
			
			SCIIntegracaoDelegate sciDelegate = EasyMock.createMock(SCIIntegracaoDelegate.class);
			EasyMock.expect(sciDelegate.obterIdInstituicao(EasyMock.anyObject(Integer.class))).andReturn(910);
			
			CAPESIntegracaoFabricaDelegates fabricaIntegracao = EasyMock.createMock(CAPESIntegracaoFabricaDelegates.class);
			EasyMock.expect(fabricaIntegracao.criarSCIIntegracaoDelegate()).andReturn(sciDelegate).anyTimes();
			EasyMock.replay(fabricaIntegracao);
			
			PowerMock.mockStatic(CAPESIntegracaoFabricaDelegates.class);
			EasyMock.expect(CAPESIntegracaoFabricaDelegates.getInstance()).andReturn(fabricaIntegracao).anyTimes();
			PowerMock.replay(CAPESIntegracaoFabricaDelegates.class);
			EasyMock.replay(sciDelegate);
			
			EmailPessoaDelegate delegate = EasyMock.createMock(EmailPessoaDelegate.class);
			EasyMock.expect(
					delegate.listarEmaisPessoaPorIdPessoaLegadoIdInstituicao(EasyMock.anyObject(Integer.class), EasyMock.anyObject(Integer.class))).andReturn(criarListaEmails());
			
			CAPESApiFabricaDelegates fabrica = EasyMock.createMock(CAPESApiFabricaDelegates.class);
			EasyMock.expect(fabrica.criarEmailPessoaDelegate()).andReturn(delegate).anyTimes();
			EasyMock.replay(fabrica);
			
			PowerMock.mockStatic(CAPESApiFabricaDelegates.class);
			EasyMock.expect(CAPESApiFabricaDelegates.getInstance()).andReturn(fabrica).anyTimes();
			PowerMock.replay(CAPESApiFabricaDelegates.class);
			EasyMock.replay(delegate);
			ejb = new ConsultaEmailPessoaServicoEJB();
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
	protected List<EmailPessoaVO> criarListaEmails() {
		EmailPessoaVO vo = new EmailPessoaVO();
		vo.setIdEmail(894304L);
		vo.setCodigoTipoEmail(new Short("0"));
		vo.setDataHoraInicio(new Date(1554336000000L));
		vo.setDescricaoEmail("FULANA_da_Silva@BELTRANA.COM");
		
		EmailPessoaVO vo2 = new EmailPessoaVO();
		vo2.setIdEmail(894246L);
		vo2.setCodigoTipoEmail(new Short("2"));
		vo2.setDataHoraInicio(new Date(1554336000000L));
		vo2.setDescricaoEmail("FULANA@BELTRANA.COM");
		
		return Arrays.asList(vo, vo2);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Mensagem criarMensagem() {

		Mensagem mensagem = new Mensagem();
		ParametroEnum parametroEnum = ParametroEnum.NUMERO_PESSOA;
		mensagem.getParametros().add(
				new Parametro(parametroEnum.getRotulo(), parametroEnum.getTipoParametro(), 31763, parametroEnum
						.getTipoDadoPersistencia()));
		
		parametroEnum = ParametroEnum.NUMERO_COOP_REMETENTE;
		mensagem.getParametros().add(
				new Parametro(parametroEnum.getRotulo(), parametroEnum.getTipoParametro(), 3353, parametroEnum
						.getTipoDadoPersistencia()));
		
		parametroEnum = ParametroEnum.TIPO_PESSOA;
		mensagem.getParametros().add(
				new Parametro(parametroEnum.getRotulo(), parametroEnum.getTipoParametro(), 0, parametroEnum
						.getTipoDadoPersistencia()));
		return mensagem;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void realizarAssert(RetornoMensagem retornoMensagem) {
		
		Assert.assertTrue(StringUtils.isNotBlank(retornoMensagem.getConteudoRetorno()));
	}

}
