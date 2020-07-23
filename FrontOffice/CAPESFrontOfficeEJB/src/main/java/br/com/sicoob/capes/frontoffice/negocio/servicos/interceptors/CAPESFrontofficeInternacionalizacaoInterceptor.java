/*
 * SICOOB
 * 
 * CAPESFrontofficeInternacionalizacaoInterceptor.java(br.com.sicoob.capes.frontoffice.negocio.servicos.interceptors.CAPESFrontofficeInternacionalizacaoInterceptor)
 */
package br.com.sicoob.capes.frontoffice.negocio.servicos.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor;
import br.com.sicoob.capes.frontoffice.infraestrutura.mensagens.CAPESFrontofficeResourceBundle;

/**
 * Interceptor que coloca o contexto de internacionalizacao para o sistema CAPESFrontOffice
 * 
 * @author Stefanini IT Solutions
 */
public class CAPESFrontofficeInternacionalizacaoInterceptor extends InternacionalizacaoInterceptor {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected BancoobResourceBundle getResourceBundle() {
		return CAPESFrontofficeResourceBundle.getInstance();
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
