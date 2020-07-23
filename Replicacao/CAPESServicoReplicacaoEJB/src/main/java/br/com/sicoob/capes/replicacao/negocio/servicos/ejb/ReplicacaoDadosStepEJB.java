package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.ReplicarCadastroClientesServicoLocal;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sws.contexto.ContextoExecucaoStep;
import br.com.sicoob.sws.exception.StepException;
import br.com.sicoob.sws.step.EJBContextualStep;
import br.com.sicoob.sws.step.ISicoobContextualStep;
import br.com.sicoob.sws.step.RetornoStep;

/**
 * A Classe ReplicacaoDadosStepEJB.
 */
@Stateless
@Remote(ISicoobContextualStep.class)
public class ReplicacaoDadosStepEJB extends EJBContextualStep {

	/** O atributo servico. */
	@EJB(mappedName = "capes/replicacao/ReplicarCadastroClientesServico")
	private ReplicarCadastroClientesServicoLocal servico;
	
	private static final SicoobLoggerPadrao LOG = SicoobLoggerPadrao.getInstance(ReplicacaoDadosStepEJB.class);

	/**
	 * {@inheritDoc}
	 */
	public RetornoStep executar(ContextoExecucaoStep ctx) throws StepException {
		RetornoStep retornoStep = new RetornoStep();
		retornoStep.setResultado(RetornoStep.RESULTADO_SUCESSO);
		try {
			servico.replicaCadastroClientes();
		} catch (BancoobException e) {
			LOG.erro(e, "Falha ao replicar os dados dos clientes.");
			retornoStep.setResultado(RetornoStep.RESULTADO_ERRO);
			retornoStep.setMensagem("Falha ao replicar os dados dos clientes. Será feita uma nova tentativa no próximo processamento.");
		} catch (BancoobRuntimeException re) {
			LOG.erro(re, "Falha ao replicar os dados dos clientes.");
			retornoStep.setResultado(RetornoStep.RESULTADO_ERRO);
			retornoStep.setMensagem("Falha ao replicar os dados dos clientes. Será feita uma nova tentativa no próximo processamento.");
		} catch (Exception e) { //NOPMD
			LOG.erro(e, "Falha ao replicar os dados dos clientes.");
			retornoStep.setResultado(RetornoStep.RESULTADO_ERRO);
			retornoStep.setMensagem("Falha ao replicar os dados dos clientes. Será feita uma nova tentativa no próximo processamento.");
		}

		return retornoStep;
	}

}