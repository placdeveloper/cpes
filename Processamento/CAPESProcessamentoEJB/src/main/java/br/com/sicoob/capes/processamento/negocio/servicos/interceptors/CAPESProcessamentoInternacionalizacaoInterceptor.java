package br.com.sicoob.capes.processamento.negocio.servicos.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor;
import br.com.sicoob.capes.processamento.infraestrutura.mensagens.CAPESProcessamentoResourceBundle;

/**
 * Interceptor que coloca o contexto de internacionalizacao para o sistema CAPESProcessamento
 * 
 * @author Stefanini IT Solutions
 */
public class CAPESProcessamentoInternacionalizacaoInterceptor extends InternacionalizacaoInterceptor {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BancoobResourceBundle getResourceBundle() {
		return CAPESProcessamentoResourceBundle.getInstance();
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
