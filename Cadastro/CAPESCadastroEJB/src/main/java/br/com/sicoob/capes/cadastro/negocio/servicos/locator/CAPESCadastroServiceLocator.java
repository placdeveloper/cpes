/*
 * SICOOB
 * 
 * CAPESCadastroServiceLocator.java(br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator)
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.locator;

import br.com.bancoob.negocio.servicos.locator.BancoobServiceLocator;
import br.com.sicoob.capes.cadastro.negocio.servicos.*;
import br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo.BemAntigoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo.SituacaoBemAntigoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo.SubTipoBemAntigoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo.TipoBemAntigoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo.TipoPosseBemAntigoServico;

/**
 * Service Locator usado pelo sistema CadastroUnicoClientesComum.
 * 
 * @author Stefanini IT Solutions
 */
public final class CAPESCadastroServiceLocator extends BancoobServiceLocator {

	/** O atributo locator. */
	private static CAPESCadastroServiceLocator locator;

	/**
	 * Singleton da class
	 * 
	 * @return A instancia da classe
	 */
	public static CAPESCadastroServiceLocator getInstance() {
		if (locator == null) {
			synchronized (CAPESCadastroServiceLocator.class) {
				if (locator == null) {
					locator = new CAPESCadastroServiceLocator();
				}
			}
		}

		return locator;
	}

	/**
	 * @param nomeAplicacao
	 */
	private CAPESCadastroServiceLocator() {
		super("capes.cadastro");
	}

	/**
	 * Localiza o servico de anotacoes.
	 * 
	 * @return o servico de anotacoes a ser retornado.
	 */
	public AnotacaoServico localizarAnotacaoServico() {
		return (AnotacaoServico) localizar("locator.capes.AnotacaoServico");
	}

	/**
	 * Localiza o servico de origem da informacaoo (Fonte).
	 * 
	 * @return o servico de origem da informacaoo (Fonte).
	 */
	public OrigemInformacaoServico localizarOrigemInformacaoServico() {
		return (OrigemInformacaoServico) localizar("locator.capes.OrigemInformacaoServico");
	}

	/**
	 * Localiza o servico de pessoas.
	 * 
	 * @return o servico de pessoas a ser retornado.
	 */
	public PessoaServico localizarPessoaServico() {
		return (PessoaServico) localizar("locator.capes.PessoaServico");
	}
	
	/**
	 * Localiza o servico de tipos de anotacao
	 * 
	 * @return o servico de tipos de anotacao.
	 */
	public TipoAnotacaoServico localizarTipoAnotacaoServico() {
		return (TipoAnotacaoServico) localizar("locator.capes.TipoAnotacaoServico");
	}

	/**
	 * Localiza o servico de mapas tipos de anotacao
	 * 
	 * @return o servico de tipos de anotacao.
	 */
	public MapaTipoAnotacaoServico localizarMapaTipoAnotacaoServico() {
		return (MapaTipoAnotacaoServico) localizar("locator.capes.MapaTipoAnotacaoServico");
	}

	/**
	 * Localiza o servico de tipos de e-mail
	 * 
	 * @return o servico de tipos de e-mail
	 */
	public TipoEmailServico localizarTipoEmailServico() {
		return (TipoEmailServico) localizar("locator.capes.TipoEmailServico");
	}

	/**
	 * Localiza o servico de tipos de endereco
	 * 
	 * @return o servico de tipos de endereco
	 */
	public TipoEnderecoServico localizarTipoEnderecoServico() {
		return (TipoEnderecoServico) localizar("locator.capes.TipoEnderecoServico");
	}

	/**
	 * Localiza o servico de tipos de telefone
	 * 
	 * @return o servico de tipos de telefone
	 */
	public TipoTelefoneServico localizarTipoTelefoneServico() {
		return (TipoTelefoneServico) localizar("locator.capes.TipoTelefoneServico");
	}

	/**
	 * Localiza o servico de tipos de captura.
	 * 
	 * @return o servico de tipos de captura.
	 */
	public TipoCapturaServico localizarTipoCapturaServico() {
		return (TipoCapturaServico) localizar("locator.capes.TipoCapturaServico");
	}

	/**
	 * Localiza o servico de periodicidades de anotacao.
	 * 
	 * @return o servico de periodicidades de anotacao.
	 */
	public PeriodicidadeAnotacaoServico localizarPeriodicidadeAnotacaoServico() {
		return (PeriodicidadeAnotacaoServico) localizar("locator.capes.PeriodicidadeAnotacaoServico");
	}

	/**
	 * Localiza o servico de transicao pessoa.
	 * 
	 * @return o servico de transicao pessoa.
	 */
	public TransicaoPessoaServico localizarTransicaoPessoaServico() {
		return (TransicaoPessoaServico) localizar("locator.capes.TransicaoPessoaServico");
	}

	/**
	 * Localiza o servico de categoria da anotacao.
	 * 
	 * @return o servico de categoria da anotacao.
	 */
	public CategoriaAnotacaoServico localizarCategoriaAnotacaoServico() {
		return (CategoriaAnotacaoServico) localizar("locator.capes.CategoriaAnotacaoServico");
	}

