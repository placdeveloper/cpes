package br.com.sicoob.capes.cadastro.persistencia;

import javax.transaction.Status;
import javax.transaction.Synchronization;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.contexto.IGerenciadorTransacao;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.ITXIntegracaoDelegate;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * A Classe InclusaoPessoaCompartilhamentoSynchronization.
 */
public class InclusaoPessoaCompartilhamentoSynchronization implements Synchronization {

	/** O atributo cpfCnpj. */
	private String cpfCnpj;
	
	/** O atributo logger. */
	private final SicoobLoggerPadrao logger = SicoobLoggerPadrao.getInstance(InclusaoPessoaCompartilhamentoSynchronization.class);
	
	/** O atributo itxIntegracaoDelegate. */
	private transient ITXIntegracaoDelegate itxIntegracaoDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarITXIntegracaoDelegate();

	/**
	 * Instancia um novo InclusaoPessoaCompartilhamentoSynchronization.
	 *
	 * @param cpfCnpj o valor de cpf cnpj
	 */
	public InclusaoPessoaCompartilhamentoSynchronization(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	/* @see javax.transaction.Synchronization#beforeCompletion() */
	/**
	 * {@inheritDoc}
	 */
	public void beforeCompletion() {
	}

	/* @see javax.transaction.Synchronization#afterCompletion(int) */
	/**
	 * {@inheritDoc}
	 */
	public void afterCompletion(final int status) {
		IGerenciadorTransacao gerenciador = obterGerenciadorTransacao();

		try {
			if (Status.STATUS_COMMITTED == status) {
				itxIntegracaoDelegate.requisitarConsultas(cpfCnpj);
			}

		} catch (BancoobException e) {
			logger.erro(e, "Erro ao recuperar as consultas externaas para o cpfCnpj: " + cpfCnpj);
		} finally {
			gerenciador.remover();
		}
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