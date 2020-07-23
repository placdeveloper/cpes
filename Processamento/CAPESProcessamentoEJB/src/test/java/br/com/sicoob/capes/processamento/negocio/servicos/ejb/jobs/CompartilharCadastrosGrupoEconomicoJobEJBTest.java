package br.com.sicoob.capes.processamento.negocio.servicos.ejb.jobs;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.processamento.persistencia.dao.GrupoEconomicoDao;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.dominio.ResultadoExecucao;
import br.com.sicoob.sws.api.dominio.TipoParametro;
import br.com.sicoob.sws.api.execucao.Step;
import br.com.sicoob.sws.api.execucao.VerificacaoDependencias;
import br.com.sicoob.sws.api.parametro.Parametro;

public class CompartilharCadastrosGrupoEconomicoJobEJBTest extends CAPESJobTest {

	@Mock
	private GrupoEconomicoDao dao;

	@InjectMocks
	private CompartilharCadastrosGrupoEconomicoJobEJB ejb;

	@Before
	public void setUp() throws BancoobException {
		MockitoAnnotations.initMocks(this);
		when(dao.contarGruposSemCompartilhamentos(55)).thenReturn(0);
		when(dao.contarGruposSemCompartilhamentos(195)).thenReturn(1);
		List<Long> listaInstituicao = new ArrayList<>();
		listaInstituicao.add(0l);
		listaInstituicao.add(1l);
		listaInstituicao.add(2l);
		listaInstituicao.add(3l);
		listaInstituicao.add(4l);
		when(dao.listarPessoasSemCompartilhamento(166)).thenReturn(listaInstituicao);
		List<Long> listaBancoob = new ArrayList<>();
		listaBancoob.add(0l);
		listaBancoob.add(5l);
		listaBancoob.add(10l);
		when(dao.listarPessoasSemCompartilhamento(1)).thenReturn(listaBancoob);
	}

	@Test
	public void verificarDependenciasSemGrupos() {
		ContextoExecucao contextoExecucao = criarContextoExecucao();
		Parametro parametroDinamico = new Parametro("IDINSTITUICAO", 55, TipoParametro.INTEIRO);
		contextoExecucao.setParametroDinamico(parametroDinamico);
		VerificacaoDependencias retorno = ejb.verificarDependencias(contextoExecucao);
		Assert.assertEquals(ResultadoExecucao.FINALIZAR_JOB, retorno.getResultado());
		Assert.assertEquals("msg.finalizarJob.semRegistros.001", retorno.getMensagem());
	}

	@Test
	public void verificarDependenciasComGrupos() {
		ContextoExecucao contextoExecucao = criarContextoExecucao();
		Parametro parametroDinamico = new Parametro("IDINSTITUICAO", 195, TipoParametro.INTEIRO);
		contextoExecucao.setParametroDinamico(parametroDinamico);
		VerificacaoDependencias retorno = ejb.verificarDependencias(contextoExecucao);
		Assert.assertEquals(ResultadoExecucao.SUCESSO, retorno.getResultado());
	}

	@Test
	public void obterSteps() {
		ContextoExecucao contexto = criarContextoExecucao();
		Parametro parametroDinamico = new Parametro("IDINSTITUICAO", 166, TipoParametro.INTEIRO);
		contexto.setParametroDinamico(parametroDinamico);
		List<Step> listaSteps = ejb.obterSteps(contexto);
		Assert.assertEquals(5, listaSteps.size());
		for (Integer i = 0; i < 5; i++) {
			Step step = listaSteps.get(i);
			Parametro parametroIdPessoa = step.getParametros().get("idPessoaCompartilhamento");
			Long valorParametroIdPessoa = parametroIdPessoa.getValor();
			Assert.assertEquals("capes/processamento/CompartilharCadastroGrupoEconomicoStepRemote", step.getServico());
			Assert.assertEquals(i.longValue(), valorParametroIdPessoa.longValue());
		}
	}

	@Test
	public void obterStepsBancoob() {
		ContextoExecucao contexto = criarContextoExecucao();
		Parametro parametroDinamico = new Parametro("IDINSTITUICAO", 1, TipoParametro.INTEIRO);
		contexto.setParametroDinamico(parametroDinamico);
		List<Step> listaSteps = ejb.obterSteps(contexto);
		Assert.assertEquals(3, listaSteps.size());
		for (Integer i = 0; i < 3; i++) {
			Step step = listaSteps.get(i);
			Parametro parametroIdPessoa = step.getParametros().get("idPessoaCompartilhamento");
			Long valorParametroIdPessoa = parametroIdPessoa.getValor();
			Assert.assertEquals("capes/processamento/CompartilharCadastroBancoobGrupoEconomicoStepRemote", step.getServico());
			Assert.assertEquals(i.longValue()*5l, valorParametroIdPessoa.longValue());
		}
	}

}
