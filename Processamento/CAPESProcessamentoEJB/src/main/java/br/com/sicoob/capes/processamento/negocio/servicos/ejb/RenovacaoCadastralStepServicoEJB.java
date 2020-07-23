package br.com.sicoob.capes.processamento.negocio.servicos.ejb;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ValidacaoCadastralServicoLocal;
import br.com.sicoob.sws.contexto.ContextoExecucaoStep;
import br.com.sicoob.sws.exception.StepException;
import br.com.sicoob.sws.step.EJBContextualStep;
import br.com.sicoob.sws.step.ISicoobContextualStep;
import br.com.sicoob.sws.step.RetornoStep;

@Stateless
@Remote({ ISicoobContextualStep.class })
public class RenovacaoCadastralStepServicoEJB extends EJBContextualStep {

	/** O atributo servico. */
	@EJB
	private ValidacaoCadastralServicoLocal servico;

	@Override
	public RetornoStep executar(ContextoExecucaoStep ctx) throws StepException {
		RetornoStep retornoStep = new RetornoStep();
		retornoStep.setResultado(RetornoStep.RESULTADO_SUCESSO);
		try {
			servico.verificarPendenciasPrazoRenovacaoCadastro();
			retornoStep.setMensagem("Teste efetuado com sucesso.");
		} catch (BancoobException e) {
			retornoStep.setMensagem("Teste efetuado com falha.");
		}

		return retornoStep;
	}

}
