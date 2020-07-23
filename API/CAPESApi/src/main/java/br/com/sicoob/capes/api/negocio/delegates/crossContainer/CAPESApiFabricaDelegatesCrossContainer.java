/*
 * SICOOB
 * 
 * CAPESApiFabricaDelegates.java(br.com.sicoob.capes.api.negocio.delegates.CAPESApiFabricaDelegates)
 */
package br.com.sicoob.capes.api.negocio.delegates.crossContainer;

import br.com.bancoob.negocio.delegates.BancoobFabricaDelegates;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.ICAPESApiFabricaDelegates;

/**
 * Fabrica dos business delegates a serem usados para integração com o CAPES	
 * 
 * @author Lucas.Borges
 */
public final class CAPESApiFabricaDelegatesCrossContainer extends BancoobFabricaDelegates implements ICAPESApiFabricaDelegates {

	/** Instancia do Fabrica de Delegates. */
	private static CAPESApiFabricaDelegatesCrossContainer fabrica = new CAPESApiFabricaDelegatesCrossContainer();

	/**
	 * @return a fabrica de delegates a ser utilizada.
	 */
	public static CAPESApiFabricaDelegatesCrossContainer getInstance() {
		return fabrica;
	}

	/**
	 * Cria uma nova instância de CAPES api fabrica delegates.
	 */
	private CAPESApiFabricaDelegatesCrossContainer() {
		super();
	}
	
	/**
	 * Cria um novo objeto PessoaDelegate.
	 * 
	 * @return the pessoa delegate
	 */
	public PessoaDelegate criarPessoaDelegate() {
		return PessoaDelegate.getInstance();
	}

	/**
	 * Cria um novo objeto ProdutividadePessoaDelegate.
	 * 
	 * @return the produtividade pessoa delegate
	 */
	public ProdutividadePessoaDelegate criarProdutividadePessoaDelegate() {
		return ProdutividadePessoaDelegate.getInstance();
	}
	
	/**
	 * Cria um novo objeto TelefonePessoaDelegate.
	 * 
	 * @return the telefone pessoa delegate
	 */
	public TelefonePessoaDelegate criarTelefonePessoaDelegate() {
		return TelefonePessoaDelegate.getInstance();
	}

	/**
	 * Cria um novo objeto EmailPessoaDelegate.
	 * 
	 * @return the email pessoa delegate
	 */
	public EmailPessoaDelegate criarEmailPessoaDelegate() {
		return EmailPessoaDelegate.getInstance();
	}
	
	/**
	 * Cria um novo objeto EnderecoPessoaDelegate.
	 * 
	 * @return the endereco pessoa delegate
	 */
	public EnderecoPessoaDelegate criarEnderecoPessoaDelegate() {
		return EnderecoPessoaDelegate.getInstance();
	}
	
	/**
	 * Cria um novo objeto FonteRendaPessoaDelegate.
	 * 
	 * @return the fonte renda pessoa delegate
	 */
	public FonteRendaPessoaDelegate criarFonteRendaPessoaDelegate() {
		return FonteRendaPessoaDelegate.getInstance();
	}
	
	/**
	 * Cria um novo objeto CertidaoPessoaDelegate.
	 * 
	 * @return the certidao pessoa delegate
	 */
	public CertidaoPessoaDelegate criarCertidaoPessoaDelegate() {
		return CertidaoPessoaDelegate.getInstance();
	}

	/**
	 * Cria um novo objeto AnotacaoPessoaDelegate.
	 * 
	 * @return the anotacao pessoa delegate
	 */
	public AnotacaoPessoaDelegate criarAnotacaoPessoaDelegate() {
		return AnotacaoPessoaDelegate.getInstance();
	}

	/**
	 * Cria um novo objeto RelacionamentoPessoaDelegate.
	 * 
	 * @return the relacionamento pessoa delegate
	 */
	public RelacionamentoPessoaDelegate criarRelacionamentoPessoaDelegate() {
		return RelacionamentoPessoaDelegate.getInstance();
	}
	
	/**
	 * Cria um novo objeto TributacaoPessoaDelegate.
	 * 
	 * @return the tributacao pessoa delegate
	 */
	public TributacaoPessoaDelegate criarTributacaoPessoaDelegate() {
		return TributacaoPessoaDelegate.getInstance();
	}
	
