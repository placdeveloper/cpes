/*
 * SICOOB
 * 
 * HistoricoPessoaFisica.java(br.com.sicoob.capes.negocio.entidades.historico.HistoricoPessoaFisica)
 */
package br.com.sicoob.capes.negocio.entidades.historico;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.com.sicoob.capes.negocio.entidades.EstadoCivil;
import br.com.sicoob.capes.negocio.entidades.GrauInstrucao;
import br.com.sicoob.capes.negocio.entidades.Nacionalidade;
import br.com.sicoob.capes.negocio.entidades.OcupacaoProfissional;
import br.com.sicoob.capes.negocio.entidades.RegimeCasamento;
import br.com.sicoob.capes.negocio.entidades.TipoDocumentoIdentificacao;
import br.com.sicoob.capes.negocio.entidades.VinculoEmpregaticio;
import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Histórico de pessoa física.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name = "HISTPESSOAFISICA", schema="CLI")
@PrimaryKeyJoinColumn(name = "IDHISTPESSOACOMPARTILHAMENTO")
public class HistoricoPessoaFisica extends HistoricoPessoaCompartilhamento implements Historico {

	/** Serial UID. */
	private static final long serialVersionUID = 5681404970438595846L;

	/** O atributo data nascimento. */
	private Date dataNascimento;

	/** O atributo menor emancipado. */
	@Column(name = "BOLMENOREMANCIPADO")
	private Boolean menorEmancipado = Boolean.FALSE;

	/** O atributo nome pai. */
	private String nomePai;

	/** O atributo nome mae. */
	private String nomeMae;

	/** O atributo tipo documento. */
	@JoinColumn(name = "CODTIPODOCUMENTOIDENTIFICACAO", referencedColumnName = "CODTIPODOCUMENTOIDENTIFICACAO")
	@ManyToOne
	private TipoDocumentoIdentificacao tipoDocumento;

	/** O atributo numero documento. */
	@Column(name = "NUMDOCUMENTOIDENTIFICACAO")
	private String numeroDocumento;

	/** O atributo orgao expedidor documento. */
	@Column(name = "DESCORGAOEXPDOCUMENTOIDENTIFICACAO")
	private String orgaoExpedidorDocumento;

	/** O atributo uf orgao expedidor documento. */
	@Column(name = "SIGLAUFORGEXPDOCUMENTOIDENTIFICACAO")
	private String ufOrgaoExpedidorDocumento;

	/** O atributo data emissao documento. */
	@Column(name = "DATAEMISSAODOCUMENTOIDENTIFICACAO")
	private Date dataEmissaoDocumento;

	/** O atributo tipo sexo. */
	@Column(name = "CODTIPOSEXO")
	private Character tipoSexo;

	/** O atributo ocupacao profissional. */
	@JoinColumn(name = "IDOCUPACAOPROFISSIONAL", referencedColumnName = "idOcupacaoProfissional")
	@ManyToOne
	private OcupacaoProfissional ocupacaoProfissional;

	/** O atributo estado civil. */
	@JoinColumn(name = "CODESTADOCIVIL", referencedColumnName = "CODESTADOCIVIL")
	@ManyToOne
	private EstadoCivil estadoCivil;

	/** O atributo regime casamento. */
	@JoinColumn(name = "CODREGIMECASAMENTO", referencedColumnName = "CODREGIMECASAMENTO")
	@ManyToOne
	private RegimeCasamento regimeCasamento;

	/** O atributo quantidade dependentes. */
	@Column(name = "QTDDEPENDENTE")
	private Short quantidadeDependentes;

	/** O atributo uniao estavel. */
	@Column(name = "BOLUNIAOESTAVEL")
	private Boolean uniaoEstavel = Boolean.FALSE;

	/** O atributo grau instrucao. */
	@JoinColumn(name = "CODGRAUINSTRUCAO", referencedColumnName = "CODGRAUINSTRUCAO")
	@ManyToOne
	private GrauInstrucao grauInstrucao;
	
	/** O atributo id naturalidade. */
	private Integer idNaturalidade;
	
	/** O atributo desc naturalidade. */
	@Column(name="descNaturalidade", updatable=false, insertable=false)
	private String descNaturalidade;	
	
	/** O atributo vinculo empregaticio. */
	@JoinColumn(name = "CODVINCULOEMPREGATICIO", referencedColumnName = "CODVINCULOEMPREGATICIO")
	@ManyToOne
	private VinculoEmpregaticio vinculoEmpregaticio;
	
	/** O atributo nacionalidade. */
	@JoinColumn(name = "CODNACIONALIDADE", referencedColumnName = "CODNACIONALIDADE")
	@ManyToOne
	private Nacionalidade nacionalidade;
	
	/** O atributo pessoa compartilhamento. */
	@ManyToOne
	@JoinColumn(name = "IDPESSOACOMPARTILHAMENTOCONJUGE")
	private PessoaCompartilhamento conjuge;
	
	/**
	 * @return the dataNascimento
	 */
	public Date getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * @param dataNascimento the dataNascimento to set
	 */
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/**
	 * @return the menorEmancipado
	 */
	public Boolean getMenorEmancipado() {
		return menorEmancipado;
	}

	/**
	 * @param menorEmancipado the menorEmancipado to set
	 */
	public void setMenorEmancipado(Boolean menorEmancipado) {
		this.menorEmancipado = menorEmancipado;
	}

	/**
	 * @return the nomePai
	 */
	public String getNomePai() {
		return nomePai;
	}

	/**
	 * @param nomePai the nomePai to set
	 */
	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	/**
	 * @return the nomeMae
	 */
	public String getNomeMae() {
		return nomeMae;
	}

