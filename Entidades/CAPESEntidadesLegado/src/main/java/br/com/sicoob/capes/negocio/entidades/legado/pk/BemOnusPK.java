/*
 * SICOOB
 * 
 * BemOnusPK.java(br.com.sicoob.capes.negocio.entidades.legado.pk.BemOnusPK)
 */
package br.com.sicoob.capes.negocio.entidades.legado.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.bancoob.negocio.entidades.BancoobChavePrimaria;
import br.com.sicoob.capes.negocio.entidades.legado.Bem;

/**
 * The Class BemOnusPK.
 */
@Embeddable
public class BemOnusPK extends BancoobChavePrimaria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** O atributo bem. */
	@ManyToOne
	@JoinColumn(name="UIDBEMPESSOA")
	private Bem bem;

	/** O atributo seq bem onus. */
	@Column(name="CODSEQonus")
	private Short seqBemOnus; 

	/**
	 * @return the bem
	 */
	public Bem getBem() {
		return bem;
	}

	/**
	 * @param bem the bem to set
	 */
	public void setBem(Bem bem) {
		this.bem = bem;
	}

	/**
	 * @return the seqBemOnus
	 */
	public Short getSeqBemOnus() {
		return seqBemOnus;
	}

	/**
	 * @param seqBemOnus the seqBemOnus to set
	 */
	public void setSeqBemOnus(Short seqBemOnus) {
		this.seqBemOnus = seqBemOnus;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bem == null) ? 0 : bem.hashCode());
		result = prime * result
				+ ((seqBemOnus == null) ? 0 : seqBemOnus.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BemOnusPK other = (BemOnusPK) obj;
		if (bem == null) {
			if (other.bem != null) {
				return false;
			}
		} else if (!bem.equals(other.bem)) {
			return false;
		}
		if (seqBemOnus == null) {
			if (other.seqBemOnus != null) {
				return false;
			}
		} else if (!seqBemOnus.equals(other.seqBemOnus)) {
			return false;
		}
		return true;
	}
}