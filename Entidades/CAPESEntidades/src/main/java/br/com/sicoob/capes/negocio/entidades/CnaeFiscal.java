/*
 * SICOOB
 * 
 * CnaeFiscal.java(br.com.sicoob.capes.negocio.entidades.CnaeFiscal)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import flexjson.JSON;


/**
 * Entidade que representa o CNAE Fiscal.
 * @author Erico.Junior
 */
@Entity
@Table(name="CNAEFISCAL", schema = "CLI")
public class CnaeFiscal extends CAPESEntidade<String> {

	/** Serial UID.*/
	private static final long serialVersionUID = 8174792029713545966L;

	/** O atributo codigo. */
	@Id
	@Column(name = "CODCNAE")
	private String codigo;
	
	/** O atributo descricao. */
	@Column(name = "DESCCNAE")
	private String descricao;
	
	/** O atributo tipo classificacao. */
	@Column(name = "CODTIPOCLASSIFICACAO")
	private Character tipoClassificacao;
	
	/** O atributo cnae pai. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODCNAEPAI", insertable = true, updatable = true, nullable = true)
	@NotFound(action = NotFoundAction.IGNORE)
	private CnaeFiscal cnaePai;

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the tipoClassificacao
	 */
	public Character getTipoClassificacao() {
		return tipoClassificacao;
	}

	/**
	 * @param tipoClassificacao the tipoClassificacao to set
	 */
	public void setTipoClassificacao(Character tipoClassificacao) {
		this.tipoClassificacao = tipoClassificacao;
	}

	/**
	 * @return the cnaePai
	 */
	@JSON(include=false)
	public CnaeFiscal getCnaePai() {
		return cnaePai;
	}

	/**
	 * @param cnaePai the cnaePai to set
	 */
	public void setCnaePai(CnaeFiscal cnaePai) {
		this.cnaePai = cnaePai;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public String getId() {
		return getCodigo();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(String id) {
		setCodigo(id);
	} 
}
