/*
 * SICOOB
 * 
 * TipoEmpresa.java(br.com.sicoob.capes.negocio.entidades.TipoEmpresa)
 */
package br.com.sicoob.capes.negocio.entidades;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bancoob.persistencia.annotations.OrdenacaoPadrao;

/**
 * Entidade que representa o tipo de empresa.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name = "TIPOEMPRESA", schema = "CLI")
@OrdenacaoPadrao(colunas = "DESTIPOEMPRESA", descendente = false)
public class TipoEmpresa extends CAPESEntidade<Short> {

	/** Serial UID. */
	private static final long serialVersionUID = -7781481588474606430L;

	
	/** O atributo ativo. */
	@Column(name="BOLATIVO")
	private Boolean ativo;
	
	/** O atributo codigo. */
	@Id
	@Column(name = "CODTIPOEMPRESA")
	private Short codigo;

	/** O atributo descricao. */
	@Column(name = "DESTIPOEMPRESA")
	private String descricao;

	/** O atributo possuiAtivoSuperior. */
	@Column(name="BOLHABILITAPOSSUIATIVO")
	private Boolean possuiAtivoSuperior;
	
	/** O atributo isSimplesNacional. */
	@Column(name="BOLHABILITASIMPLESNACIONAL")
	private Boolean isSimplesNacional;
	
	/** O atributo valorLimiteInferior. */
	@Column(name = "VALORLIMITEINFERIORFATURAMENTO")
	private BigDecimal valorLimiteInferior;
	
	/** O atributo valorLimiteSuperior. */
	@Column(name = "VALORLIMITESUPERIORFATURAMENTO")
	private BigDecimal valorLimiteSuperior;

	/**
	 * @return the codigo
	 */
	public Short getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(Short codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Short getId() {
		return getCodigo();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Short id) {
		setCodigo(id);
	}

	/**
	 * Recupera o valor de valorLimiteInferior.
	 *
	 * @return o valor de valorLimiteInferior
	 */
	public BigDecimal getValorLimiteInferior() {
		return valorLimiteInferior;
	}

	/**
	 * Define o valor de valorLimiteInferior.
	 *
	 * @param valorLimiteInferior o novo valor de valorLimiteInferior
	 */
	public void setValorLimiteInferior(BigDecimal valorLimiteInferior) {
		this.valorLimiteInferior = valorLimiteInferior;
	}

	/**
	 * Recupera o valor de valorLimiteSuperior.
	 *
	 * @return o valor de valorLimiteSuperior
	 */
	public BigDecimal getValorLimiteSuperior() {
		return valorLimiteSuperior;
	}

	/**
	 * Define o valor de valorLimiteSuperior.
	 *
	 * @param valorLimiteSuperior o novo valor de valorLimiteSuperior
	 */
	public void setValorLimiteSuperior(BigDecimal valorLimiteSuperior) {
		this.valorLimiteSuperior = valorLimiteSuperior;
	}

	/**
	 * Recupera o valor de ativo.
	 * @return ativo
	 */
	public Boolean getAtivo() {
		return ativo;
	}

	/**
	 * Define o valor de ativo.
	 * @param ativo
	 */
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	/**
	 * Recupera o valor de isSimplesNacional
	 * @return isSimplesNacional
	 */
	public Boolean getIsSimplesNacional() {
		return isSimplesNacional;
	}

	/**
	 * Define o valor de isSimplesNacional.
	 * @param isSimplesNacional
	 */
	public void setIsSimplesNacional(Boolean isSimplesNacional) {
		this.isSimplesNacional = isSimplesNacional;
	}

	/**
	 * Recupera o valor de possuiAtivoSuperior
	 * @return possuiAtivoSuperior
	 */
	public Boolean getPossuiAtivoSuperior() {
		return possuiAtivoSuperior;
	}
	
	/**
	 * Define o valor de possuiAtivoSuperior.
	 * @param possuiAtivoSuperior
	 */
	public void setPossuiAtivoSuperior(Boolean possuiAtivoSuperior) {
		this.possuiAtivoSuperior = possuiAtivoSuperior;
	}
}