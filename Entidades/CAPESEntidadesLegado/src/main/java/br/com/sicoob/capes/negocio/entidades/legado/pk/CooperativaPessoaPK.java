/*
 * SICOOB
 * 
 * CooperativaPessoaPK.java(br.com.sicoob.capes.negocio.entidades.legado.pk.CooperativaPessoaPK)
 */
package br.com.sicoob.capes.negocio.entidades.legado.pk;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;

/**
 */
@Embeddable
public class CooperativaPessoaPK implements Serializable {

	/** Serial UID. */
	private static final long serialVersionUID = 8046598324218770517L;

	/** O atributo num cooperativa origem. */
	private Integer numCooperativaOrigem;
	
	/** O atributo num pac origem. */
	private Integer numPacOrigem;
	
	/** O atributo pessoa. */
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="numPessoa")
	private Pessoa pessoa; 
	
	/**
	 * @return the numCooperativaOrigem
	 */
	public Integer getNumCooperativaOrigem() {
		return numCooperativaOrigem;
	}
	/**
	 * @param numCooperativaOrigem the numCooperativaOrigem to set
	 */
	public void setNumCooperativaOrigem(Integer numCooperativaOrigem) {
		this.numCooperativaOrigem = numCooperativaOrigem;
	}
	/**
	 * @return the numPacOrigem
	 */
	public Integer getNumPacOrigem() {
		return numPacOrigem;
	}
	/**
	 * @param numPacOrigem the numPacOrigem to set
	 */
	public void setNumPacOrigem(Integer numPacOrigem) {
		this.numPacOrigem = numPacOrigem;
	}
	/**
	 * @return the pessoa
	 */
	public Pessoa getPessoa() {
		return pessoa;
	}
	/**
	 * @param pessoa the pessoa to set
	 */
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
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
		result = prime
				* result
				+ ((numCooperativaOrigem == null) ? 0 : numCooperativaOrigem
						.hashCode());
		result = prime * result
				+ ((numPacOrigem == null) ? 0 : numPacOrigem.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
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
		CooperativaPessoaPK other = (CooperativaPessoaPK) obj;
		if (numCooperativaOrigem == null) {
			if (other.numCooperativaOrigem != null) {
				return false;
			}
		} else if (!numCooperativaOrigem.equals(other.numCooperativaOrigem)) {
			return false;
		}
		
		if (numPacOrigem == null) {
			if (other.numPacOrigem != null) {
				return false;
			}
		} else if (!numPacOrigem.equals(other.numPacOrigem)) {
			return false;
		}
		
		if (pessoa == null) {
			if (other.pessoa != null) {
				return false;
			}
		} else if (!pessoa.equals(other.pessoa)) {
			return false;
		}
		return true;
	}

}
