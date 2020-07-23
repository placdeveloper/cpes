package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.VerificarDLQReplicacaoCadastroServicoLocal;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sws.contexto.ContextoExecucaoStep;
import br.com.sicoob.sws.exception.StepException;
import br.com.sicoob.sws.step.EJBContextualStep;
import br.com.sicoob.sws.step.ISicoobContextualStep;
import br.com.sicoob.sws.step.RetornoStep;

/**
 * A Classe VerificarDLQReplicacaoCadastroStepEJB.
 */
@Stateless
@Remote(ISicoobContextualStep.class)
public class VerificarDLQReplicacaoCadastroStepEJB extends EJBContextualStep {
	
	/** O atributo servico. */
	@EJB(mappedName = "capes/replicacao/VerificarDLQReplicacaoCadastroServico")
	private VerificarDLQReplicacaoCadastroServicoLocal servico;
	
	private ISicoobLogger logger = SicoobLoggerPadrao.getInstance(VerificarDLQReplicacaoCadastroStepEJB.class);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoStep executar(ContextoExecucaoStep ctx) throws StepException {
		RetornoStep retornoStep = new RetornoStep();
		retornoStep.setResultado(RetornoStep.RESULTADO_SUCESSO);
		try {
			servico.processarMensagens();
		} catch (BancoobException e) {
			logger.erro(e, "Erro ao verificar a DLQ de replicacao.");
			retornoStep.setResultado(RetornoStep.RESULTADO_ERRO);
			retornoStep.setMensagem("Falha ao verificar DLQ replicacao. " + e.getMessage());
		}
		return retornoStep;
	}

}