	/**
	 * @param nomeMae the nomeMae to set
	 */
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	/**
	 * @return the tipoDocumento
	 */
	public TipoDocumentoIdentificacao getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(TipoDocumentoIdentificacao tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param numeroDocumento the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * @return the orgaoExpedidorDocumento
	 */
	public String getOrgaoExpedidorDocumento() {
		return orgaoExpedidorDocumento;
	}

	/**
	 * @param orgaoExpedidorDocumento the orgaoExpedidorDocumento to set
	 */
	public void setOrgaoExpedidorDocumento(String orgaoExpedidorDocumento) {
		this.orgaoExpedidorDocumento = orgaoExpedidorDocumento;
	}

	/**
	 * @return the ufOrgaoExpedidorDocumento
	 */
	public String getUfOrgaoExpedidorDocumento() {
		return ufOrgaoExpedidorDocumento;
	}

	/**
	 * @param ufOrgaoExpedidorDocumento the ufOrgaoExpedidorDocumento to set
	 */
	public void setUfOrgaoExpedidorDocumento(String ufOrgaoExpedidorDocumento) {
		this.ufOrgaoExpedidorDocumento = ufOrgaoExpedidorDocumento;
	}

	/**
	 * @return the dataEmissaoDocumento
	 */
	public Date getDataEmissaoDocumento() {
		return dataEmissaoDocumento;
	}

	/**
	 * @param dataEmissaoDocumento the dataEmissaoDocumento to set
	 */
	public void setDataEmissaoDocumento(Date dataEmissaoDocumento) {
		this.dataEmissaoDocumento = dataEmissaoDocumento;
	}

	/**
	 * @return the tipoSexo
	 */
	public Character getTipoSexo() {
		return tipoSexo;
	}

	/**
	 * @param tipoSexo the tipoSexo to set
	 */
	public void setTipoSexo(Character tipoSexo) {
		this.tipoSexo = tipoSexo;
	}

	/**
	 * @return the ocupacaoProfissional
	 */
	public OcupacaoProfissional getOcupacaoProfissional() {
		return ocupacaoProfissional;
	}

	/**
	 * @param ocupacaoProfissional the ocupacaoProfissional to set
	 */
	public void setOcupacaoProfissional(OcupacaoProfissional ocupacaoProfissional) {
		this.ocupacaoProfissional = ocupacaoProfissional;
	}

	/**
	 * @return the estadoCivil
	 */
	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	/**
	 * @param estadoCivil the estadoCivil to set
	 */
	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	/**
	 * @return the regimeCasamento
	 */
	public RegimeCasamento getRegimeCasamento() {
		return regimeCasamento;
	}

	/**
	 * @param regimeCasamento the regimeCasamento to set
	 */
	public void setRegimeCasamento(RegimeCasamento regimeCasamento) {
		this.regimeCasamento = regimeCasamento;
	}

	/**
	 * @return the quantidadeDependentes
	 */
	public Short getQuantidadeDependentes() {
		return quantidadeDependentes;
	}

	/**
	 * @param quantidadeDependentes the quantidadeDependentes to set
	 */
	public void setQuantidadeDependentes(Short quantidadeDependentes) {
		this.quantidadeDependentes = quantidadeDependentes;
	}

	/**
	 * @return the uniaoEstavel
	 */
	public Boolean getUniaoEstavel() {
		return uniaoEstavel;
	}

	/**
	 * @param uniaoEstavel the uniaoEstavel to set
	 */
	public void setUniaoEstavel(Boolean uniaoEstavel) {
		this.uniaoEstavel = uniaoEstavel;
	}

	/**
	 * @return the grauInstrucao
	 */
	public GrauInstrucao getGrauInstrucao() {
		return grauInstrucao;
	}

	/**
	 * @param grauInstrucao the grauInstrucao to set
	 */
	public void setGrauInstrucao(GrauInstrucao grauInstrucao) {
		this.grauInstrucao = grauInstrucao;
	}

	/**
	 * @return the idNaturalidade
	 */
	public Integer getIdNaturalidade() {
		return idNaturalidade;
	}

	/**
	 * @param idNaturalidade the idNaturalidade to set
	 */
	public void setIdNaturalidade(Integer idNaturalidade) {
		this.idNaturalidade = idNaturalidade;
	}

	/**
	 * @return the descNaturalidade
	 */
	public String getDescNaturalidade() {
		return descNaturalidade;
	}

	/**
	 * @param descNaturalidade the descNaturalidade to set
	 */
	public void setDescNaturalidade(String descNaturalidade) {
		this.descNaturalidade = descNaturalidade;
	}

	/**
	 * @return the vinculoEmpregaticio
	 */
	public VinculoEmpregaticio getVinculoEmpregaticio() {
		return vinculoEmpregaticio;
	}

	/**
	 * @param vinculoEmpregaticio the vinculoEmpregaticio to set
	 */
	public void setVinculoEmpregaticio(VinculoEmpregaticio vinculoEmpregaticio) {
		this.vinculoEmpregaticio = vinculoEmpregaticio;
	}

	/**
	 * @return the nacionalidade
	 */
	public Nacionalidade getNacionalidade() {
		return nacionalidade;
	}

	/**
	 * @param nacionalidade the nacionalidade to set
	 */
	public void setNacionalidade(Nacionalidade nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	/** 
	 * {@inheritDoc}
	 */
	public Serializable getIdEntidadeVigente() {
		return getPessoaCompartilhamento().getIdPessoaCompartilhamento();
	}

	public PessoaCompartilhamento getConjuge() {
		return conjuge;
	}

	public void setConjuge(PessoaCompartilhamento conjuge) {
		this.conjuge = conjuge;
	}
	
}
