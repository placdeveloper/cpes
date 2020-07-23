package br.com.sicoob.capes.corporativo.negocio.servicos.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor;
import br.com.sicoob.capes.corporativo.infraestrutura.mensagens.CAPESCorporativoResourceBundle;

/**
 * O Interceptor da internacionalização do projeto 'CAPES-CORPORATIVO-EJB'
 * 
 * @author bruno.carneiro
 */
public class CAPESCorporativoInternacionalizacaoInterceptor extends InternacionalizacaoInterceptor {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BancoobResourceBundle getResourceBundle() {
		return CAPESCorporativoResourceBundle.getInstance();
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