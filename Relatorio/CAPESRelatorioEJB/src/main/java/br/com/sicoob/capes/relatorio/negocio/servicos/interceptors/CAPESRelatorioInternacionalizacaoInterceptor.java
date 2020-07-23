package br.com.sicoob.capes.relatorio.negocio.servicos.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor;
import br.com.sicoob.capes.relatorio.infraestrutura.mensagens.CAPESRelatorioResourceBundle;

/**
 * Interceptor que coloca o contexto de internacionalizacao para o sistema CAPESRelatorio
 * 
 * @author Stefanini IT Solutions
 */
public class CAPESRelatorioInternacionalizacaoInterceptor extends InternacionalizacaoInterceptor {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BancoobResourceBundle getResourceBundle() {
		return CAPESRelatorioResourceBundle.getInstance();
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
