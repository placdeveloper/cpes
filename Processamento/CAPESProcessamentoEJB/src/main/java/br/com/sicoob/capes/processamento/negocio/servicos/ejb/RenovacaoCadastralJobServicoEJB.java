package br.com.sicoob.capes.processamento.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.sicoob.sws.contexto.ContextoExecucaoJob;
import br.com.sicoob.sws.exception.JobException;
import br.com.sicoob.sws.job.EJBContextualJob;
import br.com.sicoob.sws.job.ISicoobContextualJob;
import br.com.sicoob.sws.job.RetornoVerificacaoJob;
import br.com.sicoob.sws.step.StepMetadata;

@Stateless
@Remote({ ISicoobContextualJob.class })
public class RenovacaoCadastralJobServicoEJB extends EJBContextualJob {

	
	/** A constante NOME_JNDI_SERVICO_DEFAULT. */
	private static final String NOME_JNDI_SERVICO_DEFAULT = "capes/processamento/RenovacaoCadastralStepRemote";
	
	public List<StepMetadata> obterSteps(ContextoExecucaoJob ctx) throws JobException {
		List<StepMetadata> steps = new ArrayList<StepMetadata>();
		
		StepMetadata step = new StepMetadata();
		step.setServico(NOME_JNDI_SERVICO_DEFAULT);
		step.setTimeout((short) 0);
		step.setTipo(StepMetadata.TIPO_STEP_EJB);
		step.setTrabalhoJob(100);

		steps.add(step);
		
		return steps;
	}

	public RetornoVerificacaoJob verificarDependencias(ContextoExecucaoJob ctx) throws JobException {
		return new RetornoVerificacaoJob();
	}

	


	
}
