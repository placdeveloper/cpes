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
	 * Cria uma instância do AnotacaoDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public AnotacaoDelegate criarAnotacaoDelegate() {
		return AnotacaoDelegate.getInstance();
	}

	/**
	 * Cria uma instância do BemDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public BemDelegate criarBemDelegate() {
		return BemDelegate.getInstance();
	}
	
	/**
	 * Cria uma instância do ClienteDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public ClienteDelegate criarClienteDelegate() {
		return ClienteDelegate.getInstance();
	}

	/**
	 * Cria uma instância do EmailDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public EmailDelegate criarEmailDelegate() {
		return EmailDelegate.getInstance();
	}

	/**
	 * Cria uma instância do EnderecoDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public EnderecoDelegate criarEnderecoDelegate() {
		return EnderecoDelegate.getInstance();
	}

	/**
	 * Cria uma instância do FonteRendaDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public FonteRendaDelegate criarFonteRendaDelegate() {
		return FonteRendaDelegate.getInstance();
	}
	
	/**
	 * Cria uma instância do InformacaoProfissionalDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public InformacaoProfissionalDelegate criarInformacaoProfissionalDelegate() {
		return InformacaoProfissionalDelegate.getInstance();
	}
	
	/**
	 * Cria uma instância do MensagemDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public MensagemDelegate criarMensagemDelegate() {
		return MensagemDelegate.getInstance();
	}

	/**
	 * Cria uma instância do PessoaDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public PessoaDelegate criarPessoaDelegate() {
		return PessoaDelegate.getInstance();
	}

	/**
	 * Cria uma instância do ReferenciaDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public ReferenciaDelegate criarReferenciaDelegate() {
		return ReferenciaDelegate.getInstance();
	}
	
	/**
	 * Cria uma instância do RelacionamentoDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public RelacionamentoDelegate criarRelacionamentoDelegate() {
		return RelacionamentoDelegate.getInstance();
	}

	/**
	 * Cria uma instância do TelefoneDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public TelefoneDelegate criarTelefoneDelegate() {
		return TelefoneDelegate.getInstance();
	}
	
	/**
	 * Cria uma instância do PlataformaContabilDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public PlataformaContabilDelegate criarPlataformaContabilDelegate() {
		return PlataformaContabilDelegate.getInstance();
	}

}