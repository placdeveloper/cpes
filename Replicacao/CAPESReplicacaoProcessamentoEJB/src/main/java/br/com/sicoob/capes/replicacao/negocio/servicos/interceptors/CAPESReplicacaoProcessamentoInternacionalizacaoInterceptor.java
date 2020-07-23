package br.com.sicoob.capes.replicacao.negocio.servicos.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor;
import br.com.sicoob.capes.replicacao.negocio.infraestrutura.mensagens.CAPESReplicacaoProcessamentoResourceBundle;

/**
 * Interceptor que coloca o contexto de internacionalizacao para o sistema ReplicacaoClientesProcessamento
 * 
 * @author Stefanini IT Solutions
 */
public class CAPESReplicacaoProcessamentoInternacionalizacaoInterceptor extends InternacionalizacaoInterceptor {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BancoobResourceBundle getResourceBundle() {
		return CAPESReplicacaoProcessamentoResourceBundle.getInstance();
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
