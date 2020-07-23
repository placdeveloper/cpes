/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.contexto.IGerenciadorTransacao;
import br.com.sicoob.capes.cadastro.persistencia.InclusaoPessoaCompartilhamentoSynchronization;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * @author Erico.Junior
 * 
 */
public class InclusaoPessoaCompartilhamentoInterceptor {

	/** O atributo logger. */
	private transient ISicoobLogger logger = SicoobLoggerPadrao.getInstance(this.getClass());

	/**
	 * Intercepta a chamada ao Servi�o EJB.
	 * 
	 * @param contexto
	 *            o contexto de execu��o do EJB.
	 * @return o retorno do Servi�o EJB.
	 * @throws BancoobException
	 *             caso alguma exce��o ocorra.
	 */
	@AroundInvoke
	public Object interceptIncluirPessoaCompartilhamento(InvocationContext contexto) throws Exception {

		logger.debug("InclusaoPessoaCompartilhamentoInterceptor.intercept: inicio");

		String cpfCnpj = obterCpfCnpj(contexto);

		IGerenciadorTransacao gerenciadorTransacao = obterGerenciadorTransacao();
		if (gerenciadorTransacao.existeTransacao() && gerenciadorTransacao.obter() == null) {
			gerenciadorTransacao.registrarSynchronization(new InclusaoPessoaCompartilhamentoSynchronization(cpfCnpj));
		}
		logger.debug("InclusaoPessoaCompartilhamentoInterceptor.intercept: fim");

		return contexto.proceed();
	}

	/**
	 * Obter cpf cnpj.
	 *
	 * @param contexto o valor de contexto
	 * @return String
	 */
	private String obterCpfCnpj(InvocationContext contexto) {
		Object[] parametros = contexto.getParameters();
		
		PessoaCompartilhamento pessoaCompartilhamento = (PessoaCompartilhamento) parametros[0];
		return pessoaCompartilhamento.getPessoa().getCpfCnpj();
	}
	
	/**
	 * Obter gerenciador transacao.
	 *
	 * @return IGerenciadorTransacao
	 */
	private IGerenciadorTransacao obterGerenciadorTransacao() {
		return ReflexaoUtils.construirSingletonPorClasse("br.com.sicoob.capes.cadastro.negocio.contexto.GerenciadorTransacaoInclusaoPessoaCompartilhamento", "obterInstancia");
	}

}