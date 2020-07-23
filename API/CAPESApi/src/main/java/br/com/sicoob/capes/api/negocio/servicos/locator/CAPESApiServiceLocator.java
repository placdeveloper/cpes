/*
 * SICOOB
 * 
 * CAPESApiServiceLocator.java(br.com.sicoob.capes.api.negocio.servicos.locator.CAPESApiServiceLocator)
 */
package br.com.sicoob.capes.api.negocio.servicos.locator;

import br.com.bancoob.negocio.servicos.locator.BancoobServiceLocator;
import br.com.sicoob.capes.api.negocio.servicos.AnotacaoPessoaServico;
import br.com.sicoob.capes.api.negocio.servicos.AtualizarAnotacoesSisbrServico;
import br.com.sicoob.capes.api.negocio.servicos.BemImovelServico;
import br.com.sicoob.capes.api.negocio.servicos.BemMovelServico;
import br.com.sicoob.capes.api.negocio.servicos.BemPessoaServico;
import br.com.sicoob.capes.api.negocio.servicos.BemServico;
import br.com.sicoob.capes.api.negocio.servicos.CertidaoPessoaServico;
import br.com.sicoob.capes.api.negocio.servicos.ClienteServico;
import br.com.sicoob.capes.api.negocio.servicos.CnaeFiscalServico;
import br.com.sicoob.capes.api.negocio.servicos.DadosClienteServico;
import br.com.sicoob.capes.api.negocio.servicos.DadosEtiquetaServico;
import br.com.sicoob.capes.api.negocio.servicos.DataUltimaAtualizacaoServico;
import br.com.sicoob.capes.api.negocio.servicos.DominioServico;
import br.com.sicoob.capes.api.negocio.servicos.EmailPessoaServico;
import br.com.sicoob.capes.api.negocio.servicos.EnderecoPessoaServico;
import br.com.sicoob.capes.api.negocio.servicos.FonteRendaPessoaServico;
import br.com.sicoob.capes.api.negocio.servicos.FuncionarioServico;
import br.com.sicoob.capes.api.negocio.servicos.GrupoCompartilhamentoServico;
import br.com.sicoob.capes.api.negocio.servicos.GrupoEconomicoServico;
import br.com.sicoob.capes.api.negocio.servicos.InformacaoProfissionalServico;
import br.com.sicoob.capes.api.negocio.servicos.InformacaoUtilizadaServico;
import br.com.sicoob.capes.api.negocio.servicos.InstituicaoResponsavelServico;
import br.com.sicoob.capes.api.negocio.servicos.MensagemPessoaServico;
import br.com.sicoob.capes.api.negocio.servicos.NucleoServico;
import br.com.sicoob.capes.api.negocio.servicos.OcupacaoProfissionalServico;
import br.com.sicoob.capes.api.negocio.servicos.PessoaFisicaServico;
import br.com.sicoob.capes.api.negocio.servicos.PessoaJuridicaServico;
import br.com.sicoob.capes.api.negocio.servicos.PessoaServico;
import br.com.sicoob.capes.api.negocio.servicos.ProdutividadePessoaServico;
import br.com.sicoob.capes.api.negocio.servicos.ProdutorServico;
import br.com.sicoob.capes.api.negocio.servicos.ReferenciaPessoaServico;
import br.com.sicoob.capes.api.negocio.servicos.RelacionamentoPessoaServico;
import br.com.sicoob.capes.api.negocio.servicos.TelefonePessoaServico;
import br.com.sicoob.capes.api.negocio.servicos.TipoAnotacaoServico;
import br.com.sicoob.capes.api.negocio.servicos.TipoEmailServico;
import br.com.sicoob.capes.api.negocio.servicos.TipoEmpresaServico;
import br.com.sicoob.capes.api.negocio.servicos.TipoEnderecoServico;
import br.com.sicoob.capes.api.negocio.servicos.TipoFonteRendaServico;
import br.com.sicoob.capes.api.negocio.servicos.TipoTelefoneServico;
import br.com.sicoob.capes.api.negocio.servicos.TributacaoPessoaServico;

/**
 * Service Locator usado pelo sistema CapesIntegracao.
 * 
 */
public final class CAPESApiServiceLocator extends BancoobServiceLocator {

	/** O atributo locator. */
	private static CAPESApiServiceLocator locator = new CAPESApiServiceLocator();

	/**
	 * Singleton da classe
	 * 
	 * @return A instancia da classe
	 */
	public static CAPESApiServiceLocator getInstance() {
		return locator;
	}
	
	/**
	 * @param nomeAplicacao
	 */
	private CAPESApiServiceLocator() {
		super("capes.api");
	}
	
	/**
	 * Localiza o EJB que implementa PessoaServico.
	 * 
	 * @return the pessoa servico
	 */
	public PessoaServico localizarPessoaServico() {
		return (PessoaServico) localizar("locator.capes.PessoaServico");
	}

	/**
	 * Localiza o EJB que implementa ProdutividadeServico.
	 * 
	 * @return the produtividade pessoa servico
	 */
	public ProdutividadePessoaServico localizarProdutividadeServico() {
		return (ProdutividadePessoaServico) localizar("locator.capes.ProdutividadePessoaServico");
	}

