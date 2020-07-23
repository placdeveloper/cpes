package br.com.sicoob.capes.negocio.entidades.bem;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.com.sicoob.capes.comum.negocio.annotation.CampoAutorizacao;
import br.com.sicoob.capes.comum.negocio.annotation.CamposAutorizacao;
import br.com.sicoob.capes.comum.negocio.annotation.NaoVerificarGestorResponsavel;
import br.com.sicoob.capes.negocio.entidades.UnidadeMedida;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Classe que representa a entidade Bem imóvel
 * 
 * @author Bruno.Carneiro
 */
@Entity
@Table(schema = "CLI", name = "BEMIMOVEL")
@PrimaryKeyJoinColumn(referencedColumnName = "IDBEM")
@CamposAutorizacao(id = "idBem", camposExibicao = {
		@CampoAutorizacao(propriedade = "tipoLocalizacaoBem.descricao", label = "TIPO DE LOCALIZAÇÃO", ordenacao = 6),
		@CampoAutorizacao(propriedade = "tipoBem.descricao", label = "TIPO DO BEM", ordenacao = 7),
		@CampoAutorizacao(propriedade = "tipoUsoBem.descricao", label = "TIPO USO DO BEM", ordenacao = 8),
		@CampoAutorizacao(propriedade = "informacoesGerais", label = "INFORMAÇÕES GERAIS", ordenacao = 9),
		@CampoAutorizacao(propriedade = "denominacao", label = "DENOMINAÇÃO", ordenacao = 10),
		@CampoAutorizacao(propriedade = "areaTotal", label = "ÁREA TOTAL", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorDecimal", ordenacao = 11),
		@CampoAutorizacao(propriedade = "unidadeMedida.descricao", label = "UNIDADE DE MEDIDA", ordenacao = 12),
		@CampoAutorizacao(propriedade = "idLogradouro", label = "CEP", formatador = "br.com.sicoob.capes.cadastro.util.formatacao.FormatadorCEP", ordenacao = 13),
		@CampoAutorizacao(propriedade = "idLogradouro", label = "TIPO LOGRADOURO", formatador = "br.com.sicoob.capes.cadastro.util.formatacao.FormatadorTipoLogradouroBem", ordenacao = 14),
		@CampoAutorizacao(propriedade = "idLogradouro", label = "LOGRADOURO", formatador = "br.com.sicoob.capes.cadastro.util.formatacao.FormatadorLogradouro", ordenacao = 15),
		@CampoAutorizacao(propriedade = "numero", label = "NÚMERO", ordenacao = 16),
		@CampoAutorizacao(propriedade = "complemento", label = "COMPLEMENTO", ordenacao = 17),
		@CampoAutorizacao(propriedade = "idLogradouro", label = "BAIRRO", formatador = "br.com.sicoob.capes.cadastro.util.formatacao.FormatadorBairro", ordenacao = 18),
		@CampoAutorizacao(propriedade = "idLocalidade", label = "MUNICÍPIO", formatador="br.com.sicoob.capes.cadastro.util.formatacao.FormatadorLocalidade", ordenacao = 19),
		@CampoAutorizacao(propriedade = "descricaoRoteiro", label = "ROTEIRO DE ACESSO", ordenacao = 20),
		@CampoAutorizacao(propriedade = "latitude", label = "LATITUDE", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorDecimal", mascara = "99.999999", ordenacao = 21),
		@CampoAutorizacao(propriedade = "longitude", label = "LONGITUDE", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorDecimal", mascara = "99.999999", ordenacao = 22),
		@CampoAutorizacao(propriedade = "pessoaCompartilhamentoCartorio.nomePessoa", label = "NOME CARTÓRIO", ordenacao = 23),
		@CampoAutorizacao(propriedade = "matricula", label = "MATRÍCULA", ordenacao = 24),
		@CampoAutorizacao(propriedade = "nirf", label = "NIRF", ordenacao = 25),
		@CampoAutorizacao(propriedade = "incra", label = "INCRA", ordenacao = 26)
})

@NaoVerificarGestorResponsavel
public class BemImovel extends Bem {
	private static final long serialVersionUID = -4739463295953928842L;
	
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

	@Column(name = "DESCROTEIRO", length = 250)
	private String descricaoRoteiro;

	@Column(name = "VALORLATITUDE", precision = 10, scale = 8)
	private BigDecimal latitude;

	@Column(name = "VALORLONGITUDE", precision = 11, scale = 8)
	private BigDecimal longitude;

	@Column(name = "AREATOTALIMOVEL", precision = 13, scale = 4)
	private BigDecimal areaTotal;

	@Column(name = "IDPESSOACOMPARTILHAMENTOCARTORIO")
	private Long idPessoaCompartilhamentoCartorio;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDPESSOACOMPARTILHAMENTOCARTORIO", insertable = false, updatable = false)
	private PessoaCompartilhamento pessoaCompartilhamentoCartorio;

	@Column(name = "DESCMATRICULA", length = 6)
	private String matricula;

	@Column(name = "DESCNIRF", length = 8)
	private String nirf;

	@Column(name = "DESCINCRA", length = 13)
	private String incra;

	@OneToMany(mappedBy = "bemImovel", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = BemImovelTipoRelacionamentoPessoa.class)
	private Set<BemImovelTipoRelacionamentoPessoa> relacionamentoPessoas;
	
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
	
	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public Integer getIdLogradouro() {
		return idLogradouro;
	}

	public void setIdLogradouro(Integer idLogradouro) {
		this.idLogradouro = idLogradouro;
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
	
	public PessoaCompartilhamento getPessoaCompartilhamentoCartorio() {
		return pessoaCompartilhamentoCartorio;
	}

	public void setPessoaCompartilhamentoCartorio(PessoaCompartilhamento pessoaCompartilhamentoCartorio) {
		this.pessoaCompartilhamentoCartorio = pessoaCompartilhamentoCartorio;
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

	public Set<BemImovelTipoRelacionamentoPessoa> getRelacionamentoPessoas() {
		return relacionamentoPessoas;
	}

	public void setRelacionamentoPessoas(Set<BemImovelTipoRelacionamentoPessoa> relacionamentoPessoas) {
		this.relacionamentoPessoas = relacionamentoPessoas;
	}

}