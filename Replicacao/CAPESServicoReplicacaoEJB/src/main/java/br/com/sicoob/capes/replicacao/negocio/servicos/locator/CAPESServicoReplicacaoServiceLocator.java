package br.com.sicoob.capes.replicacao.negocio.servicos.locator;

import br.com.bancoob.negocio.servicos.locator.BancoobServiceLocator;
import br.com.sicoob.capes.replicacao.negocio.servicos.ReplicacaoDominiosServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.ReplicarCadastroClientesServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.TransicaoReplicacaoServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.VerificarDLQReplicacaoCadastroServico;

/**
 * Service Locator usado pelo sistema CAPESCadastroComum.
 * 
 * @author Stefanini IT Solutions
 */
public final class CAPESServicoReplicacaoServiceLocator extends BancoobServiceLocator {

	/** O atributo locator. */
	private static CAPESServicoReplicacaoServiceLocator locator = new CAPESServicoReplicacaoServiceLocator();

	/**
	 * Singleton da classe
	 * 
	 * @return A instancia da classe
	 */
	public static CAPESServicoReplicacaoServiceLocator getInstance() {
		return locator;
	}

	/**
	 * @param nomeAplicacao
	 */
	private CAPESServicoReplicacaoServiceLocator() {
		super("capes.servico.replicacao");
	}
	
	/**
	 * Obtém o serviço TransicacaoReplicacao.
	 * @return uma instância de TransicacaoReplicacaoEJB
	 */
	public TransicaoReplicacaoServico localizarServicoTransacaoReplicacao() {
		return (TransicaoReplicacaoServico) localizar("locator.capes.TransicaoReplicacaoServico");
	}
	
	/**
	 * Obtém o serviço TransicacaoReplicacao.
	 * @return uma instância de TransicacaoReplicacaoEJB
	 */
	public ReplicarCadastroClientesServico localizarServicoReplicarCadastroClientes() {
		return (ReplicarCadastroClientesServico) localizar("locator.capes.ReplicarCadastroClientesServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code VerificarDLQReplicacaoCadastroServico}.
	 *
	 * @return O EJB solicitado
	 * @see VerificarDLQReplicacaoCadastroServico
	 */
	public VerificarDLQReplicacaoCadastroServico localizarVerificarDLQReplicacaoCadastroServico(){
		return (VerificarDLQReplicacaoCadastroServico) localizar("locator.capes.VerificarDLQReplicacaoCadastroServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code ReplicacaoDominiosServico}.
	 *
	 * @return O EJB solicitado
	 * @see ReplicacaoDominiosServico
	 */
	public ReplicacaoDominiosServico localizarReplicacaoDominiosServico(){
		return (ReplicacaoDominiosServico) localizar("locator.capes.ReplicacaoDominiosServico");
	}
}