package br.com.sicoob.capes.processamento.negocio.servicos.ejb.steps;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoCompartilhamentoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ReplicacaoCadastroServicoLocal;
import br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoCompartilhamento;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.dominio.ResultadoExecucao;
import br.com.sicoob.sws.api.dominio.TipoParametro;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.parametro.Parametro;

public class CompartilharCadastroBancoobGrupoEconomicoStepEJBTest extends CAPESStepTest {

	@Mock
	private ReplicacaoCadastroServicoLocal servicoReplicacao;

	@Mock
	private GrupoCompartilhamentoServicoLocal grupoCompartilhamentoServico;

	@InjectMocks
	private CompartilharCadastroGrupoEconomicoStepEJB ejb;

	@Before
	public void setUp() throws BancoobException {
		MockitoAnnotations.initMocks(this);
		GrupoCompartilhamento grupoCompartilhamento = new GrupoCompartilhamento();
		grupoCompartilhamento.setCompartilhamentoCadastro(new CompartilhamentoCadastro());
		when(grupoCompartilhamentoServico.obterPorInstituicao(anyInt())).thenReturn(grupoCompartilhamento);
	}
	
	@Test
	public void executar() {
		ContextoExecucao contextoExecucao = criarContextoExecucao();
		Parametro parametroDinamico = new Parametro("IDINSTITUICAO", 1, TipoParametro.INTEIRO);
		Parametro parametroPessoaCompartilhamento = new Parametro("idPessoaCompartilhamento", 1578l, TipoParametro.LONGO);
		contextoExecucao.getParametrosStep().put("idPessoaCompartilhamento", parametroPessoaCompartilhamento);
		contextoExecucao.setParametroDinamico(parametroDinamico);
		RetornoExecucao retorno = ejb.executar(contextoExecucao);
		Assert.assertEquals(ResultadoExecucao.SUCESSO, retorno.getResultado());
	}

}
