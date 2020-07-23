package br.com.sicoob.capes.replicacao.negocio.servicos.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor;
import br.com.sicoob.capes.replicacao.infraestrutura.mensagens.CAPESReplicacaoComumResourceBundle;

/**
 * Interceptor que coloca o contexto de internacionalizacao para o sistema
 * ReplicacaoClientesBO
 * 
 * @author Stefanini IT Solutions
 */
public class CAPESReplicacaoComumInternacionalizacaoInterceptor extends
		InternacionalizacaoInterceptor {

	/**
	 * {@inheritDoc}
	 */
	@Override
	@AroundInvoke
	public Object intercept(InvocationContext contexto) throws Exception {
		return super.intercept(contexto);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BancoobResourceBundle getResourceBundle() {
		return CAPESReplicacaoComumResourceBundle.getInstance();
	}
}
