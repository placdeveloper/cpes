package br.com.sicoob.capes.processamento.negocio.servicos.ejb.jobs;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.processamento.persistencia.dao.GrupoEconomicoDao;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.dominio.TipoParametro;
import br.com.sicoob.sws.api.execucao.Step;
import br.com.sicoob.sws.api.execucao.VerificacaoDependencias;
import br.com.sicoob.sws.api.parametro.Parametro;
import br.com.sicoob.sws.api.servico.JobServico;

/**
 * Job para compartilhar os cadastros das pessoas que possuem grupo econômico automático mas não está compartilhado nas cooperativas
 * envolvidas.
 * 
 * @author Paulo.Stoppa
 *
 */
@Stateless
@Remote(JobServico.class)
public class CompartilharCadastrosGrupoEconomicoJobEJB extends CAPESJobBase {

	@Inject
	@Default
	private transient GrupoEconomicoDao dao;

	private static final String STEP_INSTITUICOES = "capes/processamento/CompartilharCadastroGrupoEconomicoStepRemote";
	private static final String STEP_BANCOOB = "capes/processamento/CompartilharCadastroBancoobGrupoEconomicoStepRemote";

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected VerificacaoDependencias executarVerificacaoDependencias(ContextoExecucao contexto) throws BancoobException {
		final int total = dao.contarGruposSemCompartilhamentos(getParametroDinamico(contexto));
		if (total > 0) {
			return sucesso();
		} else {
			return finalizarJob(MensagemUtil.getString("msg.finalizarJob.semRegistros.001"));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Step> criarListaSteps(ContextoExecucao contexto) throws BancoobException {
		final List<Long> listaPessoas = dao.listarPessoasSemCompartilhamento(getParametroDinamico(contexto));
		final List<Step> steps = new ArrayList<Step>();
		final String jndi = getParametroDinamico(contexto).equals(Constantes.Comum.ID_INSTITUICAO_BANCOOB) ? STEP_BANCOOB : STEP_INSTITUICOES;
		for (Long idPessoaCompartilhamento : listaPessoas) {
			Step step = criarStep(jndi);
			step.comParametros(new Parametro("idPessoaCompartilhamento", idPessoaCompartilhamento, TipoParametro.LONGO));
			steps.add(step);
		}
		return calcularEsforco(steps);
	}

}
