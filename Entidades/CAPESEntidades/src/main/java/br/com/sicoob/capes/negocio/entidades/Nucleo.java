/*
 * SICOOB
 * 
 * Nucleo.java(br.com.sicoob.capes.negocio.entidades.Nucleo)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe responsavel por armazenar os dados de funcionario.
 * @author juan.damasceno
 *
 */
@Entity
@Table(name="NUCLEO",schema="CLI")
public class Nucleo extends CAPESEntidade<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** O atributo id nucleo. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idNucleo;
	
	/** O atributo descricao. */
	@Column(name="DESCNUCLEO")
	private String descricao;
	
	/** O atributo id instituicao. */
	private Integer idInstituicao;
	
	/** O atributo num nucleo. */
	private Integer numNucleo;
	
	/** O atributo ativo. */
	@Column(name="BOLATIVO")
	private Boolean ativo;
	
	/**
	 * @return the numNucleo
	 */
	public Integer getNumNucleo() {
		return numNucleo;
	}

	/**
	 * @param numNucleo the numNucleo to set
	 */
	public void setNumNucleo(Integer numNucleo) {
		this.numNucleo = numNucleo;
	}

	/**
	 * @return the idNucleo
	 */
	public Integer getIdNucleo() {
		return idNucleo;
	}

	/**
	 * @param idNucleo the idNucleo to set
	 */
	public void setIdNucleo(Integer idNucleo) {
		this.idNucleo = idNucleo;
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
	 * @return the idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * @param idInstituicao the idInstituicao to set
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getId() {
		return this.idNucleo;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Integer id) {
		this.idNucleo = id;
	}

	/**
	 * @return the ativo
	 */
	public Boolean getAtivo() {
		return ativo;
	}

	/**
	 * @param ativo the ativo to set
	 */
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}