	/**
	 * Localiza o servico de autorizacao.
	 * 
	 * @return o servico de autorizacao.
	 */
	public AutorizacaoServico localizarAutorizacaoServico() {
		return (AutorizacaoServico) localizar("locator.capes.AutorizacaoServico");
	}

	/**
	 * Localiza o servico da referencia.
	 * 
	 * @return o servico da referencia.
	 */
	public ReferenciaServico localizarReferenciaServico() {
		return (ReferenciaServico) localizar("locator.capes.ReferenciaServico");
	}

	/**
	 * Localiza o servico da TipoReferenciaServico.
	 * 
	 * @return o servico da TipoReferenciaServico.
	 */
	public TipoReferenciaServico localizarTipoReferenciaServico() {
		return (TipoReferenciaServico) localizar("locator.capes.TipoReferenciaServico");
	}

	/**
	 * Localiza o servico da TipoReferenciaServico.
	 * 
	 * @return o servico da TipoReferenciaServico.
	 */
	public ResponsavelCadastroServico localizarResponsavelCadastroServico() {
		return (ResponsavelCadastroServico) localizar("locator.capes.ResponsavelCadastroServico");
	}

	/**
	 * Localiza o servico de fonte de renda.
	 * 
	 * @return o servico de fonte de renda.
	 */
	public FonteRendaServico localizarFonteRendaServico() {
		return (FonteRendaServico) localizar("locator.capes.FonteRendaServico");
	}

	/**
	 * Localiza o servico de tipos de fonte de renda.
	 * 
	 * @return o servico de tipos de fonte de renda.
	 */
	public TipoFonteRendaServico localizarTipoFonteRendaServico() {
		return (TipoFonteRendaServico) localizar("locator.capes.TipoFonteRendaServico");
	}

	/**
	 * Localiza o servico de autorizacao.
	 * 
	 * @return o servico de autorizacao.
	 */
	public AutorizacaoCadastroServico localizarAutorizacaoCadastroServico() {
		return (AutorizacaoCadastroServico) localizar("locator.capes.AutorizacaoCadastroServico");
	}

	/**
	 * Localiza o servico de historico de alteracao do cpf/cnpj.
	 * 
	 * @return o servico de historico de alteracao do cpf/cnpj.
	 */
	public HistoricoAlteracaoCpfCnpjServico localizarHistoricoAlteracaoCpfCnpjServico() {
		return (HistoricoAlteracaoCpfCnpjServico) localizar("locator.capes.HistoricoAlteracaoCpfCnpjServico");
	}

	/**
	 * Localiza o servico de BemImovelServico
	 * 
	 * @return o servico de BemImovelServico
	 */
	public br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo.BemImovelAntigoServico localizarBemImovelAntigoServico() {
		return (br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo.BemImovelAntigoServico) localizar("locator.capes.BemImovelAntigoServico");
	}

	/**
	 * Localiza o servico de unidade de medida.
	 * 
	 * @return o servico de unidade de medida.
	 */
	public UnidadeMedidaServico localizarUnidadeMedidaServico() {
		return (UnidadeMedidaServico) localizar("locator.capes.UnidadeMedidaServico");
	}

	/**
	 * Localiza o servico de tipos de bens.
	 * 
	 * @return o servico de tipos de bens.
	 */
	public TipoBemAntigoServico localizarTipoBemAntigoServico() {
		return (TipoBemAntigoServico) localizar("locator.capes.TipoBemAntigoServico");
	}

	/**
	 * Localiza o servico de situacao de bens.
	 * 
	 * @return o servico de situacao de bens.
	 */
	public SituacaoBemAntigoServico localizarSituacaoBemAntigoServico() {
		return (SituacaoBemAntigoServico) localizar("locator.capes.SituacaoBemAntigoServico");
	}

	/**
	 * Localiza o servico de situacao de bens.
	 * 
	 * @return o servico de situacao de bens.
	 */
	public BemAntigoServico localizarBemAntigoServico() {
		return (BemAntigoServico) localizar("locator.capes.BemAntigoServico");
	}

	/**
	 * Localiza o servico de subtipo de bens.
	 * 
	 * @return o servico de subtipo de bens.
	 */
	public SubTipoBemAntigoServico localizarSubTipoBemAntigoServico() {
		return (SubTipoBemAntigoServico) localizar("locator.capes.SubTipoBemAntigoServico");
	}

	/**
	 * Localiza o servico de subtipo de bens.
	 * 
	 * @return o servico de subtipo de bens.
	 */
	public TipoPosseBemAntigoServico localizarTipoPosseBemAntigoServico() {
		return (TipoPosseBemAntigoServico) localizar("locator.capes.TipoPosseBemAntigoServico");
	}

	/**
	 * Localiza o servico de email.
	 * 
	 * @return o servico de email.
	 */
	public EmailServico localizarEmailServico() {
		return (EmailServico) localizar("locator.capes.EmailServico");
	}

	/**
	 * Localiza o servico de telefone.
	 * 
	 * @return o servico de telefone.
	 */
	public TelefoneServico localizarTelefoneServico() {
		return (TelefoneServico) localizar("locator.capes.TelefoneServico");
	}

	/**
	 * Localiza o servico de TipoPessoa.
	 * 
	 * @return o servico de TipoPessoa.
	 */
	public TipoPessoaServico localizarTipoPessoaServico() {
		return (TipoPessoaServico) localizar("locator.capes.TipoPessoaServico");
	}
	
