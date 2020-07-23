/*
 * SICOOB
 * 
 * TipoBaixa.java(br.com.sicoob.capes.negocio.entidades.TipoBaixa)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe que representa o tipo de baixa.
 * @author Erico.Junior
 */
@Entity
@Table(name="TIPOBAIXA", schema = "CLI")
public class TipoBaixa extends CAPESEntidade<Short> {

	/** Serial UID.*/
	private static final long serialVersionUID = 6366784059271814612L;

	/** O atributo id tipo baixa. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Short idTipoBaixa;
	
	/** O atributo desc tipo baixa. */
	private String descTipoBaixa;

	/**
	 * Cria uma nova instância de tipo baixa.
	 */
	public TipoBaixa() {

	}

	/**
	 * Cria uma nova instância de tipo baixa.
	 * 
	 * @param idTipoBaixa
	 *            the id tipo baixa
	 */
	public TipoBaixa(Short idTipoBaixa) {

		this.idTipoBaixa = idTipoBaixa;
	}

	/**
	 * @return the idTipoBaixa
	 */
	public Short getIdTipoBaixa() {
		return idTipoBaixa;
	}

	/**
	 * @param idTipoBaixa the idTipoBaixa to set
	 */
	public void setIdTipoBaixa(Short idTipoBaixa) {
		this.idTipoBaixa = idTipoBaixa;
	}

	/**
	 * @return the descTipoBaixa
	 */
	public String getDescTipoBaixa() {
		return descTipoBaixa;
	}

	/**
	 * @param descTipoBaixa the descTipoBaixa to set
	 */
	public void setDescTipoBaixa(String descTipoBaixa) {
		this.descTipoBaixa = descTipoBaixa;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Short getId() {
		return getIdTipoBaixa();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Short id) {
		setIdTipoBaixa(id);
	}
	
}
