package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.sicoob.capes.replicacao.utils.SWSUtils;
import br.com.sicoob.sws.contexto.ContextoExecucaoJob;
import br.com.sicoob.sws.exception.JobException;
import br.com.sicoob.sws.job.EJBContextualJob;
import br.com.sicoob.sws.job.ISicoobContextualJob;
import br.com.sicoob.sws.job.RetornoVerificacaoJob;
import br.com.sicoob.sws.parametro.ParametroJob;
import br.com.sicoob.sws.step.StepMetadata;


/**
 * A Classe VerificarDLQReplicacaoCadastroJobEJB.
 */
@Stateless
@Remote(ISicoobContextualJob.class)
public class VerificarDLQReplicacaoCadastroJobEJB extends EJBContextualJob {
	
	/** A constante NOME_JNDI_SERVICO_DEFAULT. */
	private static final String NOME_JNDI_SERVICO_DEFAULT = "capes/replicacao/VerificarDLQReplicacaoCadastroStepRemote";
	
	/** A constante PARAM_NOME_JNDI_STEP. */
	private static final short PARAM_NOME_JNDI_STEP = 1;

	/**
	 * {@inheritDoc}
	 */
	public List<StepMetadata> obterSteps(ContextoExecucaoJob ctx) throws JobException {
		List<StepMetadata> steps = new ArrayList<StepMetadata>();
		StepMetadata step = new StepMetadata();
		Map<Short, ParametroJob> mapaParamJob = SWSUtils.criarMapaParametrosJob(ctx.getParametrosJob());
		step.setServico(getNomeServico(mapaParamJob));
		step.setTimeout((short) 0);
		step.setTipo(StepMetadata.TIPO_STEP_EJB);
		step.setTrabalhoJob(100);
		steps.add(step);
		return steps;
	}

	/**
	 * {@inheritDoc}
	 */
	public RetornoVerificacaoJob verificarDependencias(ContextoExecucaoJob ctx) throws JobException {
		return new RetornoVerificacaoJob();
	}
	
	/**
	 * Recupera o nome jndi do serviço de step.
	 * 
	 * @param mapaParamJob
	 *            o mapa de parametros do job.
	 * @return o valor do parametro NOME_JNDI_STEP
	 */
	protected String getNomeServico(Map<Short, ParametroJob> mapaParamJob) {
		ParametroJob param = mapaParamJob.get(PARAM_NOME_JNDI_STEP);
		if (param != null) {
			return param.getValores().get(0);
		}

		return NOME_JNDI_SERVICO_DEFAULT;
	}

}