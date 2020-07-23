/*
 * SICOOB
 * 
 * CooperativaPessoa.java(br.com.sicoob.capes.negocio.entidades.legado.CooperativaPessoa)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.legado.pk.CooperativaPessoaPK;

/**
 * @author Erico.Junior
 *
 */
@Entity
@Table(name = "COOPERATIVAPESSOA")
public class CooperativaPessoa extends CAPESEntidadeLegado<CooperativaPessoaPK> {

	/** Serial UID. */
	private static final long serialVersionUID = 8931458765761458576L;

	/** O atributo id. */
	@EmbeddedId
	private CooperativaPessoaPK id;
	
	/** O atributo data origem. */
	private DateTimeDB dataOrigem;

	/**
	 * @return the id
	 */
	public CooperativaPessoaPK getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(CooperativaPessoaPK id) {
		this.id = id;
	}

	/**
	 * @return the dataOrigem
	 */
	public DateTimeDB getDataOrigem() {
		return dataOrigem;
	}

	/**
	 * @param dataOrigem the dataOrigem to set
	 */
	public void setDataOrigem(DateTimeDB dataOrigem) {
		this.dataOrigem = dataOrigem;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public CooperativaPessoaPK getIdSQL() {
		return getId();
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (obj == null){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		CooperativaPessoa other = (CooperativaPessoa) obj;
		if (id == null) {
			if (other.id != null){
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	
}
