package br.com.sicoob.capes.replicacao.negocio.servicos.locator;

import br.com.bancoob.negocio.servicos.locator.BancoobServiceLocator;
import br.com.sicoob.capes.replicacao.negocio.servicos.AtualizarCadastroClienteServico;

/**
 * Service Locator usado pelo sistema ReplicacaoClientesProcessamento.
 * 
 * @author Stefanini IT Solutions
 */
public class CAPESReplicacaoProcessamentoServiceLocator extends BancoobServiceLocator {

	/** O atributo locator. */
	private static CAPESReplicacaoProcessamentoServiceLocator locator;

	/**
	 * Singleton da class
	 * 
	 * @return A instancia da classe
	 */
	public static CAPESReplicacaoProcessamentoServiceLocator getInstance() {
		if (locator == null) {
			synchronized (CAPESReplicacaoProcessamentoServiceLocator.class) {
				if(locator == null){
					locator = new CAPESReplicacaoProcessamentoServiceLocator();
				}
			}
		}

		return locator;
	}

	/**
	 * @param nomeAplicacao
	 */
	private CAPESReplicacaoProcessamentoServiceLocator() {
		super("capes.replicacao.processamento");
	}
	
	
	/**
	 * Localiza o EJB que implementa a interface {@code AtualizarCadastroClienteServico}.
	 *
	 * @return O EJB solicitado
	 * @see AtualizarCadastroClienteServico
	 */
	@SuppressWarnings("rawtypes")
	public AtualizarCadastroClienteServico localizarAtualizarCadastroClienteServico(){
		return (AtualizarCadastroClienteServico) localizar("locator.capes.AtualizarCadastroClienteServico");
	}	

}
