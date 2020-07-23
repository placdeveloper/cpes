/*
 * SICOOB
 * 
 * BemRegistroPK.java(br.com.sicoob.capes.negocio.entidades.legado.pk.BemRegistroPK)
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
 * The Class BemRegistroPK.
 */
@Embeddable
public class BemRegistroPK extends BancoobChavePrimaria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** O atributo bem. */
	@ManyToOne
	@JoinColumn(name="UIDBEMPESSOA")
	private Bem bem;

	/** O atributo seq bem registro. */
	@Column(name="CODSEQREGISTRO")
	private Short seqBemRegistro; 

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
	 * @return the seqBemRegistro
	 */
	public Short getSeqBemRegistro() {
		return seqBemRegistro;
	}

	/**
	 * @param seqBemRegistro the seqBemRegistro to set
	 */
	public void setSeqBemRegistro(Short seqBemRegistro) {
		this.seqBemRegistro = seqBemRegistro;
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
				+ ((seqBemRegistro == null) ? 0 : seqBemRegistro.hashCode());
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
		
		BemRegistroPK other = (BemRegistroPK) obj;
		if (bem == null) {
			if (other.bem != null) {
				return false;
			}
		} else if (!bem.equals(other.bem)) {
			return false;
		}
		if (seqBemRegistro == null) {
			if (other.seqBemRegistro != null) {
				return false;
			}
		} else if (!seqBemRegistro.equals(other.seqBemRegistro)) {
			return false;
		}
		return true;
	}
}