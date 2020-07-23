package br.com.sicoob.capes.processamento.negocio.servicos.ejb.jobs;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.Step;
import br.com.sicoob.sws.api.execucao.VerificacaoDependencias;
import br.com.sicoob.sws.api.servico.JobServico;

/**
 * Job para os fechamentos do CAPES.
 * 
 * Este é responsável por obter os steps que irão executar os fluxos que antes eram executados no Fechamento Agendado.
 *
 * @author Bruno.Carneiro
 */
@Stateless
@Remote(JobServico.class)
public class FechamentosJobEJB extends CAPESJobBase {

	private static final String STEP_BAIXAR_ANOTACOES_VENCIDAS = "capes/processamento/BaixarAnotacoesVencidasStepRemote";
	private static final String STEP_CANCELAR_AUTORIZACOES_VENCIDAS = "capes/processamento/CancelarAutorizacoesVencidasStepRemote";
	private static final String STEP_EXCLUIR_INFORMACOES_VENCIDAS = "capes/processamento/ExcluirInformacoesVencidasStepRemote";
	private static final String STEP_EXECUTAR_VALIDACAO_CADASTRAL = "capes/processamento/ExecutarValidacaoCadastralStepRemote";
	private static final String STEP_EXPORTACAO_ARQUIVO = "capes/processamento/ExportacaoArquivosStepRemote";

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected VerificacaoDependencias executarVerificacaoDependencias(ContextoExecucao contexto) throws BancoobException {
		return sucesso();
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	protected List<Step> criarListaSteps(ContextoExecucao contexto) throws BancoobException {
		List<Step> steps = new ArrayList<Step>();
		steps.add(criarStep(STEP_BAIXAR_ANOTACOES_VENCIDAS).comTimeout(0));
		steps.add(criarStep(STEP_CANCELAR_AUTORIZACOES_VENCIDAS).comTimeout(0));
		steps.add(criarStep(STEP_EXCLUIR_INFORMACOES_VENCIDAS).comTimeout(0));
		steps.add(criarStep(STEP_EXECUTAR_VALIDACAO_CADASTRAL).comTimeout(0));
		steps.add(criarStep(STEP_EXPORTACAO_ARQUIVO).comTimeout(0));
		return calcularEsforco(steps);
	}

}