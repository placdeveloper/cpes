/*
 * SICOOB
 * 
 * Empreendimento.java(br.com.sicoob.capes.negocio.entidades.legado.Empreendimento)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Erico.Junior
 * 
 */
@Entity
@Table(name = "Empreendimento")
public class Empreendimento extends EntidadeReplicavel<Integer> {

	/** Serial UID.	 **/
	private static final long serialVersionUID = -7543943217759104324L;
	
	/** O atributo id empreendimento. */
	@Id
	@Column(name = "IDEmpreendimento")
	private Integer idEmpreendimento;
	
	/** O atributo descricao. */
	@Column(name = "DescEmpreendimento")
	private String descricao;

	/** O atributo unidade area. */
	@Column(name = "IDUnidadeMedidaAreaQtde")
	private Short unidadeArea;

	/** O atributo unidade previsao. */
	@Column(name = "IDUnidadeMedidaPrevProd")
	private Short unidadePrevisao;
	
	/** O atributo finalidade. */
	@Column(name = "CodFinalidadeEmpreend")
	private Short finalidade;
	
	/** O atributo exige imovel. */
	@Column(name = "BolExigeImovel")
	private Boolean exigeImovel = Boolean.FALSE;
	
	/** O atributo exige area. */
	@Column(name = "BolExigeArea")
	private Boolean exigeArea = Boolean.FALSE;

	/**
	 * Recupera id empreendimento.
	 * 
	 * @return id empreendimento
	 */
	public Integer getIdEmpreendimento() {
		return idEmpreendimento;
	}

	/**
	 * Preenche id empreendimento.
	 * 
	 * @param idEmpreendimento
	 *            o novo id empreendimento
	 */
	public void setIdEmpreendimento(Integer idEmpreendimento) {
		this.idEmpreendimento = idEmpreendimento;
	}

	/**
	 * Recupera descricao.
	 * 
	 * @return descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Preenche descricao.
	 * 
	 * @param descricao
	 *            o novo descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Recupera unidade area.
	 * 
	 * @return unidade area
	 */
	public Short getUnidadeArea() {
		return unidadeArea;
	}

	/**
	 * Preenche unidade area.
	 * 
	 * @param unidadeArea
	 *            o novo unidade area
	 */
	public void setUnidadeArea(Short unidadeArea) {
		this.unidadeArea = unidadeArea;
	}

	/**
	 * Recupera unidade previsao.
	 * 
	 * @return unidade previsao
	 */
	public Short getUnidadePrevisao() {
		return unidadePrevisao;
	}

	/**
	 * Preenche unidade previsao.
	 * 
	 * @param unidadePrevisao
	 *            o novo unidade previsao
	 */
	public void setUnidadePrevisao(Short unidadePrevisao) {
		this.unidadePrevisao = unidadePrevisao;
	}

	/**
	 * Recupera finalidade.
	 * 
	 * @return finalidade
	 */
	public Short getFinalidade() {
		return finalidade;
	}

	/**
	 * Preenche finalidade.
	 * 
	 * @param finalidade
	 *            o novo finalidade
	 */
	public void setFinalidade(Short finalidade) {
		this.finalidade = finalidade;
	}

	/**
	 * Recupera exige imovel.
	 * 
	 * @return exige imovel
	 */
	public Boolean getExigeImovel() {
		return exigeImovel;
	}

	/**
	 * Preenche exige imovel.
	 * 
	 * @param exigeImovel
	 *            o novo exige imovel
	 */
	public void setExigeImovel(Boolean exigeImovel) {
		this.exigeImovel = exigeImovel;
	}

	/**
	 * Recupera exige area.
	 * 
	 * @return exige area
	 */
	public Boolean getExigeArea() {
		return exigeArea;
	}

	/**
	 * Preenche exige area.
	 * 
	 * @param exigeArea
	 *            o novo exige area
	 */
	public void setExigeArea(Boolean exigeArea) {
		this.exigeArea = exigeArea;
	}	
	
	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public Serializable getIdDB2() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdSQL(Serializable idSQL) {
		if(idSQL instanceof Integer){
			setIdEmpreendimento((Integer)idSQL);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getIdSQL() {
		return getIdEmpreendimento();
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdDB2(Serializable idDB2) {
		if(idDB2 instanceof Integer){
			setIdEmpreendimento((Integer) idDB2);
		}
	}
}