	/**
	 * Localiza o EJB que implementa BemPessoaServico.
	 * 
	 * @return the bem pessoa servico
	 */
	public BemPessoaServico localizarBemPessoaServico() {
		return (BemPessoaServico) localizar("locator.capes.BemPessoaServico");
	}

	/**
	 * Localiza o EJB que implementa TelefonePessoaServico.
	 * 
	 * @return the telefone pessoa servico
	 */
	public TelefonePessoaServico localizarTelefonePessoaServico() {
		return (TelefonePessoaServico) localizar("locator.capes.TelefonePessoaServico");
	}

	/**
	 * Localiza o EJB que implementa EmailPessoaServico.
	 * 
	 * @return the email pessoa servico
	 */
	public EmailPessoaServico localizarEmailPessoaServico() {
		return (EmailPessoaServico) localizar("locator.capes.EmailPessoaServico");
	}

	/**
	 * Localiza o EJB que implementa EnderecoPessoaServico.
	 * 
	 * @return the endereco pessoa servico
	 */
	public EnderecoPessoaServico localizarEnderecoPessoaServico() {
		return (EnderecoPessoaServico) localizar("locator.capes.EnderecoPessoaServico");
	}
	
	/**
	 * Localiza o EJB que implementa FonteRendaPessoaServico.
	 * 
	 * @return the fonte renda pessoa servico
	 */
	public FonteRendaPessoaServico localizarFonteRendaPessoaServico() {
		return (FonteRendaPessoaServico) localizar("locator.capes.FonteRendaPessoaServico");
	}
	
	/**
	 * Localiza o EJB que implementa CertidaoPessoaServico.
	 * 
	 * @return the certidao pessoa servico
	 */
	public CertidaoPessoaServico localizarCertidaoPessoaServico() {
		return (CertidaoPessoaServico) localizar("locator.capes.CertidaoPessoaServico");
	}

