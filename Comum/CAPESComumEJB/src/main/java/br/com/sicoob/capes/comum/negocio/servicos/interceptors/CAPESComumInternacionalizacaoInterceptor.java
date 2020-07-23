/*
 * SICOOB
 * 
 * CAPESComumInternacionalizacaoInterceptor.java(br.com.sicoob.capes.comum.negocio.servicos.interceptors.CAPESComumInternacionalizacaoInterceptor)
 */
package br.com.sicoob.capes.comum.negocio.servicos.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor;
import br.com.sicoob.capes.comum.infraestrutura.mensagens.CAPESComumResourceBundle;

/**
 * Interceptor que coloca o contexto de internacionalizacao para o sistema CAPESComum
 * 
 * @author Stefanini IT Solutions
 */
public class CAPESComumInternacionalizacaoInterceptor extends InternacionalizacaoInterceptor {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected BancoobResourceBundle getResourceBundle() {
		return CAPESComumResourceBundle.getInstance();
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
