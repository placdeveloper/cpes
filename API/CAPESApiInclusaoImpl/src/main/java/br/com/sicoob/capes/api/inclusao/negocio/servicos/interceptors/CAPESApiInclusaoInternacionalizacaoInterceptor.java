package br.com.sicoob.capes.api.inclusao.negocio.servicos.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor;
import br.com.sicoob.capes.api.inclusao.infraestrutura.mensagens.CAPESApiInclusaoResourceBundle;

/**
 * O Interceptor da internacionalização do projeto 'CAPES-API-INCLUSÃO'
 * 
 * @author bruno.carneiro
 */
public class CAPESApiInclusaoInternacionalizacaoInterceptor extends InternacionalizacaoInterceptor {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BancoobResourceBundle getResourceBundle() {
		return CAPESApiInclusaoResourceBundle.getInstance();
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