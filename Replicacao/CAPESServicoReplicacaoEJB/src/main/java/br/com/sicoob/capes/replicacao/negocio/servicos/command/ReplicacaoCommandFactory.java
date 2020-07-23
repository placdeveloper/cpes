package br.com.sicoob.capes.replicacao.negocio.servicos.command;

import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.ResponsavelCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TransicaoPessoaDelegate;
import br.com.sicoob.capes.replicacao.negocio.delegates.CAPESServicoReplicacaoFabricaDelegates;
import br.com.sicoob.capes.replicacao.negocio.delegates.TransicaoReplicacaoDelegate;


/**
 * Fábrica de ReplicacaoCommand.
 * @author Juan.Damasceno
 *
 */
public class ReplicacaoCommandFactory {

	/** O atributo factory. */
	private static ReplicacaoCommandFactory factory;

	/** A constante EXCLUSAO. */
	private static final char EXCLUSAO = 'E';
	
	/** A constante INCLUSAO. */
	private static final char INCLUSAO = 'I';
	
	/** A constante ALTERACAO. */
	private static final char ALTERACAO = 'A';
	
	/** O atributo pessoaDelegate. */
	private PessoaDelegate pessoaDelegate;
	
	/** O atributo pessoaCompartilhamentoDelegate. */
	private PessoaCompartilhamentoDelegate pessoaCompartilhamentoDelegate;
	
	/** O atributo transicaoPessoaDelegate. */
	private TransicaoPessoaDelegate transicaoPessoaDelegate; 
	
	/** O atributo transicaoReplicacaoDelegate. */
	private TransicaoReplicacaoDelegate transicaoReplicacaoDelegate;
	
	/** O atributo responsavelCadastroDelegate. */
	private ResponsavelCadastroDelegate responsavelCadastroDelegate;
	
	/**
	 * Cria a fábrica de ReplicacaoCommandFactory
	 * @return a fábrica de ReplicacaoCommandFactory
	 */
	public static ReplicacaoCommandFactory getInstance() {
		if (factory == null) {
			synchronized (ReplicacaoCommandFactory.class) {
				if (factory == null) {
					factory = new ReplicacaoCommandFactory();
				}
			}
		}
		return factory;
	}
	
	/**
	 * Cria uma instância de ReplicacaoCommandFactory criada apartir do tipo de operação.
	 * @param tipoOperacao o tipo da operação que será realizada.
	 * @return uma instância de ReplicacaoCommand criada apartir do tipo de operação.
	 */
	public ReplicacaoCommand createCommand(Character tipoOperacao) {

		AbstractReplicacaoCommand replicacaoCommand = null;

		if (tipoOperacao.equals(ALTERACAO) || tipoOperacao.equals(INCLUSAO)) {
			replicacaoCommand = new ReplicarAlteracaoCommand();
		} else if (tipoOperacao.equals(EXCLUSAO)) {
			replicacaoCommand = new ReplicarExclusaoCommand();
		}
		
		if(replicacaoCommand != null) {
			injetarDelegates(replicacaoCommand);
		}
		
		return replicacaoCommand;
	}
	
	/**
	 * Retorna uma instância de PessoaDelegate
	 * @return uma instância de PessoaDelegate
	 */
	private PessoaDelegate getPessoaDelegate() {
		return CAPESCadastroFabricaDelegates.getInstance().criarPessoaDelegate();
	}

	/**
	 * Retorna uma instancia de TransicaoReplicacaoDelegate
	 * @return uma instancia de TransicaoReplicacaoDelegate
	 */
	private TransicaoReplicacaoDelegate getTransicaoReplicacaoDelegate() {
		return CAPESServicoReplicacaoFabricaDelegates.getInstance().criarTransicaoReplicacaoDelegate();
	}
	
	/**
	 * Retorna uma instancia de TransicaoPessoaDelegate
	 * @return uma instancia de TransicaoPessoaDelegate
	 */
	private TransicaoPessoaDelegate getTransicaoPessoaDelegate() {
		return CAPESCadastroFabricaDelegates.getInstance().criarTransicaoPessoaDelegate();
	}
	
	/**
	 * Retorna uma instancia de ResponsavelCadastroDelegate
	 * @return uma instancia de ResponsavelCadastroDelegate
	 */
	private ResponsavelCadastroDelegate getResponsavelCadastroDelegate() {
		return CAPESCadastroFabricaDelegates.getInstance().criarResponsavelCadastroDelegate();
	}
	
	/**
	 * Retorna uma instancia de PessoaCompartilhamentoDelegate
	 * @return uma instancia de PessoaCompartilhamentoDelegate
	 */
	private PessoaCompartilhamentoDelegate getPessoaCompartilhamentoDelegate() {
		return CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate();
	}
	
	/**
	 * Cria os delegates.
	 */
	public void criarDelegates() {
		this.transicaoPessoaDelegate = getTransicaoPessoaDelegate();
		this.pessoaDelegate = getPessoaDelegate(); 
		this.transicaoReplicacaoDelegate = getTransicaoReplicacaoDelegate();
		this.responsavelCadastroDelegate = getResponsavelCadastroDelegate();
		this.pessoaCompartilhamentoDelegate = getPessoaCompartilhamentoDelegate();
	}
	
	/**
	 * Injeta 
	 * @param replicacaoCommand
	 */
	protected void injetarDelegates(AbstractReplicacaoCommand replicacaoCommand) {
		replicacaoCommand.setPessoaDelegate(pessoaDelegate);
		replicacaoCommand.setTransicaoPessoaDelegate(transicaoPessoaDelegate);
		replicacaoCommand.setTransicaoReplicacaoDelegate(transicaoReplicacaoDelegate);
		replicacaoCommand.setResponsavelCadastroDelegate(responsavelCadastroDelegate);
		replicacaoCommand.setPessoaCompartilhamentoDelegate(pessoaCompartilhamentoDelegate);
	}
}
