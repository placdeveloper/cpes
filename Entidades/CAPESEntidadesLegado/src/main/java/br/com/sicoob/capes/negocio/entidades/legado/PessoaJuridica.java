/*
 * SICOOB
 * 
 * PessoaJuridica.java(br.com.sicoob.capes.negocio.entidades.legado.PessoaJuridica)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * Entidade que representa a tabela PessoaJuridica
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name = "PessoaJuridica")
@PrimaryKeyJoinColumn(name = "NumPessoa")
public class PessoaJuridica extends Pessoa {

	/** Serial UID. */
	private static final long serialVersionUID = 850602082368417017L;

	/** O atributo valor capital social. */
	@Column(name = "ValorCapitalSocial")
	private BigDecimal valorCapitalSocial;

	/** O atributo numero registro orgao competente. */
	@Column(name = "NumRegOrgaoCompetente")
	private String numeroRegistroOrgaoCompetente;

	/** O atributo numero registro ata. */
	@Column(name = "NumRegAta")
	private String numeroRegistroAta;

	/** O atributo numero arquivamento alteracao contrato. */
	@Column(name = "NumArqAlteracaoContrato")
	private String numeroArquivamentoAlteracaoContrato;

	/** O atributo inscricao estadual. */
	@Column(name = "NumInscEstadual")
	private String inscricaoEstadual;

	/** O atributo data constituicao. */
	@Column(name = "DataConstituicao")
	private DateTimeDB dataConstituicao;

	/** O atributo codigo constituicao. */
	@Column(name = "CodConstituicaoPj")
	private Short codigoConstituicao;

	/** O atributo data registro orgao competente. */
	@Column(name = "DataRegOrgaoCompetente")
	private DateTimeDB dataRegistroOrgaoCompetente;

	/** O atributo data registro alteracao contrato. */
	@Column(name = "DataAlteracaoContrato")
	private DateTimeDB dataRegistroAlteracaoContrato;

	/** O atributo data registro ata. */
	@Column(name = "DataRegistroAta")
	private DateTimeDB dataRegistroAta;

	/** O atributo tipo empresa. */
	@Column(name = "CodTipoEmpresa")
	private Short tipoEmpresa;

	/** O atributo resumo contrato social. */
	@Column(name = "DescResumoContratoSocial")
	private String resumoContratoSocial;

	/** O atributo codigo esfera administrativa. */
	@Column(name = "CodTpEsferaAdminis")
	private Short codigoEsferaAdministrativa;
	
	/**
	 * @return the valorCapitalSocial
	 */
	public BigDecimal getValorCapitalSocial() {
		return valorCapitalSocial;
	}

	/**
	 * @param valorCapitalSocial
	 *            the valorCapitalSocial to set
	 */
	public void setValorCapitalSocial(BigDecimal valorCapitalSocial) {
		this.valorCapitalSocial = valorCapitalSocial;
	}

	/**
	 * @return the numeroRegistroOrgaoCompetente
	 */
	public String getNumeroRegistroOrgaoCompetente() {
		return numeroRegistroOrgaoCompetente;
	}

	/**
	 * @param numeroRegistroOrgaoCompetente
	 *            the numeroRegistroOrgaoCompetente to set
	 */
	public void setNumeroRegistroOrgaoCompetente(String numeroRegistroOrgaoCompetente) {
		this.numeroRegistroOrgaoCompetente = numeroRegistroOrgaoCompetente;
	}

	/**
	 * @return the numeroRegistroAta
	 */
	public String getNumeroRegistroAta() {
		return numeroRegistroAta;
	}

	/**
	 * @param numeroRegistroAta
	 *            the numeroRegistroAta to set
	 */
	public void setNumeroRegistroAta(String numeroRegistroAta) {
		this.numeroRegistroAta = numeroRegistroAta;
	}

	/**
	 * @return the numeroArquivamentoAlteracaoContrato
	 */
	public String getNumeroArquivamentoAlteracaoContrato() {
		return numeroArquivamentoAlteracaoContrato;
	}

	/**
	 * @param numeroArquivamentoAlteracaoContrato
	 *            the numeroArquivamentoAlteracaoContrato to set
	 */
	public void setNumeroArquivamentoAlteracaoContrato(String numeroArquivamentoAlteracaoContrato) {
		this.numeroArquivamentoAlteracaoContrato = numeroArquivamentoAlteracaoContrato;
	}

	/**
	 * @return the inscricaoEstadual
	 */
	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	/**
	 * @param inscricaoEstadual
	 *            the inscricaoEstadual to set
	 */
	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	/**
	 * @return the dataConstituicao
	 */
	public DateTimeDB getDataConstituicao() {
		return dataConstituicao;
	}

	/**
	 * @param dataConstituicao
	 *            the dataConstituicao to set
	 */
	public void setDataConstituicao(DateTimeDB dataConstituicao) {
		this.dataConstituicao = dataConstituicao;
	}

	/**
	 * @return the codigoConstituicao
	 */
	public Short getCodigoConstituicao() {
		return codigoConstituicao;
	}

	/**
	 * @param codigoConstituicao
	 *            the codigoConstituicao to set
	 */
	public void setCodigoConstituicao(Short codigoConstituicao) {
		this.codigoConstituicao = codigoConstituicao;
	}

	/**
	 * @return the dataRegistroOrgaoCompetente
	 */
	public DateTimeDB getDataRegistroOrgaoCompetente() {
		return dataRegistroOrgaoCompetente;
	}

	/**
	 * @param dataRegistroOrgaoCompetente
	 *            the dataRegistroOrgaoCompetente to set
	 */
	public void setDataRegistroOrgaoCompetente(DateTimeDB dataRegistroOrgaoCompetente) {
		this.dataRegistroOrgaoCompetente = dataRegistroOrgaoCompetente;
	}

	/**
	 * @return the dataRegistroAlteracaoContrato
	 */
	public DateTimeDB getDataRegistroAlteracaoContrato() {
		return dataRegistroAlteracaoContrato;
	}

	/**
	 * @param dataRegistroAlteracaoContrato
	 *            the dataRegistroAlteracaoContrato to set
	 */
	public void setDataRegistroAlteracaoContrato(DateTimeDB dataRegistroAlteracaoContrato) {
		this.dataRegistroAlteracaoContrato = dataRegistroAlteracaoContrato;
	}

	/**
	 * @return the dataRegistroAta
	 */
	public DateTimeDB getDataRegistroAta() {
		return dataRegistroAta;
	}

	/**
	 * @param dataRegistroAta
	 *            the dataRegistroAta to set
	 */
	public void setDataRegistroAta(DateTimeDB dataRegistroAta) {
		this.dataRegistroAta = dataRegistroAta;
	}

	/**
	 * @return the tipoEmpresa
	 */
	public Short getTipoEmpresa() {
		return tipoEmpresa;
	}

	/**
	 * @param tipoEmpresa
	 *            the tipoEmpresa to set
	 */
	public void setTipoEmpresa(Short tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}

	/**
	 * @return the resumoContratoSocial
	 */
	public String getResumoContratoSocial() {
		return resumoContratoSocial;
	}

	/**
	 * @param resumoContratoSocial
	 *            the resumoContratoSocial to set
	 */
	public void setResumoContratoSocial(String resumoContratoSocial) {
		this.resumoContratoSocial = resumoContratoSocial;
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
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdDB2(Serializable idDB2) {
		if(idDB2 instanceof Integer){
			setId((Integer)idDB2);
		}
	}

}
