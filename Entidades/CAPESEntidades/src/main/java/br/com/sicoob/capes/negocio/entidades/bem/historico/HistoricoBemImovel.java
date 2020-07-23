package br.com.sicoob.capes.negocio.entidades.bem.historico;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.sicoob.capes.negocio.entidades.UnidadeMedida;
import br.com.sicoob.capes.negocio.entidades.bem.TipoBemImovel;
import br.com.sicoob.capes.negocio.entidades.bem.TipoLocalizacaoBem;
import br.com.sicoob.capes.negocio.entidades.bem.TipoUsoBemImovel;

/**
 * A classe que representa a entidade Histórico bem imóvel.
 * 
 * @author Bruno.Carneiro
 */
@Entity
@Table(schema = "CLI", name = "HISTBEMIMOVEL")
public class HistoricoBemImovel extends HistoricoBem {
	private static final long serialVersionUID = 5402511328722932144L;
	
	@Column(name = "DESCINFORMACAOGERAL", length = 1000)
	private String informacoesGerais;

	@Column(name = "DESCDENOMINACAO", length = 50)
	private String denominacao;

	@ManyToOne
	@JoinColumn(name = "CODUNIDADEMEDIDA")
	private UnidadeMedida unidadeMedida;

	@ManyToOne
	@JoinColumn(name = "CODTIPOLOCALIZACAOBEM", referencedColumnName = "CODTIPOLOCALIZACAOBEM")
	private TipoLocalizacaoBem tipoLocalizacaoBem;

	@ManyToOne
	@JoinColumn(name = "CODTIPOBEMIMOVEL", referencedColumnName = "CODTIPOBEMIMOVEL")
	private TipoBemImovel tipoBem;

	@ManyToOne
	@JoinColumn(name = "CODTIPOUSOBEM", referencedColumnName = "CODTIPOUSOBEM")
	private TipoUsoBemImovel tipoUsoBem;

	@Column(name = "DESCNUMERO", length = 6)
	private String numero;

	@Column(name = "DESCCOMPLEMENTO", length = 25)
	private String complemento;

	@Column(name = "IDLOGRADOURO")
	private Integer idLogradouro;
	
	@Column(name = "IDLOCALIDADE")
	private Integer idLocalidade;

	@Column(name = "DESCROTEIRO", length = 50)
	private String descricaoRoteiro;

	@Column(name = "VALORLATITUDE", precision = 10, scale = 8)
	private BigDecimal latitude;

	@Column(name = "VALORLONGITUDE", precision = 11, scale = 8)
	private BigDecimal longitude;

	@Column(name = "AREATOTALIMOVEL", precision = 13, scale = 4)
	private BigDecimal areaTotal;

	@Column(name = "IDPESSOACOMPARTILHAMENTOCARTORIO")
	private Long idPessoaCompartilhamentoCartorio;

	@Column(name = "DESCMATRICULA", length = 6)
	private String matricula;

	@Column(name = "DESCNIRF", length = 8)
	private String nirf;

	@Column(name = "DESCINCRA", length = 13)
	private String incra;

	@OneToMany(mappedBy = "historicoBemImovel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<HistoricoBemImovelTipoRelacionamentoPessoa> relacionamentoPessoas;
	
	public String getInformacoesGerais() {
		return informacoesGerais;
	}

	public void setInformacoesGerais(String informacoesGerais) {
		this.informacoesGerais = informacoesGerais;
	}
	
	public String getDenominacao() {
		return denominacao;
	}

	public void setDenominacao(String denominacao) {
		this.denominacao = denominacao;
	}

	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public TipoLocalizacaoBem getTipoLocalizacaoBem() {
		return tipoLocalizacaoBem;
	}

	public void setTipoLocalizacaoBem(TipoLocalizacaoBem tipoLocalizacaoBem) {
		this.tipoLocalizacaoBem = tipoLocalizacaoBem;
	}

	public TipoBemImovel getTipoBem() {
		return tipoBem;
	}

	public void setTipoBem(TipoBemImovel tipoBem) {
		this.tipoBem = tipoBem;
	}

	public TipoUsoBemImovel getTipoUsoBem() {
		return tipoUsoBem;
	}

	public void setTipoUsoBem(TipoUsoBemImovel tipoUsoBem) {
		this.tipoUsoBem = tipoUsoBem;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Integer getIdLogradouro() {
		return idLogradouro;
	}

	public void setIdLogradouro(Integer idLogradouro) {
		this.idLogradouro = idLogradouro;
	}
	
	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getDescricaoRoteiro() {
		return descricaoRoteiro;
	}

	public void setDescricaoRoteiro(String descricaoRoteiro) {
		this.descricaoRoteiro = descricaoRoteiro;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getAreaTotal() {
		return areaTotal;
	}

	public void setAreaTotal(BigDecimal areaTotal) {
		this.areaTotal = areaTotal;
	}

	public Long getIdPessoaCompartilhamentoCartorio() {
		return idPessoaCompartilhamentoCartorio;
	}

	public void setIdPessoaCompartilhamentoCartorio(Long idPessoaCompartilhamentoCartorio) {
		this.idPessoaCompartilhamentoCartorio = idPessoaCompartilhamentoCartorio;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNirf() {
		return nirf;
	}

	public void setNirf(String nirf) {
		this.nirf = nirf;
	}

	public String getIncra() {
		return incra;
	}

	public void setIncra(String incra) {
		this.incra = incra;
	}

	public Set<HistoricoBemImovelTipoRelacionamentoPessoa> getRelacionamentoPessoas() {
		return relacionamentoPessoas;
	}

	public void setRelacionamentoPessoas(Set<HistoricoBemImovelTipoRelacionamentoPessoa> relacionamentoPessoas) {
		this.relacionamentoPessoas = relacionamentoPessoas;
	}

}