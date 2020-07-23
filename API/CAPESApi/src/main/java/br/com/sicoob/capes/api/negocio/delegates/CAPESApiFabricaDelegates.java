/*
 * SICOOB
 * 
 * CAPESApiFabricaDelegates.java(br.com.sicoob.capes.api.negocio.delegates.CAPESApiFabricaDelegates)
 */
package br.com.sicoob.capes.api.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobFabricaDelegates;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.ICAPESApiFabricaDelegates;

/**
 * Fabrica dos business delegates a serem usados para integração com o CAPES	
 * 
 * @author Lucas.Borges
 */
public final class CAPESApiFabricaDelegates extends BancoobFabricaDelegates implements ICAPESApiFabricaDelegates {

	/** Instancia do Fabrica de Delegates. */
	private static CAPESApiFabricaDelegates fabrica = new CAPESApiFabricaDelegates();

	/**
	 * @return a fabrica de delegates a ser utilizada.
	 */
	public static CAPESApiFabricaDelegates getInstance() {
		return fabrica;
	}

	/**
	 * Cria uma nova instância de CAPES api fabrica delegates.
	 */
	private CAPESApiFabricaDelegates() {
		super();
	}
	
	/**
	 * Cria um novo objeto PessoaDelegate.
	 * 
	 * @return the pessoa delegate
	 */
	public PessoaDelegate criarPessoaDelegate() {
		return new PessoaDelegate();
	}

	/**
	 * Cria um novo objeto ProdutividadePessoaDelegate.
	 * 
	 * @return the produtividade pessoa delegate
	 */
	public ProdutividadePessoaDelegate criarProdutividadePessoaDelegate() {
		return new ProdutividadePessoaDelegate();
	}
	
	/**
	 * Cria um novo objeto BemPessoaDelegate.
	 * 
	 * @return the bem pessoa delegate
	 */
	public BemPessoaDelegate criarBemPessoaDelegate() {
		return new BemPessoaDelegate();
	}

	/**
	 * Cria um novo objeto TelefonePessoaDelegate.
	 * 
	 * @return the telefone pessoa delegate
	 */
	public TelefonePessoaDelegate criarTelefonePessoaDelegate() {
		return new TelefonePessoaDelegate();
	}

	/**
	 * Cria um novo objeto EmailPessoaDelegate.
	 * 
	 * @return the email pessoa delegate
	 */
	public EmailPessoaDelegate criarEmailPessoaDelegate() {
		return new EmailPessoaDelegate();
	}
	
	/**
	 * Cria um novo objeto EnderecoPessoaDelegate.
	 * 
	 * @return the endereco pessoa delegate
	 */
	public EnderecoPessoaDelegate criarEnderecoPessoaDelegate() {
		return new EnderecoPessoaDelegate();
	}
	
	/**
	 * Cria um novo objeto FonteRendaPessoaDelegate.
	 * 
	 * @return the fonte renda pessoa delegate
	 */
	public FonteRendaPessoaDelegate criarFonteRendaPessoaDelegate() {
		return new FonteRendaPessoaDelegate();
	}
	
	/**
	 * Cria um novo objeto CertidaoPessoaDelegate.
	 * 
	 * @return the certidao pessoa delegate
	 */
	public CertidaoPessoaDelegate criarCertidaoPessoaDelegate() {
		return new CertidaoPessoaDelegate();
	}

	/**
	 * Cria um novo objeto AnotacaoPessoaDelegate.
	 * 
	 * @return the anotacao pessoa delegate
	 */
	public AnotacaoPessoaDelegate criarAnotacaoPessoaDelegate() {
		return new AnotacaoPessoaDelegate();
	}

	/**
	 * Cria um novo objeto RelacionamentoPessoaDelegate.
	 * 
	 * @return the relacionamento pessoa delegate
	 */
	public RelacionamentoPessoaDelegate criarRelacionamentoPessoaDelegate() {
		return new RelacionamentoPessoaDelegate();
	}
	
