package br.com.sicoob.capes.relatorio.negocio.vo;

import java.util.Date;

public class HistoricoPessoaFisicaVO extends HistoricoPessoaCompartilhamentoVO {

	private String idUsuarioAprovacao;
		
	private Date dataNascimento;

	private Boolean menorEmancipado = Boolean.FALSE;

	private String nomePai;

	private String nomeMae;

	private TipoDocumentoIdentificacaoVO tipoDocumento;

	private String numeroDocumento;

	private String orgaoExpedidorDocumento;

	private String ufOrgaoExpedidorDocumento;

	private Date dataEmissaoDocumento;

	private Character tipoSexo;

	private OcupacaoProfissionalVO ocupacaoProfissional = new OcupacaoProfissionalVO();

	private EstadoCivilVO estadoCivil = new EstadoCivilVO();
	
	private CompartilhamentoCadastroVO compartilhamento = new CompartilhamentoCadastroVO();

	private RegimeCasamentoVO regimeCasamento = new RegimeCasamentoVO();

	private Short quantidadeDependentes;

	private Boolean uniaoEstavel = Boolean.FALSE;

	private GrauInstrucaoVO grauInstrucao = new GrauInstrucaoVO();
	
	private Integer idNaturalidade;
	
	private String descNaturalidade;	
	
	private VinculoEmpregaticioVO vinculoEmpregaticio = new VinculoEmpregaticioVO();
	
	private NacionalidadeVO nacionalidade = new NacionalidadeVO();
	
	private PessoaFisicaVO conjuge = new PessoaFisicaVO();

	public String getIdUsuarioAprovacao() {
		return idUsuarioAprovacao;
	}

	public void setIdUsuarioAprovacao(String idUsuarioAprovacao) {
		this.idUsuarioAprovacao = idUsuarioAprovacao;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Boolean getMenorEmancipado() {
		return menorEmancipado;
	}

	public void setMenorEmancipado(Boolean menorEmancipado) {
		this.menorEmancipado = menorEmancipado;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public TipoDocumentoIdentificacaoVO getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumentoIdentificacaoVO tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getOrgaoExpedidorDocumento() {
		return orgaoExpedidorDocumento;
	}

	public void setOrgaoExpedidorDocumento(String orgaoExpedidorDocumento) {
		this.orgaoExpedidorDocumento = orgaoExpedidorDocumento;
	}

	public String getUfOrgaoExpedidorDocumento() {
		return ufOrgaoExpedidorDocumento;
	}

	public void setUfOrgaoExpedidorDocumento(String ufOrgaoExpedidorDocumento) {
		this.ufOrgaoExpedidorDocumento = ufOrgaoExpedidorDocumento;
	}

	public Date getDataEmissaoDocumento() {
		return dataEmissaoDocumento;
	}

	public void setDataEmissaoDocumento(Date dataEmissaoDocumento) {
		this.dataEmissaoDocumento = dataEmissaoDocumento;
	}

	public Character getTipoSexo() {
		return tipoSexo;
	}

	public void setTipoSexo(Character tipoSexo) {
		this.tipoSexo = tipoSexo;
	}

	public OcupacaoProfissionalVO getOcupacaoProfissional() {
		return ocupacaoProfissional;
	}

	public void setOcupacaoProfissional(OcupacaoProfissionalVO ocupacaoProfissional) {
		this.ocupacaoProfissional = ocupacaoProfissional;
	}

	public EstadoCivilVO getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivilVO estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public RegimeCasamentoVO getRegimeCasamento() {
		return regimeCasamento;
	}

	public void setRegimeCasamento(RegimeCasamentoVO regimeCasamento) {
		this.regimeCasamento = regimeCasamento;
	}

	public Short getQuantidadeDependentes() {
		return quantidadeDependentes;
	}

	public void setQuantidadeDependentes(Short quantidadeDependentes) {
		this.quantidadeDependentes = quantidadeDependentes;
	}

	public Boolean getUniaoEstavel() {
		return uniaoEstavel;
	}

	public void setUniaoEstavel(Boolean uniaoEstavel) {
		this.uniaoEstavel = uniaoEstavel;
	}

	public GrauInstrucaoVO getGrauInstrucao() {
		return grauInstrucao;
	}

	public void setGrauInstrucao(GrauInstrucaoVO grauInstrucao) {
		this.grauInstrucao = grauInstrucao;
	}

	public Integer getIdNaturalidade() {
		return idNaturalidade;
	}

	public void setIdNaturalidade(Integer idNaturalidade) {
		this.idNaturalidade = idNaturalidade;
	}

	public String getDescNaturalidade() {
		return descNaturalidade;
	}

	public void setDescNaturalidade(String descNaturalidade) {
		this.descNaturalidade = descNaturalidade;
	}

	public VinculoEmpregaticioVO getVinculoEmpregaticio() {
		return vinculoEmpregaticio;
	}

	public void setVinculoEmpregaticio(VinculoEmpregaticioVO vinculoEmpregaticio) {
		this.vinculoEmpregaticio = vinculoEmpregaticio;
	}

	public NacionalidadeVO getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(NacionalidadeVO nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public PessoaFisicaVO getConjuge() {
		return conjuge;
	}

	public void setConjuge(PessoaFisicaVO conjuge) {
		this.conjuge = conjuge;
	}

	public CompartilhamentoCadastroVO getCompartilhamento() {
		return compartilhamento;
	}

	public void setCompartilhamento(CompartilhamentoCadastroVO compartilhamento) {
		this.compartilhamento = compartilhamento;
	}

	
}
