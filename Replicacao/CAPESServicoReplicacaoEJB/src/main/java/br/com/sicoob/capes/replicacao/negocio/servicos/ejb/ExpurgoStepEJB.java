package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.TransicaoReplicacaoServicoLocal;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sws.contexto.ContextoExecucaoStep;
import br.com.sicoob.sws.exception.StepException;
import br.com.sicoob.sws.step.EJBContextualStep;
import br.com.sicoob.sws.step.ISicoobContextualStep;
import br.com.sicoob.sws.step.RetornoStep;

/**
 * A Classe ExpurgoStepEJB.
 */
@Stateless
@Remote(ISicoobContextualStep.class)
public class ExpurgoStepEJB extends EJBContextualStep {
	
	/** O atributo servico. */
	@EJB(mappedName = "capes/replicacao/TransicaoReplicacaoServico")
	private TransicaoReplicacaoServicoLocal servico;
	
	/** A constante NUMERO_DIAS_EXPURGO. */
	private static final int NUMERO_DIAS_EXPURGO = 5;
	
	private ISicoobLogger logger = SicoobLoggerPadrao.getInstance(ExpurgoStepEJB.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoStep executar(ContextoExecucaoStep ctx) throws StepException {
		RetornoStep retornoStep = new RetornoStep();
		retornoStep.setResultado(RetornoStep.RESULTADO_SUCESSO);
		try {
			
			Date data = DataUtil.obterDataAtual();
			data = DataUtil.decrementarData(data, Calendar.DAY_OF_MONTH, NUMERO_DIAS_EXPURGO);
			data = DataUtil.configurarSegundaDataIntervalo(data);
			servico.removerReplicados(data);
		} catch (BancoobException e) {
			logger.erro(e, "Erro no expurgo de informacoes.");
			retornoStep.setResultado(RetornoStep.RESULTADO_ERRO);
			retornoStep.setMensagem("Falha ao expurgar os dados para replicação. Será feita uma nova tentativa no próximo processamento.");
		}
		
		return retornoStep;
	}

}