	/**
	 * Cria um novo objeto TributacaoPessoaDelegate.
	 * 
	 * @return the tributacao pessoa delegate
	 */
	public TributacaoPessoaDelegate criarTributacaoPessoaDelegate() {
		return new TributacaoPessoaDelegate();
	}
	
	/**
	 * Cria um novo objeto MensagemPessoaDelegate.
	 * 
	 * @return the mensagem pessoa delegate
	 */
	public MensagemPessoaDelegate criarMensagemPessoaDelegate() {
		return new MensagemPessoaDelegate();
	}
	
	/**
	 * Cria um novo objeto DadosClienteDelegate.
	 * 
	 * @return the dados cliente delegate
	 */
	public DadosClienteDelegate criarDadosClienteDelegate() {
		return new DadosClienteDelegate();
	}
	
	/**
	 * Cria um novo objeto FuncionarioDelegate.
	 * 
	 * @return the funcionario delegate
	 */
	public FuncionarioDelegate criarFuncionarioDelegate() {
		return new FuncionarioDelegate();
	}
	
	/**
	 * Cria um novo objeto ReferenciaPessoaDelegate.
	 * 
	 * @return the referencia pessoa delegate
	 */
	public ReferenciaPessoaDelegate criarReferenciaPessoaDelegate() {
		return new ReferenciaPessoaDelegate();
	}
	
	/**
	 * Cria um novo objeto PessoaJuridicaDelegate.
	 * 
	 * @return the pessoa juridica delegate
	 */
	public PessoaJuridicaDelegate criarPessoaJuridicaDelegate() {
		return new PessoaJuridicaDelegate();
	}
	
	/**
	 * Cria um novo objeto PessoaFisicaDelegate.
	 * 
	 * @return the pessoa fisica delegate
	 */
	public PessoaFisicaDelegate criarPessoaFisicaDelegate() {
		return new PessoaFisicaDelegate();
	}
	
	/**
	 * Cria um novo objeto ClienteDelegate.
	 * 
	 * @return the cliente delegate
	 */
	public ClienteDelegate criarClienteDelegate() {
		return new ClienteDelegate();
	}
	
	/**
	 * Cria um novo objeto DadosEtiquetaDelegate.
	 * 
	 * @return the dados etiqueta delegate
	 */
	public DadosEtiquetaDelegate criarDadosEtiquetaDelegate() {
		return new DadosEtiquetaDelegate();
	}
	
	/**
	 * Cria um novo objeto InformacaoProfissionalDelegate.
	 * 
	 * @return the informacao profissional delegate
	 */
	public InformacaoProfissionalDelegate criarInformacaoProfissionalDelegate() {
		return new InformacaoProfissionalDelegate();
	}
	
	/**
	 * Cria a instância de {@link DataUltimaAtualizacaoDelegate}
	 * @return
	 * 		Retorna a instância de {@link DataUltimaAtualizacaoDelegate}
	 */
	public DataUltimaAtualizacaoDelegate criarDataUltimaAtualizacaoDelegate() {
		return new DataUltimaAtualizacaoDelegate();
	}

	/**
	 * Cria a instância de {@link ProdutorDelegate}
	 * @return
	 * 		Retorna a instância de {@link ProdutorDelegate}
	 */
	public ProdutorDelegate criarProdutorDelegate() {
		return new ProdutorDelegate();
	}
	
	/**
	 * Cria a instância de {@link InstituicaoResponsavelDelegate}
	 * @return
	 * 		Retorna a instância de {@link InstituicaoResponsavelDelegate}
	 */
	public InstituicaoResponsavelDelegate criarInstituicaoResponsavelDelegate() {
		return new InstituicaoResponsavelDelegate();
	}
	
	/**
	 * Cria a instância de {@link GrupoEconomicoDelegate}
	 * @return
	 * 		Retorna a instância de {@link GrupoEconomicoDelegate}
	 */
	public GrupoEconomicoDelegate criarGrupoEconomicoDelegate() {
		return new GrupoEconomicoDelegate();
	}

	/**
	 * Cria a instância de {@link DominioDelegate}
	 * @return
	 * 		Retorna a instância de {@link DominioDelegate}
	 */
	public DominioDelegate criarDominioDelegate() {		
		return new DominioDelegate();
	}
	
