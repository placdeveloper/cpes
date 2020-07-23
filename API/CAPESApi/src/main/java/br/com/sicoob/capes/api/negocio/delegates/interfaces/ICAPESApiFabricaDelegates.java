package br.com.sicoob.capes.api.negocio.delegates.interfaces;

/**
 * 
 * @author paulo.stoppa
 *
 */
public interface ICAPESApiFabricaDelegates {

	/**
	 * Cria um novo objeto PessoaDelegate.
	 * 
	 * @return the pessoa delegate
	 */
	IPessoaDelegate criarPessoaDelegate();

	/**
	 * Cria um novo objeto ProdutividadePessoaDelegate.
	 * 
	 * @return the produtividade pessoa delegate
	 */
	IProdutividadePessoaDelegate criarProdutividadePessoaDelegate();
	
	/**
	 * Cria um novo objeto TelefonePessoaDelegate.
	 * 
	 * @return the telefone pessoa delegate
	 */
	ITelefonePessoaDelegate criarTelefonePessoaDelegate();

	/**
	 * Cria um novo objeto EmailPessoaDelegate.
	 * 
	 * @return the email pessoa delegate
	 */
	IEmailPessoaDelegate criarEmailPessoaDelegate();
	
	/**
	 * Cria um novo objeto EnderecoPessoaDelegate.
	 * 
	 * @return the endereco pessoa delegate
	 */
	IEnderecoPessoaDelegate criarEnderecoPessoaDelegate();
	
	/**
	 * Cria um novo objeto FonteRendaPessoaDelegate.
	 * 
	 * @return the fonte renda pessoa delegate
	 */
	IFonteRendaPessoaDelegate criarFonteRendaPessoaDelegate();
	
	/**
	 * Cria um novo objeto CertidaoPessoaDelegate.
	 * 
	 * @return the certidao pessoa delegate
	 */
	ICertidaoPessoaDelegate criarCertidaoPessoaDelegate();

	/**
	 * Cria um novo objeto AnotacaoPessoaDelegate.
	 * 
	 * @return the anotacao pessoa delegate
	 */
	IAnotacaoPessoaDelegate criarAnotacaoPessoaDelegate();

	/**
	 * Cria um novo objeto RelacionamentoPessoaDelegate.
	 * 
	 * @return the relacionamento pessoa delegate
	 */
	IRelacionamentoPessoaDelegate criarRelacionamentoPessoaDelegate();
	
	/**
	 * Cria um novo objeto TributacaoPessoaDelegate.
	 * 
	 * @return the tributacao pessoa delegate
	 */
	ITributacaoPessoaDelegate criarTributacaoPessoaDelegate();
	
	/**
	 * Cria um novo objeto MensagemPessoaDelegate.
	 * 
	 * @return the mensagem pessoa delegate
	 */
	IMensagemPessoaDelegate criarMensagemPessoaDelegate();
	
	/**
	 * Cria um novo objeto DadosClienteDelegate.
	 * 
	 * @return the dados cliente delegate
	 */
	IDadosClienteDelegate criarDadosClienteDelegate();
	
	/**
	 * Cria um novo objeto FuncionarioDelegate.
	 * 
	 * @return the funcionario delegate
	 */
	IFuncionarioDelegate criarFuncionarioDelegate();
	
	/**
	 * Cria um novo objeto ReferenciaPessoaDelegate.
	 * 
	 * @return the referencia pessoa delegate
	 */
	IReferenciaPessoaDelegate criarReferenciaPessoaDelegate();
	
	/**
	 * Cria um novo objeto PessoaJuridicaDelegate.
	 * 
	 * @return the pessoa juridica delegate
	 */
	IPessoaJuridicaDelegate criarPessoaJuridicaDelegate();
	
	/**
	 * Cria um novo objeto PessoaFisicaDelegate.
	 * 
	 * @return the pessoa fisica delegate
	 */
	IPessoaFisicaDelegate criarPessoaFisicaDelegate();
	
	/**
	 * Cria um novo objeto ClienteDelegate.
	 * 
	 * @return the cliente delegate
	 */
	IClienteDelegate criarClienteDelegate();
	
	/**
	 * Cria um novo objeto DadosEtiquetaDelegate.
	 * 
	 * @return the dados etiqueta delegate
	 */
	IDadosEtiquetaDelegate criarDadosEtiquetaDelegate();
	
	/**
	 * Cria um novo objeto InformacaoProfissionalDelegate.
	 * 
	 * @return the informacao profissional delegate
	 */
	IInformacaoProfissionalDelegate criarInformacaoProfissionalDelegate();
	
