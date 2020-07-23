/*
 * SICOOB
 * 
 * BemPossePK.java(br.com.sicoob.capes.negocio.entidades.legado.pk.BemPossePK)
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
 * The Class BemPossePK.
 */
@Embeddable
public class BemPossePK extends BancoobChavePrimaria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** O atributo bem. */
	@ManyToOne
	@JoinColumn(name="UIDBEMPESSOA")
	private Bem bem;

	/** O atributo seq bem posse. */
	@Column(name="CODSEQPOSSE")
	private Short seqBemPosse; 

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
	 * @return the seqBemPosse
	 */
	public Short getSeqBemPosse() {
		return seqBemPosse;
	}

	/**
	 * @param seqBemPosse the seqBemPosse to set
	 */
	public void setSeqBemPosse(Short seqBemPosse) {
		this.seqBemPosse = seqBemPosse;
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
				+ ((seqBemPosse == null) ? 0 : seqBemPosse.hashCode());
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
		if (this == obj){
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BemPossePK other = (BemPossePK) obj;
		if (bem == null) {
			if (other.bem != null) {
				return false;
			}
		} else if (!bem.equals(other.bem)){
			return false;
		}
		if (seqBemPosse == null) {
			if (other.seqBemPosse != null) {
				return false;
			}
		} else if (!seqBemPosse.equals(other.seqBemPosse)) {
			return false;
		}
		return true;
	}
}