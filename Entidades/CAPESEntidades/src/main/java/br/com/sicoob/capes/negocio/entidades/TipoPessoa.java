/*
 * SICOOB
 * 
 * TipoPessoa.java(br.com.sicoob.capes.negocio.entidades.TipoPessoa)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.bancoob.persistencia.annotations.OrdenacaoPadrao;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;

/**
 * Classe que representa o tipo de pessoa.
 * @author Erico.Junior
 */
@Entity
@Table(name="TIPOPESSOA", schema = "CLI")
@OrdenacaoPadrao(colunas = "DESCTIPOPESSOA", descendente = false)
public class TipoPessoa extends CAPESEntidade<Short> {

	/** Serial UID. */
	private static final long serialVersionUID = -8824213358336584158L;

	/** O atributo cod tipo pessoa. */
	@Id
	private Short codTipoPessoa;
	
	/** O atributo desc tipo pessoa. */
	private String descTipoPessoa;

	/**
	 * @return the codTipoPessoa
	 */
	public Short getCodTipoPessoa() {
		return codTipoPessoa;
	}

	/**
	 * @param codTipoPessoa the codTipoPessoa to set
	 */
	public void setCodTipoPessoa(Short codTipoPessoa) {
		this.codTipoPessoa = codTipoPessoa;
	}

	/**
	 * @return the descTipoPessoa
	 */
	public String getDescTipoPessoa() {
		return descTipoPessoa;
	}

	/**
	 * @param descTipoPessoa the descTipoPessoa to set
	 */
	public void setDescTipoPessoa(String descTipoPessoa) {
		this.descTipoPessoa = descTipoPessoa;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	@Transient
	public Short getId() {
		return getCodTipoPessoa();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Short id) {
		setCodTipoPessoa(id);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "codTipoPessoa");
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "codTipoPessoa");
	}
	
	public TipoPessoa(){
		
	}
	
	public TipoPessoa(Short cod) {
		this.codTipoPessoa = cod;
	}
}

