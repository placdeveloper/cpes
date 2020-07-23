package br.com.sicoob.capes.cadastro.negocio.servicos.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.commons.lang.StringUtils;

import br.com.sicoob.capes.cadastro.negocio.contexto.IGerenciadorTransacao;
import br.com.sicoob.capes.cadastro.negocio.servicos.MensagemReplicacaoServico;
import br.com.sicoob.capes.cadastro.persistencia.ReplicacaoSynchronization;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * Classe responsável por atribuir uma sincronização a transação corrente. Essa sincronização
 * registrará um eventual rollback da transação no sistema de auditoria.
 * 
 * @author rodrigo.chaves
 */
public class ReplicacaoInterceptor {

	/** A constante LOGGER. */
	private static final ISicoobLogger LOGGER = SicoobLoggerPadrao.getInstance(ReplicacaoInterceptor.class);

	/**
	 * @throws Exception 
	 * @see org.jboss.aop.advice.Interceptor#invoke(org.jboss.aop.joinpoint.Invocation)
	 */
	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception{
		IGerenciadorTransacao gerenciadorTransacao = obterGerenciadorTransacao();

		String uuid = StringUtils.EMPTY;
		if ((gerenciadorTransacao.existeTransacao()) && (gerenciadorTransacao.obter() == null) && !(context.getTarget() instanceof MensagemReplicacaoServico)) {
			uuid = gerenciadorTransacao.obterIDTransacao();
			gerenciadorTransacao.registrarSynchronization(new ReplicacaoSynchronization(uuid));
			LOGGER.debug("Listener de transacao registrado: ", uuid);
		}
		Object proceeded = context.proceed();

		return proceeded;
	}

	/**
	 * @see org.jboss.aop.advice.Interceptor#getName()
	 */
	public String getName() {
		return "Replicação Interceptor";
	}
	
	/**
	 * Obter gerenciador transacao.
	 *
	 * @return IGerenciadorTransacao
	 */
	private IGerenciadorTransacao obterGerenciadorTransacao() {
		return ReflexaoUtils.construirSingletonPorClasse("br.com.sicoob.capes.cadastro.negocio.contexto.GerenciadorTransacaoReplicacao", "obterInstancia");
	}

}