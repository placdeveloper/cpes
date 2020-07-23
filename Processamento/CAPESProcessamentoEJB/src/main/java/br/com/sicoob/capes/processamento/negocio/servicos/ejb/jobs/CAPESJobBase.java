package br.com.sicoob.capes.processamento.negocio.servicos.ejb.jobs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.JobSicoobServico;
import br.com.sicoob.sws.api.execucao.Step;
import br.com.sicoob.sws.api.execucao.VerificacaoDependencias;

/**
 * Classe base para os JOB's do CAPES.
 *
 * @author Bruno.Carneiro
 */
public abstract class CAPESJobBase extends JobSicoobServico {

	/** O atributo logger. */
	private ISicoobLogger logger;

	/** A constante MSG. */
	private static final String MSG = "Ocorreu um erro ao executar o job: ";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<Step> obterSteps(ContextoExecucao contexto) {
		try {
			return criarListaSteps(contexto);
		} catch (Throwable t) { // NOPMD
			getLogger().erro(t, MSG + t.getMessage());
			throw new BancoobRuntimeException(MSG + t.getMessage());
		}
	}

	/**
	 * Recupera o logger para a classe informada.
	 *
	 * @return {@link ISicoobLogger}
	 */
	protected ISicoobLogger getLogger() {
		if (logger == null) {
			logger = SicoobLoggerPadrao.getInstance(getClass());
		}
		return logger;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final VerificacaoDependencias verificarDependencias(ContextoExecucao contexto) {
		if (contexto == null) {
			return erro("Contexto de execu\u00E7\u00E3o nulo. Acionar a equipe do SWS.");
		}

		try {
			return executarVerificacaoDependencias(contexto);
		} catch (Throwable t) {// NOPMD
			getLogger().erro(t, MSG + t.getMessage());
			return erro(ExceptionUtils.getFullStackTrace(t));
		}
	}

	/**
	 * Reagendar job.
	 * 
	 * @param contexto
	 *            o valor de contexto
	 * @param minutos
	 *            o valor de minutos
	 * @param mensagem
	 *            o valor de msg
	 *
	 * @return VerificacaoDependencias
	 */
	protected VerificacaoDependencias reagendarJob(ContextoExecucao contexto, int minutos, String mensagem) {
		VerificacaoDependencias resultado = null;
		if (isDataReagendamentoValida(contexto, minutos)) {
			resultado = reagendar(minutos, mensagem);
		} else {
			resultado = finalizarFluxo("N\u00E3o \u00E9 poss\u00EDvel reagendar o job.");
		}
		return resultado;
	}

	/**
	 * Verifica se o proximo reagendamento sera no mesmo dia do agendamento original.
	 *
	 * @param contexto
	 *            o valor de contexto
	 * @param minutos
	 *            o valor de minutos
	 * @return {@code true}, em caso de sucesso
	 */
	private boolean isDataReagendamentoValida(ContextoExecucao contexto, int minutos) {
		final Date dataProcessamento = DateUtils.truncate(contexto.getDataProcessamento(), Calendar.DATE);
		Calendar temp = Calendar.getInstance();
		temp.add(Calendar.MINUTE, minutos);
		getLogger().debug("Data calculada:", temp.getTime().toString());
		Date dataAgendamento = DateUtils.truncate(temp, Calendar.DATE).getTime();

		return dataAgendamento.compareTo(dataProcessamento) == NumberUtils.INTEGER_ZERO;
	}

	/**
	 * Executar verificacao dependencias.
	 *
	 * @param contexto
	 *            o valor de contexto
	 * @return VerificacaoDependencias
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 * @see {@link #verificarDependencias(ContextoExecucao)}
	 */
	protected abstract VerificacaoDependencias executarVerificacaoDependencias(ContextoExecucao contexto) throws BancoobException;

	/**
	 * Criar lista steps.
	 *
	 * @param contexto
	 *            o contexto de execucao
	 * @return a lista de steps
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 * @see Step
	 * @see #obterSteps(ContextoExecucao)
	 */
	protected abstract List<Step> criarListaSteps(ContextoExecucao contexto) throws BancoobException;

	/**
	 * Recupera o valor do parametro dinamico.
	 *
	 * @param <T>
	 *            o tipo generico
	 * @param contexto
	 *            o contexto de execucao recebido do TestSWS
	 * @return O valor do parametro dinamico
	 * @see ContextoExecucao#getParametroDinamico()
	 */
	@SuppressWarnings("unchecked")
	protected <T> T getParametroDinamico(ContextoExecucao contexto) {
		return (T) contexto.getParametroDinamico().getValor();
	}

	/**
	 * Calcula e define o esforco dos steps.
	 *
	 * @param steps
	 *            o valor de steps
	 * @return a lista de steps (a mesma que foi recebida)
	 */
	protected List<Step> calcularEsforco(Step... steps) {
		return calcularEsforco(Arrays.asList(steps));
	}

	/**
	 * Calcula e define o esforco dos steps.
	 *
	 * @param steps
	 *            o valor de steps
	 * @return a lista de steps (a mesma que foi recebida)
	 */
	protected List<Step> calcularEsforco(List<Step> steps) {
		Double esforco = NumberUtils.DOUBLE_ONE / steps.size();
		for (Step step : steps) {
			step.realizandoEsforcoDe(esforco);
		}
		return steps;
	}

	/**
	 * Criar steps.
	 *
	 * @param calcularEsforco
	 *            o valor de calcular esforco
	 * @param jndis
	 *            os nomes dos jndis.
	 * @return List
	 */
	protected List<Step> criarSteps(boolean calcularEsforco, String... jndis) {
		List<Step> lista = new ArrayList<Step>();
		for (String jndi : jndis) {
			Step step = criarStep(jndi);
			lista.add(step);
		}

		if (calcularEsforco) {
			return calcularEsforco(lista);
		}

		return lista;
	}

	/**
	 * Criar steps.
	 * 
	 * @param jndis
	 *            os nomes dos jndis.
	 *
	 * @return List
	 */
	protected List<Step> criarSteps(String... jndis) {
		return criarSteps(true, jndis);
	}

	/**
	 * Criar ejb.
	 *
	 * @param jndi
	 *            o valor de jndi
	 * @return Step
	 */
	protected Step criarStep(String jndi) {
		return ejb(jndi);
	}

}