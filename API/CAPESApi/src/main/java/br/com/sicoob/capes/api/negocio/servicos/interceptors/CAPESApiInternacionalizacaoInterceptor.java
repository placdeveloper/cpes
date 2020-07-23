/*
 * SICOOB
 * 
 * CAPESApiInternacionalizacaoInterceptor.java(br.com.sicoob.capes.api.negocio.servicos.interceptors.CAPESApiInternacionalizacaoInterceptor)
 */
package br.com.sicoob.capes.api.negocio.servicos.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor;
import br.com.sicoob.capes.api.infraestrutura.mensagens.CAPESApiResourceBundle;

/**
 * Interceptor que coloca o contexto de internacionalizacao para o sistema CadastroUnicoClientesComum
 * 
 * @author Stefanini IT Solutions
 */
public class CAPESApiInternacionalizacaoInterceptor extends InternacionalizacaoInterceptor {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected BancoobResourceBundle getResourceBundle() {
		return CAPESApiResourceBundle.getInstance();
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
