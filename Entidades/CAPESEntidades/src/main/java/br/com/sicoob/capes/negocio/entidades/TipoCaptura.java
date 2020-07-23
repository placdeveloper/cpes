/*
 * SICOOB
 * 
 * TipoCaptura.java(br.com.sicoob.capes.negocio.entidades.TipoCaptura)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Classe que representa o tipo de captura.
 * @author Erico.Junior
 */
@Entity
@Table(name="TIPOCAPTURA", schema = "CLI")
public class TipoCaptura extends CAPESEntidade<Short> {

	/** Serial UID. */
	private static final long serialVersionUID = 6726608800569071184L;

	/** O atributo id tipo captura. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Short idTipoCaptura;
	
	/** O atributo desc tipo captura. */
	private String descTipoCaptura;

	/**
	 * @return the idTipoCaptura
	 */
	public Short getIdTipoCaptura() {
		return idTipoCaptura;
	}

	/**
	 * @param idTipoCaptura the idTipoCaptura to set
	 */
	public void setIdTipoCaptura(Short idTipoCaptura) {
		this.idTipoCaptura = idTipoCaptura;
	}

	/**
	 * @return the descTipoCaptura
	 */
	public String getDescTipoCaptura() {
		return descTipoCaptura;
	}

	/**
	 * @param descTipoCaptura the descTipoCaptura to set
	 */
	public void setDescTipoCaptura(String descTipoCaptura) {
		this.descTipoCaptura = descTipoCaptura;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	@Transient
	public Short getId() {
		return getIdTipoCaptura();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Short id) {
		setIdTipoCaptura(id);
	}

}
