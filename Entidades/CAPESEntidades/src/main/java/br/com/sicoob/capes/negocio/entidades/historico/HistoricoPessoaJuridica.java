/*
 * SICOOB
 * 
 * HistoricoPessoaJuridica.java(br.com.sicoob.capes.negocio.entidades.historico.HistoricoPessoaJuridica)
 */
package br.com.sicoob.capes.negocio.entidades.historico;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.com.sicoob.capes.negocio.entidades.TipoEmpresa;
import br.com.sicoob.capes.negocio.entidades.TipoFormaConstituicao;

/**
 * Histórico de pessoa jurídica.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name = "HISTPESSOAJURIDICA", schema="CLI")
@PrimaryKeyJoinColumn(name = "IDHISTPESSOACOMPARTILHAMENTO")
public class HistoricoPessoaJuridica extends HistoricoPessoaCompartilhamento {

	/** Serial UID. */
	private static final long serialVersionUID = 76341315439268120L;

	/** O atributo data constituicao. */
	private Date dataConstituicao;

	/** O atributo valor capital social. */
	private BigDecimal valorCapitalSocial = BigDecimal.ZERO;

	/** O atributo inscricao estadual. */
	@Column(name = "NUMINSCRICAOESTADUAL")
	private String inscricaoEstadual;

	/** O atributo codigo esfera administrativa. */
	@Column(name = "CODESFERAADMINISTRATIVA")
	private Short codigoEsferaAdministrativa;

	/** O atributo tipo empresa. */
	@JoinColumn(name = "CODTIPOEMPRESA", referencedColumnName = "CODTIPOEMPRESA")
	@ManyToOne
	private TipoEmpresa tipoEmpresa;

	/** O atributo forma constituicao. */
	@JoinColumn(name = "CODTIPOFORMACONSTITUICAO", referencedColumnName = "CODTIPOFORMACONSTITUICAO")
	@ManyToOne
	private TipoFormaConstituicao formaConstituicao;

	/** O atributo numero registro junta comercial. */
	@Column(name = "NUMREGISTROJUNTACOMERCIAL")
	private String numeroRegistroJuntaComercial;

	/** O atributo data registro junta comercial. */
	private Date dataRegistroJuntaComercial;

	/** O atributo numero ultima alteracao contrato social. */
	@Column(name = "NUMULTIMAALTERACAOCONTRATOSOCIAL")
	private String numeroUltimaAlteracaoContratoSocial;

	/** O atributo data ultima alteracao contrato social. */
	private Date dataUltimaAlteracaoContratoSocial;

	/** O atributo numero registro representacao. */
	@Column(name = "NUMREGISTROREPRESENTACAO")
	private String numeroRegistroRepresentacao;

	/** O atributo data registro representacao. */
	private Date dataRegistroRepresentacao;

	/** O atributo contrato social. */
	@Column(name = "DESCCONTRATOSOCIAL")
	private String contratoSocial;

	/**
	 * @return the dataConstituicao
	 */
	public Date getDataConstituicao() {
		return dataConstituicao;
	}

	/**
	 * @param dataConstituicao the dataConstituicao to set
	 */
	public void setDataConstituicao(Date dataConstituicao) {
		this.dataConstituicao = dataConstituicao;
	}

	/**
	 * @return the valorCapitalSocial
	 */
	public BigDecimal getValorCapitalSocial() {
		return valorCapitalSocial;
	}

	/**
	 * @param valorCapitalSocial the valorCapitalSocial to set
	 */
	public void setValorCapitalSocial(BigDecimal valorCapitalSocial) {
		this.valorCapitalSocial = valorCapitalSocial;
	}

	/**
	 * @return the inscricaoEstadual
	 */
	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	/**
	 * @param inscricaoEstadual the inscricaoEstadual to set
	 */
	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	/**
	 * @return the codigoEsferaAdministrativa
	 */
	public Short getCodigoEsferaAdministrativa() {
		return codigoEsferaAdministrativa;
	}

	/**
	 * @param codigoEsferaAdministrativa the codigoEsferaAdministrativa to set
	 */
	public void setCodigoEsferaAdministrativa(Short codigoEsferaAdministrativa) {
		this.codigoEsferaAdministrativa = codigoEsferaAdministrativa;
	}

	/**
	 * @return the tipoEmpresa
	 */
	public TipoEmpresa getTipoEmpresa() {
		return tipoEmpresa;
	}

	/**
	 * @param tipoEmpresa the tipoEmpresa to set
	 */
	public void setTipoEmpresa(TipoEmpresa tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}

	/**
	 * @return the formaConstituicao
	 */
	public TipoFormaConstituicao getFormaConstituicao() {
		return formaConstituicao;
	}

	/**
	 * @param formaConstituicao the formaConstituicao to set
	 */
	public void setFormaConstituicao(TipoFormaConstituicao formaConstituicao) {
		this.formaConstituicao = formaConstituicao;
	}

	/**
	 * @return the numeroRegistroJuntaComercial
	 */
	public String getNumeroRegistroJuntaComercial() {
		return numeroRegistroJuntaComercial;
	}

	/**
	 * @param numeroRegistroJuntaComercial the numeroRegistroJuntaComercial to set
	 */
	public void setNumeroRegistroJuntaComercial(String numeroRegistroJuntaComercial) {
		this.numeroRegistroJuntaComercial = numeroRegistroJuntaComercial;
	}

	/**
	 * @return the dataRegistroJuntaComercial
	 */
	public Date getDataRegistroJuntaComercial() {
		return dataRegistroJuntaComercial;
	}

	/**
	 * @param dataRegistroJuntaComercial the dataRegistroJuntaComercial to set
	 */
	public void setDataRegistroJuntaComercial(Date dataRegistroJuntaComercial) {
		this.dataRegistroJuntaComercial = dataRegistroJuntaComercial;
	}

	/**
	 * @return the numeroUltimaAlteracaoContratoSocial
	 */
	public String getNumeroUltimaAlteracaoContratoSocial() {
		return numeroUltimaAlteracaoContratoSocial;
	}

	/**
	 * @param numeroUltimaAlteracaoContratoSocial the numeroUltimaAlteracaoContratoSocial to set
	 */
	public void setNumeroUltimaAlteracaoContratoSocial(String numeroUltimaAlteracaoContratoSocial) {
		this.numeroUltimaAlteracaoContratoSocial = numeroUltimaAlteracaoContratoSocial;
	}

	/**
	 * @return the dataUltimaAlteracaoContratoSocial
	 */
	public Date getDataUltimaAlteracaoContratoSocial() {
		return dataUltimaAlteracaoContratoSocial;
	}

	/**
	 * @param dataUltimaAlteracaoContratoSocial the dataUltimaAlteracaoContratoSocial to set
	 */
	public void setDataUltimaAlteracaoContratoSocial(Date dataUltimaAlteracaoContratoSocial) {
		this.dataUltimaAlteracaoContratoSocial = dataUltimaAlteracaoContratoSocial;
	}

	/**
	 * @return the numeroRegistroRepresentacao
	 */
	public String getNumeroRegistroRepresentacao() {
		return numeroRegistroRepresentacao;
	}

	/**
	 * @param numeroRegistroRepresentacao the numeroRegistroRepresentacao to set
	 */
	public void setNumeroRegistroRepresentacao(String numeroRegistroRepresentacao) {
		this.numeroRegistroRepresentacao = numeroRegistroRepresentacao;
	}

	/**
	 * @return the dataRegistroRepresentacao
	 */
	public Date getDataRegistroRepresentacao() {
		return dataRegistroRepresentacao;
	}

	/**
	 * @param dataRegistroRepresentacao the dataRegistroRepresentacao to set
	 */
	public void setDataRegistroRepresentacao(Date dataRegistroRepresentacao) {
		this.dataRegistroRepresentacao = dataRegistroRepresentacao;
	}

	/**
	 * @return the contratoSocial
	 */
	public String getContratoSocial() {
		return contratoSocial;
	}

	/**
	 * @param contratoSocial the contratoSocial to set
	 */
	public void setContratoSocial(String contratoSocial) {
		this.contratoSocial = contratoSocial;
	}

	/** 
	 * {@inheritDoc}
	 */
	public Serializable getIdEntidadeVigente() {
		return getPessoaCompartilhamento().getIdPessoaCompartilhamento();
	}

}