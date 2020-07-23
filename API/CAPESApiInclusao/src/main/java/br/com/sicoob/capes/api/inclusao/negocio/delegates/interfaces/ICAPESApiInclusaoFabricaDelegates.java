package br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces;

/**
 * Fabrica para criacao de Delegates do modulo CAPESApiInclusao.
 * 
 * @author bruno.carneiro
 */
public interface ICAPESApiInclusaoFabricaDelegates {

	/**
	 * Cria uma inst�ncia do AnotacaoDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	IAnotacaoDelegate criarAnotacaoDelegate();

	/**
	 * Cria uma inst�ncia do BemDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	IBemDelegate criarBemDelegate();
	
	/**
	 * Cria uma inst�ncia do ClienteDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	IClienteDelegate criarClienteDelegate();

	/**
	 * Cria uma inst�ncia do EmailDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	IEmailDelegate criarEmailDelegate();

	/**
	 * Cria uma inst�ncia do EnderecoDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	IEnderecoDelegate criarEnderecoDelegate();

	/**
	 * Cria uma inst�ncia do FonteRendaDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	IFonteRendaDelegate criarFonteRendaDelegate();
	
	/**
	 * Cria uma inst�ncia do InformacaoProfissionalDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	IInformacaoProfissionalDelegate criarInformacaoProfissionalDelegate();
	
	/**
	 * Cria uma inst�ncia do MensagemDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	IMensagemDelegate criarMensagemDelegate();

	/**
	 * Cria uma inst�ncia do PessoaDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	IPessoaDelegate criarPessoaDelegate();

	/**
	 * Cria uma inst�ncia do ReferenciaDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	IReferenciaDelegate criarReferenciaDelegate();
	
	/**
	 * Cria uma inst�ncia do RelacionamentoDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	IRelacionamentoDelegate criarRelacionamentoDelegate();

	/**
	 * Cria uma inst�ncia do TelefoneDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	ITelefoneDelegate criarTelefoneDelegate();
	
	/**
	 * Cria uma inst�ncia do PlataformaContabilDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	IPlataformaContabilDelegate criarPlataformaContabilDelegate();

}