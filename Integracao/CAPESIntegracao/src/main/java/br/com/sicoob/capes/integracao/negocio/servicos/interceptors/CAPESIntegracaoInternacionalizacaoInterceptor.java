package br.com.sicoob.capes.integracao.negocio.servicos.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor;
import br.com.sicoob.capes.integracao.infraestrutura.mensagens.CAPESIntegracaoResourceBundle;

/**
 * Interceptor que coloca o contexto de internacionalizacao para o sistema
 * CAPESIntegracao
 * 
 * @author Bruno.Carneiro
 */
public class CAPESIntegracaoInternacionalizacaoInterceptor extends InternacionalizacaoInterceptor {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BancoobResourceBundle getResourceBundle() {
		return CAPESIntegracaoResourceBundle.getInstance();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@AroundInvoke
	public Object intercept(InvocationContext contexto) throws Exception {
		return super.intercept(contexto);
	}
}