	/**
	 * Localiza o servico de TipoPessoaContato.
	 * 
	 * @return o servico de TipoPessoaContato.
	 */
	public TipoPessoaContatoServico localizarTipoPessoaContatoServico() {
		return (TipoPessoaContatoServico) localizar("locator.capes.TipoPessoaContatoServico");
	}
	
	

	/**
	 * Localiza o servico de TipoPessoa.
	 * 
	 * @return o servico de TipoPessoa.
	 */
	public OrgaoEmissorCertidaoServico localizarOrgaoEmissorCertidaoServico() {
		return (OrgaoEmissorCertidaoServico) localizar("locator.capes.OrgaoEmissorCertidaoServico");
	}

	/**
	 * Localiza o servico de SubTipoCertidao.
	 * 
	 * @return o servico de SubTipoCertidao.
	 */
	public SubTipoCertidaoServico localizarSubTipoCertidaoServico() {
		return (SubTipoCertidaoServico) localizar("locator.capes.SubTipoCertidaoServico");
	}

	/**
	 * Localiza o servico de TipoAbrangenciaCertidao.
	 * 
	 * @return o servico de TipoAbrangenciaCertidao.
	 */
	public TipoAbrangenciaCertidaoServico localizarTipoAbrangenciaCertidaoServico() {
		return (TipoAbrangenciaCertidaoServico) localizar("locator.capes.TipoAbrangenciaCertidaoServico");
	}

	/**
	 * Localiza o servico de TipoCertidao.
	 * 
	 * @return o servico de TipoCertidao.
	 */
	public TipoCertidaoServico localizarTipoCertidaoServico() {
		return (TipoCertidaoServico) localizar("locator.capes.TipoCertidaoServico");
	}

	/**
	 * Localiza o servico de TipoObjetoCertidao.
	 * 
	 * @return o servico de TipoObjetoCertidao.
	 */
	public TipoObjetoCertidaoServico localizarTipoObjetoCertidaoServico() {
		return (TipoObjetoCertidaoServico) localizar("locator.capes.TipoObjetoCertidaoServico");
	}

	/**
	 * Localiza o servico de TipoPrazoCertidao.
	 * 
	 * @return o servico de TipoPrazoCertidao.
	 */
	public TipoPrazoCertidaoServico localizarTipoPrazoCertidaoServico() {
		return (TipoPrazoCertidaoServico) localizar("locator.capes.TipoPrazoCertidaoServico");
	}