	/**
	 * Cria um novo objeto MensagemPessoaDelegate.
	 * 
	 * @return the mensagem pessoa delegate
	 */
	public MensagemPessoaDelegate criarMensagemPessoaDelegate() {
		return MensagemPessoaDelegate.getInstance();
	}
	
	/**
	 * Cria um novo objeto DadosClienteDelegate.
	 * 
	 * @return the dados cliente delegate
	 */
	public DadosClienteDelegate criarDadosClienteDelegate() {
		return DadosClienteDelegate.getInstance();
	}
	
	/**
	 * Cria um novo objeto FuncionarioDelegate.
	 * 
	 * @return the funcionario delegate
	 */
	public FuncionarioDelegate criarFuncionarioDelegate() {
		return FuncionarioDelegate.getInstance();
	}
	
	/**
	 * Cria um novo objeto ReferenciaPessoaDelegate.
	 * 
	 * @return the referencia pessoa delegate
	 */
	public ReferenciaPessoaDelegate criarReferenciaPessoaDelegate() {
		return ReferenciaPessoaDelegate.getInstance();
	}
	
	/**
	 * Cria um novo objeto PessoaJuridicaDelegate.
	 * 
	 * @return the pessoa juridica delegate
	 */
	public PessoaJuridicaDelegate criarPessoaJuridicaDelegate() {
		return PessoaJuridicaDelegate.getInstance();
	}
	
	/**
	 * Cria um novo objeto PessoaFisicaDelegate.
	 * 
	 * @return the pessoa fisica delegate
	 */
	public PessoaFisicaDelegate criarPessoaFisicaDelegate() {
		return PessoaFisicaDelegate.getInstance();
	}
	
	/**
	 * Cria um novo objeto ClienteDelegate.
	 * 
	 * @return the cliente delegate
	 */
	public ClienteDelegate criarClienteDelegate() {
		return ClienteDelegate.getInstance();
	}
	
	/**
	 * Cria um novo objeto DadosEtiquetaDelegate.
	 * 
	 * @return the dados etiqueta delegate
	 */
	public DadosEtiquetaDelegate criarDadosEtiquetaDelegate() {
		return DadosEtiquetaDelegate.getInstance();
	}
	
	/**
	 * Cria um novo objeto InformacaoProfissionalDelegate.
	 * 
	 * @return the informacao profissional delegate
	 */
	public InformacaoProfissionalDelegate criarInformacaoProfissionalDelegate() {
		return InformacaoProfissionalDelegate.getInstance();
	}
	
	/**
	 * Cria a instância de {@link DataUltimaAtualizacaoDelegate}
	 * @return
	 * 		Retorna a instância de {@link DataUltimaAtualizacaoDelegate}
	 */
	public DataUltimaAtualizacaoDelegate criarDataUltimaAtualizacaoDelegate() {
		return DataUltimaAtualizacaoDelegate.getInstance();
	}

	/**
	 * Cria a instância de {@link ProdutorDelegate}
	 * @return
	 * 		Retorna a instância de {@link ProdutorDelegate}
	 */
	public ProdutorDelegate criarProdutorDelegate() {
		return ProdutorDelegate.getInstance();
	}
	
	/**
	 * Cria a instância de {@link InstituicaoResponsavelDelegate}
	 * @return
	 * 		Retorna a instância de {@link InstituicaoResponsavelDelegate}
	 */
	public InstituicaoResponsavelDelegate criarInstituicaoResponsavelDelegate() {
		return InstituicaoResponsavelDelegate.getInstance();
	}
	
	/**
	 * Cria a instância de {@link GrupoEconomicoDelegate}
	 * @return
	 * 		Retorna a instância de {@link GrupoEconomicoDelegate}
	 */
	public GrupoEconomicoDelegate criarGrupoEconomicoDelegate() {
		return GrupoEconomicoDelegate.getInstance();
	}

	/**
	 * Cria a instância de {@link DominioDelegate}
	 * @return
	 * 		Retorna a instância de {@link DominioDelegate}
	 */
	public DominioDelegate criarDominioDelegate() {		
		return DominioDelegate.getInstance();
	}
	
