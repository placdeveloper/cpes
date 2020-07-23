/*
 * SICOOB
 * 
 * Nucleo.java(br.com.sicoob.capes.negocio.entidades.legado.Nucleo)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe responsavel por armazenar os dados de nucleo.
 *
 */
@Entity
@Table(name="NUCLEO")
public class Nucleo extends EntidadeReplicavel<Integer> {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** O atributo num nucleo. */
	@Id
	@Column(name="NUMNUCLEO", nullable = false)
	private Integer numNucleo;
	
	/** O atributo descricao. */
	@Column(name="DESCNUCLEO")
	private String descricao;

	/**
	 * Recupera num nucleo.
	 * 
	 * @return num nucleo
	 */
	public Integer getNumNucleo() {
		return numNucleo;
	}

	/**
	 * Preenche num nucleo.
	 * 
	 * @param numNucleo
	 *            o novo num nucleo
	 */
	public void setNumNucleo(Integer numNucleo) {
		this.numNucleo = numNucleo;
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
			setNumNucleo((Integer) idSQL);
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
		return getNumNucleo();
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdDB2(Serializable idDB2) {
		// TODO Auto-generated method stub
		
	}
}