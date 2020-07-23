/*
 * SICOOB
 * 
 * CategoriaAnotacao.java(br.com.sicoob.capes.negocio.entidades.CategoriaAnotacao)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Entidade que representa a tabela responsável por armazenar as categorias das 
 * anotações.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name="CATEGORIAANOTACAO", schema = "CLI")
public class CategoriaAnotacao extends CAPESEntidade<Short> {

	/** Serial UID.*/
	private static final long serialVersionUID = 6108291411947223242L;

	/** O atributo id categoria anotacao. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Short idCategoriaAnotacao;
	
	/** O atributo desc categoria anotacao. */
	private String descCategoriaAnotacao;

	/**
	 * @return the idCategoriaAnotacao
	 */
	public Short getIdCategoriaAnotacao() {
		return idCategoriaAnotacao;
	}

	/**
	 * @param idCategoriaAnotacao the idCategoriaAnotacao to set
	 */
	public void setIdCategoriaAnotacao(Short idCategoriaAnotacao) {
		this.idCategoriaAnotacao = idCategoriaAnotacao;
	}

	/**
	 * @return the descCategoriaAnotacao
	 */
	public String getDescCategoriaAnotacao() {
		return descCategoriaAnotacao;
	}

	/**
	 * @param descCategoriaAnotacao the descCategoriaAnotacao to set
	 */
	public void setDescCategoriaAnotacao(String descCategoriaAnotacao) {
		this.descCategoriaAnotacao = descCategoriaAnotacao;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	@Transient
	public Short getId() {
		return getIdCategoriaAnotacao();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Short id) {
		setIdCategoriaAnotacao(id);
	}
}
