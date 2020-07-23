package br.com.sicoob.capes.replicacao.negocio.delegates;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.negocio.delegates.BancoobFabricaDelegates;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.legado.EntidadeReplicavel;

/**
 * Fabrica dos business delegates a serem usados pelo Sistema
 * ReplicacaoClientesBO.
 * 
 * @author Stefanini IT Solutions
 */
public final class CAPESReplicacaoComumFabricaDelegates extends BancoobFabricaDelegates {

	/** Instancia do Fabrica de Delegates. */
	private static CAPESReplicacaoComumFabricaDelegates fabrica;

	/** Cache utilizado no método {@link #criarDelegate(Class)} */
	private static final Map<Class<?>, Method> CACHE = new HashMap<Class<?>, Method>();

	
	/**
	 * Retorna a fabrica de delegates a ser utilizada.
	 * 
	 * @return a fabrica de delegates a ser utilizada.
	 */
	public static CAPESReplicacaoComumFabricaDelegates getInstance() {
		if (fabrica == null) {
			synchronized (CAPESReplicacaoComumFabricaDelegates.class) {
				if (fabrica == null) {
					fabrica = new CAPESReplicacaoComumFabricaDelegates();
				}
			}
		}
		return fabrica;
	}

	/**
	 * Construtor privado no-args da classe
	 */
	private CAPESReplicacaoComumFabricaDelegates() {
		super();
	}

	/**
	 * Cria instancia de PessoaDelegate.
	 *
	 * @return o delegate solicitado
	 * @see PessoaDelegate
	 */
	public PessoaDelegate criarPessoaDelegate() {
		return new PessoaDelegate();
	}

	/**
	 * Cria instancia de ReferenciaDelegate.
	 *
	 * @return o delegate solicitado
	 * @see ReferenciaDelegate
	 */
	public ReferenciaDelegate criarReferenciaDelegate() {
		return new ReferenciaDelegate();
	}

	/**
	 * Cria instancia de FonteRendaDelegate.
	 *
	 * @return o delegate solicitado
	 * @see FonteRendaDelegate
	 */
	public FonteRendaDelegate criarFonteRendaDelegate() {
		return new FonteRendaDelegate();
	}

	/**
	 * Cria instancia de BemDelegate.
	 *
	 * @return o delegate solicitado
	 * @see BemDelegate
	 */
	public BemDelegate criarBemDelegate() {
		return new BemDelegate();
	}

	/**
	 * Cria instancia de ProdutorDelegate.
	 *
	 * @return o delegate solicitado
	 * @see ProdutorDelegate
	 */
	public ProdutorDelegate criarProdutorDelegate() {
		return new ProdutorDelegate();
	}

	/**
	 * Cria instancia de HistoricoAlteracaoCnpjCpfDelegate.
	 *
	 * @return o delegate solicitado
	 * @see HistoricoAlteracaoCnpjCpfDelegate
	 */
	public HistoricoAlteracaoCnpjCpfDelegate criarHistoricoAlteracaoCnpjCpfDelegate() {
		return new HistoricoAlteracaoCnpjCpfDelegate();
	}
	
	/**
	 * Cria instancia de AvaliacaoFinanceiraDelegate.
	 *
	 * @return o delegate solicitado
	 * @see AvaliacaoFinanceiraDelegate
	 */
	public AvaliacaoFinanceiraDelegate criarAvaliacaoFinanceiraDelegate() {
		return new AvaliacaoFinanceiraDelegate();
	}

	/**
	 * Cria instancia de ListaDelegate.
	 *
	 * @return o delegate solicitado
	 * @see ListaDelegate
	 */
	public ListaDelegate criarListaDelegate() {
		return new ListaDelegate();
	}

	/**
	 * Cria instancia de ListaItemDelegate.
	 *
	 * @return o delegate solicitado
	 * @see ListaItemDelegate
	 */
	public ListaItemDelegate criarListaItemDelegate() {
		return new ListaItemDelegate();
	}

