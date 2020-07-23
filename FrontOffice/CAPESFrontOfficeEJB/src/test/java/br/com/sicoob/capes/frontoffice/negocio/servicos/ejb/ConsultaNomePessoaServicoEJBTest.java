package br.com.sicoob.capes.frontoffice.negocio.servicos.ejb;

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
import br.com.sicoob.capes.api.negocio.delegates.PessoaDelegate;
import br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum;
import br.com.sicoob.capes.frontoffice.negocio.servicos.CAPESTransacaoServico;

@Ignore
@RunWith(PowerMockRunner.class)
@PrepareForTest(CAPESApiFabricaDelegates.class)
public class ConsultaNomePessoaServicoEJBTest extends CAPESTransacaoServicoEJBTest {

	@Override
	protected CAPESTransacaoServico criarServico() {
		ConsultaNomePessoaServicoEJB ejb = null;
		try {
			PessoaDelegate delegate = EasyMock.createMock(PessoaDelegate.class);
			EasyMock.expect(delegate.obterNomePessoaCompartilhamentoSicoob(EasyMock.anyObject(String.class))).andReturn("Teste");

			CAPESApiFabricaDelegates fabrica = EasyMock.createMock(CAPESApiFabricaDelegates.class);
			EasyMock.expect(fabrica.criarPessoaDelegate()).andReturn(delegate).anyTimes();
			EasyMock.replay(fabrica);

			PowerMock.mockStatic(CAPESApiFabricaDelegates.class);
			EasyMock.expect(CAPESApiFabricaDelegates.getInstance()).andReturn(fabrica).anyTimes();
			PowerMock.replay(CAPESApiFabricaDelegates.class);
			EasyMock.replay(delegate);
			ejb = new ConsultaNomePessoaServicoEJB();
			return ejb;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		return ejb;
	}

	@Override
	protected Mensagem criarMensagem() {
		Mensagem mensagem = new Mensagem();
		ParametroEnum parametroEnum = ParametroEnum.CPF_CNPJ;
		mensagem.getParametros().add(new Parametro(parametroEnum.getRotulo(), parametroEnum.getTipoParametro(), "22222222222", parametroEnum.getTipoDadoPersistencia()));
		return mensagem;
	}

	@Override
	protected void realizarAssert(RetornoMensagem retornoMensagem) {
		Assert.assertTrue(StringUtils.isNotBlank(retornoMensagem.getConteudoRetorno()));
		Assert.assertEquals("Teste\\t\\n", retornoMensagem.getConteudoRetorno());
	}

}