	/**
	 * Cria a instância de {@link IDataUltimaAtualizacaoDelegate}
	 * @return
	 * 		Retorna a instância de {@link IDataUltimaAtualizacaoDelegate}
	 */
	IDataUltimaAtualizacaoDelegate criarDataUltimaAtualizacaoDelegate();

	/**
	 * Cria a instância de {@link IProdutorDelegate}
	 * @return
	 * 		Retorna a instância de {@link IProdutorDelegate}
	 */
	IProdutorDelegate criarProdutorDelegate();
	
	/**
	 * Cria a instância de {@link IInstituicaoResponsavelDelegate}
	 * @return
	 * 		Retorna a instância de {@link IInstituicaoResponsavelDelegate}
	 */
	IInstituicaoResponsavelDelegate criarInstituicaoResponsavelDelegate();
	
	/**
	 * Cria a instância de {@link IGrupoEconomicoDelegate}
	 * @return
	 * 		Retorna a instância de {@link IGrupoEconomicoDelegate}
	 */
	IGrupoEconomicoDelegate criarGrupoEconomicoDelegate();

	/**
	 * Cria a instância de {@link IDominioDelegate}
	 * @return
	 * 		Retorna a instância de {@link IDominioDelegate}
	 */
	IDominioDelegate criarDominioDelegate();		
	
	/**
	 * Cria um novo objeto AtualizarAnotacoesSisbrDelegate.
	 * 
	 * @return the atualizar anotacoes sisbr delegate
	 */
	IAtualizarAnotacoesSisbrDelegate criarAtualizarAnotacoesSisbrDelegate();
	
	/**
	 * Criar um novo objeto NucleoDelegate.
	 * @return 
	 */
	INucleoDelegate criarNucleoDelegate();
	
	/**
	 * Cria instancia de TipoAnotacaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see ITipoAnotacaoDelegate
	 */
	ITipoAnotacaoDelegate criarTipoAnotacaoDelegate();
	
	/**
	 * Cria instancia de InformacaoUtilizadaDelegate.
	 *
	 * @return o delegate solicitado
	 * @see IInformacaoUtilizadaDelegate
	 */
	IInformacaoUtilizadaDelegate criarInformacaoUtilizadaDelegate();
	
	/**
	 * Cria instancia de CnaeFiscalDelegate.
	 *
	 * @return o delegate solicitado
	 * @see ICnaeFiscalDelegate
	 */
	ICnaeFiscalDelegate criarCnaeFiscalDelegate();
	
	/**
	 * Cria a instancia de GrupoCompartilhamentoDelegate
	 * 
	 * @return o delegate solicitado
	 */
	IGrupoCompartilhamentoDelegate criarGrupoCompartilhamentoDelegate();
	
	/**
	 * Cria a instancia de OcupacaoProfissionalDelegate
	 * 
	 * @return o delegate solicitado
	 */
	IOcupacaoProfissionalDelegate criarOcupacaoProfissionalDelegate();
	
	/**
	 * Cria a instancia de TipoEmailDelegate
	 * 
	 * @return o delegate solicitado
	 */
	ITipoEmailDelegate criarTipoEmailDelegate();
	
	/**
	 * Cria a instancia de TipoTelefoneDelegate
	 * 
	 * @return o delegate solicitado
	 */
	ITipoTelefoneDelegate criarTipoTelefoneDelegate();
	
	/**
	 * Cria a instancia de TipoEnderecoDelegate
	 * 
	 * @return o delegate solicitado
	 */
	ITipoEnderecoDelegate criarTipoEnderecoDelegate();
	
	/**
	 * Cria a instancia de TipoFonteRendaDelegate
	 * 
	 * @return o delegate solicitado
	 */
	ITipoFonteRendaDelegate criarTipoFonteRendaDelegate();
	
	/**
	 * Cria a instancia de TipoEmpresaDelegate
	 * 
	 * @return o delegate solicitado
	 */
	ITipoEmpresaDelegate criarTipoEmpresaDelegate();
	
	/**
	 * Cria a instancia de BemDelegate
	 * 
	 * @return o delegate solicitado
	 */
	IBemDelegate criarBemDelegate();

	/**
	 * Cria a instancia de BemImovelDelegate
	 * 
	 * @return o delegate solicitado
	 */
	IBemImovelDelegate criarBemImovelDelegate();

	/**
	 * Cria a instancia de BemMovelDelegate
	 * 
	 * @return o delegate solicitado
	 */
	IBemMovelDelegate criarBemMovelDelegate();
	
}