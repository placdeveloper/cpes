package br.com.sicoob.capes.api.inclusao.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobFabricaDelegates;
import br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces.ICAPESApiInclusaoFabricaDelegates;

/**
 * Fabrica para criacao de Delegates do modulo CAPESApiInclusao.
 * 
 * @author bruno.carneiro
 */
public final class CAPESApiInclusaoFabricaDelegates extends BancoobFabricaDelegates implements ICAPESApiInclusaoFabricaDelegates {

	/** O atributo fabrica. */
	private static CAPESApiInclusaoFabricaDelegates fabrica;

	/**
	 * Obter instancia.
	 * 
	 * @return CAPESApiInclusaoFabricaDelegates
	 */
	public static CAPESApiInclusaoFabricaDelegates obterInstancia() {
		if (fabrica == null) {
			synchronized (CAPESApiInclusaoFabricaDelegates.class) {
				if (fabrica == null) {
					fabrica = new CAPESApiInclusaoFabricaDelegates();
				}
			}
		}
		return fabrica;
	}

	/**
	 * Instancia um novo CAPESApiInclusaoFabricaDelegates.
	 */
	private CAPESApiInclusaoFabricaDelegates() {
		super();
	}

	/**
	 * Cria uma instância do AnotacaoDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public AnotacaoDelegate criarAnotacaoDelegate() {
		return new AnotacaoDelegate();
	}

	/**
	 * Cria uma instância do BemDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public BemDelegate criarBemDelegate() {
		return new BemDelegate();
	}
	
	/**
	 * Cria uma instância do ClienteDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public ClienteDelegate criarClienteDelegate() {
		return new ClienteDelegate();
	}

	/**
	 * Cria uma instância do EmailDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public EmailDelegate criarEmailDelegate() {
		return new EmailDelegate();
	}

	/**
	 * Cria uma instância do EnderecoDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public EnderecoDelegate criarEnderecoDelegate() {
		return new EnderecoDelegate();
	}

	/**
	 * Cria uma instância do FonteRendaDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public FonteRendaDelegate criarFonteRendaDelegate() {
		return new FonteRendaDelegate();
	}
	
	/**
	 * Cria uma instância do InformacaoProfissionalDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public InformacaoProfissionalDelegate criarInformacaoProfissionalDelegate() {
		return new InformacaoProfissionalDelegate();
	}
	
	/**
	 * Cria uma instância do MensagemDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public MensagemDelegate criarMensagemDelegate() {
		return new MensagemDelegate();
	}

	/**
	 * Cria uma instância do PessoaDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public PessoaDelegate criarPessoaDelegate() {
		return new PessoaDelegate();
	}

	/**
	 * Cria uma instância do ReferenciaDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public ReferenciaDelegate criarReferenciaDelegate() {
		return new ReferenciaDelegate();
	}
	
	/**
	 * Cria uma instância do RelacionamentoDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public RelacionamentoDelegate criarRelacionamentoDelegate() {
		return new RelacionamentoDelegate();
	}

	/**
	 * Cria uma instância do TelefoneDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public TelefoneDelegate criarTelefoneDelegate() {
		return new TelefoneDelegate();
	}
	
	/**
	 * Cria uma instância do PlataformaContabilDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public PlataformaContabilDelegate criarPlataformaContabilDelegate() {
		return new PlataformaContabilDelegate();
	}

}