	/**
	 * Localiza o EJB que implementa AnotacaoPessoaServico.
	 * 
	 * @return the anotacao pessoa servico
	 */
	public AnotacaoPessoaServico localizarAnotacaoPessoaServico() {
		return (AnotacaoPessoaServico) localizar("locator.capes.AnotacaoPessoaServico");
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
	 * Localiza o EJB que implementa TributacaoPessoaServico.
	 * 
	 * @return the tributacao pessoa servico
	 */
	public TributacaoPessoaServico localizarTributacaoPessoaServico() {
		return (TributacaoPessoaServico) localizar("locator.capes.TributacaoPessoaServico");
	}
	
	/**
	 * Localiza o EJB que implementa MensagemPessoaServico.
	 * 
	 * @return the mensagem pessoa servico
	 */
	public MensagemPessoaServico localizarMensagemPessoaServico() {
		return (MensagemPessoaServico) localizar("locator.capes.MensagemPessoaServico");
	}
	
	/**
	 * Localiza o EJB que implementa DadosClienteServico.
	 * 
	 * @return the dados cliente servico
	 */
	public DadosClienteServico localizarDadosClienteServico() {
		return (DadosClienteServico) localizar("locator.capes.DadosClienteServico");
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
	 * Localiza o EJB que implementa ReferenciaPessoaServico.
	 * 
	 * @return the referencia pessoa servico
	 */
	public ReferenciaPessoaServico localizarReferenciaPessoaServico(){
		return (ReferenciaPessoaServico) localizar("locator.capes.ReferenciaPessoaServico");
	}
	
	/**
	 * Localiza o EJB que implementa PessoaJuridicaServico.
	 * 
	 * @return the pessoa juridica servico
	 */
	public PessoaJuridicaServico localizarPessoaJuridicaServico(){
		return (PessoaJuridicaServico) localizar("locator.capes.PessoaJuridicaServico");
	}

	/**
	 * Localiza o EJB que implementa PessoaFisicaServico.
	 * 
	 * @return the pessoa fisica servico
	 */
	public PessoaFisicaServico localizarPessoaFisicaServico(){
		return (PessoaFisicaServico) localizar("locator.capes.PessoaFisicaServico");
	}
	
	/**
	 * Localiza o EJB que implementa ClienteServico.
	 * 
	 * @return the cliente servico
	 */
	public ClienteServico localizarClienteServico() {
		return (ClienteServico) localizar("locator.capes.ClienteServico");
	}
	
	
	public CnaeFiscalServico localizarCnaeFiscalServico(){
		return (CnaeFiscalServico) localizar("locator.capes.CnaeFiscalServico");
	}
	
	/**
	 * Localiza o EJB que implementa DadosEtiquetaServico.
	 * 
	 * @return the dados etiqueta servico
	 */
	public DadosEtiquetaServico localizarDadosEtiquetaServico() {
		return (DadosEtiquetaServico) localizar("locator.capes.DadosEtiquetaServico");
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
	 * Retorna a instância do Serviço EJB da data da última atualização através de lookup
	 * em <code>locator.capes.DataUltimaAtualizacaoServico</code>
	 * 
	 * @return 
	 * 		Retorna a instância do serviço EJB
	 */
	public DataUltimaAtualizacaoServico localizarDataUltimaAtualizacaoServico() {		
		return  (DataUltimaAtualizacaoServico) localizar("locator.capes.DataUltimaAtualizacaoServico");
	}

	/**
	 * Retorna a instância do Serviço EJB da produtor através de lookup
	 * em <code>locator.capes.ProdutorServico</code>
	 * 
	 * @return
	 */
	public ProdutorServico localizarProdutorServico() {
		return (ProdutorServico) localizar("locator.capes.ProdutorServico");
	}

	/**
	 * Retorna a instância do Serviço EJB dainstituição responsável através de lookup
	 * em <code>locator.capes.InstituicaoResponsavelServico</code>
	 * @return
	 */
	public InstituicaoResponsavelServico localizarInstituicaoResponsavelServico() {
		return (InstituicaoResponsavelServico) localizar("locator.capes.InstituicaoResponsavelServico");
	}

	/**
	 * Retorna a instância do Serviço EJB do Grupo Econômico através de lookup
	 * em <code>locator.capes.GrupoEconomicoServico</code>
	 * @return
	 */
	public GrupoEconomicoServico localizarGrupoEconomicoServico() {
		return (GrupoEconomicoServico) localizar("locator.capes.GrupoEconomicoServico");
	}
	
	/**
	 * Retorna a instância do Serviço EJB para a listagem de domínio através de lookup
	 * em <code>locator.capes.DominioServico</code>
	 * @return
	 */
	public DominioServico localizarDominioServico() {
		return (DominioServico)localizar("locator.capes.DominioServico");
	}

	/**
	 * Localiza o EJB que implementa AtualizarAnotacoesSisbrServico.
	 * 
	 * @return the atualizar anotacoes sisbr servico
	 */
	public AtualizarAnotacoesSisbrServico localizarAtualizarAnotacoesSisbrServico() {
		return (AtualizarAnotacoesSisbrServico)localizar("locator.capes.AtualizarAnotacoesSisbrServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code NucleoServico}.
	 *
	 * @return O EJB solicitado
	 * @see NucleoServico
	 */
	public NucleoServico localizarNucleoServico() {
		return (NucleoServico)localizar("locator.capes.NucleoServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code TipoAnotacaoServico}.
	 *
	 * @return O EJB solicitado
	 * @see TipoAnotacaoServico
	 */
	public TipoAnotacaoServico localizarTipoAnotacaoServico(){
		return (TipoAnotacaoServico) localizar("locator.capes.TipoAnotacaoServico");
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
	 * Localiza o EJB que implementa a interface
	 * {@code GrupoCompartilhamentoServico}
	 * 
	 * @return O EJB solicitado.
	 */
	public GrupoCompartilhamentoServico localizarGrupoCompartilhamentoServico() {
		return (GrupoCompartilhamentoServico) localizar("locator.capes.GrupoCompartilhamentoServico");
	}

	/**
	 * Localiza o EJB que implementa a interface
	 * {@code OcupacaoProfissionalServico}
	 * 
	 * @return O EJB solicitado.
	 */
	public OcupacaoProfissionalServico localizarOcupacaoProfissionalServico() {
		return (OcupacaoProfissionalServico) localizar("locator.capes.OcupacaoProfissionalServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface
	 * {@code TipoEmailServico}
	 * 
	 * @return O EJB solicitado.
	 */
	public TipoEmailServico localizarTipoEmailServico() {
		return (TipoEmailServico) localizar("locator.capes.TipoEmailServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface
	 * {@code TipoTelefoneServico}
	 * 
	 * @return O EJB solicitado.
	 */
	public TipoTelefoneServico localizarTipoTelefoneServico() {
		return (TipoTelefoneServico) localizar("locator.capes.TipoTelefoneServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface
	 * {@code TipoEmpresaServico}
	 * 
	 * @return O EJB solicitado.
	 */
	public TipoEmpresaServico localizarTipoEmpresaServico() {
		return (TipoEmpresaServico) localizar("locator.capes.TipoEmpresaServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface
	 * {@code TipoEnderecoServico}
	 * 
	 * @return O EJB solicitado.
	 */
	public TipoEnderecoServico localizarTipoEnderecoServico() {
		return (TipoEnderecoServico) localizar("locator.capes.TipoEnderecoServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface
	 * {@code TipoFonteRendaServico}
	 * 
	 * @return O EJB solicitado.
	 */
	public TipoFonteRendaServico localizarTipoFonteRendaServico() {
		return (TipoFonteRendaServico) localizar("locator.capes.TipoFonteRendaServico");
	}
	
	public BemServico localizarBemServico() {
		return (BemServico) localizar("locator.capes.BemServico");
	}

	public BemImovelServico localizarBemImovelServico() {
		return (BemImovelServico) localizar("locator.capes.BemImovelServico");
	}

	public BemMovelServico localizarBemMovelServico() {
		return (BemMovelServico) localizar("locator.capes.BemMovelServico");
	}
}