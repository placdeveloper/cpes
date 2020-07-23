package br.com.sicoob.capes.api.inclusao.negocio.delegates.crossContainer;

import br.com.bancoob.negocio.delegates.BancoobFabricaDelegates;
import br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces.ICAPESApiInclusaoFabricaDelegates;

/**
 * Fabrica para criacao de Delegates do modulo CAPESApiInclusao.
 * 
 * @author bruno.carneiro
 */
public final class CAPESApiInclusaoFabricaDelegatesCrossContainer extends BancoobFabricaDelegates implements ICAPESApiInclusaoFabricaDelegates {

	/** O atributo fabrica. */
	private static CAPESApiInclusaoFabricaDelegatesCrossContainer fabrica;

	/**
	 * Obter instancia.
	 * 
	 * @return CAPESApiInclusaoFabricaDelegates
	 */
	public static CAPESApiInclusaoFabricaDelegatesCrossContainer obterInstancia() {
		if (fabrica == null) {
			synchronized (CAPESApiInclusaoFabricaDelegatesCrossContainer.class) {
				if (fabrica == null) {
					fabrica = new CAPESApiInclusaoFabricaDelegatesCrossContainer();
				}
			}
		}
		return fabrica;
	}

	/**
	 * Instancia um novo CAPESApiInclusaoFabricaDelegates.
	 */
	private CAPESApiInclusaoFabricaDelegatesCrossContainer() {
		super();
	}

	/**
	 * Cria uma inst�ncia do AnotacaoDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public AnotacaoDelegate criarAnotacaoDelegate() {
		return AnotacaoDelegate.getInstance();
	}

	/**
	 * Cria uma inst�ncia do BemDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public BemDelegate criarBemDelegate() {
		return BemDelegate.getInstance();
	}
	
	/**
	 * Cria uma inst�ncia do ClienteDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public ClienteDelegate criarClienteDelegate() {
		return ClienteDelegate.getInstance();
	}

	/**
	 * Cria uma inst�ncia do EmailDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public EmailDelegate criarEmailDelegate() {
		return EmailDelegate.getInstance();
	}

	/**
	 * Cria uma inst�ncia do EnderecoDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public EnderecoDelegate criarEnderecoDelegate() {
		return EnderecoDelegate.getInstance();
	}

	/**
	 * Cria uma inst�ncia do FonteRendaDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public FonteRendaDelegate criarFonteRendaDelegate() {
		return FonteRendaDelegate.getInstance();
	}
	
	/**
	 * Cria uma inst�ncia do InformacaoProfissionalDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public InformacaoProfissionalDelegate criarInformacaoProfissionalDelegate() {
		return InformacaoProfissionalDelegate.getInstance();
	}
	
	/**
	 * Cria uma inst�ncia do MensagemDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public MensagemDelegate criarMensagemDelegate() {
		return MensagemDelegate.getInstance();
	}

	/**
	 * Cria uma inst�ncia do PessoaDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public PessoaDelegate criarPessoaDelegate() {
		return PessoaDelegate.getInstance();
	}

	/**
	 * Cria uma inst�ncia do ReferenciaDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public ReferenciaDelegate criarReferenciaDelegate() {
		return ReferenciaDelegate.getInstance();
	}
	
	/**
	 * Cria uma inst�ncia do RelacionamentoDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public RelacionamentoDelegate criarRelacionamentoDelegate() {
		return RelacionamentoDelegate.getInstance();
	}

	/**
	 * Cria uma inst�ncia do TelefoneDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public TelefoneDelegate criarTelefoneDelegate() {
		return TelefoneDelegate.getInstance();
	}
	
	/**
	 * Cria uma inst�ncia do PlataformaContabilDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public PlataformaContabilDelegate criarPlataformaContabilDelegate() {
		return PlataformaContabilDelegate.getInstance();
	}

}