	/**
	 * Localiza o servico de MensagemServico.
	 * 
	 * @return o servico de MensagemServico.
	 */
	public MensagemServico localizarMensagemServico() {
		return (MensagemServico) localizar("locator.capes.MensagemServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code TipoMensagemServico}.
	 *
	 * @return O EJB solicitado
	 * @see TipoMensagemServico
	 */
	public TipoMensagemServico localizarTipoMensagemServico(){
		return (TipoMensagemServico) localizar("locator.capes.TipoMensagemServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code TipoDestinoExibicaoServico}.
	 *
	 * @return O EJB solicitado
	 * @see TipoDestinoExibicaoServico
	 */
	public TipoDestinoExibicaoServico localizarTipoDestinoExibicaoServico(){
		return (TipoDestinoExibicaoServico) localizar("locator.capes.TipoDestinoExibicaoServico");
	}

	/**
	 * Localiza o servico de MensagemServico.
	 * 
	 * @return o servico de MensagemServico.
	 */
	public CertidaoServico localizarCertidaoServico() {
		return (CertidaoServico) localizar("locator.capes.CertidaoServico");
	}

	/**
	 * Localiza o servico de {@link TipoRelacionamentoPessoaServico}
	 * 
	 * @return o servico
	 */
	public TipoRelacionamentoPessoaServico localizarTipoRelacionamentoPessoaServico() {
		return (TipoRelacionamentoPessoaServico) localizar("locator.capes.TipoRelacionamentoPessoaServico");
	}

	/**
	 * Localiza o EJB que implementa RelacionamentoPessoaServico.
	 * 
	 * @return the relacionamento pessoa servico
	 */
	public RelacionamentoPessoaServico localizarRelacionamentoPessoaServico() {
		return (RelacionamentoPessoaServico) localizar("locator.capes.RelacionamentoPessoaServico");
	}

	/**
	 * Localiza o EJB que implementa TributacaoServico.
	 * 
	 * @return the tributacao servico
	 */
	public TributacaoServico localizarTributacaoServico() {
		return (TributacaoServico) localizar("locator.capes.TributacaoServico");
	}

	/**
	 * Localiza o EJB que implementa TipoPoderRelacionamentoServico.
	 * 
	 * @return the tipo poder relacionamento servico
	 */
	public TipoPoderRelacionamentoServico localizarTipoPoderRelacionamentoServico() {
		return (TipoPoderRelacionamentoServico) localizar("locator.capes.TipoPoderRelacionamentoServico");
	}
	
	/**
	 * Localiza o servico de PerfilCadastroServico.
	 * 
	 * @return o servico de PerfilCadastroServico.
	 */
	public PerfilCadastroServico localizarPerfilCadastroServico() {
		return (PerfilCadastroServico) localizar("locator.capes.PerfilCadastroServico");
	}

	/**
	 * Localiza o EJB que implementa GrauInstrucaoServico.
	 * 
	 * @return the grau instrucao servico
	 */
	public GrauInstrucaoServico localizarGrauInstrucaoServico() {
		return (GrauInstrucaoServico) localizar("locator.capes.GrauInstrucaoServico");
	}

	/**
	 * Localiza o EJB que implementa TipoDocumentoIdentificacaoServico.
	 * 
	 * @return the tipo documento identificacao servico
	 */
	public TipoDocumentoIdentificacaoServico localizarTipoDocumentoIdentificacaoServico() {
		return (TipoDocumentoIdentificacaoServico) localizar("locator.capes.TipoDocumentoIdentificacaoServico");
	}

	/**
	 * Localiza o EJB que implementa VinculoEmpregaticioServico.
	 * 
	 * @return the vinculo empregaticio servico
	 */
	public VinculoEmpregaticioServico localizarVinculoEmpregaticioServico() {
		return (VinculoEmpregaticioServico) localizar("locator.capes.VinculoEmpregaticioServico");
	}

	/**
	 * Localiza o EJB que implementa EstadoCivilServico.
	 * 
	 * @return the estado civil servico
	 */
	public EstadoCivilServico localizarEstadoCivilServico() {
		return (EstadoCivilServico) localizar("locator.capes.EstadoCivilServico");
	}
	
	/**
	 * Localiza o EJB que implementa RegimeCasamentoServico.
	 * 
	 * @return the regime casamento servico
	 */
	public RegimeCasamentoServico localizarRegimeCasamentoServico() {
		return (RegimeCasamentoServico) localizar("locator.capes.RegimeCasamentoServico");
	}

	/**
	 * Localiza o EJB que implementa PessoaInstituicaoServico.
	 * 
	 * @return the pessoa instituicao servico
	 */
	public PessoaInstituicaoServico localizarPessoaInstituicaoServico() {
		return (PessoaInstituicaoServico) localizar("locator.capes.PessoaInstituicaoServico");
	}

	/**
	 * Localiza o EJB que implementa NucleoServico.
	 * 
	 * @return the nucleo servico
	 */
	public NucleoServico localizarNucleoServico() {
		return (NucleoServico) localizar("locator.capes.NucleoServico");
	}

	/**
	 * Localiza o EJB que implementa FuncionarioServico.
	 * 
	 * @return the funcionario servico
	 */
	public FuncionarioServico localizarFuncionarioServico() {
		return (FuncionarioServico) localizar("locator.capes.FuncionarioServico");
	}

	/**
	 * Localiza o EJB que implementa PerfilTarifarioServico.
	 * 
	 * @return the perfil tarifario servico
	 */
	public PerfilTarifarioServico localizarPerfilTarifarioServico() {
		return (PerfilTarifarioServico) localizar("locator.capes.PerfilTarifarioServico");
	}

	/**
	 * Localiza o EJB que implementa GrupoEconomicoServico.
	 * 
	 * @return the grupo economico servico
	 */
	public GrupoEconomicoServico localizarGrupoEconomicoServico() {
		return (GrupoEconomicoServico) localizar("locator.capes.GrupoEconomicoServico");
	}
	
	/**
	 * Localiza o EJB que implementa GrupoEconomicoNovoServico.
	 * 
	 * @return the grupo economico servico
	 */
	public GrupoEconomicoNovoServico localizarGrupoEconomicoNovoServico() {
		return (GrupoEconomicoNovoServico) localizar("locator.capes.GrupoEconomicoNovoServico");
	}
	
	/**
	 * Localiza o EJB que implementa GrupoEconomicoNovoServico.
	 * 
	 * @return the grupo economico servico
	 */
	public GrupoEconomicoNovoLimpoServico localizarGrupoEconomicoNovoLimpoServico() {
		return (GrupoEconomicoNovoLimpoServico) localizar("locator.capes.GrupoEconomicoNovoLimpoServico");
	}

	/**
	 * Localiza o EJB que implementa TipoFormaConstituicaoServico.
	 * 
	 * @return the tipo forma constituicao servico
	 */
	public TipoFormaConstituicaoServico localizarTipoFormaConstituicaoServico() {
		return (TipoFormaConstituicaoServico) localizar("locator.capes.TipoFormaConstituicaoServico");
	}

	/**
	 * Localiza o EJB que implementa TipoEmpresaServico.
	 * 
	 * @return the tipo empresa servico
	 */
	public TipoEmpresaServico localizarTipoEmpresaServico() {
		return (TipoEmpresaServico) localizar("locator.capes.TipoEmpresaServico");
	}

	/**
	 * Localiza o EJB que implementa EnderecoServico.
	 * 
	 * @return the endereco servico
	 */
	public EnderecoServico localizarEnderecoServico() {
		return (EnderecoServico) localizar("locator.capes.EnderecoServico");
	}

	/**
	 * Localiza o EJB que implementa RegistroRelacionamentoServico.
	 * 
	 * @return the registro relacionamento servico
	 */
	public RegistroRelacionamentoServico localizarRegistroRelacionamentoServico() {
		return (RegistroRelacionamentoServico) localizar("locator.capes.RegistroRelacionamentoServico");
	}

	/**
	 * Localiza o EJB que implementa OcupacaoProfissionalServico.
	 * 
	 * @return the ocupacao profissional servico
	 */
	public OcupacaoProfissionalServico localizarOcupacaoProfissionalServico() {
		return (OcupacaoProfissionalServico) localizar("locator.capes.OcupacaoProfissionalServico");
	}

	/**
	 * Localiza o EJB que implementa LogCompartilhamentoCadastroServico.
	 * 
	 * @return the log compartilhamento cadastro servico
	 */
	public LogCompartilhamentoCadastroServico localizarLogCompartilhamentoCadastroServico() {
		return (LogCompartilhamentoCadastroServico) localizar("locator.capes.LogCompartilhamentoCadastroServico");
	}

	/**
	 * Localiza o EJB que implementa EnderecoCorrespondenciaServico.
	 * 
	 * @return the endereco correspondencia servico
	 */
	public EnderecoCorrespondenciaServico localizarEnderecoCorrespondenciaServico() {
		return (EnderecoCorrespondenciaServico) localizar("locator.capes.EnderecoCorrespondenciaServico");
	}

	/**
	 * Localiza o EJB que implementa CategoriaProdutorServico.
	 * 
	 * @return the categoria produtor servico
	 */
	public CategoriaProdutorServico localizarCategoriaProdutorServico() {
		return (CategoriaProdutorServico) localizar("locator.capes.CategoriaProdutorServico");
	}

	/**
	 * Localiza o EJB que implementa ProdutividadeServico.
	 * 
	 * @return the produtividade servico
	 */
	public ProdutividadeServico localizarProdutividadeServico() {
		return (ProdutividadeServico) localizar("locator.capes.ProdutividadeServico");
	}

	/**
	 * Localiza o EJB que implementa ProdutorServico.
	 * 
	 * @return the produtor servico
	 */
	public ProdutorServico localizarProdutorServico() {
		return (ProdutorServico) localizar("locator.capes.ProdutorServico");
	}

	/**
	 * Localiza o EJB que implementa EmpreendimentoServico.
	 * 
	 * @return the empreendimento servico
	 */
	public EmpreendimentoServico localizarEmpreendimentoServico() {
		return (EmpreendimentoServico) localizar("locator.capes.EmpreendimentoServico");
	}

	/**
	 * Localiza o EJB que implementa PessoaCompartilhamentoServico.
	 * 
	 * @return the pessoa compartilhamento servico
	 */
	public PessoaCompartilhamentoServico localizarPessoaCompartilhamentoServico() {
		return (PessoaCompartilhamentoServico) localizar("locator.capes.PessoaCompartilhamentoServico");
	}

	/**
	 * Localiza o EJB que implementa GrupoCompartilhamentoServico.
	 * 
	 * @return the grupo compartilhamento servico
	 */
	public GrupoCompartilhamentoServico localizarGrupoCompartilhamentoServico() {
		return (GrupoCompartilhamentoServico) localizar("locator.capes.GrupoCompartilhamentoServico");
	}

	/**
	 * Localiza o EJB que implementa PessoaIntegracaoServico.
	 * 
	 * @return the pessoa integracao servico
	 */
	public PessoaIntegracaoServico localizarPessoaIntegracaoServico() {
		return (PessoaIntegracaoServico) localizar("locator.capes.PessoaIntegracaoServico");
	}

	/**
	 * Localiza o EJB que implementa ReplicacaoCadastroServico.
	 * 
	 * @return the replicacao cadastro servico
	 */
	public ReplicacaoCadastroServico localizarReplicacaoCadastroServico() {
		return (ReplicacaoCadastroServico) localizar("locator.capes.ReplicacaoCadastroServico");
	}

	/**
	 * Localiza o EJB que implementa FichaCadastralServico.
	 * 
	 * @return the ficha cadastral servico
	 */
	public FichaCadastralServico localizarFichaCadastralServico() {
		return (FichaCadastralServico) localizar("locator.capes.FichaCadastralServico");
	}
	
	/**
	 * Localiza o EJB que implementa FichaCadastralNovaServico.
	 * 
	 * @return the ficha cadastral servico
	 */
	public FichaCadastralNovaServico localizarFichaCadastralNovaServico() {
		return (FichaCadastralNovaServico) localizar("locator.capes.FichaCadastralNovaServico");
	}

	/**
	 * Localiza o servico de CompartilhamentoCadastro.
	 * 
	 * @return o servico de CompartilhamentoCadastro.
	 */
	public CompartilhamentoCadastroServico localizarCompartilhamentoCadastroServico() {
		return (CompartilhamentoCadastroServico) localizar("locator.capes.CompartilhamentoCadastroServico");
	}

	/**
	 * Localiza o EJB que implementa GrupoEconomicoPessoaServico.
	 * 
	 * @return the grupo economico pessoa servico
	 */
	public GrupoEconomicoPessoaServico localizarGrupoEconomicoPessoaServico() {
		return (GrupoEconomicoPessoaServico) localizar("locator.capes.GrupoEconomicoPessoaServico");
	}

	/**
	 * Localiza o EJB que implementa NacionalidadeServico.
	 * 
	 * @return the nacionalidade servico
	 */
	public NacionalidadeServico localizarNacionalidadeServico() {
		return (NacionalidadeServico) localizar("locator.capes.NacionalidadeServico");
	}

	/**
	 * Localiza o EJB que implementa FuncaoServico.
	 * 
	 * @return the funcao servico
	 */
	public FuncaoServico localizarFuncaoServico() {
		return (FuncaoServico) localizar("locator.capes.FuncaoServico");
	}

	/**
	 * Localiza o EJB que implementa MensagemReplicacaoServico.
	 * 
	 * @return the mensagem replicacao servico
	 */
	public MensagemReplicacaoServico localizarMensagemReplicacaoServico() {
		return (MensagemReplicacaoServico) localizar("locator.capes.MensagemReplicacaoServico");
	}

	/**
	 * Localiza o EJB que implementa AutorizacaoDocumentoServico.
	 * 
	 * @return the autorizacao documento servico
	 */
	public AutorizacaoDocumentoServico localizarAutorizacaoDocumentoServico() {
		return (AutorizacaoDocumentoServico) localizar("locator.capes.AutorizacaoDocumentoServico");
	}

	/**
	 * Localiza o EJB que implementa InformacaoProfissionalServico.
	 * 
	 * @return the informacao profissional servico
	 */
	public InformacaoProfissionalServico localizarInformacaoProfissionalServico() {
		return (InformacaoProfissionalServico) localizar("locator.capes.InformacaoProfissionalServico");
	}

	/**
	 * Localiza o EJB que implementa ConselhoRegionalServico.
	 * 
	 * @return the conselho regional servico
	 */
	public ConselhoRegionalServico localizarConselhoRegionalServico() {
		return (ConselhoRegionalServico) localizar("locator.capes.ConselhoRegionalServico");
	}

	/**
	 * Localiza o EJB que implementa TipoFuncionarioServico.
	 * 
	 * @return the tipo funcionario servico
	 */
	public TipoFuncionarioServico localizarTipoFuncionarioServico() {
		return (TipoFuncionarioServico) localizar("locator.capes.TipoFuncionarioServico");
	}

	/**
	 * Localiza o EJB que implementa TipoAfastamentoServico.
	 * 
	 * @return the tipo afastamento servico
	 */
	public TipoAfastamentoServico localizarTipoAfastamentoServico() {
		return (TipoAfastamentoServico) localizar("locator.capes.TipoAfastamentoServico");
	}

	/**
	 * Localiza o EJB que implementa DadosConfiguracaoFluxoServico.
	 * 
	 * @return the dados configuracao fluxo servico
	 */
	public DadosConfiguracaoFluxoServico localizarDadosConfiguracaoFluxoServico() {
		return (DadosConfiguracaoFluxoServico) localizar("locator.capes.DadosConfiguracaoFluxoServico");
	}

	/**
	 * Localiza o EJB que implementa FinalidadeEmpreendimentoServico.
	 * 
	 * @return the finalidade empreendimento servico
	 */
	public FinalidadeEmpreendimentoServico localizarFinalidadeEmpreendimentoServico() {
		return (FinalidadeEmpreendimentoServico) localizar("locator.capes.FinalidadeEmpreendimentoServico");
	}

	/**
	 * Localiza o EJB que implementa CnaeFiscalServico.
	 * 
	 * @return the cnae fiscal servico
	 */
	public CnaeFiscalServico localizarCnaeFiscalServico() {
		return (CnaeFiscalServico) localizar("locator.capes.CnaeFiscalServico");
	}

	/**
	 * Localiza o serviço de alteração de cpf/cnpj.
	 * 
	 * @return o serviço de alteração de cpf/cnpj.
	 */
	public AlterarDocumentoPessoaServico localizarAlterarDocumentoPessoaServico() {
		return (AlterarDocumentoPessoaServico) localizar("locator.capes.AlterarDocumentoPessoaServico");
	}

	/**
	 * Localiza o serviço de autorização.
	 * 
	 * @return o serviço de autorização.
	 */
	public AutorizarServico localizarAutorizarServico() {
		return (AutorizarServico) localizar("locator.capes.AutorizarServico");
	}

	/**
	 * Localiza o serviço de detalhamento de anotações.
	 * 
	 * @return o serviço de detalhamento de anotações.
	 */
	public DetalharAnotacaoServico localizarDetalharAnotacaoServico() {
		return (DetalharAnotacaoServico) localizar("locator.capes.DetalharAnotacaoServico");
	}

	/**
	 * Localiza o EJB que implementa MonitoracaoFilasCapesServico.
	 * 
	 * @return the monitoracao filas capes servico
	 */
	public MonitoracaoFilasCapesServico localizarMonitoracaoFilasCapesServico() {
		return (MonitoracaoFilasCapesServico) localizar("locator.capes.MonitoracaoFilasCapesServico");
	}

	/**
	 * Localiza o serviço de relacionamento instituição.
	 * 
	 * @return o serviço de relacionamento instituição.
	 */
	public RelacionamentoInstituicaoServico localizarRelacionamentoInstituicaoServico() {
		return (RelacionamentoInstituicaoServico) localizar("locator.capes.RelacionamentoInstituicaoServico");
	}

	/**
	 * Localiza o EJB que implementa SolicitacaoTransferenciaInformacoesServico.
	 * 
	 * @return the solicitacao transferencia informacoes servico
	 */
	public SolicitacaoTransferenciaInformacoesServico localizarSolicitacaoTransferenciaInformacoesServico() {
		return (SolicitacaoTransferenciaInformacoesServico) localizar("locator.capes.SolicitacaoTransferenciaInformacoesServico");
	}

	/**
	 * Localiza o EJB que implementa TipoConsultaOrigem.
	 * 
	 * @return the tipo consulta origem servico
	 */
	public TipoConsultaOrigemServico localizarTipoConsultaOrigem() {
		return (TipoConsultaOrigemServico) localizar("locator.capes.TipoConsultaOrigemServico");
	}

	/**
	 * Localiza o EJB que implementa TipoFormaConstituicaoEsferaAdministrativaServico.
	 * 
	 * @return the tipo forma constituicao esfera administrativa servico
	 */
	public TipoFormaConstituicaoEsferaAdministrativaServico localizarTipoFormaConstituicaoEsferaAdministrativaServico() {
		return (TipoFormaConstituicaoEsferaAdministrativaServico) localizar("locator.capes.TipoFormaConstituicaoEsferaAdministrativaServico");
	}

	/**
	 * Localiza o EJB que implementa ValidacaoCadastralErroServico.
	 * 
	 * @return the validacao cadastral erro servico
	 */
	public ValidacaoCadastralErroServico localizarValidacaoCadastralErroServico() {
		return (ValidacaoCadastralErroServico) localizar("locator.capes.ValidacaoCadastralErroServico");
	}

	/**
	 * Localiza o EJB que implementa ValidacaoCadastralRegraServico.
	 * 
	 * @return the validacao cadastral regra servico
	 */
	public ValidacaoCadastralRegraServico localizarValidacaoCadastralRegraServico() {
		return (ValidacaoCadastralRegraServico) localizar("locator.capes.ValidacaoCadastralRegraServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code EnderecoFiscalServico}.
	 *
	 * @return O EJB solicitado
	 * @see EnderecoFiscalServico
	 */
	public EnderecoFiscalServico localizarEnderecoFiscalServico() {
		return (EnderecoFiscalServico) localizar("locator.capes.EnderecoFiscalServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code CidadaniaServico}.
	 *
	 * @return O EJB solicitado
	 * @see CidadaniaServico
	 */
	public CidadaniaServico localizarCidadaniaServico() {
		return (CidadaniaServico) localizar("locator.capes.CidadaniaServico");
	}

	/**
	 * Localiza o EJB que implementa ValidacaoCadastralServico.
	 * 
	 * @return the validacao cadastral servico
	 */
	public ValidacaoCadastralServico localizarValidacaoCadastralServico() {
		return (ValidacaoCadastralServico) localizar("locator.capes.ValidacaoCadastralServico");
	}

	/**
	 * Localiza o EJB que implementa TipoRegraValidacaoCadastralServico.
	 * 
	 * @return the tipo regra validacao cadastral servico
	 */
	public TipoRegraValidacaoCadastralServico localizarTipoRegraValidacaoCadastralServico() {
		return (TipoRegraValidacaoCadastralServico) localizar("locator.capes.TipoRegraValidacaoCadastralServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code CentraisSingularesServico}.
	 *
	 * @return O EJB solicitado
	 * @see CentraisSingularesServico
	 */
	public CentraisSingularesServico localizarCentraisSingularesServico() {
		return (CentraisSingularesServico) localizar("locator.capes.CentraisSingularesServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code InformacaoUtilizadaServico}.
	 *
	 * @return O EJB solicitado
	 * @see InformacaoUtilizadaServico
	 */
	public InformacaoUtilizadaServico localizarInformacaoUtilizadaServico() {
		return (InformacaoUtilizadaServico) localizar("locator.capes.InformacaoUtilizadaServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code ObservacaoAnotacaoServico}.
	 *
	 * @return O EJB solicitado
	 * @see ObservacaoAnotacaoServico
	 */
	public ObservacaoAnotacaoServico localizarObservacaoAnotacaoServico() {
		return (ObservacaoAnotacaoServico) localizar("locator.capes.ObservacaoAnotacaoServico");
	}
	

	/**
	 * Localiza o EJB que implementa a interface {@code DestinoExportacaoServico}.
	 *
	 * @return O EJB solicitado
	 * @see DestinoExportacaoServico
	 */
	public DestinoExportacaoServico localizarDestinoExportacaoServico() {
		return (DestinoExportacaoServico) localizar("locator.capes.DestinoExportacaoServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code TipoInformacaoServico}.
	 *
	 * @return O EJB solicitado
	 * @see TipoInformacaoServico
	 */
	public TipoInformacaoServico localizarTipoInformacaoServico() {
		return (TipoInformacaoServico) localizar("locator.capes.TipoInformacaoServico");
	}

	/**
	 * Realiza o lookup do {@code DadosArquivoExportacaoServico}
	 * 
	 * @since 1.2.1.0
	 * @return O EJB
	 * @see DadosArquivoExportacaoServico
	 */
	public DadosArquivoExportacaoServico localizarDadosArquivoExportacaoServico() {
		return (DadosArquivoExportacaoServico) localizar("locator.capes.DadosArquivoExportacaoServico");
	}

	/**
	 * Realiza o lookup do {@code ExportacaoServico}
	 * 
	 * @since 1.2.1.0
	 * @return O EJB
	 * @see ExportacaoServico
	 */
	public ExportacaoServico localizarExportacaoServico() {
		return (ExportacaoServico) localizar("locator.capes.ExportacaoServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code VisualizarExportacaoServico}.
	 *
	 * @return O EJB solicitado
	 * @see VisualizarExportacaoServico
	 */
	public VisualizarExportacaoServico localizarVisualizarExportacaoServico() {
		return (VisualizarExportacaoServico) localizar("locator.capes.VisualizarExportacaoServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code TipoObservacaoAnotacaoServico}.
	 *
	 * @return O EJB solicitado
	 * @see TipoObservacaoAnotacaoServico
	 */
	public TipoObservacaoAnotacaoServico localizarTipoObservacaoAnotacao() {
		return (TipoObservacaoAnotacaoServico) localizar("locator.capes.TipoObservacaoAnotacaoServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code AtividadeEconomicaServico}.
	 *
	 * @return O EJB solicitado
	 * @see AtividadeEconomicaServico
	 */
	public AtividadeEconomicaServico localizarAtividadeEconomicaServico() {
		return (AtividadeEconomicaServico) localizar("locator.capes.AtividadeEconomicaServico");
	}
	
	public TipoBeneficiarioSicorServico localizarTipoBeneficiarioSicorServico() {
		return (TipoBeneficiarioSicorServico) localizar("locator.capes.TipoBeneficiarioSicorServico");
	}
	
	public BemServico localizarBemDelegate() {
		return (BemServico) localizar("locator.capes.BemServico");
	}
	
	public BemPessoaCompartilhamentoServico localizarBemPessoaCompartilhamentoServico() {
		return (BemPessoaCompartilhamentoServico) localizar("locator.capes.BemPessoaCompartilhamentoServico");
	}
	
	public TipoClassificacaoBemServico localizarTipoClassificacaoBemServico() {
		return (TipoClassificacaoBemServico) localizar("locator.capes.TipoClassificacaoBemServico");
	}
	
	public TipoBemImovelServico localizarTipoBemImovelServico() {
		return (TipoBemImovelServico) localizar("locator.capes.TipoBemImovelServico");
	}

	public TipoBemMovelServico localizarTipoBemMovelServico() {
		return (TipoBemMovelServico) localizar("locator.capes.TipoBemMovelServico");
	}

	public TipoLocalizacaoBemServico localizarTipoLocalizacaoBemServico() {
		return (TipoLocalizacaoBemServico) localizar("locator.capes.TipoLocalizacaoBemServico");
	}

	public TipoUsoBemServico localizarTipoUsoBemServico() {
		return (TipoUsoBemServico) localizar("locator.capes.TipoUsoBemServico");
	}
	
	public TipoEstadoConservacaoBemServico localizarTipoEstadoConservacaoBemServico() {
		return (TipoEstadoConservacaoBemServico) localizar("locator.capes.TipoEstadoConservacaoBemServico");
	}

	public TipoImplantacaoBemImovelServico localizarTipoImplantacaoBemImovelServico() {
		return (TipoImplantacaoBemImovelServico) localizar("locator.capes.TipoImplantacaoBemImovelServico");
	}

	public TipoPadraoAcabamentoBemImovelServico localizarTipoPadraoAcabamentoBemImovelServico() {
		return (TipoPadraoAcabamentoBemImovelServico) localizar("locator.capes.TipoPadraoAcabamentoBemImovelServico");
	}
	
	public TipoServicoCondominialBemImovelServico localizarTipoServicoCondominialBemImovelServico() {
		return (TipoServicoCondominialBemImovelServico) localizar("locator.capes.TipoServicoCondominialBemImovelServico");
	}
	
	public TipoRelacionamentoBemImovelServico localizarTipoRelacionamentoBemImovelServico() {
		return (TipoRelacionamentoBemImovelServico) localizar("locator.capes.TipoRelacionamentoBemImovelServico");
	}
	
	public TipoOnusBemServico localizarTipoOnusBemServico() {
		return (TipoOnusBemServico) localizar("locator.capes.TipoOnusBemServico");
	}

	public TipoChassiBemServico localizarTipoChassiBemServico() {
		return (TipoChassiBemServico) localizar("locator.capes.TipoChassiBemServico");
	}

	public TipoCorAutomovelBemServico localizarTipoCorAutomovelBemServico() {
		return (TipoCorAutomovelBemServico) localizar("locator.capes.TipoCorAutomovelBemServico");
	}

	public GrupoTipoAnotacaoServico localizarGrupoTipoAnotacaoServico() {
		return (GrupoTipoAnotacaoServico) localizar("locator.capes.GrupoTipoAnotacaoServico");
	}
	
	/**
	 * Localiza o servico de parametro.
	 * 
	 * @return o servico de parâmetro.
	 */
	public ParametroServico localizarParametroServico() {
		return (ParametroServico) localizar("locator.capes.ParametroServico");
	}
	
}