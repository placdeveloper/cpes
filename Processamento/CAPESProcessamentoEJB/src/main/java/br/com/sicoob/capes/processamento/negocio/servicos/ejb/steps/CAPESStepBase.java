package br.com.sicoob.capes.processamento.negocio.servicos.ejb.steps;

import java.lang.reflect.UndeclaredThrowableException;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.commons.lang.exception.ExceptionUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.execucao.StepSicoobServico;

/**
 * Classe base para os steps do CAPES.
 *
 * @author Bruno.Carneiro
 */
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public abstract class CAPESStepBase extends StepSicoobServico {

	/** A constante MSG. */
	private static final String MSG = "Erro ao executar o step: ";

	/** O atributo logger. */
	private ISicoobLogger logger;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final RetornoExecucao executar(ContextoExecucao contexto) {
		if (contexto == null) {
			return erro("Contexto de execu\u00E7\u00E3o nulo. Acionar equipe do SWS.");
		}

		RetornoExecucao retorno;
		try {
			retorno = executarStep(contexto);
		} catch (NegocioException e) {
			getLogger().erro(e, MSG + e.getMessage());
			retorno = erro(e.getMessage());
		} catch (UndeclaredThrowableException e) {
			getLogger().erro(e, MSG + e.getMessage());

			Throwable causa = e.getCause();
			if (causa != null) {
				getLogger().erro(causa, MSG + causa.getMessage());
			}
			retorno = erro(ExceptionUtils.getFullStackTrace(e));
		} catch (Throwable t) {// NOPMD
			getLogger().erro(t, MSG + t.getMessage());
			retorno = erro(ExceptionUtils.getFullStackTrace(t));
		}
		return retorno;
	}

	/**
	 * Recupera o valor de logger.
	 *
	 * @return o objeto responsavel por imprimir os logs
	 */
	public ISicoobLogger getLogger() {
		if (logger == null) {
			logger = SicoobLoggerPadrao.getInstance(getClass());
		}
		return logger;
	}

	/**
	 * Executar trabalho.
	 *
	 * @param contexto
	 *            o valor de contexto
	 * @return RetornoExecucao
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	protected abstract RetornoExecucao executarStep(ContextoExecucao contexto) throws BancoobException;

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
		return (T) contexto.getParametroDinamico().<Long> getValor();
	}
	
}