	/**
	 * Cria instancia de UnidadeMedidaDelegate.
	 *
	 * @return o delegate solicitado
	 * @see UnidadeMedidaDelegate
	 */
	public UnidadeMedidaDelegate criarUnidadeMedidaDelegate() {
		return new UnidadeMedidaDelegate();
	}

	/**
	 * Cria instancia de TipoBemDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoBemDelegate
	 */
	public TipoBemDelegate criarTipoBemDelegate() {
		return new TipoBemDelegate();
	}
	
	/**
	 * Cria instancia de TipoEmpresaDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoEmpresaDelegate
	 */
	public TipoEmpresaDelegate criarTipoEmpresaDelegate() {
		return new TipoEmpresaDelegate();
	}

	/**
	 * Cria instancia de BemOnusDelegate.
	 *
	 * @return o delegate solicitado
	 * @see BemOnusDelegate
	 */
	public BemOnusDelegate criarBemOnusDelegate() {
		return new BemOnusDelegate();
	}

	/**
	 * Cria instancia de BemPosseDelegate.
	 *
	 * @return o delegate solicitado
	 * @see BemPosseDelegate
	 */
	public BemPosseDelegate criarBemPosseDelegate() {
		return new BemPosseDelegate();
	}

	/**
	 * Cria instancia de BemRegistroDelegate.
	 *
	 * @return o delegate solicitado
	 * @see BemRegistroDelegate
	 */
	public BemRegistroDelegate criarBemRegistroDelegate() {
		return new BemRegistroDelegate();
	}

	/**
	 * Cria instancia de BemImovelDelegate.
	 *
	 * @return o delegate solicitado
	 * @see BemImovelDelegate
	 */
	public BemImovelDelegate criarBemImovelDelegate() {
		return new BemImovelDelegate();
	}

	/**
	 * Cria instancia de EnderecoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see EnderecoDelegate
	 */
	public EnderecoDelegate criarEnderecoDelegate() {
		return new EnderecoDelegate();
	}

	/**
	 * Cria instancia de MensagemDelegate.
	 *
	 * @return o delegate solicitado
	 * @see MensagemDelegate
	 */
	public MensagemDelegate criarMensagemDelegate() {
		return new MensagemDelegate();
	}

	/**
	 * Cria instancia de TelefoneDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TelefoneDelegate
	 */
	public TelefoneDelegate criarTelefoneDelegate() {
		return new TelefoneDelegate();
	}

	/**
	 * Cria instancia de CertidaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see CertidaoDelegate
	 */
	public CertidaoDelegate criarCertidaoDelegate() {
		return new CertidaoDelegate();
	}
	
	/**
	 * Cria instancia de ClienteDelegate.
	 *
	 * @return o delegate solicitado
	 * @see ClienteDelegate
	 */
	public ClienteDelegate criarClienteDelegate() {
		return new ClienteDelegate();
	}
	
	/**
	 * Cria instancia de FuncionarioDelegate.
	 *
	 * @return o delegate solicitado
	 * @see FuncionarioDelegate
	 */
	public FuncionarioDelegate criarFuncionarioDelegate() {
		return new FuncionarioDelegate();
	}
	
	/**
	 * Cria instancia de RelacionamentoPessoaDelegate.
	 *
	 * @return o delegate solicitado
	 * @see RelacionamentoPessoaDelegate
	 */
	public RelacionamentoPessoaDelegate criarRelacionamentoPessoaDelegate() {
		return new RelacionamentoPessoaDelegate();
	}
	
	/**
	 * Cria instancia de ProdutividadeDelegate.
	 *
	 * @return o delegate solicitado
	 * @see ProdutividadeDelegate
	 */
	public ProdutividadeDelegate criarProdutividadeDelegate() {
		return new ProdutividadeDelegate();
	}
	
	/**
	 * Cria instancia de EmpreendimentoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see EmpreendimentoDelegate
	 */
	public EmpreendimentoDelegate criarEmpreendimentoDelegate() {
		return new EmpreendimentoDelegate();
	}
	
