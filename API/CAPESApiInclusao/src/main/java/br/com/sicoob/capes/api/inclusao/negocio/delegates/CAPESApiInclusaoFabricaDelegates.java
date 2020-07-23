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
	 * Cria uma inst�ncia do AnotacaoDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public AnotacaoDelegate criarAnotacaoDelegate() {
		return new AnotacaoDelegate();
	}

	/**
	 * Cria uma inst�ncia do BemDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public BemDelegate criarBemDelegate() {
		return new BemDelegate();
	}
	
	/**
	 * Cria uma inst�ncia do ClienteDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public ClienteDelegate criarClienteDelegate() {
		return new ClienteDelegate();
	}

	/**
	 * Cria uma inst�ncia do EmailDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public EmailDelegate criarEmailDelegate() {
		return new EmailDelegate();
	}

	/**
	 * Cria uma inst�ncia do EnderecoDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public EnderecoDelegate criarEnderecoDelegate() {
		return new EnderecoDelegate();
	}

	/**
	 * Cria uma inst�ncia do FonteRendaDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public FonteRendaDelegate criarFonteRendaDelegate() {
		return new FonteRendaDelegate();
	}
	
	/**
	 * Cria uma inst�ncia do InformacaoProfissionalDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public InformacaoProfissionalDelegate criarInformacaoProfissionalDelegate() {
		return new InformacaoProfissionalDelegate();
	}
	
	/**
	 * Cria uma inst�ncia do MensagemDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public MensagemDelegate criarMensagemDelegate() {
		return new MensagemDelegate();
	}

	/**
	 * Cria uma inst�ncia do PessoaDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public PessoaDelegate criarPessoaDelegate() {
		return new PessoaDelegate();
	}

	/**
	 * Cria uma inst�ncia do ReferenciaDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public ReferenciaDelegate criarReferenciaDelegate() {
		return new ReferenciaDelegate();
	}
	
	/**
	 * Cria uma inst�ncia do RelacionamentoDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public RelacionamentoDelegate criarRelacionamentoDelegate() {
		return new RelacionamentoDelegate();
	}

	/**
	 * Cria uma inst�ncia do TelefoneDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public TelefoneDelegate criarTelefoneDelegate() {
		return new TelefoneDelegate();
	}
	
	/**
	 * Cria uma inst�ncia do PlataformaContabilDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public PlataformaContabilDelegate criarPlataformaContabilDelegate() {
		return new PlataformaContabilDelegate();
	}

}