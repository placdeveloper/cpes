/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.dominio.SistemaCooperativoCache;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * Interceptor para encontrar o sistema cooperativo referente a cooperativa do
 * usuário logado.
 * 
 * @author Erico.Junior
 */
public class SistemaCooperativoInterceptor {

	/** O atributo logger. */
	private transient ISicoobLogger logger = SicoobLoggerPadrao.getInstance(this.getClass());

	/**
	 * Intercepta a chamada ao Serviço EJB.
	 * 
	 * @param contexto
	 *            o contexto de execução do EJB.
	 * @return o retorno do Serviço EJB.
	 * @throws Exception 
	 */
	@AroundInvoke
	public Object intercept(InvocationContext contexto) throws Exception {

		InformacoesUsuarioCAPES informacoes = InformacoesUsuarioCAPES.getInstance();

		logger.debug("SistemaCooperativoInterceptor.intercept: inicio");
		if (informacoes != null && informacoes.getIdInstituicao() != null) {

			Integer idInstituicao = Integer.valueOf(informacoes.getIdInstituicao());
			Integer idSistemaCooperativo = recuperarIdSistemaCooperativo(idInstituicao);

			logger.debug("Recuperado o idSistemaCooperativo = " + idSistemaCooperativo
					+ " para instituicao = " + idInstituicao);

			informacoes.setIdSistemaCooperativo(idSistemaCooperativo);
			InformacoesUsuarioCAPES.setInstance(informacoes);
		}

		logger.debug("SistemaCooperativoInterceptor.intercept: fim");
		
		return contexto.proceed();
	}

	/**
	 * Recupera o idSistemaCooperativo para o idInstituicao informado.
	 * 
	 * @param idInstituicao
	 *            o identificador de instituição.
	 * @return o idSistemaCooperativo para o idInstituicao informado.
	 */
	private Integer recuperarIdSistemaCooperativo(Integer idInstituicao) throws BancoobException {

		SistemaCooperativoCache cache = SistemaCooperativoCache.getInstance();
		return cache.recuperarIdSistemaCooperativo(idInstituicao);
	}

}