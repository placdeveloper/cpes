/*
 * SICOOB
 * 
 * CooperativaPK.java(br.com.sicoob.capes.negocio.entidades.legado.pk.CooperativaPK)
 */
package br.com.sicoob.capes.negocio.entidades.legado.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;

import br.com.sicoob.capes.comum.util.ReflexaoUtils;

/**
 * The Class CooperativaPK.
 */
@Embeddable
public class CooperativaPK implements Serializable {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -2210716520377056959L;
	
	/** O atributo num cooperativa. */
	private Integer numCooperativa;
	
	/** O atributo num pac. */
	private Integer numPac;

	/**
	 * Recupera num cooperativa.
	 * 
	 * @return num cooperativa
	 */
	public Integer getNumCooperativa() {
		return numCooperativa;
	}

	/**
	 * Preenche num cooperativa.
	 * 
	 * @param numCooperativa
	 *            o novo num cooperativa
	 */
	public void setNumCooperativa(Integer numCooperativa) {
		this.numCooperativa = numCooperativa;
	}

	/**
	 * Recupera num pac.
	 * 
	 * @return num pac
	 */
	public Integer getNumPac() {
		return numPac;
	}

	/**
	 * Preenche num pac.
	 * 
	 * @param numPac
	 *            o novo num pac
	 */
	public void setNumPac(Integer numPac) {
		this.numPac = numPac;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "numCooperativa", "numPac");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "numCooperativa", "numPac");
	}
	
}