	/**
	 * Cria um novo objeto AtualizarAnotacoesSisbrDelegate.
	 * 
	 * @return the atualizar anotacoes sisbr delegate
	 */
	public AtualizarAnotacoesSisbrDelegate criarAtualizarAnotacoesSisbrDelegate() {
		return new AtualizarAnotacoesSisbrDelegate();
	}
	
	/**
	 * Criar um novo objeto NucleoDelegate.
	 * @return 
	 */
	public NucleoDelegate criarNucleoDelegate(){
		return new NucleoDelegate();
	}
	
	/**
	 * Cria instancia de TipoAnotacaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoAnotacaoDelegate
	 */
	public TipoAnotacaoDelegate criarTipoAnotacaoDelegate(){
		return new TipoAnotacaoDelegate();
	}
	
	/**
	 * Cria instancia de InformacaoUtilizadaDelegate.
	 *
	 * @return o delegate solicitado
	 * @see InformacaoUtilizadaDelegate
	 */
	public InformacaoUtilizadaDelegate criarInformacaoUtilizadaDelegate() {
		return new InformacaoUtilizadaDelegate();
	}
	
	/**
	 * Cria instancia de CnaeFiscalDelegate.
	 *
	 * @return o delegate solicitado
	 * @see CnaeFiscalDelegate
	 */
	public CnaeFiscalDelegate criarCnaeFiscalDelegate() {
		return new CnaeFiscalDelegate();
	}
	
	/**
	 * Cria a instancia de GrupoCompartilhamentoDelegate
	 * 
	 * @return o delegate solicitado
	 */
	public GrupoCompartilhamentoDelegate criarGrupoCompartilhamentoDelegate() {
		return new GrupoCompartilhamentoDelegate();
	}
	
	/**
	 * Cria a instancia de OcupacaoProfissionalDelegate
	 * 
	 * @return o delegate solicitado
	 */
	public OcupacaoProfissionalDelegate criarOcupacaoProfissionalDelegate() {
		return new OcupacaoProfissionalDelegate();
	}
	
	/**
	 * Cria a instancia de TipoEmailDelegate
	 * 
	 * @return o delegate solicitado
	 */
	public TipoEmailDelegate criarTipoEmailDelegate() {
		return new TipoEmailDelegate();
	}
	
	/**
	 * Cria a instancia de TipoTelefoneDelegate
	 * 
	 * @return o delegate solicitado
	 */
	public TipoTelefoneDelegate criarTipoTelefoneDelegate() {
		return new TipoTelefoneDelegate();
	}
	
	/**
	 * Cria a instancia de TipoEnderecoDelegate
	 * 
	 * @return o delegate solicitado
	 */
	public TipoEnderecoDelegate criarTipoEnderecoDelegate() {
		return new TipoEnderecoDelegate();
	}
	
	/**
	 * Cria a instancia de TipoFonteRendaDelegate
	 * 
	 * @return o delegate solicitado
	 */
	public TipoFonteRendaDelegate criarTipoFonteRendaDelegate() {
		return new TipoFonteRendaDelegate();
	}
	
	/**
	 * Cria a instancia de TipoEmpresaDelegate
	 * 
	 * @return o delegate solicitado
	 */
	public TipoEmpresaDelegate criarTipoEmpresaDelegate() {
		return new TipoEmpresaDelegate();
	}
	
	/**
	 * Cria a instancia de BemDelegate
	 * 
	 * @return o delegate solicitado
	 */
	public BemDelegate criarBemDelegate() {
		return new BemDelegate();
	}

	/**
	 * Cria a instancia de BemImovelDelegate
	 * 
	 * @return o delegate solicitado
	 */
	public BemImovelDelegate criarBemImovelDelegate() {
		return new BemImovelDelegate();
	}

	/**
	 * Cria a instancia de BemMovelDelegate
	 * 
	 * @return o delegate solicitado
	 */
	public BemMovelDelegate criarBemMovelDelegate() {
		return new BemMovelDelegate();
	}
}