	/**
	 * Cria instancia de ReplicacaoCadastroDelegate.
	 *
	 * @return o delegate solicitado
	 * @see ReplicacaoCadastroDelegate
	 */
	public ReplicacaoCadastroDelegate criarReplicacaoCadastroDelegate() {
		return new ReplicacaoCadastroDelegate();
	}	
	
	/**
	 * Cria instancia de NucleoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see NucleoDelegate
	 */
	public NucleoDelegate criarNucleoDelegate() {
		return new NucleoDelegate();
	}

	/**
	 * Cria instancia de CooperativaDelegate.
	 *
	 * @return o delegate solicitado
	 * @see CooperativaDelegate
	 */
	public CooperativaDelegate criarCooperativaDelegate() {
		return new CooperativaDelegate();
	}

	/**
	 * Cria instancia de InformacaoProfissionalDelegate.
	 *
	 * @return o delegate solicitado
	 * @see InformacaoProfissionalDelegate
	 */
	public InformacaoProfissionalDelegate criarInformacaoProfissionalDelegate() {
		return new InformacaoProfissionalDelegate();
	}
	
	/**
	 * <p>
	 * Cria a delegate apropriada para a entidade do tipo da classe recebida
	 * como parâmetro.
	 * </p>
	 * <p>
	 * <strong>Atenção!</strong> A delegate é identificada pelo tipo genérico
	 * configurado na classe. Por este motivo, este método só funciona para
	 * delegates que estendam {@code CadastroUnicoClientesComumCrudDelegate}
	 * </p>
	 * 
	 * @param <D>
	 *            Classe da hierárquia de
	 *            {@link CadastroUnicoClientesComumCrudDelegate}
	 * @param <E>
	 *            Classe da hierárquia de
	 *            {@link CadastroUnicoClientesComumEntidade}
	 * @param classe
	 *            A classe da entidade cuja delegate se deseja obter
	 * @return A delegate apropriada
	 * @throws BancoobException
	 */
	@SuppressWarnings(value={ "unchecked" })	
	public EntidadeReplicavelDelegate<EntidadeReplicavel<?>, ?> criarEntidadeReplicavelDelegate(Class<?> classe) 
			throws BancoobException {

		Method metodo = CACHE.get(classe);

		// recupera os métodos públicos
		Method[] metodos = this.getClass().getMethods();
		for (int i = 0; (i < metodos.length) && (metodo == null); i++) {

			// recupera o tipo de retorno do método
			Class<?> returnType = metodos[i].getReturnType();

			// se retorna uma subclasse de
			// ReplicacaoClientesComumFabricaDelegates e não este método
			if (CAPESReplicacaoComumFabricaDelegates.class.isAssignableFrom(returnType)
					&& (!metodos[i].getName().equals("criarDelegate"))) {

				// recupera a superclasse genérica
				Type type = returnType.getGenericSuperclass();

				// se a superclasse for do tipo parametrizada
				if (type instanceof ParameterizedType) {
					ParameterizedType parameterizedType = (ParameterizedType) type;

					// recupera os parâmetros genéricos atuais
					Type[] typeArguments = parameterizedType.getActualTypeArguments();
					for (int j = 0; (j < typeArguments.length) && (metodo == null); j++) {
						Type typeArgument = typeArguments[j];

						// se o tipo genérico declarado é igual à classe da
						// entidade e o método não
						// recebe parâmetros
						if (classe.equals((Class<?>) typeArgument)
								&& (metodos[i].getParameterTypes().length == 0)) {
							metodo = metodos[i];
							CACHE.put(classe, metodo);
						}
					}
				}
			}
		}

		// se não encontrou o método apropriado, lança exceção
		if (metodo == null) {
			throw new BancoobRuntimeException("msg.erro.delegate.nao.encontrada",
					new Object[] { classe.getName() });
		}
		return (EntidadeReplicavelDelegate<EntidadeReplicavel<?>, ?>) ReflexaoUtils.invocarMetodo(this, metodo);
	}	
}