	/**
	 * Cria um novo objeto AtualizarAnotacoesSisbrDelegate.
	 * 
	 * @return the atualizar anotacoes sisbr delegate
	 */
	public AtualizarAnotacoesSisbrDelegate criarAtualizarAnotacoesSisbrDelegate() {
		return AtualizarAnotacoesSisbrDelegate.getInstance();
	}
	
	/**
	 * Criar um novo objeto NucleoDelegate.
	 * @return 
	 */
	public NucleoDelegate criarNucleoDelegate(){
		return NucleoDelegate.getInstance();
	}
	
	/**
	 * Cria instancia de TipoAnotacaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoAnotacaoDelegate
	 */
	public TipoAnotacaoDelegate criarTipoAnotacaoDelegate(){
		return TipoAnotacaoDelegate.getInstance();
	}
	
	/**
	 * Cria instancia de InformacaoUtilizadaDelegate.
	 *
	 * @return o delegate solicitado
	 * @see InformacaoUtilizadaDelegate
	 */
	public InformacaoUtilizadaDelegate criarInformacaoUtilizadaDelegate() {
		return InformacaoUtilizadaDelegate.getInstance();
	}
	
	/**
	 * Cria instancia de CnaeFiscalDelegate.
	 *
	 * @return o delegate solicitado
	 * @see CnaeFiscalDelegate
	 */
	public CnaeFiscalDelegate criarCnaeFiscalDelegate() {
		return CnaeFiscalDelegate.getInstance();
	}
	
	/**
	 * Cria a instancia de GrupoCompartilhamentoDelegate
	 * 
	 * @return o delegate solicitado
	 */
	public GrupoCompartilhamentoDelegate criarGrupoCompartilhamentoDelegate() {
		return GrupoCompartilhamentoDelegate.getInstance();
	}
	
	/**
	 * Cria a instancia de OcupacaoProfissionalDelegate
	 * 
	 * @return o delegate solicitado
	 */
	public OcupacaoProfissionalDelegate criarOcupacaoProfissionalDelegate() {
		return OcupacaoProfissionalDelegate.getInstance();
	}
	
	/**
	 * Cria a instancia de TipoEmailDelegate
	 * 
	 * @return o delegate solicitado
	 */
	public TipoEmailDelegate criarTipoEmailDelegate() {
		return TipoEmailDelegate.getInstance();
	}
	
	/**
	 * Cria a instancia de TipoTelefoneDelegate
	 * 
	 * @return o delegate solicitado
	 */
	public TipoTelefoneDelegate criarTipoTelefoneDelegate() {
		return TipoTelefoneDelegate.getInstance();
	}
	
	/**
	 * Cria a instancia de TipoEnderecoDelegate
	 * 
	 * @return o delegate solicitado
	 */
	public TipoEnderecoDelegate criarTipoEnderecoDelegate() {
		return TipoEnderecoDelegate.getInstance();
	}
	
	/**
	 * Cria a instancia de TipoFonteRendaDelegate
	 * 
	 * @return o delegate solicitado
	 */
	public TipoFonteRendaDelegate criarTipoFonteRendaDelegate() {
		return TipoFonteRendaDelegate.getInstance();
	}
	
	/**
	 * Cria a instancia de TipoEmpresaDelegate
	 * 
	 * @return o delegate solicitado
	 */
	public TipoEmpresaDelegate criarTipoEmpresaDelegate() {
		return TipoEmpresaDelegate.getInstance();
	}
	
	/**
	 * Cria a instancia de BemDelegate
	 * 
	 * @return o delegate solicitado
	 */
	public BemDelegate criarBemDelegate() {
		return BemDelegate.getInstance();
	}

	/**
	 * Cria a instancia de BemImovelDelegate
	 * 
	 * @return o delegate solicitado
	 */
	public BemImovelDelegate criarBemImovelDelegate() {
		return BemImovelDelegate.getInstance();
	}

	/**
	 * Cria a instancia de BemMovelDelegate
	 * 
	 * @return o delegate solicitado
	 */
	public BemMovelDelegate criarBemMovelDelegate() {
		return BemMovelDelegate.getInstance();
	}

}