package br.com.sicoob.capes.processamento.negocio.servicos.ejb.jobs;

import java.util.HashMap;

import org.junit.Ignore;
import org.mockito.Mockito;

import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.parametro.Parametro;

/**
 * Classe com métodos utilitários para os testes
 * @author Paulo.Stoppa
 *
 */
@Ignore
public abstract class CAPESJobTest extends Mockito {
	
	protected void adicionarParametroJob(ContextoExecucao contexto, Parametro parametro) {
		if(contexto.getParametrosJob() == null) {
			contexto.setParametrosJob(new HashMap<String, Parametro>());
		}
		contexto.getParametrosJob().put(parametro.getChave(), parametro);
	}
	
	protected void adicionarParametroFluxo(ContextoExecucao contexto, Parametro parametro) {
		if(contexto.getParametrosFluxo() == null) {
			contexto.setParametrosFluxo(new HashMap<String, Parametro>());
		}
		contexto.getParametrosFluxo().put(parametro.getChave(), parametro);
	}
	
	protected ContextoExecucao criarContextoExecucao() {
		ContextoExecucao contexto = new ContextoExecucao();
		contexto.setParametrosJob(new HashMap<String, Parametro>());
		contexto.setParametrosFluxo(new HashMap<String, Parametro>());
		return contexto;
	}
	
}
