/*
 * SICOOB
 * 
 * CategoriaProdutor.java(br.com.sicoob.capes.negocio.entidades.legado.CategoriaProdutor)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidade que mapeia a tabela: CategoriaProdutor.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name = "CategoriaProdutor")
public class CategoriaProdutor extends CAPESEntidadeLegado<Short> {

	/** Serial UID. */
	private static final long serialVersionUID = 6668277423823092390L;

	/** O atributo id categoria. */
	@Id
	@Column(name = "IDCategoriaProdutor")
	private Short idCategoria;

	/**
	 * Recupera id categoria.
	 * 
	 * @return id categoria
	 */
	public Short getIdCategoria() {
		return idCategoria;
	}

	/**
	 * Preenche id categoria.
	 * 
	 * @param idCategoria
	 *            o novo id categoria
	 */
	public void setIdCategoria(Short idCategoria) {
		this.idCategoria = idCategoria;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Short getIdSQL() {
		return getIdCategoria();
	}
}
