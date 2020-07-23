package br.com.sicoob.capes.relatorio.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.GrupoCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoAnotacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoEmailDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoEnderecoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoFonteRendaDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoTelefoneDelegate;

/**
 * Fabrica dos business delegates a serem usados pelo Sistema CAPESRelatorio.
 * 
 * @author Stefanini IT Solutions
 */
public final class CAPESRelatorioFabricaDelegates extends BancoobFabricaDelegates {

	/** Instancia do Fabrica de Delegates. */
	private static CAPESRelatorioFabricaDelegates fabrica;

	/**
	 * Retorna a fabrica de delegates a ser utilizada.
	 * 
	 * @return a fabrica de delegates a ser utilizada.
	 */
	public static CAPESRelatorioFabricaDelegates getInstance() {
		if (fabrica == null) {
			synchronized (CAPESRelatorioFabricaDelegates.class) {
				if (fabrica == null) {
					fabrica = new CAPESRelatorioFabricaDelegates();
				}
			}
		}
		return fabrica;
	}

	/**
	 * Construtor privado no-args da classe
	 */
	private CAPESRelatorioFabricaDelegates() {
		super();
	}
	
	/**
	 * Cria instancia de RelatorioAnotacaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see RelatorioAnotacaoDelegate
	 */
	public RelatorioAnotacaoDelegate criarRelatorioAnotacaoDelegate() {
		return new RelatorioAnotacaoDelegate();
	}

	/**
	 * Cria instancia de RelatorioProvaVidaDelegate.
	 *
	 * @return o delegate solicitado
	 * @see RelatorioProvaVidaDelegate
	 */
	public RelatorioProvaVidaDelegate criarRelatorioProvaVidaDelegate() {
		return new RelatorioProvaVidaDelegate();
	}
	
	/**
	 * Cria instancia de RelatorioFichaCadastralDelegate.
	 *
	 * @return o delegate solicitado
	 * @see RelatorioFichaCadastralDelegate
	 */
	public RelatorioFichaCadastralDelegate criarRelatorioFichaCadastralDelegate() {
		return new RelatorioFichaCadastralDelegate();
	}
	
	/**
	 * Cria instancia de RelatorioFichaCadastralEmBrancoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see RelatorioFichaCadastralEmBrancoDelegate
	 */
	public RelatorioFichaCadastralEmBrancoDelegate criarRelatorioFichaCadastralEmBrancoDelegate() {
		return new RelatorioFichaCadastralEmBrancoDelegate();
	}
	
	/**
	 * Cria instancia de RelCompartilhamentoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see RelCompartilhamentoDelegate
	 */
	public RelatorioCompartilhamentoDelegate criarRelCompartilhamentoDelegate() {
		return new RelatorioCompartilhamentoDelegate();
	}
	
	/**
	 * Cria instancia de RelatorioCadastroCompartilhadoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see RelatorioCadastroCompartilhadoDelegate
	 */
	public RelatorioCadastroCompartilhadoDelegate criarRelatorioCadastroCompartilhado() {
		return new RelatorioCadastroCompartilhadoDelegate();
	}
	
	/**
	 * Cria instancia de RelVencimentoCadastroDelegate.
	 *
	 * @return o delegate solicitado
	 * @see RelVencimentoCadastroDelegate
	 */
	public RelatorioVencimentoCadastroDelegate criarRelVencimentoCadastroDelegate() {
		return new RelatorioVencimentoCadastroDelegate();
	}
	
	/**
	 * Cria instancia de TipoAnotacaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoAnotacaoDelegate
	 */
	public RelatorioTipoAnotacaoDelegate criarTipoAnotacaoDelegate() {
		return new RelatorioTipoAnotacaoDelegate();
	}

	/**
	 * Cria instancia de TipoEmailDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoEmailDelegate
	 */
	public RelatorioTipoEmailDelegate criarTipoEmailDelegate() {
		return new RelatorioTipoEmailDelegate();
	}

	/**
	 * Cria instancia de TipoEnderecoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoEnderecoDelegate
	 */
	public RelatorioTipoEnderecoDelegate criarTipoEnderecoDelegate() {
		return new RelatorioTipoEnderecoDelegate();
	}

	/**
	 * Cria instancia de TipoTelefoneDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoTelefoneDelegate
	 */
	public RelatorioTipoTelefoneDelegate criarTipoTelefoneDelegate() {
		return new RelatorioTipoTelefoneDelegate();
	}

	/**
	 * Cria instancia de TipoFonteRendaDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoFonteRendaDelegate
	 */
	public RelatorioTipoFonteRendaDelegate criarTipoFonteRendaDelegate() {
		return new RelatorioTipoFonteRendaDelegate();
	}

	/**
	 * Cria instancia de FichaCadastralDelegate.
	 *
	 * @return o delegate solicitado
	 * @see FichaCadastralDelegate
	 */
	public FichaCadastralDelegate criarFichaCadastralDelegate() {
		return new FichaCadastralDelegate();
	}
	
	/**
	 * Cria instancia de GrupoCompartilhamentoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see GrupoCompartilhamentoDelegate
	 */
	public RelatorioGrupoCompartilhamentoDelegate criarGrupoCompartilhamentoDelegate(){
		return new RelatorioGrupoCompartilhamentoDelegate();
	}
	
	/**
	 * Cria instancia de RelatorioGrupoEconomicoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see RelatorioGrupoEconomicoDelegate
	 */
	public RelatorioGrupoEconomicoDelegate criarRelatorioGrupoEconomicoDelegate(){
		return new RelatorioGrupoEconomicoDelegate();
	}
	
	/**
	 * Cria instancia de TipoRelacionamentoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoRelacionamentoDelegate
	 */
	public RelatorioTipoRelacionamentoPessoaDelegate criarTipoRelacionamentoDelegate() {
		return new RelatorioTipoRelacionamentoPessoaDelegate();
	}
	
	/**
	 * Cria instancia de PessoaCompartilhamentoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see PessoaCompartilhamentoDelegate
	 */
	public RelatorioPessoaCompartilhamentoDelegate criarPessoaCompartilhamentoDelegate(){
		return new RelatorioPessoaCompartilhamentoDelegate();
	}

	/**
	 * Cria instancia de RelatorioValidacaoCadastralDelegate.
	 *
	 * @return o delegate solicitado
	 * @see RelatorioValidacaoCadastralDelegate
	 */
	public RelatorioValidacaoCadastralDelegate criarRelatorioValidacaoCadastralDelegate() {
		return new RelatorioValidacaoCadastralDelegate();
	}
	
	/**
	 * Cria instancia de RelatorioTributacaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see RelatorioTributacaoDelegate
	 */
	public RelatorioTributacaoDelegate criarRelatorioTributacaoDelegate() {
		return new RelatorioTributacaoDelegate();
	}

	/**
	 * Cria instancia de RelatorioDeclaracaoPropositoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see RelatorioDeclaracaoPropositoDelegate
	 */
	public RelatorioDeclaracaoPropositoDelegate criarRelatorioDeclaracaoPropositoDelegate() {
		return new RelatorioDeclaracaoPropositoDelegate();
	}
	
	public FormularioVisitaDelegate criarFormularioVisitaDelegate(){
		return new FormularioVisitaDelegate();
	}
}