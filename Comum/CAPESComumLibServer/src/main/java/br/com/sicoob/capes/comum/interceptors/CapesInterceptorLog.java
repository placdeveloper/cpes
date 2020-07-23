package br.com.sicoob.capes.comum.interceptors;

import java.util.Collection;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.capes.comum.infraestrutura.mensagens.CapesResourceBundle;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * Interceptor para os EJB's
 * 
 * @author Paulo Stoppa - Mirante Tecnologia
 * 
 */
public class CapesInterceptorLog {

	private final ISicoobLogger log = SicoobLoggerPadrao.getInstance(CapesInterceptorLog.class);

	/**
	 * método que intercepta os métodos do EJB, e debuga os parâmetros e gera log Exception.
	 * 
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
	@AroundInvoke
	public Object interceptarMetodo(InvocationContext ctx) throws Exception { // NOSONAR é necessário retornar exception para não ter erro
																				// de incompatibilidade de tipos na excução do EJB
		Object retorno = null;
		if (!ctx.getMethod().getName().equals("verificarDisponibilidade")) {
			debug("interceptorLog.001", ctx.getTarget().getClass().getName(), ctx.getMethod().getName());
			gerarLogParametros(ctx.getParameters());
			try {
				retorno = ctx.proceed();
				gerarLogRetorno(retorno);
			} catch (NegocioException e) {
				debug("interceptorLog.003", e.getClass().getName());
				log.alerta(e, e.getMessage());
				throw e;
			} catch (Exception e) { // NOSONAR É necessário pegar todos tipos de exceção para gerar o log
				debug("interceptorLog.003", e.getClass().getName());
				log.erro(e, e.getMessage());
				throw e;
			}
			debug("interceptorLog.002", ctx.getTarget().getClass().getName(), ctx.getMethod().getName());
		} else {
			retorno = ctx.proceed();
		}
		return retorno;
	}

	/**
	 * [
	 * 
	 * @param parametros
	 */
	private void gerarLogParametros(Object[] parametros) {
		if (log.isDebugEnabled()) {
			debug("interceptorLog.004");
			if (parametros != null && parametros.length > 0) {
				for (Object param : parametros) {
					debug("interceptorLog.005", obterLogParametro(param));
				}
			} else {
				debug("interceptorLog.009");
			}
		}
	}

	/**
	 * Obtem a string do parâmetro para log
	 * 
	 * @param param
	 */
	protected String obterLogParametro(Object param) {
		String paramToLog = "NULO";
		if (param != null) {
			paramToLog = param.toString();
		}
		return paramToLog;
	}

	/**
	 * 
	 * @param retorno
	 */
	private void gerarLogRetorno(Object retorno) {
		if (log.isDebugEnabled()) {
			if (retorno != null) {
				String toStringRetorno;
				if (retorno instanceof Collection) {
					debug("interceptorLog.006", ((Collection<?>) retorno).size());
					toStringRetorno = obterLogRetorno((Collection<?>) retorno);
				} else if (retorno instanceof ConsultaDto<?>) {
					toStringRetorno = obterLogRetorno((ConsultaDto<?>) retorno);
				} else {
					toStringRetorno = obterLogRetorno(retorno);
				}
				debug("interceptorLog.007", toStringRetorno);
			} else {
				debug("interceptorLog.008");
			}
		}
	}

	/**
	 * Obtem a string do retorno para log
	 * 
	 * @param retorno
	 */
	protected String obterLogRetorno(Collection<?> retorno) {
		return ToStringBuilder.reflectionToString(((Collection<?>) retorno).toArray(), ToStringStyle.MULTI_LINE_STYLE);
	}

	/**
	 * Obtem a string do retorno para log
	 * 
	 * @param retorno
	 */
	protected String obterLogRetorno(ConsultaDto<?> retorno) {
		return ToStringBuilder.reflectionToString(retorno, ToStringStyle.MULTI_LINE_STYLE);
	}

	/**
	 * Obtem a string do retorno para log
	 * 
	 * @param retorno
	 */
	protected String obterLogRetorno(Object retorno) {
		return retorno.toString();
	}

	/**
	 * recupera a mensagem e gera o log de DEBUG
	 * 
	 * @param chave
	 * @param parametros
	 */
	private void debug(String chave, Object... parametros) {
		log.debug(recuperarMensagem(chave, parametros));
	}

	/**
	 * retorna a mensagem
	 * 
	 * @param chave
	 * @param parametros
	 * @return
	 */
	private String recuperarMensagem(String chave, Object... parametros) {
		return MensagemUtil.getString(chave, CapesResourceBundle.getInstance(), parametros);
	}

}