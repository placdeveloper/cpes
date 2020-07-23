package br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces;

/**
 * Fabrica para criacao de Delegates do modulo CAPESApiInclusao.
 * 
 * @author bruno.carneiro
 */
public interface ICAPESApiInclusaoFabricaDelegates {

	/**
	 * Cria uma instância do AnotacaoDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	IAnotacaoDelegate criarAnotacaoDelegate();

	/**
	 * Cria uma instância do BemDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	IBemDelegate criarBemDelegate();
	
	/**
	 * Cria uma instância do ClienteDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	IClienteDelegate criarClienteDelegate();

	/**
	 * Cria uma instância do EmailDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	IEmailDelegate criarEmailDelegate();

	/**
	 * Cria uma instância do EnderecoDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	IEnderecoDelegate criarEnderecoDelegate();

	/**
	 * Cria uma instância do FonteRendaDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	IFonteRendaDelegate criarFonteRendaDelegate();
	
	/**
	 * Cria uma instância do InformacaoProfissionalDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	IInformacaoProfissionalDelegate criarInformacaoProfissionalDelegate();
	
	/**
	 * Cria uma instância do MensagemDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	IMensagemDelegate criarMensagemDelegate();

	/**
	 * Cria uma instância do PessoaDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	IPessoaDelegate criarPessoaDelegate();

	/**
	 * Cria uma instância do ReferenciaDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	IReferenciaDelegate criarReferenciaDelegate();
	
	/**
	 * Cria uma instância do RelacionamentoDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	IRelacionamentoDelegate criarRelacionamentoDelegate();

	/**
	 * Cria uma instância do TelefoneDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	ITelefoneDelegate criarTelefoneDelegate();
	
	/**
	 * Cria uma instância do PlataformaContabilDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	IPlataformaContabilDelegate criarPlataformaContabilDelegate();

}