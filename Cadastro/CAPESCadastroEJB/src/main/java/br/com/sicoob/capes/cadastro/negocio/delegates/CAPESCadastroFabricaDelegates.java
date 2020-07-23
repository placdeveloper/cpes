package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.negocio.delegates.BancoobFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.bemantigo.BemAntigoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.bemantigo.BemImovelAntigoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.bemantigo.SituacaoBemAntigoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.bemantigo.SubTipoBemAntigoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.bemantigo.TipoBemAntigoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.bemantigo.TipoPosseBemAntigoDelegate;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;

/**
 * Fabrica dos business delegates a serem usados pelo Sistema
 * CadastroUnicoClientesComum.
 *
 * @author Stefanini IT Solutions
 */
public final class CAPESCadastroFabricaDelegates extends BancoobFabricaDelegates {

	/** Instancia do Fabrica de Delegates. */
	private static CAPESCadastroFabricaDelegates fabrica;

	/** Cache utilizado no método {@link #criarDelegate(Class)} */
	private static final Map<Class<?>, Method> CACHE = new HashMap<Class<?>, Method>();

	/**
	 * Retorna a fabrica de delegates a ser utilizada.
	 *
	 * @return a fabrica de delegates a ser utilizada.
	 */
	public static CAPESCadastroFabricaDelegates getInstance() {
		if (fabrica == null) {
			synchronized (CAPESCadastroFabricaDelegates.class) {
				if (fabrica == null) {
					fabrica = new CAPESCadastroFabricaDelegates();
				}
			}
		}
		return fabrica;
	}

	/**
	 * Construtor privado no-args da classe
	 */
	private CAPESCadastroFabricaDelegates() {
		super();
	}

	/**
	 * <p>
	 * Cria a delegate apropriada para a entidade do tipo da classe recebida
	 * como parametro.
	 * </p>
	 * <p>
	 * <strong>Atencao!</strong> A delegate é identificada pelo tipo generico
	 * configurado na classe. Por este motivo, este metodo so funciona para
	 * delegates que estendam {@code CAPESCadastroCrudDelegate}
	 * </p>
	 *
	 * @param <D>
	 *            Classe da hierarquia de
	 *            {@link CAPESCadastroCrudDelegate}
	 * @param <E>
	 *            Classe da hierarquia de
	 *            {@link CAPESEntidade}
	 * @param classeEntidade
	 *            A classe da entidade cuja delegate se deseja obter
	 * @return A delegate apropriada
	 * @throws BancoobException
	 */
	@SuppressWarnings(value={ "unchecked", "rawtypes" })
	public <C> CAPESCadastroCrudDelegate<CAPESEntidade<?>, ?>
			criarDelegate(Class<C> classeEntidade) throws BancoobException {

		Method metodoAlternativo = null;
		Method metodo = CACHE.get(classeEntidade);

		// recupera os metodos publicos
		Method[] metodos = this.getClass().getMethods();

		for (int i = 0; (i < metodos.length) && (metodo == null); i++) {
			Method m = metodos[i];

			// recupera o tipo de retorno do metodo
			Class<?> tipoRetorno = m.getReturnType();
			if (CAPESCadastroCrudDelegate.class.isAssignableFrom(tipoRetorno)
					&& !m.getName().equals("criarDelegate")
					&& !m.getName().equals("getInstance")) {

				Class<?> tipoEntidade = ReflexaoUtils.obterParametroGenerico(tipoRetorno);
				if (classeEntidade.equals(tipoEntidade)) {
					metodo = m;
					CACHE.put(classeEntidade, metodo);
				} else if (tipoEntidade.isAssignableFrom(classeEntidade)){
					metodoAlternativo = m;
				}
			}
		}

		// se nao encontrou o metodo apropriado para a classe
		if (metodo == null) {

			// se nao encontrou um metodo alternativo, lanca excecao
			if (metodoAlternativo == null) {
				throw new BancoobRuntimeException("msg.erro.delegate.nao.encontrada",
						new Object[] { classeEntidade.getName() });
			} else {
				metodo = metodoAlternativo;
				CACHE.put(classeEntidade, metodo);
			}
		}
		return (CAPESCadastroCrudDelegate) ReflexaoUtils.invocarMetodo(this, metodo);
	}
	
	/**
	 * Cria instancia de AnotacaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see AnotacaoDelegate
	 */
	public AnotacaoDelegate criarAnotacaoDelegate() {
		return new AnotacaoDelegate();
	}

	/**
	 * Cria instancia de TipoAnotacaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoAnotacaoDelegate
	 */
	public TipoAnotacaoDelegate criarTipoAnotacaoDelegate() {
		return new TipoAnotacaoDelegate();
	}

	/**
	 * Cria instancia de TipoEmailDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoEmailDelegate
	 */
	public TipoEmailDelegate criarTipoEmailDelegate() {
		return new TipoEmailDelegate();
	}
	
	/**
	 * Cria instancia de TipoEnderecoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoEnderecoDelegate
	 */
	public TipoEnderecoDelegate criarTipoEnderecoDelegate() {
		return new TipoEnderecoDelegate();
	}

	/**
	 * Cria instancia de TipoTelefoneDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoTelefoneDelegate
	 */
	public TipoTelefoneDelegate criarTipoTelefoneDelegate() {
		return new TipoTelefoneDelegate();
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
	 * Cria instancia de TransicaoPessoaDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TransicaoPessoaDelegate
	 */
	public TransicaoPessoaDelegate criarTransicaoPessoaDelegate() {
		return new TransicaoPessoaDelegate();
	}

	/**
	 * Cria instancia de OrigemInformacaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see OrigemInformacaoDelegate
	 */
	public OrigemInformacaoDelegate criarOrigemInformacaoDelegate() {
		return new OrigemInformacaoDelegate();
	}

	/**
	 * Cria instancia de CategoriaAnotacaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see CategoriaAnotacaoDelegate
	 */
	public CategoriaAnotacaoDelegate criarCategoriaAnotacaoDelegate() {
		return new CategoriaAnotacaoDelegate();
	}

	/**
	 * Cria instancia de TipoCapturaDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoCapturaDelegate
	 */
	public TipoCapturaDelegate criarTipoCapturaDelegate() {
		return new TipoCapturaDelegate();
	}

	/**
	 * Cria instancia de PeriodicidadeAnotacaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see PeriodicidadeAnotacaoDelegate
	 */
	public PeriodicidadeAnotacaoDelegate criarPeriodicidadeAnotacaoDelegate() {
		return new PeriodicidadeAnotacaoDelegate();
	}

	/**
	 * Cria instancia de AutorizacaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see AutorizacaoDelegate
	 */
	public AutorizacaoDelegate criarAutorizacaoDelegate() {
		return new AutorizacaoDelegate();
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
	 * Cria instancia de TipoReferenciaDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoReferenciaDelegate
	 */
	public TipoReferenciaDelegate criarTipoReferenciaDelegate() {
		return new TipoReferenciaDelegate();
	}

	/**
	 * Cria instancia de ResponsavelCadastroDelegate.
	 *
	 * @return o delegate solicitado
	 * @see ResponsavelCadastroDelegate
	 */
	public ResponsavelCadastroDelegate criarResponsavelCadastroDelegate() {
		return new ResponsavelCadastroDelegate();
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
	 * Cria instancia de TipoFonteRendaDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoFonteRendaDelegate
	 */
	public TipoFonteRendaDelegate criarTipoFonteRendaDelegate() {
		return new TipoFonteRendaDelegate();
	}
	
	/**
	 * Cria instancia de AutorizacaoCadastroDelegate.
	 *
	 * @return o delegate solicitado
	 * @see AutorizacaoCadastroDelegate
	 */
	public AutorizacaoCadastroDelegate criarAutorizacaoCadastroDelegate() {
		return new AutorizacaoCadastroDelegate();
	}

	/**
	 * Cria instancia de HistoricoAlteracaoCpfCnpjDelegate.
	 *
	 * @return o delegate solicitado
	 * @see HistoricoAlteracaoCpfCnpjDelegate
	 */
	public HistoricoAlteracaoCpfCnpjDelegate criarHistoricoAlteracaoCpfCnpjDelegate() {
		return new HistoricoAlteracaoCpfCnpjDelegate();
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
	 * @see TipoBemAntigoDelegate
	 */
	public TipoBemAntigoDelegate criarTipoBemAntigoDelegate() {
		return new TipoBemAntigoDelegate();
	}

	/**
	 * Cria instancia de BemImovelAntigoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see BemImovelAntigoDelegate
	 */
	public BemImovelAntigoDelegate criarBemImovelAntigoDelegate() {
		return new BemImovelAntigoDelegate();
	}

	/**
	 * Cria instancia de BemDelegate.
	 *
	 * @return o delegate solicitado
	 * @see BemAntigoDelegate
	 */
	public BemAntigoDelegate criarBemAntigoDelegate() {
		return new BemAntigoDelegate();
	}

	/**
	 * Cria instancia de SubTipoBemDelegate.
	 *
	 * @return o delegate solicitado
	 * @see SubTipoBemAntigoDelegate
	 */
	public SubTipoBemAntigoDelegate criarSubTipoBemAntigoDelegate() {
		return new SubTipoBemAntigoDelegate();
	}

	/**
	 * Cria instancia de TipoPosseBemDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoPosseBemAntigoDelegate
	 */
	public TipoPosseBemAntigoDelegate criarTipoPosseBemAntigoDelegate() {
		return new TipoPosseBemAntigoDelegate();
	}

	/**
	 * Cria instancia de SituacaoBemDelegate.
	 *
	 * @return o delegate solicitado
	 * @see SituacaoBemAntigoDelegate
	 */
	public SituacaoBemAntigoDelegate criarSituacaoBemAntigoDelegate() {
		return new SituacaoBemAntigoDelegate();
	}

	/**
	 * Cria instancia de EmailDelegate.
	 *
	 * @return o delegate solicitado
	 * @see EmailDelegate
	 */
	public EmailDelegate criarEmailDelegate() {
		return new EmailDelegate();
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
	 * Cria instancia de TipoPessoaDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoPessoaDelegate
	 */
	public TipoPessoaDelegate criarTipoPessoaDelegate() {
		return new TipoPessoaDelegate();
	}
	
	/**
	 * Cria instancia de TipoPessoaContatoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoPessoaContatoDelegate
	 */
	public TipoPessoaContatoDelegate criarTipoPessoaContatoDelegate() {
		return new TipoPessoaContatoDelegate();
	}

	/**
	 * Cria instancia de OrgaoEmissorCertidaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see OrgaoEmissorCertidaoDelegate
	 */
	public OrgaoEmissorCertidaoDelegate criarOrgaoEmissorCertidaoDelegate() {
		return new OrgaoEmissorCertidaoDelegate();
	}

	/**
	 * Cria instancia de SubTipoCertidaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see SubTipoCertidaoDelegate
	 */
	public SubTipoCertidaoDelegate criarSubTipoCertidaoDelegate() {
		return new SubTipoCertidaoDelegate();
	}

	/**
	 * Cria instancia de TipoAbrangenciaCertidaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoAbrangenciaCertidaoDelegate
	 */
	public TipoAbrangenciaCertidaoDelegate criarTipoAbrangenciaCertidaoDelegate() {
		return new TipoAbrangenciaCertidaoDelegate();
	}

	/**
	 * Cria instancia de TipoCertidaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoCertidaoDelegate
	 */
	public TipoCertidaoDelegate criarTipoCertidaoDelegate() {
		return new TipoCertidaoDelegate();
	}

	/**
	 * Cria instancia de TipoObjetoCertidaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoObjetoCertidaoDelegate
	 */
	public TipoObjetoCertidaoDelegate criarTipoObjetoCertidaoDelegate() {
		return new TipoObjetoCertidaoDelegate();
	}

	/**
	 * Cria instancia de TipoPrazoCertidaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoPrazoCertidaoDelegate
	 */
	public TipoPrazoCertidaoDelegate criarTipoPrazoCertidaoDelegate() {
		return new TipoPrazoCertidaoDelegate();
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
	 * Cria instancia de TipoMensagemDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoMensagemDelegate
	 */
	public TipoMensagemDelegate criarTipoMensagemDelegate(){
		return new TipoMensagemDelegate();
	}

	/**
	 * Cria instancia de TipoDestinoExibicaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoDestinoExibicaoDelegate
	 */
	public TipoDestinoExibicaoDelegate criarTipoDestinoExibicaoDelegate(){
		return new TipoDestinoExibicaoDelegate();
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
	 * Cria instancia de TipoRelacionamentoPessoaDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoRelacionamentoPessoaDelegate
	 */
	public TipoRelacionamentoPessoaDelegate criarTipoRelacionamentoPessoaDelegate() {
		return new TipoRelacionamentoPessoaDelegate();
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
	 * Cria instancia de TributacaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TributacaoDelegate
	 */
	public TributacaoDelegate criarTributacaoDelegate() {
		return new TributacaoDelegate();
	}
	
	/**
	 * Cria instancia de PerfilCadastroDelegate.
	 *
	 * @return o delegate solicitado
	 * @see PerfilCadastroDelegate
	 */
	public PerfilCadastroDelegate criarPerfilCadastroDelegate() {
		return new PerfilCadastroDelegate();
	}

	/**
	 * Cria instancia de TipoDocumentoIdentificacaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoDocumentoIdentificacaoDelegate
	 */
	public TipoDocumentoIdentificacaoDelegate criarTipoDocumentoIdentificacaoDelegate() {
		return new TipoDocumentoIdentificacaoDelegate();
	}
	
	/**
	 * Cria instancia de VinculoEmpregaticioDelegate.
	 *
	 * @return o delegate solicitado
	 * @see VinculoEmpregaticioDelegate
	 */
	public VinculoEmpregaticioDelegate criarVinculoEmpregaticioDelegate() {
		return new VinculoEmpregaticioDelegate();
	}

	/**
	 * Cria instancia de GrauInstrucaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see GrauInstrucaoDelegate
	 */
	public GrauInstrucaoDelegate criarGrauInstrucaoDelegate() {
		return new GrauInstrucaoDelegate();
	}

	/**
	 * Cria instancia de TipoPoderRelacionamentoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoPoderRelacionamentoDelegate
	 */
	public TipoPoderRelacionamentoDelegate criarTipoPoderRelacionamentoDelegate() {
		return new TipoPoderRelacionamentoDelegate();
	}

	/**
	 * Cria instancia de EstadoCivilDelegate.
	 *
	 * @return o delegate solicitado
	 * @see EstadoCivilDelegate
	 */
	public EstadoCivilDelegate criarEstadoCivilDelegate() {
		return new EstadoCivilDelegate();
	}

	/**
	 * Cria instancia de RegimeCasamentoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see RegimeCasamentoDelegate
	 */
	public RegimeCasamentoDelegate criarRegimeCasamentoDelegate() {
		return new RegimeCasamentoDelegate();
	}

	/**
	 * Cria instancia de PessoaInstituicaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see PessoaInstituicaoDelegate
	 */
	public PessoaInstituicaoDelegate criarPessoaInstituicaoDelegate() {
		return new PessoaInstituicaoDelegate();
	}

	/**
	 * Cria instancia de TipoFormaConstituicaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoFormaConstituicaoDelegate
	 */
	public TipoFormaConstituicaoDelegate criarTipoFormaConstituicaoDelegate() {
		return new TipoFormaConstituicaoDelegate();
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
	 * Cria instancia de NucleoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see NucleoDelegate
	 */
	public NucleoDelegate criarNucleoDelegate() {
		return new NucleoDelegate();
	}

	/**
	 * Cria instancia de PerfilTarifarioDelegate.
	 *
	 * @return o delegate solicitado
	 * @see PerfilTarifarioDelegate
	 */
	public PerfilTarifarioDelegate criarPerfilTarifarioDelegate() {
		return new PerfilTarifarioDelegate();
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
	 * Cria instancia de GrupoEconomicoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see GrupoEconomicoDelegate
	 */
	public GrupoEconomicoDelegate criarGrupoEconomicoDelegate() {
		return new GrupoEconomicoDelegate();
	}
	
	/**
	 * Cria instancia de GrupoEconomicoNovoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see GrupoEconomicoNovoDelegate
	 */
	public GrupoEconomicoNovoDelegate criarGrupoEconomicoNovoDelegate() {
		return new GrupoEconomicoNovoDelegate();
	}
	
	/**
	 * Cria instancia de GrupoEconomicoNovoLimpoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see GrupoEconomicoNovoLimpoDelegate
	 */
	public GrupoEconomicoNovoLimpoDelegate criarGrupoEconomicoNovoLimpoDelegate() {
		return new GrupoEconomicoNovoLimpoDelegate();
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
	 * Cria instancia de RegistroRelacionamentoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see RegistroRelacionamentoDelegate
	 */
	public RegistroRelacionamentoDelegate criarRegistroRelacionamentoDelegate() {
		return new RegistroRelacionamentoDelegate();
	}

	/**
	 * Cria instancia de OcupacaoProfissionalDelegate.
	 *
	 * @return o delegate solicitado
	 * @see OcupacaoProfissionalDelegate
	 */
	public OcupacaoProfissionalDelegate criarOcupacaoProfissionalDelegate() {
		return new OcupacaoProfissionalDelegate();
	}

	/**
	 * Cria instancia de LogCompartilhamentoCadastroDelegate.
	 *
	 * @return o delegate solicitado
	 * @see LogCompartilhamentoCadastroDelegate
	 */
	public LogCompartilhamentoCadastroDelegate criarLogCompartilhamentoCadastroDelegate() {
		return new LogCompartilhamentoCadastroDelegate();
	}

	/**
	 * Cria instancia de EnderecoCorrespondenciaDelegate.
	 *
	 * @return o delegate solicitado
	 * @see EnderecoCorrespondenciaDelegate
	 */
	public EnderecoCorrespondenciaDelegate criarEnderecoCorrespondenciaDelegate() {
		return new EnderecoCorrespondenciaDelegate();
	}

	/**
	 * Cria instancia de CategoriaProdutorDelegate.
	 *
	 * @return o delegate solicitado
	 * @see CategoriaProdutorDelegate
	 */
	public CategoriaProdutorDelegate criarCategoriaProdutorDelegate() {
		return new CategoriaProdutorDelegate();
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
	 * Cria instancia de ProdutorDelegate.
	 *
	 * @return o delegate solicitado
	 * @see ProdutorDelegate
	 */
	public ProdutorDelegate criarProdutorDelegate() {
		return new ProdutorDelegate();
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
	 * Cria instancia de PessoaCompartilhamentoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see PessoaCompartilhamentoDelegate
	 */
	public PessoaCompartilhamentoDelegate criarPessoaCompartilhamentoDelegate() {
		return new PessoaCompartilhamentoDelegate();
	}

	/**
	 * Cria instancia de GrupoCompartilhamentoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see GrupoCompartilhamentoDelegate
	 */
	public GrupoCompartilhamentoDelegate criarGrupoCompartilhamentoDelegate() {
		return new GrupoCompartilhamentoDelegate();
	}

	/**
	 * Cria instancia de PessoaIntegracaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see PessoaIntegracaoDelegate
	 */
	public PessoaIntegracaoDelegate criarPessoaIntegracaoDelegate() {
		return new PessoaIntegracaoDelegate();
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
	 * Cria instancia de FichaCadastralDelegate.
	 *
	 * @return o delegate solicitado
	 * @see FichaCadastralDelegate
	 */
	public FichaCadastralDelegate criarFichaCadastralDelegate() {
		return new FichaCadastralDelegate();
	}

	/**
	 * Cria instancia de CompartilhamentoCadastroDelegate.
	 *
	 * @return o delegate solicitado
	 * @see CompartilhamentoCadastroDelegate
	 */
	public CompartilhamentoCadastroDelegate criarCompartilhamentoCadastroDelegate() {
		return new CompartilhamentoCadastroDelegate();
	}

	/**
	 * Cria instancia de NacionalidadeDelegate.
	 *
	 * @return o delegate solicitado
	 * @see NacionalidadeDelegate
	 */
	public NacionalidadeDelegate criarNacionalidadeDelegate() {
		return new NacionalidadeDelegate();
	}

	/**
	 * Cria instancia de FuncaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see FuncaoDelegate
	 */
	public FuncaoDelegate criarFuncaoDelegate() {
		return new FuncaoDelegate();
	}

	/**
	 * Cria instancia de GrupoEconomicoPessoaDelegate.
	 *
	 * @return o delegate solicitado
	 * @see GrupoEconomicoPessoaDelegate
	 */
	public GrupoEconomicoPessoaDelegate criarGrupoEconomicoPessoaDelegate() {
		return new GrupoEconomicoPessoaDelegate();
	}

	/**
	 * Cria instancia de MensagemReplicacaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see MensagemReplicacaoDelegate
	 */
	public MensagemReplicacaoDelegate criarMensagemReplicacaoDelegate() {
		return new MensagemReplicacaoDelegate();
	}

	/**
	 * Cria instancia de AutorizacaoDocumentoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see AutorizacaoDocumentoDelegate
	 */
	public AutorizacaoDocumentoDelegate criarAutorizacaoDocumentoDelegate() {
		return new AutorizacaoDocumentoDelegate();
	}

	/**
	 * Cria instancia de TipoAfastamentoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoAfastamentoDelegate
	 */
	public TipoAfastamentoDelegate criarTipoAfastamentoDelegate() {
		return new TipoAfastamentoDelegate();
	}

	/**
	 * Cria instancia de TipoFuncionarioDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoFuncionarioDelegate
	 */
	public TipoFuncionarioDelegate criarTipoFuncionarioDelegate() {
		return new TipoFuncionarioDelegate();
	}

	/**
	 * Cria instancia de ConselhoRegionalDelegate.
	 *
	 * @return o delegate solicitado
	 * @see ConselhoRegionalDelegate
	 */
	public ConselhoRegionalDelegate criarConselhoRegionalDelegate() {
		return new ConselhoRegionalDelegate();
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
	 * Cria instancia de DadosConfiguracaoFluxoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see DadosConfiguracaoFluxoDelegate
	 */
	public DadosConfiguracaoFluxoDelegate criarDadosConfiguracaoFluxoDelegate() {
		return new DadosConfiguracaoFluxoDelegate();
	}

	/**
	 * Cria instancia de FinalidadeEmpreendimentoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see FinalidadeEmpreendimentoDelegate
	 */
	public FinalidadeEmpreendimentoDelegate criarFinalidadeEmpreendimentoDelegate() {
		return new FinalidadeEmpreendimentoDelegate();
	}

	/**
	 * Cria instancia de CnaeFiscalDelegate.
	 *
	 * @return o delegate solicitado
	 * @see CnaeFiscalDelegate
	 */
	public CnaeFiscalDelegate criarCnaeFiscalDelegate(){
		return new CnaeFiscalDelegate();
	}

	/**
	 * Cria instancia de AlterarDocumentoPessoaDelegate.
	 *
	 * @return o delegate solicitado
	 * @see AlterarDocumentoPessoaDelegate
	 */
	public AlterarDocumentoPessoaDelegate criarAlterarDocumentoPessoaDelegate() {
		return new AlterarDocumentoPessoaDelegate();
	}

	/**
	 * Cria instancia de AutorizarDelegate.
	 *
	 * @return o delegate solicitado
	 * @see AutorizarDelegate
	 */
	public AutorizarDelegate criarAutorizarDelegate() {
		return new AutorizarDelegate();
	}

	/**
	 * Cria instancia de DetalharAnotacaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see DetalharAnotacaoDelegate
	 */
	public DetalharAnotacaoDelegate criarDetalharAnotacaoDelegate() {
		return new DetalharAnotacaoDelegate();
	}

	/**
	 * Cria instancia de MonitoracaoFilasCapesDelegate.
	 *
	 * @return o delegate solicitado
	 * @see MonitoracaoFilasCapesDelegate
	 */
	public MonitoracaoFilasCapesDelegate criarMonitoracaoFilasCapesDelegate() {
		return new MonitoracaoFilasCapesDelegate();
	}

	/**
	 * Cria instancia de RelacionamentoInstituicaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see RelacionamentoInstituicaoDelegate
	 */
	public RelacionamentoInstituicaoDelegate criarRelacionamentoInstituicaoDelegate() {
		return new RelacionamentoInstituicaoDelegate();
	}

	/**
	 * Cria instancia de SolicitacaoTransferenciaInformacoesDelegate.
	 *
	 * @return o delegate solicitado
	 * @see SolicitacaoTransferenciaInformacoesDelegate
	 */
	public SolicitacaoTransferenciaInformacoesDelegate criarSolicitacaoTransferenciaInformacoesDelegate() {
		return new SolicitacaoTransferenciaInformacoesDelegate();
	}

	/**
	 * Cria instancia de MapaTipoAnotacaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see MapaTipoAnotacaoDelegate
	 */
	public MapaTipoAnotacaoDelegate criarMapaTipoAnotacaoDelegate(){
		return new MapaTipoAnotacaoDelegate();
	}

	/**
	 * Cria instancia de TipoConsultaOrigemDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoConsultaOrigemDelegate
	 */
	public TipoConsultaOrigemDelegate criarTipoConsultaOrigemDelegate() {
		return new TipoConsultaOrigemDelegate();
	}
	
	/**
	 * Cria instancia de TipoObservacaoAnotacaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoObservacaoAnotacaoDelegate
	 */
	public TipoObservacaoAnotacaoDelegate criarTipoObservacaoAnotacaoDelegate() {
		return new TipoObservacaoAnotacaoDelegate();
	}
	
	/**
	 * Cria instancia de TipoFormaConstituicaoEsferaAdministrativaDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoFormaConstituicaoEsferaAdministrativaDelegate
	 */
	public TipoFormaConstituicaoEsferaAdministrativaDelegate criarTipoFormaConstituicaoEsferaAdministrativaDelegate() {
		return new TipoFormaConstituicaoEsferaAdministrativaDelegate();
	}
	
	/**
	 * Cria instancia de ValidacaoCadastralRegraDelegate.
	 *
	 * @return o delegate solicitado
	 * @see ValidacaoCadastralRegraDelegate
	 */
	public ValidacaoCadastralRegraDelegate criarValidacaoCadastralRegraDelegate() {
		return new ValidacaoCadastralRegraDelegate();
	}

	/**
	 * Cria instancia de ValidacaoCadastralDelegate.
	 *
	 * @return o delegate solicitado
	 * @see ValidacaoCadastralDelegate
	 */
	public ValidacaoCadastralDelegate criarValidacaoCadastralDelegate() {
		return new ValidacaoCadastralDelegate();
	}

	/**
	 * Cria instancia de ValidacaoCadastralErroDelegate.
	 *
	 * @return o delegate solicitado
	 * @see ValidacaoCadastralErroDelegate
	 */
	public ValidacaoCadastralErroDelegate criarValidacaoCadastralErroDelegate() {
		return new ValidacaoCadastralErroDelegate();
	}
	
	/**
	 * Cria instancia de CidadaniaDelegate.
	 *
	 * @return o delegate solicitado
	 * @see CidadaniaDelegate
	 */
	public CidadaniaDelegate criarCidadaniaDelegate() {
		return new CidadaniaDelegate();
	}
	
	/**
	 * Cria instancia de EnderecoFiscalDelegate.
	 *
	 * @return o delegate solicitado
	 * @see EnderecoFiscalDelegate
	 */
	public EnderecoFiscalDelegate criarEnderecoFiscalDelegate() {
		return new EnderecoFiscalDelegate();
	}

	/**
	 * Cria instancia de TipoRegraValidacaoCadastralDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoRegraValidacaoCadastralDelegate
	 */
	public TipoRegraValidacaoCadastralDelegate criarTipoRegraValidacaoCadastralDelegate() {
	    return new TipoRegraValidacaoCadastralDelegate();
    }
	
	/**
	 * Cria instancia de CentraisSingularesDelegate.
	 *
	 * @return o delegate solicitado
	 * @see CentraisSingularesDelegate
	 */
	public CentraisSingularesDelegate criarCentraisSingularesDelegate() {
		return new CentraisSingularesDelegate();
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
	 * Cria instancia de DestinoExportacaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see DestinoExportacaoDelegate
	 */
	public DestinoExportacaoDelegate criarDestinoExportacaoDelegate() {
		return new DestinoExportacaoDelegate();
	}
	
	/**
	 * Cria instancia de TipoInformacaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TipoInformacaoDelegate
	 */
	public TipoInformacaoDelegate criarTipoInformacaoDelegate() {
		return new TipoInformacaoDelegate();
	}

	/**
	 * Cria instancia de ExportacaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see ExportacaoDelegate
	 */
	public ExportacaoDelegate criarExportacaoDelegate() {
		return new ExportacaoDelegate();
	}
	
	/**
	 * Cria instancia de DadosArquivoExportacaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see DadosArquivoExportacaoDelegate
	 */
	public DadosArquivoExportacaoDelegate criarDadosArquivoExportacaoDelegate() {
		return new DadosArquivoExportacaoDelegate();
	}
	
	/**
	 * Cria instancia de VisualizarExportacaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see VisualizarExportacaoDelegate
	 */
	public VisualizarExportacaoDelegate criarVisualizarExportacaoDelegate() {
		return new VisualizarExportacaoDelegate();
	}

	/**
	 * Cria instancia de ObservacaoAnotacaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see ObservacaoAnotacaoDelegate
	 */
	public ObservacaoAnotacaoDelegate criarObservacaoAnotacaoDelegate() {
		return new ObservacaoAnotacaoDelegate();
	}

	/**
	 * Cria instancia de AtividadeEconomicaDelegate.
	 *
	 * @return o delegate solicitado
	 * @see AtividadeEconomicaDelegate
	 */
	public AtividadeEconomicaDelegate criarAtividadeEconomicaDelegate() {
		return new AtividadeEconomicaDelegate();
	}
	
	public TipoBeneficiarioSicorDelegate criarTipoBeneficiarioSicorDelegate() {
		return new TipoBeneficiarioSicorDelegate();
	}
	
	public BemPessoaCompartilhamentoDelegate criarBemPessoaCompartilhamentoDelegate() {
		return new BemPessoaCompartilhamentoDelegate();
	}

	public TipoClassificacaoBemDelegate criarTipoClassificacaoBemDelegate() {
		return new TipoClassificacaoBemDelegate();
	}

	public TipoBemImovelDelegate criarTipoBemImovelDelegate() {
		return new TipoBemImovelDelegate();
	}

	public TipoBemMovelDelegate criarTipoBemMovelDelegate() {
		return new TipoBemMovelDelegate();
	}

	public TipoLocalizacaoBemDelegate criarTipoLocalizacaoBemDelegate() {
		return new TipoLocalizacaoBemDelegate();
	}

	public TipoUsoBemDelegate criarTipoUsoBemDelegate() {
		return new TipoUsoBemDelegate();
	}
	
	public TipoEstadoConservacaoBemDelegate criarTipoEstadoConservacaoDelegate() {
		return new TipoEstadoConservacaoBemDelegate();
	}

	public TipoImplantacaoBemImovelDelegate criarTipoImplantacaoBemImovelDelegate() {
		return new TipoImplantacaoBemImovelDelegate();
	}

	public TipoPadraoAcabamentoBemImovelDelegate criarTipoPadraoAcabamentoBemImovelDelegate() {
		return new TipoPadraoAcabamentoBemImovelDelegate();
	}
	
	public TipoServicoCondominialBemImovelDelegate criarTipoServicoCondominialBemImovelDelegate() {
		return new TipoServicoCondominialBemImovelDelegate();
	}
	
	public TipoRelacionamentoBemImovelDelegate criarTipoRelacionamentoBemImovelDelegate() {
		return new TipoRelacionamentoBemImovelDelegate();
	}
	
	public TipoOnusBemDelegate criarTipoOnusBemDelegate() {
		return new TipoOnusBemDelegate();
	}
	
	public TipoChassiBemDelegate criarTipoChassiDelegate() {
		return new TipoChassiBemDelegate();
	}

	public TipoCorAutomovelBemDelegate criarTipoCorAutomovelDelegate() {
		return new TipoCorAutomovelBemDelegate();
	}

	public BemDelegate criarBemDelegate() {
		return new BemDelegate();
	}

	public GrupoTipoAnotacaoDelegate criarGrupoTipoAnotacaoDelegate() {
		return new GrupoTipoAnotacaoDelegate();
	}

	public FichaCadastralNovaDelegate criarFichaCadastralNovaDelegate() {
		return new FichaCadastralNovaDelegate();
	}
	
	/**
	 * Cria instancia de ParametroDelegate.
	 *
	 * @return o delegate solicitado
	 * @see ParametroDelegate
	 */
	public ParametroDelegate criarParametroDelegate() {
		return new ParametroDelegate();
	}
	
}